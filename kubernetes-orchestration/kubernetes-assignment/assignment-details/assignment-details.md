### Assignment Details:

####
```bash
$ docker images
$ docker push ssamantr/candidate-service:1.0.0
$ docker push ssamantr/job-service:1.0.0
$ echo -n "mongodb://candidate_user:candidate_password@mongodb:27017/candidate" | base64
$ echo -n "mongodb://job_user:job_password@mongodb:27017/job" | base64
$ kubectl apply -f kubernetes-orchestration/kubernetes-assignment/.
$ kubectl delete -f kubernetes-orchestration/kubernetes-assignment/.
```

#### Mongo: 
- Listens on port: `27017`
- We need a persistent storage
- Refer to below ConfigMap format to inject multiple files

```
apiVersion: v1
kind: ConfigMap
metadata:
  name: [ name ]
data:
  file1.txt: |
    line 1
    line 2
  file2.txt: |
    line 1
    line 2
```

#### Candidate Service:

- It is a Spring boot Microservice
- Listens on port `8080`
- health endpoint: `/actuator/health`
- Both startup and readiness probe can use same endpoint
- Min required replica: 1
- CPU / Memory Usage
```
CPU => min: 100m , max: 2000m
Memory => min: 100Mi, Max: 2000Mi 
```
- Endpoints to test if you are curious
```
http://localhost/candidate/all
http://localhost/candidate/1
```

#### Job Service:
- It is a Spring boot Microservice
- Listens on port `8080`
- health endpoint: `/actuator/health`
- Both startup and readiness probe can use same endpoint
- Min required replica: 1
- CPU / Memory Usage
```
CPU => min: 100m , max: 2000m
Memory => min: 100Mi, Max: 2000Mi 
```
- Endpoints to test if you are curious
```
http://localhost/job/all
http://localhost/job/search?skills=java
```

#### Ingress Routing Rules
- `/` should load home page
- `/candidates` should go to candidate service
- `/jobs` should go to job-service