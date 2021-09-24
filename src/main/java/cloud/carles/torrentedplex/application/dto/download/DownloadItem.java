package cloud.carles.torrentedplex.application.dto.download;

public class DownloadItem {
    private final String id;
    private final String name;
    private final String state;
    private final int seeds;
    private final int leechs;
    private final long downloadSpeed;
    private final long size;
    private final long downloaded;
    private final double progress;
    private final long eta;

    public DownloadItem(
            String id,
            String name,
            String state,
            int seeds,
            int leechs,
            long downloadSpeed,
            long size,
            long downloaded,
            double progress,
            long eta
    ) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.seeds = seeds;
        this.leechs = leechs;
        this.downloadSpeed = downloadSpeed;
        this.size = size;
        this.downloaded = downloaded;
        this.progress = progress;
        this.eta = eta;
    }

    public String getId() {
        return id;
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
