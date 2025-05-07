package org.example.dao;

import org.example.domain.Equipo;

public class EquipoDaoImplementacion implements EquipoDAO{
    private Equipos lista;
    @Override
    public boolean insertarEquipo(Equipo equipo) {
        return lista.insertarEquipo(equipo);
    }

    @Override
    public void eliminarEquipo(Equipo equipo) {

    }

    @Override
    public void modificarEquipo() {

    }

    @Override
    public void listarEquipos() {

    }

    @Override
    public void listarJugador() {

    }
}
