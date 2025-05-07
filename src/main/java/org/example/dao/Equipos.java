package org.example.dao;

import org.example.domain.Equipo;

import java.util.List;

public class Equipos {
    private final List<Equipo> equipos;
    public Equipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public boolean insertarEquipo(Equipo equipo) {
        return equipos.add(equipo);
    }
}
