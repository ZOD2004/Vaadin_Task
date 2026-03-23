package com.example.repo;

import com.example.entity.Employee;
import com.example.entity.ProductCatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Integer countByNameContainingIgnoreCase(String filter);
}
