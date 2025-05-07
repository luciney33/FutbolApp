package org.example.ui;

import org.example.common.ExcepcionAsistencias;
import org.example.common.ExcepcionGoles;
import org.example.domain.Jugador;

public class App {
    public static void main(String[] args) {
        Jugador jugador = new Jugador();
        int cantidad = -1;
        try{
            jugador.incrementarGoles(cantidad);
        }catch (ExcepcionGoles e) {
            System.out.println(e.getMessage());
        }

        int cantidad2 =-1;
        try{
            jugador.incrementarAsistencias(cantidad2);
        }catch (ExcepcionAsistencias e2) {
            System.out.println(e2.getMessage());
        }

    }
}