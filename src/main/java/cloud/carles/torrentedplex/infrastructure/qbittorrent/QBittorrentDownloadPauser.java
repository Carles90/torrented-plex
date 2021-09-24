package cloud.carles.torrentedplex.infrastructure.qbittorrent;

import cloud.carles.torrentedplex.application.exception.DownloadStatusChangeFailedException;
import cloud.carles.torrentedplex.application.service.download.DownloadPauser;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.client.QBittorrentRequest;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.exception.QBittorrentLoginException;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QBittorrentDownloadPauser implements DownloadPauser {
    @Autowired
    private QBittorrentRequest qBittorrentRequest;

    @Override
    public void execute(String fileId) throws DownloadStatusChangeFailedException {
        RequestBody formBody = new FormBody.Builder()
                .add("hashes", fileId)
                .build();

        try {
            Response response = qBittorrentRequest.post("/torrents/pause", formBody);

            if (response.code() != 200) {
                throw new DownloadStatusChangeFailedException();
            }
        } catch (QBittorrentLoginException | IOException e) {
            throw new DownloadStatusChangeFailedException();
        }
    }
}
