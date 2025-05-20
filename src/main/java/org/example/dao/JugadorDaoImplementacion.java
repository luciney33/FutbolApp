package org.example.dao;


import org.example.domain.Jugador;

import java.util.*;

public class JugadorDaoImplementacion implements JugadorDAO {
    private Liga liga;

    public JugadorDaoImplementacion(Liga liga) {
        this.liga = liga;
    }

    public JugadorDaoImplementacion() {
        liga = new Liga();
    }

    @Override
    public Set<Jugador> getJugadores() {
        return liga.getJugadores();
    }

    @Override
    public boolean insertarJugador(Jugador jugador) {
        return liga.getJugadores().add(jugador);
    }

    @Override
    public boolean eliminarJugador(Jugador jugador) {
        return liga.getJugadores().remove(jugador);
    }

    @Override
    public void modificarJugador(int id, int goles, int asistencias, String equipo) {
        liga.getJugadores().stream().filter(j -> j.getId() == id).findAny().ifPresent(j ->
        {
            j.setGoles(goles);
            j.setAsistencias(asistencias);
            j.setEquipo(equipo);
        });

    }

    @Override
    public Optional<Jugador> buscarPorId(int id) {
        return liga.getJugadores().stream().filter(j -> j.getId() == id).findFirst();
    }
}
