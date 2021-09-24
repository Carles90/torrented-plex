package cloud.carles.torrentedplex.application.service.search;

import cloud.carles.torrentedplex.application.dto.search.SearchRequest;
import cloud.carles.torrentedplex.application.dto.search.SearchResult;
import cloud.carles.torrentedplex.application.exception.SearchNotPerformedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchProcessor {
    List<SearchResult> execute(SearchRequest request) throws SearchNotPerformedException;
}
