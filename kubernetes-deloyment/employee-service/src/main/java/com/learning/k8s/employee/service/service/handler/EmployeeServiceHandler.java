package com.learning.k8s.employee.service.service.handler;

import com.learning.k8s.employee.service.document.Employee;
import com.learning.k8s.employee.service.mapper.EmployeeMapper;
import com.learning.k8s.employee.service.repository.EmployeeRepository;
import com.learning.k8s.employee.service.web.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class EmployeeServiceHandler {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public Mono<ServerResponse> searchAllEmployees(ServerRequest serverRequest) {
        Flux<Employee> employeeFlux = this.employeeRepository.findAll();
        Flux<EmployeeResponse> employeeResponseFlux = employeeMapper.toModel(employeeFlux);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeResponseFlux, EmployeeResponse.class);
    }

    public Mono<ServerResponse> getAllEmployees(ServerRequest serverRequest) {
        EmployeeResponse employeeResponse1 = EmployeeResponse.create(50882, "Sashank", "sashank1703@gmail.com", "32", null);
        EmployeeResponse employeeResponse2 = EmployeeResponse.create(50883, "Aparna", "aparna.samal7@gmail.com", "30", null);
        EmployeeResponse employeeResponse3 = EmployeeResponse.create(50884, "Monalisa", "monalisa.samantray11@gmail.com", "34", null);

        Flux<EmployeeResponse> employeeResponseFlux = Flux.fromIterable(Arrays.asList(employeeResponse1, employeeResponse2, employeeResponse3));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeResponseFlux, EmployeeResponse.class);
    }
}
