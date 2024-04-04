### GKE k8s Cluster Details

> Cluster Name: k8s-cluster
> Location Type: Zonal
> Zone: us-central1-c

##### Connect to GKE k8s Cluster
```bash
$ gcloud container clusters get-credentials k8s-cluster --zone us-central1-c --project spring-microservices-350123
$ kubectl get nodes
$ kubectl top nodes
```

> Open cloud shell editor and upload all the k8s yaml resources.
> Create directory `k8s-practice` and upload all the yaml files which are present in gke directory into it.
```bash
$ kubectl apply -f k8s-practice/.
```