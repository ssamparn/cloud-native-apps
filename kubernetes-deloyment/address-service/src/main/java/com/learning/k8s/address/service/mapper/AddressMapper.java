package com.learning.k8s.address.service.mapper;

import com.learning.k8s.address.service.entity.AddressEntity;
import com.learning.k8s.address.service.web.response.AddressResponse;
import com.learning.k8s.address.service.web.response.EmployeeId;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressResponse toModel(AddressEntity entity) {
        return AddressResponse.create(
                entity.getId(),
                entity.getHouseNumber(),
                entity.getStreetName(),
                entity.getCity(),
                entity.getState(),
                EmployeeId.create(
                        entity.getEmployeeId()
                )
        );
    }
}