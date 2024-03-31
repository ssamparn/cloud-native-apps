package com.spring.dockerize.candidateservice.generic;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class BaseTest {

    private static final int MONGO_DB_PORT = 27017;
    private static final int MOCK_SERVER_PORT = 1080;
    private static final String MONGO_INIT_JS = "/docker-entrypoint-initdb.d/init.js";
    private static final String MOCK_INIT_JSON = "/config/init.json";

    private static final String MONGO_URI_FORMAT = "mongodb://candidate_user:candidate_password@%s:%s/candidate";
    private static final String MOCK_SERVER_URI_FORMAT = "http://%s:%s/jobs/";


    @Container
    private static final GenericContainer mongoContainer = new GenericContainer(DockerImageName.parse("mongo"))
            .withExposedPorts(MONGO_DB_PORT)
            .withClasspathResourceMapping("data/candidate-init.js", MONGO_INIT_JS, BindMode.READ_ONLY)
            .waitingFor(Wait.forListeningPort());

    @Container
    private static final GenericContainer mockServerContainer = new GenericContainer(DockerImageName.parse("mockserver/mockserver"))
            .withExposedPorts(MOCK_SERVER_PORT)
            .withClasspathResourceMapping("data/job-init.json", MOCK_INIT_JSON, BindMode.READ_ONLY)
            .waitingFor(Wait.forListeningPort());

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mongoContainer.start();
        mockServerContainer.start();
        String mongoUri = String.format(MONGO_URI_FORMAT, mongoContainer.getHost(), mongoContainer.getMappedPort(MONGO_DB_PORT));
        String mockServerUri = String.format(MOCK_SERVER_URI_FORMAT, mockServerContainer.getHost(), mockServerContainer.getMappedPort(MOCK_SERVER_PORT));
        registry.add("spring.data.mongodb.uri", () -> mongoUri);
        registry.add("job.service.url", () -> mockServerUri);
    }
}