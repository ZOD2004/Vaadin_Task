package com.example.ui;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import java.util.List;

@Route("employees")
@PageTitle("EmployeeGrid")
public class EmployeeGrid extends VerticalLayout {

    EmployeeService employeeService;
    Grid<Employee>grid = new Grid<>();

    public EmployeeGrid(EmployeeService employeeService){
        this.employeeService=employeeService;
        this.setSizeFull();

        createEmployeeGrid();

        add(grid);

    }

    private void createEmployeeGrid() {
        grid.setSizeFull();
        grid.setHeightFull();
        grid.addColumn(emp -> emp.getFirst_name()+" "+emp.getLast_name())
                .setHeader("Name")
                .setSortProperty("name");
        grid.addColumn(Employee::getEmail)
                .setHeader("Email");
        grid.addColumn(Employee::getHire_date)
                .setHeader("Hire Date");
        grid.addColumn(Employee::getLocation)
                .setHeader("Location")
                .setSortProperty("location");
        grid.addColumn(Employee::getRole)
                .setHeader("Role")
                .setSortProperty("role");

//        grid.setItemsPageable(
//                pageable -> employeeService.fetchAllProducts()
//        );
        List<Employee> page = employeeService.fetchAllProducts();
        grid.setItems(page);
    }
}
