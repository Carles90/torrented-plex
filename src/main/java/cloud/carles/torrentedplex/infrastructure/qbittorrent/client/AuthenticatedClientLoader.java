package cloud.carles.torrentedplex.infrastructure.qbittorrent.client;

import cloud.carles.torrentedplex.application.ApplicationProperties;
import cloud.carles.torrentedplex.infrastructure.qbittorrent.exception.QBittorrentLoginException;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;

@Service
public class AuthenticatedClientLoader {
    @Autowired
    private ApplicationProperties properties;

    private OkHttpClient cachedClient = null;
    private Calendar cachedClientExpires = null;

    public OkHttpClient execute() throws QBittorrentLoginException {
        if (cachedClient == null || isCacheExpired()) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.cookieJar(new PersistentCookieJar());
            OkHttpClient client = builder.build();

            String loginUrl = properties.getQBittorrentApiUrl() + "/auth/login";

            RequestBody formBody = new FormBody.Builder()
                    .add("username", properties.getQBittorrentApiUser())
                    .add("password", properties.getQBittorrentApiPassword())
                    .build();

            Request request = new Request.Builder()
                    .url(loginUrl)
                    .post(formBody)
                    .build();

            Call call = client.newCall(request);
            try {
                Response response = call.execute();

                if (response.code() != 200) {
                    throw new QBittorrentLoginException();
                }
            } catch (IOException e) {
                throw new QBittorrentLoginException();
            }

            cachedClient = client;

            cachedClientExpires = Calendar.getInstance();
            cachedClientExpires.add(Calendar.MINUTE, 15);
        }

        return cachedClient;
    }

    private boolean isCacheExpired() {
        if (cachedClientExpires == null) {
            return true;
        }

        Calendar now = Calendar.getInstance();
        return now.getTimeInMillis() > cachedClientExpires.getTimeInMillis();
    }
}
