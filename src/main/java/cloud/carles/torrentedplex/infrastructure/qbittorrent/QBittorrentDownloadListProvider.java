package cloud.carles.torrentedplex.infrastructure.qbittorrent;

import cloud.carles.torrentedplex.application.dto.download.DownloadItem;
import cloud.carles.torrentedplex.application.exception.DownloadListNotProvidedException;
import cloud.carles.torrentedplex.application.service.download.DownloadListProvider;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.client.QBittorrentRequest;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.dto.MaindataResponse;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.exception.QBittorrentLoginException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class QBittorrentDownloadListProvider implements DownloadListProvider {
    @Autowired
    private QBittorrentRequest qBittorrentRequest;

    @Override
    public List<DownloadItem> execute() throws DownloadListNotProvidedException {
        try {
            Response response = qBittorrentRequest.get("/sync/maindata");

            if (response.code() != 200) {
                throw new DownloadListNotProvidedException();
            }

            return (new ObjectMapper())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(response.body().byteStream(), MaindataResponse.class)
                    .toDownloadItemList();
        } catch (QBittorrentLoginException | IOException e) {
            throw new DownloadListNotProvidedException();
        }
    }
}
