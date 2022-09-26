package com.company.ReflectionAndAnnotations;


import java.lang.reflect.Method;

public class Methods {
    public static void main(String[] args) {
        Class clazz = Reflection.class;

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method method: declaredMethods
             ) {
            System.out.println(method.getName());
        }
    }
}
