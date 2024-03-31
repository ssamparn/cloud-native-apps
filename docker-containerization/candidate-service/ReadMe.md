> Run Mongo Image from Docker Compose only.
### Run a particular service from docker compose file
```bash
$ docker compose up <service-name>
$ docker compose up mongodb
$ docker compose -f docker-containerization/candidate-service/docker-compose.yml up mongodb
```

### Run a particular service from docker compose file. Then create a docker profile
```bash
$ docker compose up
```
> Only `mongodb` service will be up.

### Run all the services
```bash
$ docker compose --profile=<profile-name> up 
$ docker compose --profile=app up 
$ docker compose -f docker-containerization/candidate-service/docker-compose.yml --profile=app up
$ docker compose -f docker-containerization/candidate-service/docker-compose.yml --profile=app down
```
> Both `mongodb` and `candidate-service` service will be up.
> Note: Docker profile works like a spring profile

### Build Candidate Service Application and Create Image
```bash
$ mvn clean install
```

### Create a docker network
```bash
$ docker network create candidate-service-network
```

### Check the created docker network
```bash
$ docker network ls
```

### Run Candidate Service docker image in the network created
> Even if you create a network with a particular name, the docker will append a prefix to the network name
```bash
$ docker run -p 8080:8080 --network=candidate-service_candidate-service-network --env spring.data.mongodb.uri="mongodb://candidate_user:candidate_password@mongodb:27017/candidate" ssamantr/candidate-service:1.0.0
```

### Run docker compose
```bash
$ docker compose -f docker-containerization/candidate-service/docker-compose.yml up
```
