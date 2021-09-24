package cloud.carles.torrentedplex.infrastructure.rest;

import cloud.carles.torrentedplex.application.dto.SuccessResponse;
import cloud.carles.torrentedplex.application.dto.download.DownloadRequest;
import cloud.carles.torrentedplex.application.exception.DownloadNotAddedException;
import cloud.carles.torrentedplex.application.service.download.DownloadProcessor;
import cloud.carles.torrentedplex.infrastructure.gson.GsonContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("download")
public class DownloadController {
    @Autowired
    DownloadProcessor downloadProcessor;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String download(@RequestBody DownloadRequest download) throws DownloadNotAddedException {
        downloadProcessor.execute(download);
        return GsonContainer.get().toJson(new SuccessResponse());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDownloads() {
        return GsonContainer.get().toJson(new SuccessResponse());
    }

    @PostMapping(path = "/{fileId}/pause", produces = MediaType.APPLICATION_JSON_VALUE)
    public String pause(@PathVariable String fileId) {
        return GsonContainer.get().toJson(new SuccessResponse());
    }

    @PostMapping(path = "/{fileId}/resume", produces = MediaType.APPLICATION_JSON_VALUE)
    public String resume(@PathVariable String fileId) {
        return GsonContainer.get().toJson(new SuccessResponse());
    }

    @DeleteMapping(path = "/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable String fileId) {
        return GsonContainer.get().toJson(new SuccessResponse());
    }
}
