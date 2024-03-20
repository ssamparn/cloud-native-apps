## Kubernetes Commands Cheatsheet

> Prerequisite: Before creating a k8s Service, we assume a k8s cluster and k8s deployment with some pods in it are created already.

#### Get all the services
```bash
$ kubectl get <kind>
$ kubectl get svc
$ kubectl get service
```

#### exec into pod `service-load-balancing.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/service/service-load-balancing.yaml
$ kubectl exec -it pod/my-pod -- bash
$ curl http://my-app:8080
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/service/service-load-balancing.yaml
```

#### Redis Assignment (Restaurant Locator Application): Very Important
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/service/redis-assignment.yaml
$ kubectl port-forward deployment.apps/application-assignment 8080:8080
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/service/redis-assignment.yaml
```

