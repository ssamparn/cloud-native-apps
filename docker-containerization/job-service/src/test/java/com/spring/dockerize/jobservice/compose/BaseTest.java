package com.spring.dockerize.jobservice.compose;

import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public abstract class BaseTest {

    private static final int MONGO_PORT = 27017;
    private static final String MONGO = "mongodb";
    private static final String MONGO_URI_FORMAT = "mongodb://job_user:job_password@%s:%s/job";

    @ClassRule
    public static final DockerComposeContainer<?> docker_compose_container = new DockerComposeContainer<>(new File("docker-compose.yml"));

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        docker_compose_container
                .withEnv("HOST_PORT", "0")
                .withExposedService(MONGO, MONGO_PORT, Wait.forListeningPort())
                .start();

        String host = docker_compose_container.getServiceHost(MONGO, MONGO_PORT);
        int port = docker_compose_container.getServicePort(MONGO, MONGO_PORT);

        registry.add("spring.data.mongodb.uri", () -> String.format(MONGO_URI_FORMAT, host, port));
    }
}
