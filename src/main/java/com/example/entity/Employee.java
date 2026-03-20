package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    Integer employee_id;
    String first_name;
    String last_name;
    String email;
    Integer department_id;
    LocalDate hire_date;
    Integer salary;
    String location;
    String role;
}
