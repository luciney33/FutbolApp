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
        int id = r.nextInt(100);
        String nombre = f.football().players();
        String equipo = f.football().teams();
        int goles = r.nextInt(35);
        int asistencias = r.nextInt(25);
        LocalDate fechaNac = LocalDate.of(r.nextInt(30), r.nextInt(12), r.nextInt(2005));
        String posicion = f.football().positions();

        return new Jugador(id, nombre, equipo, goles, asistencias, fechaNac, posicion);
    }
    public static Equipo crearEquipos() {
        Faker f = new Faker();
        Random r = new Random();
        int id = r.nextInt(100);
        String nombre = f.football().teams();
        String estadio = "Estadio de "+ f.address().cityName();
        String entrenador = f.football().coaches();
        return new Equipo(id,nombre,estadio,entrenador);
    }
    public static Partido crearPartidos() {
        return null;
    }
}
