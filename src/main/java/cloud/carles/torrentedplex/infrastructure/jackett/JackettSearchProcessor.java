package cloud.carles.torrentedplex.infrastructure.jackett;

import cloud.carles.torrentedplex.application.ApplicationProperties;
import cloud.carles.torrentedplex.application.dto.search.SearchRequest;
import cloud.carles.torrentedplex.application.dto.search.SearchResult;
import cloud.carles.torrentedplex.application.exception.SearchNotPerformedException;
import cloud.carles.torrentedplex.application.service.search.SearchProcessor;
import cloud.carles.torrentedplex.infrastructure.jackett.dto.JackettSearchResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JackettSearchProcessor implements SearchProcessor {
    @Autowired
    ApplicationProperties properties;

    @Autowired
    JackettRequest jackettRequest;

    @Override
    public List<SearchResult> execute(SearchRequest request) throws SearchNotPerformedException {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("apikey", properties.getJackettApiKey());
            parameters.put("Query", request.getQuery());

            InputStream response = jackettRequest.get("/indexers/all/results", parameters);

            return (new ObjectMapper())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(response, JackettSearchResponse.class)
                    .toSearchResultList();
        } catch (IOException e) {
            throw new SearchNotPerformedException();
        }
    }
}
