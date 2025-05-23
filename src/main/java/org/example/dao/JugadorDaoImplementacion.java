package org.example.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.ComprobacionId;
import org.example.common.ExcepcionIdErroneo;
import org.example.domain.Jugador;

import java.util.*;

public class JugadorDaoImplementacion implements JugadorDAO {
    private static final Logger log = LogManager.getLogger(JugadorDaoImplementacion.class);
    private Liga liga;

    public JugadorDaoImplementacion(Liga liga) {
        this.liga = liga;
    }

    public JugadorDaoImplementacion() {
        liga = new Liga();
    }

    @Override
    public Set<Jugador> getJugadores() {
        return liga.getJugadores();
    }

    @Override
    public boolean insertarJugador(Jugador jugador) throws ExcepcionIdErroneo {
        boolean insertarSi = false;
        Optional<Jugador> existe = buscarPorId(jugador.getId());
        if (existe.isEmpty()) {
            insertarSi = true;
            liga.getJugadores().add(jugador);
        } else {
            log.error("No existe el id");
            insertarSi = false;
        }
        return insertarSi;

    }

    @Override
    public boolean eliminarJugador(Jugador jugador) {
        return liga.getJugadores().remove(jugador);
    }

    @Override
    public void modificarJugador(int id, int goles, int asistencias, String equipo) {
        liga.getJugadores().stream().filter(j -> j.getId() == id).findAny().ifPresent(j ->
        {
            j.setGoles(goles);
            j.setAsistencias(asistencias);
            j.setEquipo(equipo);
        });

    }

    @Override
    public Optional<Jugador> buscarPorId(int id) throws ExcepcionIdErroneo {
        ComprobacionId.comprobarId(id);
        return liga.getJugadores().stream().filter(j -> j.getId() == id).findFirst();
    }
}
