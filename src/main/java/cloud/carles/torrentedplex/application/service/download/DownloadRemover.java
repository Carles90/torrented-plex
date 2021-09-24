package cloud.carles.torrentedplex.application.service.download;

import cloud.carles.torrentedplex.application.exception.DownloadNotRemovedException;

public interface DownloadRemover {
    void execute(String fileId) throws DownloadNotRemovedException;
}
