package com.learning.k8s.employee.service.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class AddressResponse {
    private int id;
    private int houseNumber;
    private String streetName;
    private String city;
    private String state;
    private EmployeeId employeeId;
}