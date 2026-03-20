package com.example.service;

import com.example.entity.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class EmployeeService {

    private final RestClient restClient;

    public EmployeeService(@Value("${api.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public List<Employee> fetchAllProducts() {
        return restClient.get()
                .uri("employee")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Employee>>() {});
    }
}