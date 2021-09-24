package cloud.carles.torrentedplex.infrastructure.qbittorrent.client;

import cloud.carles.torrentedplex.application.ApplicationProperties;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.exception.QBittorrentLoginException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QBittorrentRequest {
    @Autowired
    AuthenticatedClientLoader authenticatedClientLoader;

    @Autowired
    private ApplicationProperties properties;

    public Response post(String path, RequestBody body) throws QBittorrentLoginException, IOException {
        OkHttpClient client = authenticatedClientLoader.execute();

        Request request = new Request.Builder()
                .url(properties.getQBittorrentApiUrl() + path)
                .post(body)
                .build();

        return client.newCall(request).execute();
    }

    public Response get(String path) throws QBittorrentLoginException, IOException {
        OkHttpClient client = authenticatedClientLoader.execute();

        Request request = new Request.Builder()
                .url(properties.getQBittorrentApiUrl() + path)
                .get()
                .build();

        return client.newCall(request).execute();
    }
}
