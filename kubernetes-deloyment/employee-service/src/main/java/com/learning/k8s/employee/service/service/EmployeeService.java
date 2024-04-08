package com.learning.k8s.employee.service.service;

import com.learning.k8s.employee.service.mapper.EmployeeMapper;
import com.learning.k8s.employee.service.repository.EmployeeRepository;
import com.learning.k8s.employee.service.web.response.AddressResponse;
import com.learning.k8s.employee.service.web.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WebClient restClient;
    private final EmployeeMapper employeeMapper;

    public Mono<EmployeeResponse> getEmployeeById(int employeeId) {
        Mono<EmployeeResponse> employeeMono = employeeRepository
                .findById(employeeId)
                .map(employeeMapper::toModel);

        Mono<AddressResponse> addressResponseMono = restClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/{employeeId}").build(employeeId))
                .retrieve()
                .bodyToMono(AddressResponse.class);

        return Mono.zip(employeeMono, addressResponseMono, (employeeResponse, addressResponse) -> {
            employeeResponse.setAddress(addressResponse);
            return employeeResponse;
        });
    }
}