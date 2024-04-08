package com.learning.k8s.employee.service.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Employee {

    @Id
    private Integer id;
    private String name;
    private String email;
    private String age;
}
