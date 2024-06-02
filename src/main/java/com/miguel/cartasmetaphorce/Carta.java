package com.miguel.cartasmetaphorce;

public class Carta {
    private int palo; //(1-"Corazón", 2-"Diamante", 3"Trébol", 4"Espada").
    private int valor0; //(1 para el As, 2 para el 2, etc), solo sirve para generar las cartas.
    private int valor; //(Convierte los 11, 12 y 13 a 10), es el que se usa siempre.
    //private boolean as;

    public Carta(int palo, int valor0, int valor/*, boolean as*/) {
        this.valor0 = valor0;
        this.palo = palo;
        this.valor = valor;
        //this.as = as;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getPalo() {
        return palo;
    }

    public void setPalo(int palo) {
        this.palo = palo;
    }

    public int getValor0() {
        return valor0;
    }

    public void setValor0(int valor0) {
        this.valor0 = valor0;
    }
    
    /*public boolean isAs() {
        return as;
    }

    public void setAs(boolean as) {
        this.as = as;
    }*/
    
}
