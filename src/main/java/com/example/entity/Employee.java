package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {
    @Id
    Integer employeeId;
    String name;
    String email;
    Integer department_id;
    LocalDate hire_date;
    Integer salary;
    String location;
    String role;
}
