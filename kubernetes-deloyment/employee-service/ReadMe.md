#### Create MySql Database instance
```bash
$ docker compose -f kubernetes-deloyment/docker-compose.yaml up mongodb
```
> Launch Robo 3T

#### Check statuses of several k8s components
```bash
$ kubectl get componentstatuses
```

#### Create employee service deployment
```bash
$ docker push ssamantr/employee-service:1.0.0
$ kubectl create deployment employee-app --image=ssamantr/employee-service:1.0.0
```

#### Access a deployment with port-forward or as a LoadBalancer
```bash
$ kubectl expose deployment employee-app --type=LoadBalancer --port=8080
$ kubectl port-forward <pod-name> 8080:8080
$ kubectl port-forward <deployment-name> 8080:8080
```
> kubectl expose deployment will create a k8s service of provided type
> kubectl port-forward can work on an individual pod or on a deployment

#### Scale a deployment
```bash
$ kubectl scale deployment employee-app --replicas=3
$ kubectl get replicasets
$ kubectl describe <replica-set-name>
```

#### Fetch all the events and resources inside a k8s cluster
```bash
$ kubectl get events
$ kubectl get events --sort-by=.metadata.creationTimestamp
```

#### Fetch all the pods inside a k8s cluster
```bash
$ kubectl get pods
$ kubectl get pods -o wide
$ kubectl get pods -o yaml
$ kubectl get pods -o json
```

#### Explain a k8s resource
```bash
$ kubectl explain pods
$ kubectl explain replicasets
$ kubectl explain deployments
$ kubectl explain services
```

#### Describe a k8s resource
```bash
$ kubectl describe <resource-kind/resource-name>
$ kubectl describe <resource-kind> <resource-name> 
```

#### Fetch all the replicasets inside a k8s cluster
```bash
$ kubectl get replicasets
$ kubectl get rs
```

#### Fetch all the deployments inside a k8s cluster
```bash
$ kubectl get deployments
$ kubectl get deploy
```

#### Fetch all the services inside a k8s cluster
```bash
$ kubectl get services
$ kubectl get svc
```
> Note: You can use either singular or plural to get all the resources

#### Delete a k8s resource
```bash
$ kubectl delete <resource-name>
```

#### Set an image to a deployment
```bash
$ docker push ssamantr/employee-service:2.0.0
$ kubectl get replicasets -o wide
$ kubectl set image deployment <deployment-name> <container-name>=<new-image-name>
$ kubectl set image deployment employee-app employee-service=ssamantr/employee-service:1.0.0
$ kubectl set image deployment employee-app employee-service=ssamantr/employee-service:2.0.0
```
> kubectl set image will create a new replicaset. The new image will be bound to the new replica set. A new pod will be created. 