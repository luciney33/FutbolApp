package dao;

import org.example.dao.EquipoDAO;
import org.example.domain.Equipo;
import org.example.service.GestionEquipoImplementacion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class GestionEquipoImplementacionTest {

    @Mock
    EquipoDAO equipoDAO;

    @InjectMocks
    GestionEquipoImplementacion gestionEquipo;

    Equipo equipo1 = new Equipo(1, "Atleti", "Madrid", "Estadio Atleti", "Entrenador1");
    Equipo equipo2 = new Equipo(2, "Madrid", "Madrid", "Estadio Madrid","Entrenador2");
    Equipo equipo3 = new Equipo(3, "Barça", "Barcelona", "Estadio Barcelona", "Entrenador3");

    @Test
    void insertarEquipo_devuelveFalse_siEquipoYaExiste() {
        when(equipoDAO.buscarPorId(anyInt())).thenReturn(Optional.of(equipo1));

        boolean resultado = gestionEquipo.insertarEquipo(equipo1);

        assertFalse(resultado);
        verify(equipoDAO, never()).insertarEquipo(any());
    }

    @Test
    void listarEquiposOrdenadosPorNombre_devuelveEquiposOrdenados() {
        when(equipoDAO.getEquipos()).thenReturn(Set.of(equipo2, equipo3, equipo1));

        Set<Equipo> resultado = gestionEquipo.listarEquiposOrdenadosPorNombre();

        List<Equipo> listaOrdenada = new ArrayList<>(resultado);

        assertThat(listaOrdenada.get(0).getNombre()).isEqualTo("Atleti");
        assertThat(listaOrdenada.get(1).getNombre()).isEqualTo("Barça");
        assertThat(listaOrdenada.get(2).getNombre()).isEqualTo("Madrid");
    }

    @Test
    void eliminarEquipo_devuelveTrue() {
        when(equipoDAO.eliminarEquipo(equipo1)).thenReturn(true);

        boolean resultado = gestionEquipo.eliminarEquipo(equipo1);

        assertTrue(resultado);
        verify(equipoDAO).eliminarEquipo(equipo1);
    }

    @Test
    void buscarPorCiudad_devuelveEquipoCorrecto() {
        when(equipoDAO.getEquipos()).thenReturn(Set.of(equipo1, equipo2, equipo3));

        Optional<Equipo> resultado = gestionEquipo.buscarPorCiudad("Madrid");

        assertTrue(resultado.isPresent());
        assertThat(resultado.get().getCiudad()).isEqualToIgnoringCase("Madrid");
    }

    @Test
    void modificarEntrenador_devuelveTrue_siEquipoExistia() {
        when(equipoDAO.modificarEquipo(equipo1.getId(), "NuevoEntrenador")).thenReturn(true);

        boolean resultado = gestionEquipo.modificarEntrenador(equipo1.getId(), "NuevoEntrenador");

        assertTrue(resultado);
        verify(equipoDAO).modificarEquipo(equipo1.getId(), "NuevoEntrenador");
    }

    @Test
    void listarEquipos_devuelveTodosLosEquipos() {
        when(equipoDAO.getEquipos()).thenReturn(Set.of(equipo1, equipo2, equipo3));

        Set<Equipo> resultado = gestionEquipo.listarEquipos();

        assertEquals(3, resultado.size());
        assertTrue(resultado.contains(equipo1));
        assertTrue(resultado.contains(equipo2));
        assertTrue(resultado.contains(equipo3));
    }
}
