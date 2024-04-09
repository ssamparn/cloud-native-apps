package com.learning.k8s.employee.service.web.controller;

import com.learning.k8s.employee.service.service.EmployeeService;
import com.learning.k8s.employee.service.web.response.AppVersion;
import com.learning.k8s.employee.service.web.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/employees/version")
    public ResponseEntity<AppVersion> getVersion() {
        return new ResponseEntity<>(AppVersion.create("v1"), HttpStatus.OK);
    }
}