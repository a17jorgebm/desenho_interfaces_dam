package org.example;

public class Operacion {
    private Long numero1;
    private Long numero2;
    private Character operacion;
    private boolean completada;

    public Operacion() {
        this.completada=false;
    }

    public Float ejecutarOperacion(){
        if (!operacionListaParaCalcular()) return null;
        Float n1=numero1.floatValue();
        Float n2=numero2.floatValue();
        this.completada=true;
        return switch (operacion){
            case '+' -> n1+n2;
            case '-' -> n1-n2;
            case '*' -> n1*n2;
            case '/' -> (numero2!=0) ? n1/n2 : null;
            default -> null;
        };
    }

    public void resetearValores(){
        this.numero1=null;
        this.numero2=null;
        this.operacion=null;
    }

    public boolean operacionListaParaCalcular(){
        return numero1!=null && numero2!=null && operacion!=null;
    }

    public Long getNumero1() {
        return numero1;
    }

    public void setNumero1(Long numero1) {
        this.completada=false;
        this.numero1 = numero1;
    }

    public Long getNumero2() {
        return numero2;
    }

    public void setNumero2(Long numero2) {
        this.numero2 = numero2;
    }

    public Character getOperacion() {
        return operacion;
    }

    public void setOperacion(Character operacion) {
        this.operacion = operacion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}
