package cloud.carles.torrentedplex.application.service.download;

import cloud.carles.torrentedplex.application.dto.download.DownloadRequest;
import cloud.carles.torrentedplex.application.exception.DownloadNotAddedException;

public interface DownloadProcessor {
    void execute(DownloadRequest request) throws DownloadNotAddedException;
}
