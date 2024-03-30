## Mock Server Commands

#### To spin up dockerized mock server
```bash
> Configure 01-init.json
$ docker compose -f docker-containerization/mock-server/docker-compose.yaml up
$ docker compose -f docker-containerization/mock-server/docker-compose.yaml ps
$ curl localhost:1080/say/hello
$ curl localhost:1080/some/path
> Configure 02-init.json
$ curl -v localhost:1080/some/sdkjdsnv
$ curl localhost:1080/query\?q=docker
$ curl -v localhost:1080/query\?q=k8s
$ curl -v localhost:1080/error/500
```