package org.example.ui;

import org.example.common.Constantes;
import org.example.common.ExcepcionAsistencias;
import org.example.common.ExcepcionGoles;
import org.example.dao.*;
import org.example.domain.DatosAleatorios;
import org.example.domain.Jugador;

public class App {
    public static void main(String[] args) {
        Liga liga = new Liga();
        Jugador jugador = new Jugador();
        JugadorDAO jugadorDAO = new JugadorDaoImplementacion(liga);
        EquipoDAO equipoDAO = new EquipoDaoImplementacion(liga);
        DatosAleatorios generador = new DatosAleatorios();
        boolean salir = false;
        int cantidad = -1;
        try{
            jugador.incrementarGoles(cantidad);
        }catch (ExcepcionGoles e) {
            EntradaSalida.mostrarError(Constantes.OPCION_INVALIDA);
        }

        int cantidad2 =-1;
        try{
            jugador.incrementarAsistencias(cantidad2);
        }catch (ExcepcionAsistencias e2) {
            EntradaSalida.mostrarError(Constantes.OPCION_INVALIDA);
        }

    }
}