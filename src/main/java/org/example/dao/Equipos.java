package org.example.dao;

import org.example.domain.Equipo;

import java.util.Set;

public class Equipos {
    private final Set<Equipo> equipos;
    public Equipos(Set<Equipo> equipos) {
        this.equipos = equipos;
    }

    public boolean insertarEquipo(Equipo equipo) {
        return equipos.add(equipo);
    }
}
