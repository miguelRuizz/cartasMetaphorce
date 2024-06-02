package com.miguel.cartasmetaphorce;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    public List<Carta> mazo = new ArrayList<>();
    private int puntuaje;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Carta> getMazo() {
        return mazo;
    }

    public void setMazo(List<Carta> mazo) {
        this.mazo = mazo;
    }

    public int getPuntuaje() {
        return puntuaje;
    }

    public void setPuntuaje(int puntuaje) {
        this.puntuaje = puntuaje;
    }
}
