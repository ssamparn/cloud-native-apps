## Kubernetes Pods: Commands Cheatsheet

> Prerequisite: Before creating a k8s pod, we assume k8s cluster is created.

#### Get all the k8s Resources:
```bash
$ kubectl get all
```

#### Get all the pods
```bash
$ kubectl get <kind>
$ kubectl get pods
```

#### Get a specific pod
```bash
$ kubectl get <kind> <resource-name>
$ kubectl get pod pod-1
```

#### Create a pod
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/simple-pod.yaml

# Run failing-pod.yaml to create CrashLoopBackOff - Lecture 19
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/failing-pod.yaml

# Run multiple-pods.yaml to demo pod labels - Lecture 21
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/multiple-pods.yaml

# Run restart-policy.yaml to demo restart policy - Lecture 26
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/restart-policy.yaml

# Run pod-args.yaml to demo pod argument - Lecture 28
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/pod-args.yaml
$ kubectl logs pod/my-pod

# Run pod-shell-args.yaml to demo pod arguments in shell form - Lecture 30
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/pod-shell-args.yaml
$ kubectl logs pod/my-pod

# Run termination-grace-period.yaml to demo graceful shut down of pods - Lecture 31
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/termination-grace-period.yaml

# Run pod-command.yaml to demo pod entrypoint - Lecture 32
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/pod-command.yaml
$ kubectl logs pod/my-pod
```

#### Delete pod(s)
```bash
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/simple-pod.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/failing-pod.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/multiple-pods.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/restart-policy.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/pod-args.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/pod-shell-args.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/termination-grace-period.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/pod-command.yaml
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

#### Port Forwarding of pod: `pod-port.yaml`
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/pod-port.yaml
$ kubectl port-forward <pod-name> <host-port:container-port>
$ kubectl port-forward my-pod 8080:80
$ curl http://localhost:8080/
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/pod-port.yaml
```

#### View the logs of a pod: `pod-args.yaml`
```bash
$ kubectl logs <pod-name>
$ kubectl logs my-pod
```

#### View logs of a particular container of a pod in case the pod is a multi-container pod
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/multi-container-pod.yaml
$ kubectl logs <pod-name> -c <container-name>
$ kubectl logs my-pod -c nginx
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/multi-container-pod.yaml
```

#### Access a particular pod like `docker exec`
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/simple-pod.yaml
$ kubectl exec -it <pod-name> -- <command>
$ kubectl exec -it my-pod -- bash
```

#### Access a particular container of a multi-container pod like `docker exec`
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/pod/multi-container-pod.yaml
$ kubectl exec -it <pod-name> -c <container-name> -- <command>
$ kubectl exec -it my-pod -c nginx -- bash
$ kubectl exec -it my-pod -c nginx-helper -- bash
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/pod/multi-container-pod.yaml
```

