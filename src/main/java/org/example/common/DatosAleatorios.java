package org.example.common;

import net.datafaker.Faker;
import org.example.domain.Equipo;
import org.example.domain.Jugador;
import org.example.domain.Partido;

import java.time.LocalDate;
import java.util.Random;

public class DatosAleatorios {
    public static Jugador crearJugadores() {
        Faker f = new Faker();
        Random r = new Random();
        String id = String.valueOf(f.number().numberBetween(1000, 9999));
        String nombre = f.football().players();
        String equipo = f.football().teams();
        int goles = r.nextInt(35);
        int asistencias = r.nextInt(25);
        LocalDate fechaNac = f.date().birthday();
        String posicion = f.football().positions();

        return new Jugador(id, nombre, equipo, goles, asistencias, fechaNac, posicion);
    }
    public static Equipo crearEquipos() {

        return null;
    }
    public static Partido crearPartidos() {
        return null;
    }
}
