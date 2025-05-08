package org.example.dao;

import org.example.domain.Jugador;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JugadorDaoImplementacion implements JugadorDAO{
    private Set<Jugador>jugadores = new HashSet<>();

    @Override
    public void insertarJugador(Jugador jugador) {

    }

    @Override
    public void eliminarJugador(Jugador jugador) {

    }

    @Override
    public void modificarJugador(Jugador jugador) {

    }

    @Override
    public List<Jugador> getTodosLosJugadores() {
        return List.of();
    }

    @Override
    public Jugador buscarPorId(String id) {
        return null;
    }

}
