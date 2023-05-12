package com.example.javabase.design.factory.factory2;

import com.example.javabase.design.factory.factory1.Person;
import jdk.jshell.PersistentSnippet;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 10:10
 */
public class PersonFacotory2 {

    public Person createFacotory(Class<? extends Person> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return clazz.getConstructor().newInstance();
    }
}
