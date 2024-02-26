## Docker Commands Cheatsheet

#### Remove unused data
```bash
$ docker system prune [--all] [--filter] [--force] [--volumes]
```

#### To show the list of images you have in your machine
```bash
$ docker images
```

#### To create a container of the image
```bash
$ docker run <image-name>
```

#### To pull the image from Dockerhub
```bash
$ docker pull <image-name>
```

#### To run hello-world image from Dockerhub
```bash
$ docker pull hello-world
$ docker run hello-world
```
> Docker run does a pull also before running the downloaded image.

#### To run ubuntu image from Dockerhub
```bash
$ docker pull ubuntu
$ docker run ubuntu
```

#### To delete image
```bash
$ docker rmi <image-name>
```

#### To show a list of running container(s)
```bash
$ docker ps
```

#### To show all container(s) including the exited ones
```bash
$ docker ps -a
```

#### To pass the command to be executed inside the container
```bash
$ docker run <image-name> <command-to-be-executed>
$ docker run ubuntu date
$ docker run ubuntu ls
```
> Passing a command into the container depends on the image.


#### To start a container in interactive mode
```bash
$ docker run -it <image-name>
$ docker run -it ubuntu
```
> `i` stands for `standard-input` & `t` stands for `standard-output` 
> Running containers in interactive mode is almost doing ssh into the running container.

#### To assign a name for your container
```bash
$ docker run -name=<name-you-want-to-give> <image-name>
$ docker run -name=sam-ubuntu ubuntu
```

#### To start a container (in interactive mode)
```bash
$ docker start <container-name>
$ docker start -ia <container-name>
```

#### To stop a running container
```bash
$ docker stop <container-name/id>
```
> Stopping a running container might take some time.

#### To kill a running container.
```bash
$ docker kill <container-name/id>
```
> Killing a running container will stop the container at once with a `SIGKILL`

#### To remove a container
```bash
$ docker rm <container-name/id>
```

#### To start/execute a command in a running container (in interactive mode)
```bash
$ docker exec <container-name/id> <command>
$ docker exec -it <container-name/id> <command>
$ docker exec -it obu bash
```
> `docker run` creates new container and executes the command, but `docker exec` does the similar thing on a running container.


### Docker Image Name Format: 
<sub>`[repository-host:port]/[username]/[image-name][:tag]`</sub>

+ Default for `repository-host` is `docker.io`. 
+ Default for `username` is `library`. 
+ Default for `tag` is `latest`

### Docker Port Mapping: Map Host Port to a Container Port

#### To map the host port to a container port
```bash
$ docker run -p <host-port>:<container-port> <image-name>
$ docker run -p <host-port>:<container-port> <image-name>
$ docker run -p 8080:80 nginx
$ docker run -p 8090:80 nginx
```

#### To map multiple host ports to multiple container ports
```bash
$ docker run -p <host-port1>:<container-port1> -p <host-port2>:<container-port2> <image-name>
$ docker run -p <host-port>:<container-port> <image-name>
```





