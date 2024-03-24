## Kubernetes Commands Cheatsheet

> Prerequisite: Before creating a k8s startup probe for a pod or deployment, we assume a k8s cluster is created already.

#### httpGet probe `startup-http-get.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/probe/startup-http-get.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/probe/startup-http-get.yaml
```

#### tcpSocket probe `startup-tcp-socket.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/probe/startup-tcp-socket.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/probe/startup-tcp-socket.yaml
```

#### exec probe `startup-exec.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/probe/startup-exec.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/probe/startup-exec.yaml
```