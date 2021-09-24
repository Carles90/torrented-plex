package cloud.carles.torrentedplex.infrastructure.jacket;

import cloud.carles.torrentedplex.application.ApplicationProperties;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class JacketRequest {
    @Autowired
    private ApplicationProperties properties;

    public InputStream get(String path) throws IOException {
        return get(path, new HashMap<>());
    }

    public InputStream get(String path, Map<String, String> parameters) throws IOException {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(properties.getJacketApiUrl() + path).newBuilder();

        for (Map.Entry<String, String> param : parameters.entrySet()) {
            httpBuilder.addQueryParameter(param.getKey(), param.getValue());
        }

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(httpBuilder.build()).build();

        Response response = client.newCall(request).execute();

        return response.body().byteStream();
    }
}
