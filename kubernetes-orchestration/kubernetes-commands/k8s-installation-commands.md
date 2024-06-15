## Kubernetes Installation: Commands Cheatsheet

> [kubectl installation](https://kubernetes.io/docs/tasks/tools/install-kubectl-macos/#install-with-homebrew-on-macos)

#### Install Kube Control (kubectl)
```bash
$ brew install kubectl
```
> During installation, Homebrew create a directory `kubectl` in `/usr/local/bin/kubectl`

#### Check the (k8s) client installation
```bash
$ kubectl version --client --output=json
$ kubectl version --client --output=yaml
```

> [kind installation](https://kind.sigs.k8s.io/docs/user/quick-start/#installing-with-a-package-manager)

#### Install Kind
```bash
$ brew install kind
```
#### Check the installation
```bash
$ kind version
```