package org.example.dao;


import net.datafaker.Faker;
import org.example.domain.Jugador;
import org.example.domain.Liga;

import java.util.*;

public class JugadorDaoImplementacion implements JugadorDAO {
    private Liga liga;

    public JugadorDaoImplementacion(Liga liga) {
        this.liga = liga;
    }

    @Override
    public Set<Jugador> getJugadores() {
        return Set.of();
    }

    @Override
    public boolean insertarJugador(Jugador jugador) {
        return false;
    }

    @Override
    public void eliminarJugador(Jugador jugador) {

    }

    @Override
    public boolean modificarJugador(String id, int goles, int asistencias, String equipo) {
        return false;
    }

    @Override
    public Set<Jugador> getTodosLosJugadores() {
        return Set.of();
    }

    @Override
    public Jugador buscarPorId(String id) {
        return null;
    }
}
