package com.spring.dockerize.candidateservice;

import com.spring.dockerize.candidateservice.generic.BaseTest;
import com.spring.dockerize.candidateservice.dto.CandidateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@SpringBootTest
@AutoConfigureWebTestClient
public class CandidateMongoServiceGenericIT extends BaseTest {

    @Autowired
    private WebTestClient testClient;

    @BeforeEach
    void setUp() {
        mockServerClient.reset();
    }

    @Test
    void allCandidatesTest() {
        this.testClient.get()
                .uri("/candidates/all")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$").isNotEmpty();
    }

    @Test
    void getCandidateByIdTest() {
        mockServerClient.when(request()
                .withMethod("GET")
                .withPath("/jobs/search")
                .withQueryStringParameter("skills", "java", "spring"))
                .respond(response().withStatusCode(200)
                        .withBody(json(
                                """
                                 [
                                     {
                                         "id" : "mock-id-1",
                                         "description": "mocked-job-desc-1",
                                         "company": "mock",
                                         "skills": [ "skill-1", "skill-2" ],
                                         "salary": 10000,
                                         "isRemote": false
                                     },
                                     {
                                         "id" : "mock-id-2",
                                         "description": "mocked-job-desc-2",
                                         "company": "mock",
                                         "skills": [ "skill-3", "skill-4" ],
                                         "salary": 50000,
                                         "isRemote": true
                                     }
                                 ]
                                  """
                        )));

        this.testClient.get()
                .uri("/candidates/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("sam")
                .jsonPath("$.skills.size()").isEqualTo(2)
                .jsonPath("$.recommendedJobs.size()").isEqualTo(2);
    }

    @Test
    void postCandidateTest(){
        CandidateDto dto = CandidateDto.create(null, "dr.dre", Set.of("k8s"), null);

        this.testClient.post()
                .uri("/candidates/")
                .bodyValue(dto)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("dr.dre");
    }

    @Test
    void jobServiceReturns4xx() {
        this.testClient.get()
                .uri("/candidates/2")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.name").isEqualTo("jake")
                .jsonPath("$.skills.size()").isEqualTo(1)
                .jsonPath("$.recommendedJobs").isEmpty();
    }

    @Test
    void jobServiceReturns5xx() {
        this.testClient.get()
                .uri("/candidates/3")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.name").isEqualTo("mike")
                .jsonPath("$.skills.size()").isEqualTo(1)
                .jsonPath("$.recommendedJobs").isEmpty();
    }

}
