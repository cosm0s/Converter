package es.cosm0s.converter.core;

import es.cosm0s.converter.annotation.FieldsOperation;
import es.cosm0s.converter.util.UtilConverter;
import es.cosm0s.converter.util.UtilOperation;

import javax.management.AttributeNotFoundException;
import java.lang.reflect.Field;
import java.util.Map;

public class ConverterParser<D> {

    private static final String space = " ";

    public D parser(D destination) {
        Map<String, Map<Class <?>, Object>> fields = UtilConverter.getFieldsValues(this.getClass(), this);
        Class classDestination = destination.getClass();

        for(Field field:classDestination.getFields()){
            if(field.getAnnotations().length > 0){
               if(field.isAnnotationPresent(FieldsOperation.class)){
                   FieldsOperation fieldsOperation = field.getAnnotation(FieldsOperation.class);
                   Object value = null;
                   if(fieldsOperation.type().equals(OperationType.CONCAT)) {
                       value = UtilOperation.getValueConcated(fieldsOperation, space, fields);
                   } else if (fieldsOperation.type().equals(OperationType.ADD)) {
                       try {
                           value = UtilOperation.getValueAdd(fieldsOperation, fields);
                       } catch (AttributeNotFoundException e) {
                           e.printStackTrace();
                       }
                   }
                   UtilConverter.setMethodValue(value, field, destination);
               }
            } else {
                if(fields != null && fields.size() > 0) {
                    Map<Class <?>, Object> values = fields.get(field.getName());
                    if(values != null && values.size() == 1){
                        UtilConverter.setMethodValue(values.get(field.getType()), field, destination);
                    }
                }
            }
        }
        return destination;
    }
}
