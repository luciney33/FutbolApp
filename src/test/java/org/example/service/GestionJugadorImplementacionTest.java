package org.example.service;

import org.example.dao.JugadorDAO;
import org.example.dao.JugadorDaoImplementacion;
import org.example.domain.Jugador;

import java.io.IOException;
import java.util.Set;

public class GestionJugadorImplementacionTest implements GestionJugador{
    private JugadorDAO jugadorDAO;
    public GestionJugadorImplementacionTest(JugadorDAO jugadorDAO){
        this.jugadorDAO = jugadorDAO;
    }
    public GestionJugadorImplementacionTest() {
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
            //aqui tengo una duda, pondria un sout diciendo "el jugador ya exite"
            // pero se que aqui no esta bien poner mesnsajes seria mas en entrada y salida
        }
        return insertarSi;
    }

    @Override
    public boolean eliminarJugadorPorId(int id) {
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
