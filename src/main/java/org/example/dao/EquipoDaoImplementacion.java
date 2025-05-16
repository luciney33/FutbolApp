package org.example.dao;

import org.example.domain.Equipo;
import org.example.domain.Liga;

import java.util.Set;
import java.util.stream.Collectors;

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
    public boolean eliminarEquipo(Equipo equipo) {
        return liga.getEquipos().remove(equipo);
    }

    @Override
    public void modificarEquipo(int id, String entrenador) {
        liga.getEquipos().stream().filter(e->e.getId() == id).findAny().ifPresent(e ->
        {
            e.setEntrenador(entrenador);
        });
    }

    @Override
    public Equipo buscarPorId(int id) {
        return liga.getEquipos().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<Equipo> buscarPorCiudad(String ciudad) {
        return liga.getEquipos().stream().filter(e -> e.getCiudad().equals(ciudad)).collect(Collectors.toSet());
    }
}
