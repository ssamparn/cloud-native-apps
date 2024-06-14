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

#### Docker Compose Volume Mapping
```bash
$ docker compose -f docker-containerization/docker-compose/docker-compose-volume-mapping/docker-compose.yml up
$ curl http://localhost:8080/
```

#### Docker Compose Network
```bash
$ docker compose -f docker-containerization/docker-compose/docker-compose-network/docker-compose.yml up
$ curl http://localhost:8080/
```

#### Docker Compose: Container Environment Variable
```bash
$ docker run ubuntu env
$ docker run -e INPUT=55 ubuntu env
$ docker compose -f docker-containerization/docker-compose/docker-container-env-variables/docker-compose.yml up
$ docker compose -f docker-containerization/docker-compose/docker-container-env-variables/docker-compose.yml down
```

#### Docker Compose: Passing Environment Variable
```bash
$ docker compose -f docker-containerization/docker-compose/docker-compose-environment-variable/docker-compose.yml up
$ export TAG=perl
$ docker images
$ docker compose -f docker-containerization/docker-compose/docker-compose-environment-variable/docker-compose.yml up
$ docker images
$ docker compose -f docker-containerization/docker-compose/docker-compose-environment-variable/docker-compose.yml down
```

#### Docker Compose: Mongo-Express
```bash
$ docker compose -f docker-containerization/docker-compose/docker-compose-mongoexpress/docker-compose.yml up
$ docker compose -f docker-containerization/docker-compose/docker-compose-mongoexpress/docker-compose.yml down
```

#### Docker Compose: Postgres
```bash
$ docker compose -f docker-containerization/docker-compose/docker-compose-postgres/docker-compose.yml up
$ docker compose -f docker-containerization/docker-compose/docker-compose-postgres/docker-compose.yml down
```
> Note: Provide localhost as `hostname` in pgAdmin tool.

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