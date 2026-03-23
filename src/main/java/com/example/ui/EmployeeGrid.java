package com.example.ui;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.data.domain.Page;


import java.util.List;

@Route("employees")
@PageTitle("EmployeeGrid")
public class EmployeeGrid extends VerticalLayout {

    private ConfigurableFilterDataProvider<Employee,Void,String> filterDataProvider;

    private Button prev = new Button("Prev");
    private Button next = new Button("Next");
    private Span pageInfo = new Span();

    private int currentPage = 0;
    private int pageSize = 5;
    private int totalPages = 1;

    EmployeeService employeeService;
    Grid<Employee>grid = new Grid<>();

    public EmployeeGrid(EmployeeService employeeService){
        this.employeeService=employeeService;
        this.setSizeFull();

        createEmployeeGrid();
        buttonEvents();
        add(grid,new HorizontalLayout(prev, pageInfo, next));

        loadData();
    }

    private void buttonEvents() {
        prev.addClickListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                loadData();
            }
        });

        next.addClickListener(e -> {
            if (currentPage < totalPages - 1) {
                currentPage++;
                loadData();
            }
        });
    }

    private void loadData(){
        Page<Employee> page = employeeService.pagedEmployeeList(currentPage, pageSize);
        grid.setItems(page.getContent());
        totalPages = page.getTotalPages();
        pageInfo.setText("Page " + (currentPage+1)+" of "+totalPages);
        prev.setEnabled(currentPage > 0);
        next.setEnabled(currentPage < totalPages - 1);
    }

    private void createEmployeeGrid() {
        grid.setSizeFull();
        grid.setHeightFull();
        grid.addColumn(Employee::getName)
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
    }
}
