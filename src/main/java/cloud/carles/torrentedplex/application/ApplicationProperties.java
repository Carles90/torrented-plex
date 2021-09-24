package cloud.carles.torrentedplex.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ApplicationProperties {
    @Autowired
    private Environment env;

    public String getJacketApiUrl() {
        return env.getProperty("jacket.api.url");
    }

    public String getJacketApiKey() {
        return env.getProperty("jacket.api.key");
    }
}
