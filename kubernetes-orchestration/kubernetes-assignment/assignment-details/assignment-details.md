### Assignment Details:

####
```bash
$ docker images
$ docker push ssamantr/candidate-service:1.0.0
$ docker push ssamantr/job-service:1.0.0
$ docker push ssamantr/frontend-service:1.0.0
$ echo -n "mongodb://candidate_user:candidate_password@mongodb:27017/candidate" | base64
$ echo -n "mongodb://job_user:job_password@mongodb:27017/job" | base64
$ kubectl apply -f kubernetes-orchestration/kubernetes-assignment/.
$ kubectl delete -f kubernetes-orchestration/kubernetes-assignment/.
```

> If Ingress routing does not work then use port-forward to frontend pod.
```bash
kubectl port-forward pod/frontend-app-5cdbdcd778-8xs6l 9090:80
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
http://localhost/candidates/all
http://localhost/candidates/1
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
http://localhost/jobs/all
http://localhost/jobs/search?skills=java
```

## Frontend Service
- It contains static content
- Listens on port `80`
- health endpoint: `/`
- It depends on Job and Candidate services
- When the home page `index.html` is loaded, it makes a call to Job-service to fetch all the jobs available.
```
http://localhost/jobs/all
```
- When we click on the `Candidates` tab, it makes a call to Candidate-service to fetch all candidates
```
http://localhost/candidates/all
```

#### Ingress Routing Rules
- `/` should load home page
- `/candidates` should go to candidate service
- `/jobs` should go to job-service