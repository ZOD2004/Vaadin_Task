package com.example.anno;


import com.vaadin.flow.component.Component;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.FieldInfo;
import io.github.classgraph.ScanResult;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ScanClasses {
    public ScanClasses() {
//        System.out.println("This is here \n\n\n\n\n\n\n\n");
        AddBorderImple addBorderImple = new AddBorderImple();
        Set<Field>objSet = new HashSet<>();
        try(
                ScanResult result = new ClassGraph()
                        .enableAllInfo()
                        .acceptPackages("com.example.ui")
                        .scan();
        ){
            for(ClassInfo classInfo : result.getAllClasses()){
                System.out.println(classInfo.getName());
                for (FieldInfo fieldInfo : classInfo.getDeclaredFieldInfo()){
//                    System.out.println(fieldInfo.getName());
//                    System.out.println("Inner for loop");
                    if(fieldInfo.hasAnnotation(AddBorder.class)){
                        Class<?> clazz = classInfo.loadClass();
                        Field field = clazz.getDeclaredField(fieldInfo.getName());
                        objSet.add(field);
                    }
                }
            }

//            System.out.println(objSet);
            if(!objSet.isEmpty()){
                addBorderImple.applyBorder(objSet);
            }
        } catch (Exception e) {
            System.out.println("Error !!!!");
        }
    }
//    public ScanClasses(){
//
//        Reflections reflections = new Reflections("com.example.ui");
//        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
//        for (Class<?> clazz : allClasses) {
//            try {
//                Object instance = clazz.getDeclaredConstructor().newInstance();
//
//                Field[] fields = clazz.getDeclaredFields();
//
//                for (Field field : fields) {
//                    field.setAccessible(true);
//
//                    if (field.isAnnotationPresent(AddBorder.class)) {
//                        Object value = field.get(instance);
//                        if (value instanceof Component component) {
//                            component.addClassName("border-textField");
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Skipping: " + clazz.getName());
//            }
//        }
//    }


}
