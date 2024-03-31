package com.spring.dockerize.candidateservice.service;

import com.spring.dockerize.candidateservice.client.JobRestClient;
import com.spring.dockerize.candidateservice.dto.CandidateDetailsDto;
import com.spring.dockerize.candidateservice.dto.CandidateDto;
import com.spring.dockerize.candidateservice.mapper.CandidateMapper;
import com.spring.dockerize.candidateservice.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;
    private final JobRestClient jobRestClient;

    public Flux<CandidateDto> allCandidates() {
        return this.candidateRepository.findAll()
                .map(candidateMapper::toDto);
    }

    public Mono<CandidateDetailsDto> getCandidate(String candidateId) {
        return this.candidateRepository.findById(candidateId)
                .map(candidateMapper::toDetailsDto)
                .flatMap(this::addRecommendedjobs);
    }

    private Mono<CandidateDetailsDto> addRecommendedjobs(CandidateDetailsDto dto) {
        return this.jobRestClient.getRecommendedJobs(dto.getSkills())
                .doOnNext(dto::setRecommendedJobs)
                .thenReturn(dto);
    }

    public Mono<CandidateDto> saveCandidate(Mono<CandidateDto> candidateMono) {
        return candidateMono
                .map(candidateMapper::toEntity)
                .flatMap(candidateRepository::save)
                .map(candidateMapper::toDto);
    }
}
