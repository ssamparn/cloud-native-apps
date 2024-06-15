### Create front-end docker image on top of nginx image
```bash
$ cd docker-containerization/api-gateway/
$ docker build -t ssamantr/frontend-service:1.0.0 .
$ docker images
```

`Now test the image`
```bash
$ docker run -d --name frontend-service -p 9090:80 ssamantr/frontend-service:1.0.0
$ docker push ssamantr/frontend-service:1.0.0
```

### Run nginx as api-gateway
```bash
$ docker images
$ docker compose -f docker-containerization/api-gateway/docker-compose.yaml up
$ docker compose -f docker-containerization/api-gateway/docker-compose.yaml ps
$ docker compose -f docker-containerization/api-gateway/docker-compose.yaml stop
$ docker compose -f docker-containerization/api-gateway/docker-compose.yaml down
```