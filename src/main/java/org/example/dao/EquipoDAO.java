package org.example.dao;

import org.example.domain.Equipo;

public interface EquipoDAO {
    public boolean insertarEquipo(Equipo equipo);
    public void eliminarEquipo(Equipo equipo);
    public void modificarEquipo();
    public void listarEquipos();
    public void listarJugador();
}
