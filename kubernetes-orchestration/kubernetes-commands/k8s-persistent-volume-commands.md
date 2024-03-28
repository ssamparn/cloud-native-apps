## Kubernetes Persistent Volumes: Commands Cheatsheet

> Prerequisite: Before creating a k8s persistent volume claim, we assume k8s cluster is created.

#### Get all the k8s Storage classes
```bash
$ kubectl get storageclass
$ kubectl get sc
```

#### Get all the persistent volume claims
```bash
$ kubectl get persistentvolumeclaims
$ kubectl get pvc
```

#### Get all the persistent volumes
```bash
$ kubectl get persistentvolumes
$ kubectl get pv
```

#### Create a persistent volume claim `simple-persistent-volume-claim.yaml`
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/volume/simple-persistent-volume-claim.yaml
$ kubectl get pvc
$ kubectl describe pvc
$ kubectl get pv
```

#### Attach a persistent volume to k8s pod `pod-with-persistence.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/volume/pod-with-persistence.yaml
$ kubectl get pvc
$ kubectl describe pvc
$ kubectl get pv
$ kubectl describe pv
$ docker ps
$ docker exec -it local-cluster-worker bash
$ cd var/local-path-provisioner
$ ls
$ cd pvc......
$ echo "Hello PVC" > index.html
$ cat index.html
$ exit
$ kubectl get pods
$ kubectl port-forward pod/my-pod 8080:80
$ curl localhost:8080
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/volume/pod-with-persistence.yaml
```

#### Delete a persistent volume claim `simple-persistent-volume-claim.yaml`
```bash
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/volume/simple-persistent-volume-claim.yaml
$ kubectl get pvc
$ kubectl get pv
$ kubectl delete pvc --all
```
> By deleting the persistent volume claim, the persistent volume is also deleted.

#### Attach a persistent volume to k8s deployment `deployment-with-persistence.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/volume/deployment-with-persistence.yaml
$ kubectl get pvc
$ kubectl describe pvc
$ kubectl get pv
$ kubectl describe pv
$ kubectl port-forward deployment.apps/my-deployment 8080:80
$ curl localhost:8080
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/volume/deployment-with-persistence.yaml
```

#### Create a k8s stateful set `simple-stateful-set.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/volume/simple-stateful-set.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/volume/simple-stateful-set.yaml
```

#### Attach a stateful set to k8s service `service-with-stateful-set.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/volume/service-with-stateful-set.yaml
$ kubectl exec -it pod/demo-pod -- bash
$ curl my-service
$ curl my-service
$ curl my-service
$ curl my-service
$ curl my-service
$ kubectl port-forward deployment.apps/my-deployment 8080:80
$ curl localhost:8080
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/volume/service-with-stateful-set.yaml
```

#### Attach a stateful set to k8s headless service `headless-service-with-stateful-set.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/volume/headless-service-with-stateful-set.yaml
$ kubectl exec -it pod/demo-pod -- bash
$ nslookup my-service
`or`
$ nslookup my-stateful-set-0.my-service
$ nslookup my-stateful-set-1.my-service
$ nslookup my-stateful-set-2.my-service
$ curl my-service
$ curl my-stateful-set-0.my-service
$ curl my-stateful-set-1.my-service
$ curl my-stateful-set-1.my-service
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/volume/headless-service-with-stateful-set.yaml
```

#### Attach a dynamic persistent volume claim to a k8s stateful set `dynamic-persistent-volume-claim.yaml`
```bash
$ kubectl delete pvc --all
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/volume/dynamic-persistent-volume-claim.yaml
$ kubectl get pvc
$ kubectl get pv
$ kubectl port-forward pod/my-stateful-set-0 8080:80
$ kubectl port-forward pod/my-stateful-set-1 8081:80
$ kubectl port-forward pod/my-stateful-set-2 8082:80
$ curl localhost:8080
$ curl localhost:8081
$ curl localhost:8082
$ kubectl delete pvc --all
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/volume/dynamic-persistent-volume-claim.yaml
```