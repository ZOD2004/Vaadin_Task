package com.example.anno;

import com.vaadin.flow.component.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Logger;

public class AddBorderImple {

    Logger logger = Logger.getLogger(AddBorderImple.class.getName());

    public void applyBorders(Set<Object> listObj) throws IllegalAccessException {

        logger.info("Came in apply border \n\n\n\n");
        for(Object obj:listObj) {
//            System.out.println(obj);
            Class<?> objClass = obj.getClass();
            Field[] fields = objClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(AddBorder.class)) {
                    Object val = field.get(obj);
                    Component component = (Component) val;

//                AddBorder annotation = field.getAnnotation(AddBorder.class);
//                String color = annotation.color();
//                String width = annotation.width();
//                String type = annotation.type();
//                component.getStyle().setBorder(width+" "+type+" "+color);

                    component.addClassName("border-textField");
                }
            }
        }

    }
    public void applyBorder(Set<Field> fields) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        for(Field field : fields){
            field.setAccessible(true);
            Class<?> cls = field.getDeclaringClass();
            Object ins = cls.getDeclaredConstructor().newInstance();
            Object val = field.get(ins);
            if(val instanceof Component component){
                component.addClassName("border-textField");
            }
        }
    }
}
