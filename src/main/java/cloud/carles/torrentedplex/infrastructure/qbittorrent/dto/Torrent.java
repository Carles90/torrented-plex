package cloud.carles.torrentedplex.infrastructure.qbittorrent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Torrent {
    @JsonProperty("name")
    private String name;

    @JsonProperty("state")
    private String state;

    @JsonProperty("num_seeds")
    private int seeds;

    @JsonProperty("num_leechs")
    private int leechs;

    @JsonProperty("dlspeed")
    private long downloadSpeed;

    @JsonProperty("total_size")
    private long size;

    @JsonProperty("downloaded")
    private long downloaded;

    @JsonProperty("progress")
    private double progress;

    @JsonProperty("eta")
    private long eta;

    public Torrent() {
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public int getSeeds() {
        return seeds;
    }

    public int getLeechs() {
        return leechs;
    }

    public long getDownloadSpeed() {
        return downloadSpeed;
    }

    public long getSize() {
        return size;
    }

    public long getDownloaded() {
        return downloaded;
    }

    public double getProgress() {
        return progress;
    }

    public long getEta() {
        return eta;
    }
}
