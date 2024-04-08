package com.learning.k8s.employee.service.mapper;

import com.learning.k8s.employee.service.document.Employee;
import com.learning.k8s.employee.service.web.response.EmployeeResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class EmployeeMapper {

    public EmployeeResponse toModel(Employee employee) {
        return EmployeeResponse.create(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getAge(),
                null
        );
    }

    public Flux<EmployeeResponse> toModel(Flux<Employee> employeeFlux) {
        return employeeFlux.map(this::toModel);
    }
}
