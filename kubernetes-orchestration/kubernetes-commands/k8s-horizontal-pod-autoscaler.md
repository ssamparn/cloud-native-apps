## Kubernetes Horizontal POD Autoscaler: Commands Cheatsheet
  
> Prerequisite: Before configuring k8s pod autoscaling, we assume k8s cluster is created.

#### Introducton to `top` command:
> `top` command will show the processes running in a machine. 
> e.g: CPU and Memory utilization  
```bash
$ docker run -it ubuntu
$ top
```

#### Display resource (CPU / Memory) usage of k8s workloads
```bash
$ kubectl top pod
$ kubectl top node
```

#### Install metrics server
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/hpa/resources/metrics-server.yaml
$ kubectl get ns
$ kubectl get pod -n kube-system
$ kubectl top nodes
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/hpa/resources/metrics-server.yaml 
```
> Metric Server is not properly installed. Getting `error: Metrics API not available` 

#### View the cpu and memory usages of k8s deplyment `deployment-cpu-memory-usage.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/hpa/deployment-cpu-memory-usage.yaml
$ kubectl top pods
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/hpa/deployment-cpu-memory-usage.yaml
```

#### Create and Get horizontal pod autoscaler `horizontal-pod-autoscaler.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/hpa/horizontal-pod-autoscaler.yaml
$ kubectl get hpa
```

#### Horizontal Pod Autoscaling Demo `deployment-auto-scaling-demo.yaml` and `horizontal-pod-autoscaler.yaml`
```bash
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/hpa/deployment-auto-scaling-demo.yaml
$ kubectl apply -f kubernetes-orchestration/kubernetes-resources/hpa/horizontal-pod-autoscaler.yaml
$ kubectl exec -it pod/demo-pod -- bash
$ curl http://my-service/
$ ab
$ ab -n 40000 -c 5 http://my-service/
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/hpa/deployment-auto-scaling-demo.yaml
$ kubectl delete -f kubernetes-orchestration/kubernetes-resources/hpa/horizontal-pod-autoscaler.yaml
```
> `ab` is apache bench which is installed in demo-pod.
> Note: HPA Autoscaling is not working

