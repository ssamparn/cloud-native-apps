package com.spring.dockerize.candidateservice.web.controller;

import com.spring.dockerize.candidateservice.dto.CandidateDetailsDto;
import com.spring.dockerize.candidateservice.dto.CandidateDto;
import com.spring.dockerize.candidateservice.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateServiceRestController {

    private final CandidateService candidateService;

    @GetMapping("/all")
    public Flux<CandidateDto> getAllCandidates() {
        return this.candidateService.allCandidates();
    }

    @GetMapping("/{candidateId}")
    public Mono<CandidateDetailsDto> getCandidate(@PathVariable String candidateId) {
        return this.candidateService.getCandidate(candidateId);
    }

    @PostMapping("/")
    public Mono<CandidateDto> createCandidate(@RequestBody Mono<CandidateDto> candidateDtoMono) {
        return this.candidateService.saveCandidate(candidateDtoMono);
    }

}
