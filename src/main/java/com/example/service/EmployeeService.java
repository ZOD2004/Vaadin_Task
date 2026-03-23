package com.example.service;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private final RestClient restClient;

    public EmployeeService(@Value("${api.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public List<Employee> fetchAllEmployee() {
        return restClient.get()
                .uri("employee")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Employee>>() {});
    }

    public List<Employee> fetch(int currentPage, int pageSize, String filter) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        if(filter == null || filter.isBlank()){
            return employeeRepository.findAll(pageable).getContent();
        } else {
            return employeeRepository.findByNameContainingIgnoreCase(filter, pageable).getContent();
        }
    }

    public int count(String filter) {
        if(filter == null || filter.isBlank()){
            return (int)employeeRepository.count();
        }
        else{
            return employeeRepository.countByNameContainingIgnoreCase(filter);
        }
    }


    public Page<Employee> pagedEmployeeList(int currentPage, int pageSize,String filter) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        if (filter == null || filter.isBlank()) {
            return employeeRepository.findAll(pageable);
        } else {
            return employeeRepository.findByNameContainingIgnoreCase(filter, pageable);
        }
    }
}