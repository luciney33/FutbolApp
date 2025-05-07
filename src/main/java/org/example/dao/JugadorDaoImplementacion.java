package org.example.dao;

import org.example.domain.Jugador;

import java.util.List;

public class JugadorDaoImplementacion implements JugadorDAO{

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

    @Override
    public void verEstadisticas() {

    }

    @Override
    public int calcularEdad() {
        return 0;
    }

    @Override
    public double calcularPromedioGolesPorPartido(int totalPartidos) {
        return 0;
    }

    @Override
    public void incrementarGoles(int cantidad) {

    }

    @Override
    public void incrementarAsistencias(int cantidad) {

    }

    @Override
    public boolean haSuperadoA(Jugador otroJugador) {
        return false;
    }
}
