package es.cosm0s.converter.annotation;

import es.cosm0s.converter.core.OperationType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsOperation {
    public OperationType type();
    public String values();
}
