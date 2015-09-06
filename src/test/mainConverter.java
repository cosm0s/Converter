package test;

import es.cosm0s.converter.Converter;
import test.domain.ObjectLeft2;
import test.domain.ObjectRight1;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class mainConverter {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IntrospectionException, InvocationTargetException, NoSuchMethodException {
        ObjectRight1 objectRight1 = new ObjectRight1();
        objectRight1.setCadenaDos("dos");
        objectRight1.setCadenaUno("uno");
        objectRight1.setEnteroDos(2);
        objectRight1.setEnteroUno(1);

        Converter converter =new Converter(objectRight1, new ObjectLeft2());

        ObjectLeft2 objectLeft2 = (ObjectLeft2) converter.parser();

        System.out.println(objectLeft2.getSumaUno());
        System.out.println(objectLeft2.getSumaDos());

    }
}
