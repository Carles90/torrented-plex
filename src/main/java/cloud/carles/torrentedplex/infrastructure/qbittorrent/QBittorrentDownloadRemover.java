package cloud.carles.torrentedplex.infrastructure.qbittorrent;

import cloud.carles.torrentedplex.application.exception.DownloadNotRemovedException;
import cloud.carles.torrentedplex.application.service.download.DownloadRemover;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.client.QBittorrentRequest;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.exception.QBittorrentLoginException;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QBittorrentDownloadRemover implements DownloadRemover {
    @Autowired
    private QBittorrentRequest qBittorrentRequest;

    @Override
    public void execute(String fileId) throws DownloadNotRemovedException {
        RequestBody formBody = new FormBody.Builder()
                .add("hashes", fileId)
                .add("deleteFiles", "true")
                .build();

        try {
            Response response = qBittorrentRequest.post("/torrents/delete", formBody);

            if (response.code() != 200) {
                throw new DownloadNotRemovedException();
            }
        } catch (QBittorrentLoginException | IOException e) {
            throw new DownloadNotRemovedException();
        }
    }
}
