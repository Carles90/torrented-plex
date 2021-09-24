package cloud.carles.torrentedplex.application.dto.search;

public class SearchResult {
    private final String title;
    private final String link;
    private final long size;
    private final int seeders;
    private final int peers;
    private final String tracker;

    public SearchResult(String title, String link, long size, int seeders, int peers, String tracker) {
        this.title = title;
        this.link = link;
        this.size = size;
        this.seeders = seeders;
        this.peers = peers;
        this.tracker = tracker;
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
