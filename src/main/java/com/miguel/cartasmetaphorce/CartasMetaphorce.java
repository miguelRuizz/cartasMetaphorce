package com.miguel.cartasmetaphorce;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Stack;

public class CartasMetaphorce {
    
    static List<Carta> Mazo = new ArrayList<>();
    static Stack<Carta> pilaReserva = new Stack();
    static Jugador jug1 = new Jugador("Gustavo");
    static Jugador jug2 = new Jugador("Cecilia");

    public static void main(String[] args) {
        crearMazo();
        mezclarMazoReserva();
        muestraMazo(Mazo);
        System.out.println("");
        llenarPilas();
        iniciaJuego();
    }
    
    public static void crearMazo(){
        for(int i=1; i<=4; i++){//Palos
            for(int j=1; j<=13; j++){//Valores normales
                if(j>=11){//Si J es 11, 12 o 13, se trata de una Jota, Rey o Reina y esos siempre valen 10
                    Mazo.add(new Carta(i,j,10));
                } else {
                    Mazo.add(new Carta(i,j,j));
                }                
            }
        }
    }
    
    public static void mezclarMazoReserva(){
        Collections.shuffle(Mazo);
    }
    
    public static void muestraMazo(List<Carta> mazo){
        for(int i=0; i<=mazo.size()-1 ; i++){//Palos    
            System.out.println("Indice: " + i +
                                " Palo: " + mazo.get(i).getPalo() + 
                                " Valor: " + mazo.get(i).getValor() + ".");
        }
    }
    
    public static void llenarPilas(){ //Es el crearPilas()
        Carta cartaJugador;
        for (Carta carta : Mazo) { //Se llena la pila de reserva
            pilaReserva.push(carta);
        }
        for(int i = 0; i<4 ; i++){//Se llenan las pilas de los jugadores, son 2 cartas para cada pila
            cartaJugador = pilaReserva.pop();
            if(i<2){
                jug1.mazo.add(cartaJugador);
            } else if (i>=2) {
                jug2.mazo.add(cartaJugador);
            }        
        }     
    }
    
    public static void tomarCarta(List<Carta> mazoDestino){
        Carta cartaJugador = pilaReserva.pop();
        mazoDestino.add(cartaJugador);
    }
    
    public static void iniciaJuego(){
        int conteo = 0;
        System.out.println("Mazo de " + jug1.getNombre());
        muestraMazo(jug1.mazo);
        for (Carta carta : jug1.mazo) { //Se llena la pila de reserva
            conteo += carta.getValor();
        }
        
        int conteo2 = 0;
        System.out.println("Mazo de " + jug2.getNombre());
        muestraMazo(jug2.mazo);
        for (Carta carta : jug2.mazo) { //Se llena la pila de reserva
            conteo2 += carta.getValor();
        }

        conteoValores(conteo, jug1.getNombre(), conteo2, jug2.getNombre());
    }
    
    public static void conteoValores(int conteoj1, String jugador1, int conteoj2, String jugador2){
        if(conteoj1 == 21){
            System.out.println("El puntaje de " + jugador1 + " es: " + conteoj1 + ".");
            System.out.println("¡Felicidades, ganó " + jugador1 + "!");
            System.exit(0);
        } else if (conteoj1 > 21) {
            System.out.println("Ya perdió " + jugador1 + ".");
            System.out.println("Por lo tanto, ¡felicidades, ganó " + jugador2 + "!");
            System.exit(0);
        }

        if(conteoj2 == 21){
            System.out.println("El puntaje de " + jugador2 + " es: " + conteoj2 + ".");
            System.out.println("¡Felicidades, ganó " + jugador2 + "!");
            System.exit(0);
        } else if (conteoj2 > 21) {
            System.out.println("Ya perdió " + jugador2 + ".");
            System.out.println("Por lo tanto, ¡felicidades, ganó " + jugador1 + "!");
            System.exit(0);
        }
        else {
            if(conteoj1 > conteoj2){
                System.out.println(jugador1 + " tuvo un puntaje más acercado a 21. ¡" + jugador1 + " ganó!");
            } else {
                System.out.println(jugador2 + " tuvo un puntaje más acercado a 21. ¡" + jugador2 + " ganó!");
            }
        }
    }
}
