package org.example.dao;

import org.example.domain.Jugador;

import java.util.List;

public interface JugadorDA0 {
    public void añadirJugador(Jugador jugador);
    public void eliminarJugador(Jugador jugador);
    public void modificarJugador(Jugador jugador);
    public List<Jugador> getTodosLosJugadores();
    public Jugador buscarPorId(String id);
    public void verEstadisticas();
    public int calcularEdad();
    public double calcularPromedioGolesPorPartido(int totalPartidos);
    public void incrementarGoles(int cantidad);
    public void incrementarAsistencias(int cantidad);
    public boolean haSuperadoA(Jugador otroJugador);
}
