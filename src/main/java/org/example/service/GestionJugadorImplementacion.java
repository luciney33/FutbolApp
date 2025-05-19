package org.example.service;

import org.example.dao.JugadorDAO;
import org.example.dao.JugadorDaoImplementacion;
import org.example.domain.Jugador;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GestionJugadorImplementacion implements GestionJugador{
    private JugadorDAO jugadorDAO;

    public GestionJugadorImplementacion(JugadorDAO jugadorDAO){
        this.jugadorDAO = jugadorDAO;
    }
    public GestionJugadorImplementacion() {
        this.jugadorDAO = new JugadorDaoImplementacion();
    }
    @Override
    public boolean insertarJugadorSiNoExiste(Jugador jugador) {
        boolean insertarSi = false;
        Optional<Jugador> existe = jugadorDAO.buscarPorId(jugador.getId());
        if (existe.isEmpty()) {
            insertarSi = true;
            jugadorDAO.insertarJugador(jugador);
        } else {
            insertarSi = false;
        }
        return insertarSi;
    }

    @Override
    public boolean eliminarJugador(Jugador jugador) {
        return jugadorDAO.eliminarJugador(jugador);
    }

    @Override
    public Jugador obtenerJugadorMasJoven() {
        return  jugadorDAO.getJugadores().stream().min(Comparator.comparing(Jugador::calcularEdad)).get();
    }

    @Override
    public Jugador obtenerJugadorMasGoleador() {
        return jugadorDAO.getJugadores().stream().max(Comparator.comparingInt(Jugador::getGoles)).get();
    }

    @Override
    public Set<Jugador> listarJugadores() {
        return jugadorDAO.getJugadores();
    }

    @Override
    public void mostrarEstadisticas() {

    }

    @Override
    public Set<Jugador> listarJugadoresPorEdadAscendente() {
        return jugadorDAO.getJugadores().stream().sorted(Comparator.comparing(Jugador::calcularEdad)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<Jugador> filtrarPorEquipo(String nombreEquipo) {
        return jugadorDAO.getJugadores().stream().filter(j -> j.getEquipo().equalsIgnoreCase(nombreEquipo) ).collect(Collectors.toSet());
    }

    @Override
    public Optional<Jugador> buscarPorId(int id) {
        return jugadorDAO.buscarPorId(id);
    }

    @Override
    public void crearFicheros() throws IOException {

    }

    @Override
    public boolean cargarFichero() throws IOException {
        return false;
    }

    @Override
    public boolean escribirFichero() throws IOException {
        return false;
    }
}
