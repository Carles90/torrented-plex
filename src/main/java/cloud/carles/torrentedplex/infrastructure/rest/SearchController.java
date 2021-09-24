package cloud.carles.torrentedplex.infrastructure.rest;

import cloud.carles.torrentedplex.application.dto.search.SearchRequest;
import cloud.carles.torrentedplex.application.dto.search.SearchResult;
import cloud.carles.torrentedplex.application.exception.SearchNotPerformedException;
import cloud.carles.torrentedplex.application.service.search.SearchProcessor;
import cloud.carles.torrentedplex.infrastructure.gson.GsonContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    public SearchProcessor searchProcessor;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String search(@RequestBody SearchRequest search) throws SearchNotPerformedException {
        List<SearchResult> results = searchProcessor.execute(search);
        return GsonContainer.get().toJson(results);
    }
}
