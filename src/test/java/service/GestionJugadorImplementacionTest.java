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
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class GestionJugadorImplementacionTest{
    @Mock
    JugadorDAO jugadorDAO;

    @InjectMocks
    GestionJugadorImplementacion gestionJugador;

    Jugador jugador = new Jugador(1,"David","Atleti",12,5, LocalDate.of(1999, 5, 12),"delantero");
    Jugador jugador2 = new Jugador(1, "Pepe", "Atleti", 12, 5, LocalDate.of(1999, 5, 12), "delantero");
    Jugador jugador3 = new Jugador(2, "Juan", "Madrid", 20, 3, LocalDate.of(1998, 8, 10), "defensa");

    @Test
    void insertarJugadorSiNoExiste_devuelveFalse_siJugadorYaExiste() {
        when(jugadorDAO.buscarPorId(anyInt())).thenReturn(Optional.of(jugador));

        boolean resultado = gestionJugador.insertarJugadorSiNoExiste(jugador);

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

}
