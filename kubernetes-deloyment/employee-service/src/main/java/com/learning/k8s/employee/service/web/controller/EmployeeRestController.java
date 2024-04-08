package com.learning.k8s.employee.service.web.controller;

import com.learning.k8s.employee.service.service.EmployeeService;
import com.learning.k8s.employee.service.web.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/employees/{employeeId}")
    public Mono<EmployeeResponse> getEmployeeDetails(@PathVariable("employeeId") int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
}