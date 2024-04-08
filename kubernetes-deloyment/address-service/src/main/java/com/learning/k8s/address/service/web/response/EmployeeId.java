package com.learning.k8s.address.service.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "create")
public class EmployeeId {
    private int id;
}