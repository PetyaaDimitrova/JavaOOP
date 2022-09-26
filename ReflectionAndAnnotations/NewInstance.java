package com.company.ReflectionAndAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NewInstance {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Reflection.class;

        Constructor constructor = clazz.getConstructor();

        Object o = constructor.newInstance();

        System.out.println(o.getClass().getSimpleName());
    }
}
