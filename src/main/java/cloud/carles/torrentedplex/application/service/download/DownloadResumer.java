package cloud.carles.torrentedplex.application.service.download;

import cloud.carles.torrentedplex.application.exception.DownloadStatusChangeFailedException;

public interface DownloadResumer {
    void execute(String fileId) throws DownloadStatusChangeFailedException;
}
