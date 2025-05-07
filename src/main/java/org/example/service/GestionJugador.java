package org.example.service;

import org.example.domain.Jugador;

import java.util.List;

public interface GestionJugador{
    public boolean insertarJugadorSiNoExiste(Jugador jugador);
    public boolean eliminarJugadorPorId(String id);
    public Jugador obtenerJugadorMasJoven();
    public Jugador obtenerJugadorMasGoleador();
    public List<Jugador> listarJugadores();
    public void mostrarEstadisticas();
}
