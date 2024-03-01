package com.spring.dockerize.jobservice.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class JobServiceRestController {

    @GetMapping("/all")
    public List<String> getAllJobs() {
        return Collections.emptyList();
    }

    @GetMapping("/search")
    public String searchJob() {
        return "Collections";
    }

    @PostMapping("/")
    public String createJobs() {
        return "";
    }

}
