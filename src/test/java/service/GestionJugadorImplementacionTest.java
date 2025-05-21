package service;

import org.example.dao.JugadorDAO;
import org.example.domain.Jugador;
import org.example.service.GestionJugadorImplementacion;
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

public class GestionJugadorImplementacionTest{
    @Mock
    JugadorDAO jugadorDAO;

    @InjectMocks
    GestionJugadorImplementacion gestionJugador;

    Jugador jugador = new Jugador(1,"David","Atleti",12,5, LocalDate.of(1999, 5, 12),"delantero");
    Jugador jugador2 = new Jugador(1, "Pepe", "Atleti", 18, 10, LocalDate.of(2000, 3, 22), "delantero");
    Jugador jugador3 = new Jugador(2, "Juan", "Madrid", 20, 3, LocalDate.of(1998, 8, 10), "defensa");

    @Test
    void insertarJugador_devuelveFalse_siJugadorYaExiste() {
        when(jugadorDAO.buscarPorId(anyInt())).thenReturn(Optional.of(jugador));

        boolean resultado = gestionJugador.insertarJugador(jugador);

        assertFalse(resultado);
        verify(jugadorDAO, never()).insertarJugador(any());
    }
    @Test
    void filtrarPorEquipo_devuelveSoloJugadoresDelEquipoEspecificado() {
        when(jugadorDAO.getJugadores()).thenReturn(Set.of(jugador2, jugador3));

        Set<Jugador> resultado = gestionJugador.filtrarPorEquipo("Atleti");

        assertTrue(resultado.contains(jugador));
        assertFalse(resultado.contains(jugador2));
    }

    @Test
    void eliminarJugador_delDAO() {
        when(jugadorDAO.eliminarJugador(jugador)).thenReturn(true);

        boolean resultado = gestionJugador.eliminarJugador(jugador);

        assertTrue(resultado);
        verify(jugadorDAO).eliminarJugador(jugador);
    }
    @Test
    void obtenerJugadorMasGoleador_devuelveJugadorConMasGoles() {
        when(jugadorDAO.getJugadores()).thenReturn(Set.of(jugador,jugador2, jugador3));

        Jugador j = gestionJugador.obtenerJugadorMasGoleador();

        assertTrue(j.getGoles()==20);
    }

    @Test
    void obtenerJugadorMasJoven_devuelveJugadorConMenorEdad() {

        when(jugadorDAO.getJugadores()).thenReturn(Set.of(jugador, jugador2, jugador3));

        Jugador masJoven = gestionJugador.obtenerJugadorMasJoven();

        assertThat(masJoven.getNombre()).isEqualTo(jugador2.getNombre());
    }

    @Test
    void listarJugadoresPorEdadAscendente_devuelveJugadoresOrdenados() {

        when(jugadorDAO.getJugadores()).thenReturn(Set.of(jugador, jugador2, jugador3));

        Set<Jugador> resultado = gestionJugador.listarJugadoresPorEdadAscendente();
        List<Jugador> listaOrdenada = new ArrayList<>(resultado);

        assertThat(listaOrdenada.get(0)).isEqualTo(jugador3.getNombre());
        assertThat(listaOrdenada.get(1)).isEqualTo(jugador.getNombre());
        assertThat(listaOrdenada.get(2)).isEqualTo(jugador2.getNombre());
    }

}
