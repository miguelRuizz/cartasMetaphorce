package com.miguel.cartasmetaphorce;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Stack;

public class CartasMetaphorce {
    
    static List<Carta> Mazo = new ArrayList<>();
    static Stack<Carta> pilaReserva = new Stack();
    static List<Carta> mazoJ1 = new ArrayList<>();
    static List<Carta> mazoJ2 = new ArrayList<>();

    public static void main(String[] args) {
        crearMazo();
        mezclarMazoReserva();
        muestraMazo(Mazo);
        llenarPilas();
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
                mazoJ1.add(cartaJugador);
            } else if (i>=2) {
                mazoJ2.add(cartaJugador);
            }        
        }     
    }
    
    public static void tomarCarta(List<Carta> mazoDestino){
        Carta cartaJugador = pilaReserva.pop();
        mazoDestino.add(cartaJugador);
    }
    
    public static void iniciaJuego(){
        int conteo = 0;
        for (Carta carta : mazoJ1) { //Se llena la pila de reserva
            muestraMazo(mazoJ1);
            conteo += carta.getValor();
        }
        conteoValores(conteo,"Jugador Uno");
        
        int conteo2 = 0;
        for (Carta carta : mazoJ2) { //Se llena la pila de reserva
            muestraMazo(mazoJ1);
            conteo2 += carta.getValor();
        }
        conteoValores(conteo2,"Jugador Dos");
    }
    
    public static void conteoValores(int conteo, String jugador){
        if(conteo == 21){
            System.out.println("Tu puntaje es: " + conteo);
            System.out.println("¡Felicidades, ya ganaste, " + jugador + "!");
            System.exit(0);
        } else if (conteo > 21) {
            System.out.println("Ya perdiste.");
            System.exit(0);
        } else {
            System.out.println("Ni uno ni otro ganó.");
            System.exit(0);
        }
    }
}
