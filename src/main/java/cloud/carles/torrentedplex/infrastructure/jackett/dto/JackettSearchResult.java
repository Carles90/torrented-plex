package cloud.carles.torrentedplex.infrastructure.jackett.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JackettSearchResult {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Link")
    private String link;

    @JsonProperty("Size")
    private long size;

    @JsonProperty("Seeders")
    private int seeders;

    @JsonProperty("Peers")
    private int peers;

    @JsonProperty("Tracker")
    private String tracker;

    public JackettSearchResult() {

    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public long getSize() {
        return size;
    }

    public int getSeeders() {
        return seeders;
    }

    public int getPeers() {
        return peers;
    }

    public String getTracker() {
        return tracker;
    }
}
