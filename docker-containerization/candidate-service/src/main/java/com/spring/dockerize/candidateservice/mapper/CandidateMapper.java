package com.spring.dockerize.candidateservice.mapper;

import com.spring.dockerize.candidateservice.dto.CandidateDto;
import com.spring.dockerize.candidateservice.entity.Candidate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    public CandidateDto toDto(Candidate entity) {
        CandidateDto dto = new CandidateDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Candidate toEntity(CandidateDto dto) {
        Candidate entity = new Candidate();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

}
