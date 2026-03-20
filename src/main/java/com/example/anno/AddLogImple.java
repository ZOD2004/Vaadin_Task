package com.example.anno;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.logging.Logger;

public class AddLogImple {
    Logger logger = Logger.getLogger(AddLogImple.class.getName());
    public void apply(Object obj){

        Class<?> cls = obj.getClass();

        Method[] methods = cls.getDeclaredMethods();

        for (Method method:methods){
            Type methodType = method.getReturnType();
            if(method.isAnnotationPresent(AddLog.class)){
                if(methodType == Void.TYPE){
                    logger.info(method.getName()+" has no return type");
                }
                else{
                    logger.info(method.getName()+" returns "+ method.getReturnType());
                }
            }
        }

    }
}
