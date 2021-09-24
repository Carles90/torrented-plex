package cloud.carles.torrentedplex.infrastructure.qbittorrent.dto;

import cloud.carles.torrentedplex.application.dto.download.DownloadItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaindataResponse {
    @JsonProperty("torrents")
    private Map<String, Torrent> torrents;

    public MaindataResponse() {
    }

    public Map<String, Torrent> getTorrents() {
        return torrents;
    }

    public List<DownloadItem> toDownloadItemList() {
        return torrents.entrySet()
                .stream()
                .map(torrent -> new DownloadItem(
                        torrent.getKey(),
                        torrent.getValue().getName(),
                        torrent.getValue().getState(),
                        torrent.getValue().getSeeds(),
                        torrent.getValue().getLeechs(),
                        torrent.getValue().getDownloadSpeed(),
                        torrent.getValue().getSize(),
                        torrent.getValue().getDownloaded(),
                        torrent.getValue().getProgress(),
                        torrent.getValue().getEta()
                ))
                .collect(Collectors.toList());
    }
}
