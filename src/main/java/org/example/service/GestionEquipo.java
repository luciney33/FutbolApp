package org.example.service;

import org.example.domain.Equipo;

import java.util.Optional;
import java.util.Set;

public interface GestionEquipo {
        public boolean insertarEquipo(Equipo equipo);

        public boolean eliminarEquipo(Equipo equipo);

        public Optional<Equipo> buscarPorId(int id);

        public Set<Equipo> listarEquiposOrdenadosPorNombre();

        public Optional<Equipo> buscarPorCiudad(String ciudad);

        public boolean modificarEntrenador(int id, String nuevoEntrenador);

        public Set<Equipo> listarEquipos();

}
