package com.example.ui;

import com.example.anno.AddBorder;
import com.example.anno.AddBorderImple;
import com.example.entity.ProductCatalogItem;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class ProductForm extends BaseForm {

    TextField name = new TextField("Name");
    @AddBorder
    TextArea desc = new TextArea("Description");
    TextField categoryField = new TextField("Category");
    TextField brandField = new TextField("Brand");
    @AddBorder
    NumberField priceField = new NumberField("Price");
    @AddBorder
    NumberField stockField = new NumberField("stockQuantity");
    RadioButtonGroup<Boolean> inStockField = new RadioButtonGroup<>();

    @AddBorder
    TextField textField = new TextField("Example");

    private final Binder<ProductCatalogItem> binder;
//    AddBorderImple addBorderImple;

    public ProductForm(){
        super();
//        addBorderImple = new AddBorderImple();

        inStockField.setLabel("In Stock");
        inStockField.setItems(true, false);
        inStockField.setItemLabelGenerator(value -> value ? "Yes" : "No");
        inStockField.setValue(true);//default

        add(name, desc, categoryField, brandField, priceField, stockField, inStockField);

        // can I call this?
//        this.applyAnnotationStyles();

        binder = new Binder<>();

        binder.forField(name)
                .asRequired("Name is required")
                .bind(ProductCatalogItem::getName,ProductCatalogItem::setName);
        binder.forField(desc)
                .bind(ProductCatalogItem::getDescription,ProductCatalogItem::setDescription);
        binder.forField(categoryField)
                .bind(ProductCatalogItem::getCategory,ProductCatalogItem::setCategory);
        binder.forField(brandField)
                .bind(ProductCatalogItem::getBrand,ProductCatalogItem::setBrand);
        binder.forField(priceField)
                .asRequired("Price is required")
                .withValidator(price -> price > 0, "Price must be positive")
                .withConverter(
                        value -> value == null ? 0.0 : value,
                        value -> value == null ? 0.0 : value
                )
                .bind(ProductCatalogItem::getPrice,ProductCatalogItem::setPrice);
        binder.forField(stockField)
                .withConverter(
                        value -> value == null ? 0 : value.intValue(),
                        value -> value == null ? 0.0 : value.doubleValue()
                )
                .bind(ProductCatalogItem::getStockQuantity,ProductCatalogItem::setStockQuantity);
        binder.forField(inStockField)
                .bind(ProductCatalogItem::getInStock,ProductCatalogItem::setInStock);


    }
    public void setFormDataObject(@Nullable ProductCatalogItem productCatalogItem) {
        binder.setBean(productCatalogItem);
    }

    public Optional<ProductCatalogItem> getFormDataObject(){
        if(binder.getBean() == null){
            throw new RuntimeException("The bean is empty");
        }
        if(binder.validate().isOk()){
            return Optional.of(binder.getBean());
        }
        else{
            return Optional.empty();
        }
    }
}
