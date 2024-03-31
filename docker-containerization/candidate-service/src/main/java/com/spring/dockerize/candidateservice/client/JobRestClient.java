package com.spring.dockerize.candidateservice.client;

import com.spring.dockerize.candidateservice.dto.JobDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class JobRestClient {

    private final WebClient client;

    public JobRestClient(@Value("${job.service.url}") String baseUrl) {
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<List<JobDto>> getRecommendedJobs(Set<String> skills){
        return this.client.get()
                .uri(u -> u.path("search").queryParam("skills", skills).build())
                .retrieve()
                .bodyToFlux(JobDto.class)
                .collectList()
                .onErrorReturn(Collections.emptyList());
    }
}