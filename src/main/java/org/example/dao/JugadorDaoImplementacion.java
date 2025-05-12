package org.example.dao;


import net.datafaker.Faker;
import org.example.domain.Jugador;

import java.util.*;

public class JugadorDaoImplementacion implements JugadorDAO{
    private Set<Jugador> jugadores;

    public JugadorDaoImplementacion(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    public JugadorDaoImplementacion() {
        this.jugadores = new HashSet<Jugador>();
        Random random = new Random();

    }

    @Override
    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    @Override
    public boolean insertarJugador(Jugador jugador) {
        return jugadores.add(jugador);
    }

    @Override
    public void eliminarJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }

    @Override
    public boolean modificarJugador(int goles, int asistencias, String equipo) {
        boolean encontrado = false;
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador j
        }

        return false;
    }

    @Override
    public Set<Jugador> getTodosLosJugadores() {
        return;
    }

    @Override
    public Jugador buscarPorId(String id) {
        return null;
    }

}
