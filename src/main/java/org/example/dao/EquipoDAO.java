package org.example.dao;

import org.example.domain.Equipo;

import java.util.Set;

public interface EquipoDAO {
    public boolean insertarEquipo(Equipo equipo);
    public boolean eliminarEquipo(String id);
    public boolean modificarEquipo(String id, String entrenador);
    public Set<Equipo> getEquipos();
    public Equipo buscarPorId(String id);
    public Set<Equipo> buscarPorCiudad(String ciudad);
}
