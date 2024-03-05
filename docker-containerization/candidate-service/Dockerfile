# Dockerfile has no use here as we are replying on Cloud Native Buildpack of Spring Boot

FROM openjdk:17
WORKDIR /tmp
COPY target/candidate-service.jar candidate-service.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar /candidate-service.jar"]