package com.spring.dockerize.candidateservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "create")
public class TestService {
    private String name;
    private int port;
    private String hostPort;
    private String uri;
    private String hostPortEnvVariable;
}
