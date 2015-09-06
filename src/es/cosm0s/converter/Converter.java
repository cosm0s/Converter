package es.cosm0s.converter;

import es.cosm0s.converter.annotation.FieldsOperation;
import es.cosm0s.converter.core.OperationType;
import es.cosm0s.converter.util.UtilConverter;
import es.cosm0s.converter.util.UtilOperation;

import javax.management.AttributeNotFoundException;
import java.lang.reflect.Field;
import java.util.Map;

public class Converter<O,D> {

    private O origin;
    private D destination;
    private static final String space = " ";

    public Converter(O origin, D destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public O getOrigin() {
        return origin;
    }

    public void setOrigin(O origin) {
        this.origin = origin;
    }

    public D getDestination() {
        return destination;
    }

    public void setDestination(D destination) {
        this.destination = destination;
    }

    public D parser() {
        Map<String, Map<Class <?>, Object>> fields = UtilConverter.getFieldsValues(this.origin.getClass(), this.origin);
        Class classDestination = this.destination.getClass();

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
                   UtilConverter.setMethodValue(value, field, this.destination);
               }
            } else {
                if(fields != null && fields.size() > 0) {
                    Map<Class <?>, Object> values = fields.get(field.getName());
                    if(values != null && values.size() == 1){
                        UtilConverter.setMethodValue(values.get(field.getType()), field, this.destination);
                    }
                }
            }
        }
        return this.destination;
    }
}
