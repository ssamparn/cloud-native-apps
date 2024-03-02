package com.spring.dockerize.jobservice.web.controller;

import com.spring.dockerize.jobservice.dto.JobDto;
import com.spring.dockerize.jobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobServiceRestController {

    private final JobService jobService;

    @GetMapping("/all")
    public Flux<JobDto> getAllJobs() {
        return this.jobService.allJobs();
    }

    @GetMapping("/search")
    public Flux<JobDto> searchJob(@RequestParam Set<String> skills) {
        return this.jobService.jobsBySkillsIn(skills);
    }

    @PostMapping("/")
    public Mono<JobDto> createJob(@RequestBody Mono<JobDto> jobMono) {
        return this.jobService.saveJobs(jobMono);
    }

}
