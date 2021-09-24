package cloud.carles.torrentedplex.infrastructure.gson;

import com.google.gson.Gson;

public class GsonContainer {
    public static Gson get() {
        return new Gson();
    }
}
