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

#### Node Port Service Creation `service-node-port.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/service/service-node-port.yaml
$ curl http://localhost:30001
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/service/service-node-port.yaml
```

#### Rolling Update Service Demo `rolling-update-service.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/service/rolling-update-service.yaml
$ kubectl exec -it pod/demo-pod -- bash
$ curl http://order-service:8080
$ for i in {1..10000}; do curl -s http://order-service:8080 | grep -o "<title>[^<]*" | tail -c+8; done
$ kubectl rollout undo deploy/order-service-deployment
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/service/rolling-update-service.yaml
```
