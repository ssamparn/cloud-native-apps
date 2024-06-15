package com.spring.dockerize.jobservice;

import com.spring.dockerize.jobservice.dto.JobDto;
import com.spring.dockerize.jobservice.generic.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
// Both generic and compose version of BaseTest works fine
public class JobServiceIT extends BaseTest {

	@Autowired
	private WebTestClient client;

	@Test
	void getAllJobsTest() {
		this.client.get()
			.uri("/jobs/all")
			.exchange()
			.expectStatus().is2xxSuccessful()
			.expectBody()
			.consumeWith(e -> System.out.println(new String(e.getResponseBody())))
			.jsonPath("$").isNotEmpty();
	}

	@Test
	public void searchJobBySkillsTest() {
		this.client.get()
				.uri(uriBuilder -> uriBuilder
						.path("/jobs/search")
						.queryParam("skills", "project")
						.build()
				)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.consumeWith(e -> System.out.println(new String(e.getResponseBody())))
				.jsonPath("$").isNotEmpty();
	}

	@Test
	public void postJobTest() {
		JobDto jobPayload = JobDto.create(null, "k8s engineer", "google", Set.of("k8s"), 200000, true,  null);

		this.client.post()
				.uri("/jobs/")
				.bodyValue(jobPayload)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.description").isEqualTo("k8s engineer");
	}
}