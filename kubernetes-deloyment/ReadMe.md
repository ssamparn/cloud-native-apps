#### Launch applications and database(s)
```bash
$ docker compose -f kubernetes-deloyment/docker-compose.yaml up
```

#### Check k8s cluster information
```bash
$ kubectl cluster-info
```

#### Check statuses of several k8s components
```bash
$ kubectl get componentstatuses
```

#### Create employee and address service deployment
```bash
$ docker push ssamantr/employee-service:1.0.0
$ kubectl create deployment employee-app --image=ssamantr/employee-service:1.0.0
$ kubectl create deployment address-app --image=ssamantr/address-service:1.0.0
```
> Creating address deployment will lead to error

#### Access a deployment with port-forward or as a LoadBalancer
```bash
$ kubectl expose deployment employee-app --type=LoadBalancer --port=8080
$ kubectl port-forward <pod-name> 8080:8080
$ kubectl port-forward <deployment-name> 8080:8080
$ kubectl port-forward <service-name> 8080:8080
```
> kubectl expose deployment will create a k8s service of provided type
> kubectl port-forward can work on an individual pod, on a deployment and also on a service.

#### Scale a deployment
```bash
$ kubectl scale deployment employee-app --replicas=3
$ kubectl get replicasets
$ kubectl describe <replica-set-name>
```

#### View the previous version(s) of a deployment
```bash
$ kubectl rollout history deployment <deployment-name>
$ kubectl rollout history deployment employee-app
```

#### Check the rollout status of a deployment
```bash
$ kubectl rollout status deployment <deployment-name>
$ kubectl rollout status deployment employee-app
```

#### Undo rollout of a deployment
```bash
$ kubectl rollout undo deployment <deployment-name> --to-revision=<get-revision-from-rollout-history>
$ kubectl rollout undo deployment employee-app --to-revision=3
```

#### Set an image to a deployment
```bash
$ docker push ssamantr/employee-service:2.0.0
$ kubectl get replicasets -o wide
$ kubectl set image deployment <deployment-name> <container-name>=<new-image-name>
$ kubectl set image deployment employee-app employee-service=ssamantr/employee-service:1.0.0 --record=true
$ kubectl set image deployment employee-app employee-service=ssamantr/employee-service:2.0.0 --record=true
$ kubectl rollout history deployment employee-app
```
> kubectl set image will create a new replicaset. The new image will be bound to the new replica set. A new pod will be created.

#### Fetch all the events and resources inside a k8s cluster
```bash
$ kubectl get events
$ kubectl get events --sort-by=.metadata.creationTimestamp
```

#### Fetch all the pods inside a k8s cluster across all namespaces
```bash
$ kubectl get pods
$ kubectl get pods -o wide
$ kubectl get pods -o yaml
$ kubectl get pods -o json
$ kubectl get pods --all-namespaces
$ kubectl get pods -l app-label=employee-app
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
$ kubectl get rs --all-namespaces
```

#### Fetch all the deployments inside a k8s cluster
```bash
$ kubectl get deployments
$ kubectl get deploy
$ kubectl get deployments --all-namespaces --sort-by=.spec.replicas
$ kubectl get deployments --all-namespaces
```

#### Fetch all the services inside a k8s cluster
```bash
$ kubectl get services
$ kubectl get svc
$ kubectl get services --all-namespaces
$ kubectl get services --all-namespaces --sort-by=.metadata.name
```
> Note: You can use either singular or plural to get all the resources

#### Fetch all the logs of a k8s resource
```bash
$ kubectl logs <resource-id>
$ kubectl logs <resource-id> -f
$ kubectl logs deployment.apps/employee-app -f
```

#### Get ingress gateway
```bash
$ kubectl get ingress
$ kubectl get ing
$ kubectl describe ing api-gateway
```

#### Create `deployment.yaml` from a specific deployment
```bash
$ kubectl get deployment <deployment-name> -o yaml > deployment.yaml
$ kubectl get deployment employee-app -o yaml > deployment.yaml
```

#### Create `service.yaml` from a specific service
```bash
$ kubectl get deployment <deployment-name> -o yaml > service.yaml
$ kubectl get service employee-app -o yaml > service.yaml
```

#### Create k8s deployment and service from yaml
```bash
$ kubectl apply -f kubernetes-deloyment/k8s/employee-service/employee-service.yaml
$ kubectl delete -f kubernetes-deloyment/k8s/employee-service/employee-service.yaml
$ kubectl apply -f kubernetes-deloyment/k8s/multi-deployment/multiple-employee-service.yaml
$ kubectl delete -f kubernetes-deloyment/k8s/multi-deployment/multiple-employee-service.yaml
```

#### Find the difference between 2 k8s deployment
```bash
$ kubectl diff -f kubernetes-deloyment/k8s/employee-service/employee-service.yaml
```
> Works like `git diff`. Helpful for debugging

#### Delete a/all k8s resource and/or with a specific label
```bash
$ kubectl delete <resource-name>
$ kubectl delete all --all
$ kubectl delete all -l app-label=employee-app
```

####
```bash
$ docker images
$ docker push ssamantr/employee-service:1.0.0
$ docker push ssamantr/employee-service:2.0.0
$ docker push ssamantr/address-service:1.0.0
$ echo -n "mongodb://employee_user:employee_password@mongodb:27017/employee" | base64
$ echo -n "jdbc:mysql://mysql:3306/address" | base64
$ echo -n "address" | base64
$ echo -n "address_user" | base64
$ echo -n "address_password" | base64
$ echo -n "Sashank@1991" | base64
```

#### Address Service: k8s Deployment 
```bash
$ kubectl apply -f kubernetes-deloyment/k8s/address-service/.
$ kubectl delete -f kubernetes-deloyment/k8s/address-service/.
`or`
$ kubectl apply -f kubernetes-deloyment/k8s/address-service/address-secrets.yaml
$ kubectl apply -f kubernetes-deloyment/k8s/address-service/mysql-init-config-map.yaml
$ kubectl apply -f kubernetes-deloyment/k8s/address-service/mysql-stateful-set.yaml
$ kubectl apply -f kubernetes-deloyment/k8s/address-service/address-service.yaml
```

#### Employee Service: k8s Deployment
```bash
$ kubectl apply -f kubernetes-deloyment/k8s/employee-service/.
$ kubectl delete -f kubernetes-deloyment/k8s/employee-service/.
`or`
$ kubectl apply -f kubernetes-deloyment/k8s/employee-service/employee-secrets.yaml
$ kubectl apply -f kubernetes-deloyment/k8s/employee-service/mongo-init-config-map.yaml
$ kubectl apply -f kubernetes-deloyment/k8s/employee-service/mongo-stateful-set.yaml
$ kubectl apply -f kubernetes-deloyment/k8s/employee-service/employee-service.yaml
```

#### k8s Deployment
```bash
$ kubectl apply -f kubernetes-deloyment/k8s/gke/resources/.
$ kubectl delete -f kubernetes-deloyment/k8s/gke/resources/.
```

### Google Cloud Services: k8s Deployment

#### GKE k8s Cluster Details

> Cluster Name: k8s-cluster
> Location Type: Zonal
> Zone: us-central1-c

##### Connect to GKE k8s Cluster
```bash
$ gcloud container clusters get-credentials k8s-cluster --zone us-central1-c --project spring-microservices-350123
$ kubectl get nodes
$ kubectl top nodes
```

> Open cloud shell editor.
> Goto File --> Open Folder --> `/home/sashank1703` 
> Create a directory `k8s-deployment`
> Upload all the yaml files which are present in gke directory into it.
> 
```bash
$ kubectl apply -f k8s-deployment/.
$ kubectl delete -f k8s-deployment/.
```