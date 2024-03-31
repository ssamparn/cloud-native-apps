package com.spring.dockerize.candidateservice.compose;

import com.spring.dockerize.candidateservice.model.TestService;
import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public abstract class BaseTest {
    private static final TestService MONGO_DB_SERVICE = TestService.create(
            "mongodb", 27017, "0", "mongodb://candidate_user:candidate_password@%s:%s/candidate", "MONGO_DB_PORT"
    );

    private static final TestService JOB_MOCK_SERVICE = TestService.create(
            "job-mock-service", 1080, "0", "http://%s:%s/jobs/", "MOCK_SERVER_PORT"
    );

    @ClassRule
    public static final DockerComposeContainer<?> docker_compose_container = new DockerComposeContainer<>(new File("docker-compose.yml"));

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        docker_compose_container
                .withEnv(MONGO_DB_SERVICE.getHostPortEnvVariable(), MONGO_DB_SERVICE.getHostPort())
                .withEnv(JOB_MOCK_SERVICE.getHostPortEnvVariable(), JOB_MOCK_SERVICE.getHostPort())
                .withExposedService(MONGO_DB_SERVICE.getName(), MONGO_DB_SERVICE.getPort(), Wait.forListeningPort())
                .withExposedService(JOB_MOCK_SERVICE.getName(), JOB_MOCK_SERVICE.getPort(), Wait.forHttp("/health").forStatusCode(200))
                .start();

        String mongoHost = docker_compose_container.getServiceHost(MONGO_DB_SERVICE.getName(), MONGO_DB_SERVICE.getPort());
        int mongoPort = docker_compose_container.getServicePort(MONGO_DB_SERVICE.getName(), MONGO_DB_SERVICE.getPort());

        String jobHost = docker_compose_container.getServiceHost(JOB_MOCK_SERVICE.getName(), JOB_MOCK_SERVICE.getPort());
        int jobPort = docker_compose_container.getServicePort(JOB_MOCK_SERVICE.getName(), JOB_MOCK_SERVICE.getPort());

        registry.add("spring.data.mongodb.uri", () -> String.format(MONGO_DB_SERVICE.getUri(), mongoHost, mongoPort));
        registry.add("job.service.url", () -> String.format(JOB_MOCK_SERVICE.getUri(), jobHost, jobPort));
    }
}
