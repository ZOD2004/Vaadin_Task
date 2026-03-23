package com.example.ui;

import com.example.anno.AddBorder;
import com.example.anno.AddBorderImple;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.data.domain.Page;


import java.util.List;

@Route("employees")
@PageTitle("EmployeeGrid")
public class EmployeeGrid extends VerticalLayout {
    private boolean initialized = false;
    @Override
    public void onAttach(AttachEvent attachEvent){
        super.onAttach(attachEvent);
        if(!initialized){
            new AddBorderImple().apply(this);
            initialized = true;
        }
    }


    private Button prev = new Button("Prev");
    private Button next = new Button("Next");
    private Span pageInfo = new Span();

    private int currentPage = 0;
    private int pageSize = 6;
    private int totalPages = 1;

    TextField searchField = new TextField();
    String searchFilter="";
    @AddBorder
    IntegerField recordToShowField = new IntegerField();

    EmployeeService employeeService;
    Grid<Employee>grid = new Grid<>();

    public EmployeeGrid(EmployeeService employeeService){
        this.employeeService=employeeService;
        this.setSizeFull();

        createEmployeeGrid();
        buttonEvents();
        createSearchField();
        createRecordToShowField();
        add(new HorizontalLayout(searchField,recordToShowField),grid,new HorizontalLayout(prev, pageInfo, next));

        loadData();
    }

    private void createRecordToShowField() {
        recordToShowField.setPlaceholder("No. Record to show");
        recordToShowField.setMin(1);
        recordToShowField.getStyle().setAlignItems(Style.AlignItems.END);
        recordToShowField.setValueChangeMode(ValueChangeMode.LAZY);
        recordToShowField.addValueChangeListener(e->{
                    if (e.getValue() != null) {
                        pageSize = e.getValue();
                        loadData();
                    }
                }
        );
    }

    private void createSearchField() {
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setValueChangeMode(ValueChangeMode.LAZY);

        searchField.addValueChangeListener(e->{
            searchFilter = e.getValue();
            currentPage = 0;
            loadData();
        });

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
        Page<Employee> page = employeeService.pagedEmployeeList(currentPage, pageSize,searchFilter);
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
