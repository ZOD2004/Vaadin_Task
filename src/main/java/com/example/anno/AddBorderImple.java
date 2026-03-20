package com.example.anno;

import com.vaadin.flow.component.Component;
import java.lang.reflect.Field;

public class AddBorderImple {

    public void apply(Object instance) {
        Class<?> clazz = instance.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AddBorder.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(instance);

                    if (value instanceof Component component) {
//                        AddBorder annotation = field.getAnnotation(AddBorder.class);
//                        String color = annotation.color();
//                        String width = annotation.width();
//                        String type = annotation.type();
//                        component.getStyle().setBorder(width+" "+type+" "+color);

                         component.addClassName("border-textField");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}