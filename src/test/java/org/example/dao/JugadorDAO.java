package org.example.dao;

import org.example.domain.Jugador;

import java.util.Set;

public interface JugadorDAO {
    public Set<Jugador> getJugadores();
    public boolean insertarJugador(Jugador jugador);
    public boolean eliminarJugador(Jugador jugador);
    public void modificarJugador(int id, int goles, int asistencias, String equipo);
    public Jugador buscarPorId(int id);
}
