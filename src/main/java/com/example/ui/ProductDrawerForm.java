package com.example.ui;

import com.example.entity.ProductCatalogItem;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class ProductDrawerForm extends VerticalLayout {

    private ProductForm productForm;
    public ProductDrawerForm(){
        H2 title = new H2("Product Details");

        this.setWidth("300px");
        this.setHeight("100%");
        this.getStyle().set("display", "flex");
        this.getStyle().set("flexDirection", "column");

        productForm = new ProductForm();

        Scroller scroller = new Scroller(productForm);
        scroller.setSizeFull();

        add(title, scroller);
    }
    public void setProductDetails(@Nullable ProductCatalogItem productCatalogItem) {
        productForm.setFormDataObject(productCatalogItem);
    }


}
