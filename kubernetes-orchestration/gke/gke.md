### GKE k8s Cluster Details

> Cluster Mode: standard (Default cluster mode is Auto Pilot in GKE)
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
# create directory
$ mkdir k8s-deployment

# Copy all the k8s files into this directory

$ kubectl apply -f k8s-deployment/.
$ kubectl delete -f k8s-deployment/.

# remove directory
$ rm -r k8s-deployment
```