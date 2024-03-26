## Kubernetes Namespace: Commands Cheatsheet

> Prerequisite: Before creating a k8s namespace, we assume a k8s cluster is created already.

#### Get all the namespace(s)
```bash
$ kubectl get <kind>
$ kubectl get ns
$ kubectl get namespace
```
> Whatever resource or workloads we create, gets created in `default` namespace.
> Namespaces starting with the name `kube` is reserved for kubernetes.
> `local-path-storage` namespace is created and used by `kind` cluster

#### Creating a namespace
```bash
$ kubectl create ns <name-space-name>
$ kubectl create ns dev
$ kubectl create ns qa
```

#### Query against a particular namespace
```bash
$ kubectl get pod -n <name-space-name>
$ kubectl get deployments -n <name-space-name>
$ kubectl get services -n <name-space-name>

$ kubectl get pod -n dev
$ kubectl get deployments -n dev
$ kubectl get services -n dev

$ kubectl get pod -n qa
$ kubectl get deployments -n qa
$ kubectl get services -n qa
```
> Append `-n <name-space-name>` in a `kubectl` command to make it work against a namespace

#### Delete a particular namespace
```bash
$ kubectl delete ns <name-space-name>
$ kubectl delete ns dev
$ kubectl delete ns qa
```
> Deleting a namespace also deletes all the resources and workloads from within that namespace. 

#### Demo Kubernetes namespace `rolling-update-service.yaml`
```bash
$ kubectl create ns dev
$ kubectl create ns qa

> Run `version 1` of the application in `dev` namespace 
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/namespace/rolling-update-service.yaml -n dev

> Run `version 2` of the application in `qa` namespace
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/namespace/rolling-update-service.yaml -n qa

$ kubectl port-forward deployment.apps/order-service-deployment 8080:80 -n dev
> This will show `version 1` of the application

$ kubectl port-forward deployment.apps/order-service-deployment 8080:80 -n qa
> This will show `version 2` of the application
```