package org.example.dao;

import org.example.domain.Jugador;

public interface JugadorDA0 {
    public void añadirJugador(Jugador jugador);
    public void eliminarJugador(Jugador jugador);
    public void modificarJugador(Jugador jugador);
    public void verEstadisticas();
    public int calcularEdad();
}
