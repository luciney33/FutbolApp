package org.example.dao;

import org.example.domain.Equipo;
import org.example.domain.Liga;

import java.util.Set;

public class EquipoDaoImplementacion implements EquipoDAO{
    private Liga liga;
    public EquipoDaoImplementacion(Liga liga) {
        this.liga = liga;
    }

    @Override
    public Set<Equipo> getEquipos() {
        return liga.getEquipos();
    }

    @Override
    public boolean insertarEquipo(Equipo equipo) {
        return liga.getEquipos().add(equipo);
    }

    @Override
    public boolean eliminarEquipo(String id) {
        return liga.getEquipos().remove(id);
    }

    @Override
    public boolean modificarEquipo(String id, String entrenador) {
        return false;
    }

    @Override
    public Equipo buscarPorId(String id) {
        return null;
    }

    @Override
    public Set<Equipo> buscarPorCiudad(String ciudad) {
        return Set.of();
    }
}
