## Kubernetes Configuration: Commands Cheatsheet

> Prerequisite: Before creating a k8s configuration, we assume a k8s cluster is created already.

#### Fetch k8s configMaps `simple-configmap.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/configuration/simple-configmap.yaml
$ kubectl get configmaps
$ kubectl get cm
```

#### To see the contents (properties) of configMaps
```bash
$ kubectl describe cm <config-map-metadata-name>
$ kubectl describe cm application-properties
```

#### To see the logs of the pod
```bash
$ kubectl logs pod/my-pod
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/configuration/simple-configmap.yaml
```

#### Inject configMap as environment variable `inject-configmap-as-env.yaml`
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/configuration/inject-configmap-as-env.yaml
$ kubectl get configmaps
$ kubectl describe cm application-properties
$ kubectl logs pod/my-pod
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/configuration/inject-configmap-as-env.yaml
```

#### Inject configMap as file `inject-configmap-as-file.yaml`
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/configuration/inject-configmap-as-file.yaml
$ kubectl get cm
$ kubectl get cm kube-root-ca.crt -o yaml
$ kubectl get cm application-properties -o yaml
$ kubectl exec -it pod/my-pod -- bash
$ cd usr/share/props/
$ ls
$ cat base64.crt
$ exit
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/configuration/inject-configmap-as-file.yaml
```

#### Create k8s secrets
```bash
$ kubectl create secret generic app-secret --from-literal=username=sassaman --from-literal=password=password
$ kubectl get secrets
$ kubectl get secrets -o yaml
$ kubectl delete secret app-secret
```

#### Fetch k8s secrets
```bash
$ kubectl get secrets
```

#### Create k8s secrets `simple-secret.yaml`
```bash
$ echo -n sassaman | base64
$ echo -n password | base64
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/configuration/simple-secret.yaml
$ kubectl get secrets -o yaml
$ kubectl logs pod/my-pod
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/configuration/simple-secret.yaml
```

#### Inject k8s secrets as environment variable `inject-secret-as-env.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/configuration/inject-secret-as-env.yaml
$ kubectl get secrets -o yaml
$ kubectl logs pod/my-pod
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/configuration/inject-secret-as-env.yaml
```

#### Inject k8s secrets as file `inject-secret-as-file.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/configuration/inject-secret-as-file.yaml
$ kubectl exec -it my-pod -- bash
$ cd /usr/share/props
$ ls
$ cat private.key
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/configuration/inject-secret-as-file.yaml
```

#### Assignment
```bash
$ docker compose -f kubernetes-orchestration/kubernetes-resources/configuration/assignment/docker-compose.yaml up
$ docker compose -f kubernetes-orchestration/kubernetes-resources/configuration/assignment/docker-compose.yaml down
$ echo -n root | base64
$ echo -n rootpassword | base64
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/configuration/solution/.
$ kubectl port-forward service/mongoexpress-service 8081:8081
$ curl http://localhost:8081/
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/configuration/solution/.
```

> If you want to decode a base64 string run `$ echo "base64 String" | base64 --decode`
> e.g: echo "ZGV2b3BzY3ViZQo=" | base64 --decode