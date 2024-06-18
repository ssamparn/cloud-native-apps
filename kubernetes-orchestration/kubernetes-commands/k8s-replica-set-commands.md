## Kubernetes ReplicaSet: Commands Cheatsheet

> Prerequisite: Before creating a k8s replicaSet, we assume k8s cluster is created.

#### Get all the k8s Resources:
```bash
$ kubectl get all
```

#### Get all the replicaset
```bash
$ kubectl get replicaset
$ kubectl get rs
```

#### Create a replica set
```bash
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/replica-set/simple-replica-set.yaml

# To demo how replica sets manages existing pods - lecture 49
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/replica-set/multiple-pods.yaml
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/replica-set/existing-pod-manager.yaml

# To demo multiple replica sets - lecture 50
$ kubectl create -f kubernetes-orchestration/kubernetes-resources/replica-set/multiple-replica-sets.yaml
```

#### Delete replica set
```bash
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/replica-set/simple-replica-set.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/replica-set/multiple-pods.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/replica-set/existing-pod-manager.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/replica-set/multiple-replica-sets.yaml
```

#### Describe a replica set
```bash
$ kubectl describe <kind-name>/<replica-set-name>
$ kubectl describe rs/my-rs-1
```