package cloud.carles.torrentedplex.infrastructure.jacket.dto;

import cloud.carles.torrentedplex.application.dto.search.SearchResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class JacketSearchResponse {
    @JsonProperty("Results")
    private List<JacketSearchResult> results;

    public JacketSearchResponse() {

    }

    public List<SearchResult> toSearchResultList() {
        return results.stream()
                .map(result -> new SearchResult(
                        result.getTitle(),
                        result.getLink(),
                        result.getSize(),
                        result.getSeeders(),
                        result.getPeers(),
                        result.getTracker()
                ))
                .collect(Collectors.toList());
    }
}
