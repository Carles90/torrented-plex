# TPlex Backend

TPlex creates a bridge between a Plex Media Server, and a qBittorrent instance exposing a REST API which you can use to
search and download movies, and keep them automatically in your Plex library.

This system is designed to run in a Raspberry Pi, but it can run in any device with Docker installed.

## Connected Applications

- qBittorrent: torrent client
- Jackett: torrent indexer (for torrent searching)
- Plex Media Server: stream your movies to your TV or other devices
- TPlex Backend: search torrents in Jackett, download them with qBittorrent, and move the media files to the Plex
  library.

## Deploying

1- Open a Terminal and go to the project folder.

```shell
cd /path/to/torrented-plex
```

2- Copy the .env.example properties file to .env.

```shell
cp .env.example .env
```

3- In case you are starting a new Plex Media Server, request a claim token with your Plex account here:

https://www.plex.tv/claim/

4- Edit the .env file with your favorite text editor, configuring these options:

- `TPLEX_JACKETT_CONFIG_DIR`: directory in your host device where Jackett configuration will be placed (it must exist).
- `TPLEX_JACKETT_PORT`: public port for the Jackett web API.
- `TPLEX_QBITTORRENT_CONFIG_DIR`: directory in your host device where qBittorrent configuration will be placed (it must
  exist).
- `TPLEX_QBITTORRENT_DOWNLOADS_DIR`: directory in your host device where qBittorrent will download your torrents (it
  must exist).
- `TPLEX_QBITTORRENT_PORT`: public port for qBittorrent.
- `TPLEX_QBITTORRENT_WEBUI_PORT`: public port for qBittorrent API and its web UI.
- `TPLEX_PLEX_CLAIM`: Plex claim token obtained in step 3. Or your claim token if you previously had one.
- `TPLEX_PLEX_CONFIG_DIR`: directory in your host device where Plex Media Server configuration will be placed (it must
  exist).
- `TPLEX_JACKETT_API_URL`: public API URL that Jackett will have. It should be something
  like `http://<my_local_ip>:9117/api/v2.0`, where `my_local_ip` is your Raspberry local IP.
- `TPLEX_JACKETT_API_KEY`: Jackett API key. Leave it blank by the moment.
- `TPLEX_QBITTORRENT_API_URL`: public API URL that qBittorrent will have. It should be something
  like `http://<my_local_ip>:8123/api/v2`, where `my_local_ip` is your Raspberry local IP.
- `TPLEX_QBITTORRENT_USER`: qBittorrent user (it will be defaulted to `admin` during the installation).
- `TPLEX_QBITTORRENT_PASSWORD`: qBittorrent password (it will be defaulted to `adminadmin` during the installation).
- `TPLEX_API_PORT`: public port for the TPlex backend API. This will be used from the UI.

5- Initialize the Jackett Docker container

```shell
docker-compose up -d jackett
```

6- From your browser, access to the Jackett web UI:

http://192.168.1.1:9117/UI (change the IP and the port for the ones referencing to your Raspberry)

7- Copy the API Key value from your Jackett dashboard and set it to the `TPLEX_JACKETT_API_KEY` parameter in your `.env`
file.

8- Time to start all the system.

```shell
docker-compose up -d
```

## Configuring Indexers

Jacket will not find any torrent unless you configure indexers. To configure them, you have to go to the Jackett UI.

http://192.168.1.1:9117/UI (change the IP and the port for the ones referencing to your Raspberry)

Once there, click on "Add indexer", and select the indexers from the list you want to add.

Recommendation: filter the indexers by your language, so you can find movies only in your language (example: for
Spanish, filter by `es-ES`).

## Configuring Plex Media Server

Go to the Plex Media Server URL:

http://192.168.1.1:32400/web

1. Login to Plex or create an account.
2. Once you login, you will find a wizard to configure this server.
3. Enter a name for your server. You'll see this name in your devices. Then click next to go to your library options.
4. Click on the "Add Library" button.
5. Select "Movies" as the library type.
6. Choose the language that your movies will have, and click Next.
7. Find the folder where your library is placed. You should set it as `/movies`.
8. Click on next and finish your configuration.
9. To keep your library updated, you should go to Settings -> Library, and check the option "Scan my library
   automatically"

Now you are able to connect to your Plex Media Server. You can download the Plex application in your TV and login with
the same account. Then, you'll see your server, and when you download any folder, it will appear there automatically.

## Android/iOS Application or WebUI

To access this backend you'll need the Torrented Plex UI application.

You will find it in: https://github.com/Carles90/torrented-plex-ui

## Stopping the server

Just stop the Docker containers.

```shell
docker-compose down
```