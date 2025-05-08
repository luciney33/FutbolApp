package org.example.dao;

import org.example.domain.Jugador;

import java.util.List;

public interface JugadorDAO {
    public void insertarJugador(Jugador jugador);
    public void eliminarJugador(Jugador jugador);
    public void modificarJugador(Jugador jugador);
    public List<Jugador> getTodosLosJugadores();
    public Jugador buscarPorId(String id);
}
