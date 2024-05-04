## Docker Compose Commands Cheatsheet

#### To spin up all the containers with custom bridge network
```bash
$ docker compose up
$ docker compose -f a/b/c/docker-compose-file.yml up
$ docker compose -f docker-containerization/docker-compose/docker-compose-simple/docker-compose.yml up
```

#### To spin up all the containers in detached mode
```bash
$ docker compose up -d
```

#### To check service status
```bash
$ cd to-directory-where-docker-compose-file-is-placed
$ docker compose ps
```

#### To stop or bring down the app, containers and networks
```bash
$ docker compose down
$ docker compose -f a/b/c/docker-compose-file.yml down
$ docker compose -f docker-containerization/docker-compose/docker-compose-simple/docker-compose.yml down
```

#### To check the service logs for debugging
```bash
$ cd to-directory-where-docker-compose-file-is-placed
$ docker compose logs <service-name>
$ docker compose logs web-app
```