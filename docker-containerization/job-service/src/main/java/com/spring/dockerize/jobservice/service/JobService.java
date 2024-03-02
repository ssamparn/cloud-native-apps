package com.spring.dockerize.jobservice.service;

import com.spring.dockerize.jobservice.dto.JobDto;
import com.spring.dockerize.jobservice.mapper.JobMapper;
import com.spring.dockerize.jobservice.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public Flux<JobDto> allJobs() {
        return this.jobRepository.findAll()
                .map(jobMapper::toDto);
    }

    public Flux<JobDto> jobsBySkillsIn(Set<String> skills) {
        return this.jobRepository.findBySkillsIn(skills)
                .map(jobMapper::toDto);
    }

    public Mono<JobDto> saveJobs(Mono<JobDto> jobMono) {
        return jobMono
                .map(jobMapper::toEntity)
                .flatMap(jobRepository::save)
                .map(jobMapper::toDto);
    }
}
