package com.spring.dockerize.candidateservice.generic;

import com.spring.dockerize.candidateservice.model.TestService;
import org.mockserver.client.MockServerClient;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class BaseTest {

    private static final String MONGO_INIT_JS = "/docker-entrypoint-initdb.d/init.js";

    private static final TestService MONGO_DB_SERVICE = TestService.create(
            "mongodb", 27017, "0", "mongodb://root:rootpassword@%s:%s/candidate", "MONGO_DB_PORT"
    );

    private static final TestService JOB_MOCK_SERVICE = TestService.create(
            "job-mock-service", 1080, "0", "http://%s:%s/jobs/", "MOCK_SERVER_PORT"
    );

    @Container
    private static final GenericContainer mongoContainer = new GenericContainer(DockerImageName.parse("mongo"))
            .withExposedPorts(MONGO_DB_SERVICE.getPort())
            .withClasspathResourceMapping("data/candidate-init.js", MONGO_INIT_JS, BindMode.READ_ONLY)
            .waitingFor(Wait.forListeningPort());

    @Container
    private static final MockServerContainer mockServerContainer = new MockServerContainer(DockerImageName.parse("mockserver/mockserver"));

    public static MockServerClient mockServerClient;

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mongoContainer.start();
        String mongoUri = String.format(MONGO_DB_SERVICE.getUri(), mongoContainer.getHost(), mongoContainer.getMappedPort(MONGO_DB_SERVICE.getPort()));
        String mockServerUri = String.format(JOB_MOCK_SERVICE.getUri(), mockServerContainer.getHost(), mockServerContainer.getMappedPort(JOB_MOCK_SERVICE.getPort()));

        mockServerClient = new MockServerClient(mockServerContainer.getHost(), mockServerContainer.getServerPort());

        registry.add("spring.data.mongodb.uri", () -> mongoUri);
        registry.add("job.service.url", () -> mockServerUri);
    }
}