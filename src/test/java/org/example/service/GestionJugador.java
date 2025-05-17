package org.example.service;

import org.example.domain.Jugador;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface GestionJugador{
    public boolean insertarJugadorSiNoExiste(Jugador jugador);
    public boolean eliminarJugadorPorId(int id);
    public Jugador obtenerJugadorMasJoven();
    public Jugador obtenerJugadorMasGoleador();
    public Set<Jugador> listarJugadores();
    public void mostrarEstadisticas();
    public Set<Jugador> listarJugadoresPorEdadAscendente();
    public Set<Jugador> filtrarPorEquipo(String nombreEquipo);
    public Jugador buscarPorId(int id);
    public void crearFicheros() throws IOException;
    public boolean cargarFichero() throws IOException;
    public boolean escribirFichero() throws IOException;
}
