package cloud.carles.torrentedplex.infrastructure.qbittorrent;

import cloud.carles.torrentedplex.application.dto.download.DownloadRequest;
import cloud.carles.torrentedplex.application.exception.DownloadNotAddedException;
import cloud.carles.torrentedplex.application.service.download.DownloadProcessor;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.client.QBittorrentRequest;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.exception.QBittorrentLoginException;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QBittorrentDownloadProcessor implements DownloadProcessor {
    @Autowired
    private QBittorrentRequest qBittorrentRequest;

    @Override
    public void execute(DownloadRequest request) throws DownloadNotAddedException {
        RequestBody formBody = new FormBody.Builder()
                .add("urls", request.getLink())
                .add("savepath", "/downloads/")
                .build();

        try {
            Response response = qBittorrentRequest.post("/torrents/add", formBody);

            if (response.code() != 200) {
                throw new DownloadNotAddedException();
            }
        } catch (QBittorrentLoginException | IOException e) {
            throw new DownloadNotAddedException();
        }
    }
}
