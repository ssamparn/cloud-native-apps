package com.learning.k8s.employee.service.web.router;

import com.learning.k8s.employee.service.service.handler.EmployeeServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class EmployeeServiceRouter {

    private final EmployeeServiceHandler employeeServiceHandler;

    @Bean
    public RouterFunction<ServerResponse> employeeRouter() {
        return route(GET("/employees/search-all"), employeeServiceHandler::searchAllEmployees)
                .andRoute(GET("/employees/get-all"), employeeServiceHandler::getAllEmployees);
    }

}
