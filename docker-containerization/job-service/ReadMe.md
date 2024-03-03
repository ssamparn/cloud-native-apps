> Run Mongo Image from Docker Compose only.
### Run a particular service from docker compose file
```bash
$ docker compose up <service-name>
$ docker compose up mongodb
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
```
> Both `mongodb` and `job-service` service will be up.
> Note: Docker profile works like a spring profile

### Build Job Service Application and Create Image
```bash
$ mvn clean install
```

### Create a docker network
```bash
$ docker network create job-service-network
```

### Check the created docker network
```bash
$ docker network ls
```

### Run Job Service docker image in the network created
> Even if you create a network with a particular name, the docker will append a prefix to the network name
```bash
$ docker run -p 8080:8080 --network=job-service_job-service-network --env spring.data.mongodb.uri="mongodb://root:rootpassword@mongodb:27017/job" ssamantr/job-service:1.0.0
```

### Run docker compose
```bash
$ docker compose -f docker-containerization/job-service/docker-compose.yml up
```
