package com.learning.k8s.address.service.service;

import com.learning.k8s.address.service.entity.AddressEntity;
import com.learning.k8s.address.service.mapper.AddressMapper;
import com.learning.k8s.address.service.repository.AddressRepository;
import com.learning.k8s.address.service.web.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressResponse findAddressByEmployeeId(int employeeId) {
        AddressEntity addressEntity = null;
        Optional<AddressEntity> address = addressRepository.findByEmployeeId(employeeId);
        if (address.isPresent()) {
            addressEntity = address.get();
        }
        return addressMapper.toModel(addressEntity);
    }
}
