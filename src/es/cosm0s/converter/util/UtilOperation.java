package es.cosm0s.converter.util;

import es.cosm0s.converter.annotation.FieldsOperation;

import javax.management.AttributeNotFoundException;
import java.util.Map;

public class UtilOperation {

    public static String getValueConcated(FieldsOperation fieldsOperation, String space, Map<String, Map<Class<?>, Object>> fields){
        StringBuilder stringBuilder = new StringBuilder();
        if(fieldsOperation != null){
            for(String values: fieldsOperation.values().split(",")){
                if(fields != null && fields.size() > 0) {
                    Map<Class <?>, Object> value = fields.get(values);
                    stringBuilder.append(value.get(String.class));
                    stringBuilder.append(space);
                }
            }
        }
        return stringBuilder.toString().trim();
    }

    //TODO CREAR ANOTACIONES PROPIAS
    public static Integer getValueAdd(FieldsOperation fieldsOperation, Map<String, Map<Class<?>, Object>> fields) throws AttributeNotFoundException {
        int result = 0;
        if(fieldsOperation != null){
            for(String values: fieldsOperation.values().split(",")){
                if(fields != null && fields.size() > 0) {
                    Map<Class <?>, Object> value = fields.get(values);

                    if(value.get(int.class) == null){
                        throw new AttributeNotFoundException("Se ha indicado un attributo que no es entero");
                    } else {
                        result = result + (int) value.get(int.class);
                    }
                }
            }
        }
        return result;
    }
}
