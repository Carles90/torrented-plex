package cloud.carles.torrentedplex.application.service.download;

import cloud.carles.torrentedplex.application.exception.DownloadStatusChangeFailedException;

public interface DownloadPauser {
    void execute(String fileId) throws DownloadStatusChangeFailedException;
}
