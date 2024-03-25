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

> liveness probe kicks in after start up probe is successful.

#### liveness probe `liveness-probe.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/probe/liveness-probe.yaml
$ kubectl describe pod/my-pod
$ kubectl exec -it pod/my-pod -- bash
$ cd /usr/share/nginx/html/
$ ls -l
```

> To simulate an error scenario remove `live.html`
```bash
$ rm live.html
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/probe/liveness-probe.yaml
```

#### readiness probe `readiness-probe.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/probe/readiness-probe.yaml
$ kubectl exec -it pod/my-pod -- bash
$ cd /usr/share/nginx/html/
$ ls -l
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/probe/readiness-probe.yaml
```

> To simulate an error scenario remove `ready.html`
```bash
$ rm ready.html
$ touch ready.html
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/probe/liveness-probe.yaml
```