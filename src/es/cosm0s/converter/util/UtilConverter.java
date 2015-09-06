package es.cosm0s.converter.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UtilConverter {

    public static<E> Map<String, Map<Class <?>, Object>> getFieldsValues(Class objectClass, E object) {
        Map<String, Map<Class <?>, Object>> fields = new HashMap<>();
        for(Field field:objectClass.getFields()){
            Map<Class <?>, Object> typeObject = new HashMap<>();
            Object result = getMethodValue(field, objectClass, object);
            if(result != null) {
                typeObject.put(field.getType(), result);
            }
            fields.put(field.getName(), typeObject);
        }
        return  fields;
    }

    private static<E>  Object getMethodValue(Field field, Class objectClass, E object){
        String getter = methodNameFactory("get", field);
        Object result = null;
        try {
            Method method = objectClass.getMethod(getter);
            result = method.invoke(object);
        } catch (NoSuchMethodException|InvocationTargetException |IllegalAccessException e) {
            //TODO GESTIONAR LAS EXCEPCIONES Y HACER UNA PROPIA
        }
        return result;
    }

    public static<E> void setMethodValue(Object value, Field field, E object){
        Class objectClass = object.getClass();
        String setter = methodNameFactory("set", field);
        try {
            Method method = objectClass.getMethod(setter, field.getType());
            method.invoke(object, value);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static String methodNameFactory(String prefix, Field field){
        return prefix + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }
}
