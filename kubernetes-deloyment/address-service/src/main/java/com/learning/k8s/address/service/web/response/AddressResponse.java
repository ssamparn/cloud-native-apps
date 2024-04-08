package com.learning.k8s.address.service.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "create")
public class AddressResponse {
    private int id;
    private int houseNumber;
    private String streetName;
    private String city;
    private String state;
    private EmployeeId employeeId;
}