package com.github.lightverse.fresco.learner.utils;

import java.lang.reflect.Field;

public class ReflectUtils {

    public static <T> T getField(Object object, String fieldName, Class<T> fieldClass){
        try {
            Field declaredField = object.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            Object result = declaredField.get(object);
            if (fieldClass.isInstance(result)) {
                return (T) result;
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
        return null;
    }
}
