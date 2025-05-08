package org.example.service;

import org.example.domain.Jugador;

import java.util.List;

public class GestionJugadorImplementacion implements GestionJugador{
    @Override
    public boolean insertarJugadorSiNoExiste(Jugador jugador) {
        return false;
    }

    @Override
    public boolean eliminarJugadorPorId(String id) {
        return false;
    }

    @Override
    public Jugador obtenerJugadorMasJoven() {
        return null;
    }

    @Override
    public Jugador obtenerJugadorMasGoleador() {
        return null;
    }

    @Override
    public List<Jugador> listarJugadores() {
        return List.of();
    }

    @Override
    public void mostrarEstadisticas() {

    }

    @Override
    public List<Jugador> listarJugadoresPorEdadAscendente() {
        return List.of();
    }

    @Override
    public List<Jugador> filtrarPorEquipo(String nombreEquipo) {
        return List.of();
    }

    @Override
    public Jugador buscarPorId(String id) {
        return null;
    }
}
