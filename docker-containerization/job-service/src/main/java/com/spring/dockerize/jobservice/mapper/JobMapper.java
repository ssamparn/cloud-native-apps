package com.spring.dockerize.jobservice.mapper;

import com.spring.dockerize.jobservice.dto.JobDto;
import com.spring.dockerize.jobservice.entity.Job;
import com.spring.dockerize.jobservice.util.AppUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobDto toDto(Job entity) {
        JobDto dto = new JobDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setHostName(AppUtil.getHostName());
        return dto;
    }

    public Job toEntity(JobDto dto) {
        Job entity = new Job();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
