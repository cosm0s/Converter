package test.domain;

import es.cosm0s.converter.annotation.FieldsOperation;
import es.cosm0s.converter.core.OperationType;

public class ObjectLeft2 {

    @FieldsOperation(values = "cadenaUno,cadenaDos", type= OperationType.CONCAT)
    public String sumaUno;
    public String cadenaDos;
    @FieldsOperation(values = "enteroUno,enteroDos", type= OperationType.ADD)
    public int sumaDos;
    public int enteroDos;



    public String getSumaUno() {
        return sumaUno;
    }

    public void setSumaUno(String sumaUno) {
        this.sumaUno = sumaUno;
    }

    public String getCadenaDos() {
        return cadenaDos;
    }

    public void setCadenaDos(String cadenaDos) {
        this.cadenaDos = cadenaDos;
    }

    public int getSumaDos() {
        return sumaDos;
    }

    public void setSumaDos(int sumaDos) {
        this.sumaDos = sumaDos;
    }

    public int getEnteroDos() {
        return enteroDos;
    }

    public void setEnteroDos(int enteroDos) {
        enteroDos = enteroDos;
    }
}
