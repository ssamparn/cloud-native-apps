## Kubernetes Deployments: Commands Cheatsheet

> Prerequisite: Before creating a k8s Deployment, we assume k8s cluster is created.

#### Get all the k8s Resources:
```bash
$ kubectl get all
```

#### Get all the deployments
```bash
$ kubectl get <kind>
$ kubectl get deployments
```

#### Get a specific deployment
```bash
$ kubectl get <kind> <resource-name>
$ kubectl get deployment deployment-name
```

#### Create a deployment
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/deployment/simple-deployment.yaml
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/deployment/simple-deployment.yaml
```
> Note: Difference between `create` and `apply`: If the resource exists, `$ kubectl create` will error out and `$ kubectl apply` will not error out.

#### Delete a deployment
```bash
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/deployment/simple-deployment.yaml
```

#### Delete a particular deployment
```bash
$ kubectl delete deployment <deployment-name>
$ kubectl delete deployment my-deployment
```

#### Show deployment labels
```bash
$ kubectl get deployments --show-labels
```

#### Describe Deployments
```bash
$ kubectl describe deployments
```

#### Describe a particular deployment
```bash
$ kubectl describe deployment <deployment-name>
$ kubectl describe deployment my-deployment
```

#### Applying changes of a deployment.yaml. e.g: Upgrading version of image
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/deployment/simple-deployment.yaml
```

#### Filter/Query deployment(s) based on label(s)
```bash
$ kubectl get deployment -l key=value
$ kubectl get deployment -l dept=dept-1
$ kubectl get deployment -l team!=team-a
$ kubectl get deployment -l dept=dept-1,team=team-a
```

#### Get more information about deployments.
```bash
$ kubectl get <kind-name> -o wide
$ kubectl get deployments -o wide
$ kubectl get deployments -o yaml
$ kubectl get deployments <deployment-name> -o yaml
$ kubectl get deployments <deployment-name> -o json
```
> `o` stands for `output`


#### View the logs of a deployment: `pod-args.yaml`
```bash
$ kubectl logs [kind]/[resource-name]
$ kubectl logs deployment/my-deployment
```

#### Access a particular deployment like `docker exec`
```bash
$ kubectl exec -it [kind]/[deployment-name] -- <command>
$ kubectl exec -it deployment/my-deployment -- bash
```

#### Port Forwarding of deployment:
```bash
$ kubectl port-forward [kind]/[deployment-name] <host-port:container-port>
$ kubectl port-forward deployment/my-deployment 8080:80
$ curl http://localhost:8080/
```

#### Show the rollout revision history of a deployment
```bash
# deploy-rollout.yaml
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/deployment/deploy-rollout.yaml
$ kubectl port-forward deployment/order-service-deployment 8080:80
$ curl http://localhost:8080/
$ kubectl rollout history deploy
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/deployment/deploy-rollout.yaml
```

#### Rollout a deployment (application) to a previous version of deployment (application)
```bash
$ kubectl rollout undo deployment/<deployment-name>
$ kubectl rollout undo deployment/order-service-deployment
```

#### Rollout a deployment (application) to a specific version of deployment (application)
> Get the revision number from `$ kubectl rollout history deploy` command
```bash
$ kubectl rollout undo deployment/<deployment-name> --to-revision=<revision-number>
$ kubectl rollout undo deployment/order-service-deployment --to-revision=5
```

#### Show the changes occurred in a particular deployment using a revision from the revision history of a deployment
> Get the revision number from `$ kubectl rollout history deploy` command
```bash
$ kubectl rollout history deploy --revision=<revision-number>
$ kubectl rollout history deploy --revision=1
```

#### Assignment
```bash
# create redis deployment
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/deployment/redis-deployment.yaml
$ kubectl get pod -o wide

# Fetch the IP address of Redis
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/deployment/application-assignment.yaml

# Port-Forward
$ kubectl port-forward deployment.apps/application-assignment 8080:8080
$ curl http://localhost:8080/

$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/deployment/redis-deployment.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/deployment/application-assignment.yaml
```