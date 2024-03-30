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

#### Simple Ingress Demo `simple-ingress.yaml` and `simple-deployment.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/ingress/simple-ingress/simple-ingress.yaml
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/ingress/simple-ingress/simple-deployment.yaml
$ kubectl get ingress
$ docker ps
$ curl localhost:8080
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/ingress/simple-ingress/simple-ingress.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/ingress/simple-ingress/simple-deployment.yaml
```

#### Path based routing ingress demo 
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/ingress/path-based-routing/.
$ kubectl get ingress
$ kubectl describe ingress my-ingress
$ curl localhost:8080
$ curl localhost:8080/product
$ curl localhost:8080/user
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/ingress/path-based-routing/.
```
> Path based routing is not working

#### Host based routing ingress demo
```bash
$ watch -t -x kubectl get all -n dev
$ watch -t -x kubectl get all -n qa
$ watch -t -x kubectl get all -n prod
$ kubectl create ns dev
$ kubectl create ns qa
$ kubectl create ns prod
$ kubectl get ns
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/ingress/host-based-routing/dev/.
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/ingress/host-based-routing/qa/.
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/ingress/host-based-routing/prod/.

$ kubectl get ingress -n dev
$ kubectl get ingress -n qa
$ kubectl get ingress -n prod
$ kubectl describe ingress my-ingress -n dev
$ kubectl describe ingress my-ingress -n qa
$ kubectl describe ingress my-ingress -n prod

$ sudo nano /etc/hosts
$ curl http://dev.myapp.com:8080/
$ curl http://qa.myapp.com:8080/
$ curl http://prod.myapp.com:8080/

$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/ingress/host-based-routing/dev/.
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/ingress/host-based-routing/qa/.
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/ingress/host-based-routing/prod/.
```

> Refer https://setapp.com/how-to/edit-mac-hosts-file