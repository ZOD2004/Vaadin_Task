package com.example.ui;

import com.example.anno.AddBorder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import java.lang.reflect.Field;

public class BaseForm extends FormLayout {

    public BaseForm() {
        applyAnnotationStyles();
    }

    protected void applyAnnotationStyles() {
        try {
            Class<?> objClass = this.getClass();
            Field[] fields = objClass.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(AddBorder.class)) {
                    field.setAccessible(true);
                    Object val = field.get(this);
                    System.out.println("Field: " + field.getName() + " Value: " + val);

                    if (val instanceof Component component) {
                        component.addClassName("border-textField");
                        // AddBorder anno = field.getAnnotation(AddBorder.class);
                        // component.getStyle().set("border", anno.width() + " " + anno.type() + " " + anno.color());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to apply styles via reflection", e);
        }
    }
}