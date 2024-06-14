## Docker Commands Cheatsheet

#### Remove unused data
```bash
$ docker system prune [--all] [--filter] [--force] [--volumes]
$ docker system prune -af
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
$ docker images
$ docker run hello-world
```
> Docker run does a pull also before running the downloaded image.

#### To run ubuntu image from Dockerhub
```bash
$ docker pull ubuntu
$ docker images
$ docker run ubuntu
$ docker ps -a
$ docker run ubuntu ls
$ docker run ubuntu date
$ docker run -it ubuntu bash
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
$ ls
$ mkdir sashank
$ ls
$ cd sashank/
$ pwd
$ echo "docker is awesome" > dummy.txt
$ ls -al
$ cat dummy.txt
$ exit
```
> `i` stands for `standard-input` & `t` stands for `standard-output` 
> Running containers in interactive mode is almost doing ssh into the running container.

#### To start a container in detached mode
```bash
$ docker run -d <image-name>
$ docker run -d -p 7079:80 nginx
```

#### To assign a name for your container
```bash
$ docker run -it --name=<name-you-want-to-give> <image-name>
$ docker run -it --name=sam-ubuntu ubuntu
```

#### To start a container (in interactive mode)
```bash
$ docker start <container-name/id>
$ docker start -ia <container-name/id>
$ cd sashank/
$ cat dummy.txt
$ exit
$ docker system prune -f
```

#### To stop a running container
```bash
$ docker run -it --name=sam-ubuntu ubuntu
$ docker ps
$ docker stop <container-name/id>
$ docker stop sam-ubuntu
```
> Stopping a running container might take some time.

#### To kill a running container.
```bash
$ docker kill <container-name/id>
$ docker kill sam-ubuntu
```
> Killing a running container will stop the container at once with a `SIGKILL`

#### To remove a container
```bash
$ docker rm <container-name/id>
```

#### To start/execute a command in a running container (in interactive mode)
```bash
# in one terminal
$ docker run -it --name=sam-ubuntu ubuntu
$ docker exec -it <container-name/id> <command>
$ docker exec -it <container-name/id> <command>

# in another terminal
$ docker exec -it sam-ubuntu bash
# or
$ docker exec sam-ubuntu date
# or
$ docker exec sam-ubuntu ls
```
> `docker run` creates new container and executes the command, but `docker exec` does the similar thing on a running container.


### Docker Image Name Format: 
<sub>`[repository-host:port]/[username]/[image-name][:tag]`</sub>

+ Default for `repository-host` is `docker.io`. 
+ Default for `username` is `library`. 
+ Default for `tag` is `latest`

```bash
$ docker system prune -f
$ docker pull hello-world
```

### Docker Port Mapping: Map Host Port to a Container Port

#### Create nginx container
```bash
$ docker system prune -f
$ docker images
$ docker pull nginx
$ docker images
# ubuntu container will exit immediately, as ubuntu is an operating system
$ docker run ubuntu
# however, nginx container will not exit immediately, as it is a server. It's job is to run indefinitely. 
$ docker run nginx
# curl in host without accessing nginx container. Should not work.
$ curl http://localhost
$ docker exec -it <container-name/id> bash
# curl in nginx container after accessing it. It should work
$ curl http://localhost
```

#### To map the host port to a container port
```bash
$ docker run -p <host-port>:<container-port> <image-name>
$ docker run -p 8080:80 nginx
$ curl http://localhost:8080
$ docker run -p 8090:80 nginx
$ curl http://localhost:8090
```

#### To map the host port to a container port in detached mode
```bash
$ docker run -d -p <host-port>:<container-port> <image-name>
$ docker run -d -p 7070:80 nginx
$ curl http://localhost:7070
```

#### To map multiple host ports to multiple container ports
```bash
$ docker run -p <host-port1>:<container-port1> -p <host-port2>:<container-port2> <image-name>
$ docker run -p <host-port>:<container-port> <image-name>
```

#### To access container logs
```bash
$ docker logs <container-name/id>
```

#### Docker volume mapping: To map a specific directory to a container directory.
```bash
$ docker run -v /host-path:/container-path <image-name>
$ docker run -it -v $PWD:/sam/dir/ ubuntu
```
> Provide absolute paths for host and container. `PWD` should be in capital.
> Here we are mounting the current directory ($PWD) to /sam/dir/ directory.

#### Docker volume mapping for multiple paths
```bash
$ docker run 
-v /host-path-1:/container-path-1 
-v /host-path-2:/container-path-2
<image-name>
```

#### Docker volume mapping: nginx
```bash
$ docker run --name <some-name> -v /some/content:/usr/share/nginx/html:ro -d nginx
$ docker run -p 8080:80 --name sam-server -v $PWD/docker-containerization/docker-resources/index.html:/usr/share/nginx/html/index.html -d nginx
$ curl http://localhost:8080
```
> Here we are mounting the current directory ($PWD) to /usr/share/nginx/html directory.

#### Docker mounting a single file instead of an entire directory
```bash
$ docker run --name <some-name> -v /some/content/filename:/usr/share/nginx/html/filename -d nginx
$ docker run -p 8080:80 --name sam-server -v $PWD/index.html:/usr/share/nginx/html/index.html -d nginx
```

#### Docker mounting a directory or file in read-only mode
```bash
$ docker run --name <some-name> -v /some/content/filename-or-directory:/usr/share/nginx/html/filename-or-directory:ro -d nginx
$ docker run -p 8080:80 --name sam-server -v $PWD/index.html:/usr/share/nginx/html/index.html:ro -d nginx
```

## Docker Network

### Docker Network Drivers
+ **Bridge**
    - Default
    - Custom / User Defined
+ **None** (Disable all the network)
+ **Host** (Works only on Linux Machines. Does not work on Mac or Windows)
+ **Overlay** (Using this one container in one machine can communicate with another container in a separate machine. Used in docker swarm. We will cover it as part of Kubernetes)

#### Create a Docker Network
```bash
$ docker network create <network-name>
$ docker network create dummy
```
> This will create a custom network called dummy

#### To list all the networks
```bash
$ docker network ls
```

#### Create a Docker Container inside a particular network
```bash
$ docker run --name=<container-name> --network=<network-name> <image-name>
$ docker run --name=sam-nginx --network=dummy nginx
```

## Docker File: Commands

| Command                                                                                       | Description                                                                                                      |   
|-----------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| FROM`image-name`                                                                              | The base image for your docker image. Any image should extend another image                                      |
| ADD`host-dir` `container-dir` <br/>COPY`host-dir` `container-dir`                             | Adds files from  host directory to the image.<br/> `COPY` is more or less like `ADD`. `ADD` can also accept url. | 
| RUN`command`                                                                                  | Command to execute during the image build process. Useful to install any software or create directories etc.     |
| ENV`key` `value`                                                                              | Sets an environment variable                                                                                     |
| WORKDIR`path`                                                                                 | Creates a workspace in /default working directory inside the image. If we ignore /root directory  would be used. |
| EXPOSE`port`                                                                                  | Exposes port                                                                                                     |
| CMD`command to execute when container starts`<br/>ENTRYPOINT`command to execute when container starts` | The command to be executed/process to be started when the container is created                                   |

### Docker Image Creation

#### Create a docker image from a Dockerfile
```bash
$ docker build . -t <image-name-you-want-to-provide> -f <directory/sub-directory/Dockerfile>
$ docker build . -t ubuntu-helloworld-image -f docker-containerization/docker-files/helloworld-dockerfile
```
#### Run the newly created docker image
```bash
$ docker images
$ docker run -it <image-name>
$ docker run -it ubuntu-helloworld-image
$ docker run -it ubuntu-helloworld-image bash
$ ls
$ cat welcome.txt
$ exit
```

#### Install Java in an Ubuntu image
```bash
$ docker build . -t <image-name-you-want-to-provide> -f <directory/sub-directory/Dockerfile>
$ docker build . -t ubuntu-java-image -f docker-containerization/docker-files/java-install-dockerfile
$ docker run -it ubuntu-java-image bash
$ ls
$ java -version
```

#### Run a Java program in an Ubuntu image
```bash
$ docker build . -t java-table-image -f docker-containerization/docker-files/java-table-program-dockerfile
$ docker run -it java-table-image bash
```

#### Pass an environment variable in to the docker container
```bash
$ docker run -e <ENV-VAR-NAME>=<ENV-VAL> <image-name>
$ docker run -e input=13 java-table-image
```

#### Docker Command in Shell Form Vs Exec Form:
> While writing docker file we can pass command in either shell form or exec form.
```bash
// shell form
CMD cat hello-world.txt

// exec form
CMD ["cat", "hello-world.txt"]
```

#### To create tag/alias for the docker image
```bash
$ docker tag <image-name> <user-name/image-name>:<tag>
$ docker tag ubuntu-java-image ssamantr/ubuntu-java-image:latest
```

#### To push docker image
```bash
$ docker push <image-name>
$ docker push ssamantr/ubuntu-java-image:v1
```