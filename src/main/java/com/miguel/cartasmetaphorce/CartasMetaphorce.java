package com.miguel.cartasmetaphorce;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Stack;
import java.util.Scanner;

public class CartasMetaphorce {
    
    static List<Carta> Mazo = new ArrayList<>();
    static Stack<Carta> pilaReserva = new Stack();
    static Jugador jug1 = new Jugador("Gustavo");
    static Jugador jug2 = new Jugador("Cecilia");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        crearMazo();
        mezclarMazoReserva();
        muestraMazo(Mazo);
        System.out.println(",");
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
            System.out.println("Carta No.: " + i +
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
    
    public static void tomarCarta(List<Carta> mazoDestino, String jugador){
        int as = 0;
        Carta cartaJugador = pilaReserva.pop();
        if(cartaJugador.getValor() == 1){//Evalúa si tiene un As y pregunta si quiere cambiar su valor
            System.out.println(jugador + ", tienes un as, " +
                    "¿qué valor quieres darle? puedes elegir 1 u 11.");
            as = sc.nextInt();
            if(as == 1){
                cartaJugador.setValor(1);
            }else{
                cartaJugador.setValor(11);
            }
        }
        mazoDestino.add(cartaJugador);
    }
    
    public static void iniciaJuego(){
        String respuesta = "";
        do {
            iniciarRonda();
            System.out.println("¿Quieres continuar a una nueva ronda?");
            respuesta = sc.nextLine();
            tomarCarta(jug1.mazo, jug1.getNombre());
            tomarCarta(jug2.mazo, jug2.getNombre());
        } while (respuesta.equalsIgnoreCase("Si")
                || respuesta.equalsIgnoreCase("s")
                || respuesta.equalsIgnoreCase(""));
        conteoValores(jug1.getPuntuaje(), jug1.getNombre(), jug2.getPuntuaje(), jug2.getNombre(), true);
    }

    public static void iniciarRonda(){
        int conteo = 0;
        System.out.println("Mazo de " + jug1.getNombre());
        muestraMazo(jug1.mazo);
        for (Carta carta : jug1.mazo) {
            conteo += carta.getValor();
        }

        int conteo2 = 0;
        System.out.println("Mazo de " + jug2.getNombre());
        muestraMazo(jug2.mazo);
        for (Carta carta : jug2.mazo) {
            conteo2 += carta.getValor();
        }
        jug1.setPuntuaje(conteo);
        jug2.setPuntuaje(conteo2);
        conteoValores(jug1.getPuntuaje(), jug1.getNombre(), jug2.getPuntuaje(), jug2.getNombre(), false);

    }
    
    public static void conteoValores(int conteoj1, String jugador1, int conteoj2, String jugador2, boolean fin){
        if(conteoj1 == 21){
            System.out.println("El puntaje de " + jugador1 + " es exactamente " + conteoj1 + ".");
            System.out.println("¡Felicidades, ganó " + jugador1 + "!");
            System.exit(0);
        } else if (conteoj1 > 21) {
            System.out.println("Ya perdió " + jugador1 + ".");
            System.out.println("Por lo tanto, ¡felicidades, ganó " + jugador2 + "!");
            System.exit(0);
        }

        if(conteoj2 == 21){
            System.out.println("El puntaje de " + jugador2 + " es exactamente " + conteoj2 + ".");
            System.out.println("¡Felicidades, ganó " + jugador2 + "!");
            System.exit(0);
        } else if (conteoj2 > 21) {
            System.out.println("Ya perdió " + jugador2 + ".");
            System.out.println("Por lo tanto, ¡felicidades, ganó " + jugador1 + "!");
            System.exit(0);
        }

        if(fin==true) {
            if(conteoj1 > conteoj2){
                System.out.println(jugador1 + " tuvo un puntaje más acercado a 21 con " + conteoj1 + ". ¡" + jugador1 + " ganó!");
            } else {
                System.out.println(jugador2 + " tuvo un puntaje más acercado a 21 con " + conteoj2 + ". ¡" + jugador2 + " ganó!");
            }
        }
    }
}
