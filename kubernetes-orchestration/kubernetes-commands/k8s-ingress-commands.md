## Kubernetes Ingress: Commands Cheatsheet

> Prerequisite: While configuring k8s ingress rules and ingress controller, set up a kind ingress cluster with extraPortMappings

#### Setting up ingress cluster in kind with extraPortMappings `ingress-cluster.yaml`
```bash
$ kind create cluster --config kubernetes-orchestration/kubernetes-resources/ingress/cluster/ingress-cluster.yaml
$ docker ps
```

#### Create ingress controller
```bash
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
$ kubectl get ns
```

#### Simple Ingress Demo
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/ingress/simple-ingress/simple-ingress.yaml
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/ingress/simple-ingress/simple-deployment.yaml
$ kubectl get ingress
$ curl localhost:52510
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/ingress/simple-ingress/simple-ingress.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/ingress/simple-ingress/simple-deployment.yaml
```

#### Path based routing ingress demo
```bash
$ kubectl 
```
