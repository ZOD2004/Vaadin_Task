package com.example.ui;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("/app")
@PageTitle("MyApplication")
@CssImport("./styles/addStyle.css")
public class MainView extends VerticalLayout {
    public MainView(){
//        this.getStyle().set("display", "flex");
//        this.getStyle().set("justify-content", "center");
//        this.getStyle().set("align-items", "center");
//        this.getStyle().setBackgroundColor("#88BDF2");
//        this.setSizeFull();
//        Div centerBox = new Div();
//        VerticalLayout box = new VerticalLayout();
//        box.setSizeFull();
//        box.setAlignItems(Alignment.CENTER);
//        box.setJustifyContentMode(JustifyContentMode.CENTER);
//        box.setWidth("350px");
//        box.getStyle().setBackgroundColor("white");
//        box.getStyle().set("display", "flex");
//        box.getStyle().set("justify-content", "center");
//        box.getStyle().set("align-items", "center");



//        box.add(new H3("Welcome to the BOX"));
        add(new H1("Hello World!!"));
//        TextField name = new TextField("What is your Name?");
//        name.setWidth("100%");
//        name.getElement().getStyle().set("border", "2px solid #0077b6");
//        name.getElement().getStyle().set("border-radius", "10%");
//        name.addClassName("border-textField");
//        box.add(name);
//        Span nameSpan = new Span();
//        nameSpan.setVisible(false);
//        box.add(nameSpan);
//        Button button = new Button("Say Hello",
//                event->{
//                    nameSpan.setText("Hello %s ".formatted(name.getValue()));
//                    nameSpan.setVisible(!name.isEmpty());
//                });
//        button.getStyle().setBackgroundColor("Blue");
//        button.getStyle().setColor("Yellow");
//        box.add(button);
//        add(box);
//        TextField whatsYourName = new TextField("What is your name?");
//        add(whatsYourName);
//        add(new Button("Say Hello",
//                event ->
//                add(new Span("Hello %s !!".formatted(whatsYourName.getValue())))));
//
//        add(new Button("Hi ..",event -> add(new Paragraph("hi in text"))));
//        add(new Paragraph("The paragraph after the button"));
//        TextField nameField = new TextField("What is your name?");
//        add(nameField);
//        Span greeting = new Span();
//        greeting.setVisible(false);//same as 101 but stays hidden always we need to give coz of the gap it forms
//        add(greeting);
//        add(new Button("Say Hello", event ->{
//                greeting.setText("Hello, %s!".formatted(nameField.getValue()));
//                greeting.setVisible(!nameField.isEmpty());//101 enter name and gets filled
//    }));



    }
}
