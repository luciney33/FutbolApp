package org.example.service;

import org.example.domain.Jugador;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public interface GestionJugador{
    public boolean insertarJugadorSiNoExiste(Jugador jugador);
    public boolean eliminarJugador(Jugador jugador);
    public Jugador obtenerJugadorMasJoven();
    public Jugador obtenerJugadorMasGoleador();
    public Set<Jugador> listarJugadores();
    public void mostrarEstadisticas();
    public Set<Jugador> listarJugadoresPorEdadAscendente();
    public Set<Jugador> filtrarPorEquipo(String nombreEquipo);
    public Optional<Jugador> buscarPorId(int id);
    public void crearFicheros() throws IOException;
    public boolean cargarFichero() throws IOException;
    public boolean escribirFichero() throws IOException;
}
