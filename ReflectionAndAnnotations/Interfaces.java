package com.company.ReflectionAndAnnotations;

import JarOfT.ss;

public class Interfaces {
    public static void main(String[] args) {
        Class[]interfaces = Reflection.class.getInterfaces();

        for (Class c:interfaces
        ) {
            System.out.println(c.getSimpleName());
        }
    }
}
