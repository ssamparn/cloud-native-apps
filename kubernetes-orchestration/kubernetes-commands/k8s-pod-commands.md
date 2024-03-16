## Kubernetes Commands Cheatsheet

> Prerequisite: Before creating a k8s pod, we assume k8s cluster is created.

#### Get all the pods
```bash
$ kubectl get pods
```

#### Get a specific pod
```bash
$ kubectl get pod <pod-name>
$ kubectl get pod pod-1
```

#### Create a pod
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/simple-pod.yaml
```

#### Delete pod(s)
```bash
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/simple-pod.yaml
```

#### Delete a particular pod
```bash
$ kubectl delete pod <pod-name>
$ kubectl delete pod pod-1
```

#### Describe pods
```bash
$ kubectl describe pods
```

#### Describe a particular pod
```bash
$ kubectl describe pod <pod-name>
$ kubectl describe pod pod-1
```

> The `Events` section after describing pod helps in debugging a container

#### Applying changes of a pod.yaml. e.g: Upgrading version of image
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/pod/simple-pod.yaml
```

#### Get the labels of pod(s)
```bash
$ kubectl get pods --show-labels
```

#### Filter/Query pod(s) based on label(s)
```bash
$ kubectl get pod -l key=value
$ kubectl get pod -l dept=dept-1
$ kubectl get pod -l team!=team-a
$ kubectl get pod -l dept=dept-1,team=team-a
```

#### Get more information about pods.
```bash
$ kubectl get pods -o wide
$ kubectl get pods -o yaml
$ kubectl get pod <pod-name> -o yaml
$ kubectl get pod <pod-name> -o json
$ kubectl get pod pod-1 -o yaml
$ kubectl get pod pod-1 -o json
```
> `o` stands for `output`

#### Port Forwarding of pod
```bash
$ kubectl port-forward <pod-name> <host-port:container-port>
$ kubectl port-forward my-pod 8080:80
```