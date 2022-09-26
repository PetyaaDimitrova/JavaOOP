package com.company.ReflectionAndAnnotations;

import java.lang.reflect.Field;

public class Fields {
    public static void main(String[] args) {
        Class clazz = Reflection.class;

        Field[] fields = clazz.getDeclaredFields();

        for (Field f:fields
             ) {
            System.out.println(f.getName());
        }
    }
}
