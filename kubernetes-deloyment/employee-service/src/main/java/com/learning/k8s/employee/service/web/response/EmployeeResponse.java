package com.learning.k8s.employee.service.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class EmployeeResponse {
    private int employeeId;
    private String name;
    private String email;
    private String age;
    private AddressResponse address;
}
