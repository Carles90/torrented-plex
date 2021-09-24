package cloud.carles.torrentedplex.application.service.download;

import cloud.carles.torrentedplex.application.dto.download.DownloadItem;
import cloud.carles.torrentedplex.application.exception.DownloadListNotProvidedException;

import java.util.List;

public interface DownloadListProvider {
    List<DownloadItem> execute() throws DownloadListNotProvidedException;
}
