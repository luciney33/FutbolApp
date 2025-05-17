package org.example.service;

import org.example.dao.JugadorDAO;
import org.example.dao.JugadorDaoImplementacion;
import org.example.domain.Jugador;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;

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
        Jugador exite = jugadorDAO.buscarPorId(jugador.getId());
        if (exite == null){
            insertarSi = true;
            jugadorDAO.insertarJugador(jugador);
        }else {
            insertarSi = false;
        }
        return insertarSi;
    }

    @Override
    public boolean eliminarJugadorPorId(int id) {
        boolean eliminarSi = false;
        Jugador j = jugadorDAO.buscarPorId(id);
        if (j != null){
            eliminarSi = true;
            jugadorDAO.eliminarJugador(j);
        }else {
            eliminarSi = false;
        }
        return eliminarSi;
    }

    @Override
    public Jugador obtenerJugadorMasJoven() {
        return  jugadorDAO.getJugadores().stream().min(Comparator.comparing(Jugador::calcularEdad)).get();
    }

    @Override
    public Jugador obtenerJugadorMasGoleador() {
        return null;
    }

    @Override
    public Set<Jugador> listarJugadores() {
        return Set.of();
    }

    @Override
    public void mostrarEstadisticas() {

    }

    @Override
    public Set<Jugador> listarJugadoresPorEdadAscendente() {
        return Set.of();
    }

    @Override
    public Set<Jugador> filtrarPorEquipo(String nombreEquipo) {
        return Set.of();
    }

    @Override
    public Jugador buscarPorId(int id) {
        return null;
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
