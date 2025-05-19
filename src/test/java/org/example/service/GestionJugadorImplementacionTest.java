package org.example.service;

import org.example.dao.JugadorDAO;
import org.example.domain.Jugador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class GestionJugadorImplementacionTest{
    @Mock
    JugadorDAO jugadorDAO;

    @InjectMocks
    GestionJugadorImplementacion gestionJugador;

    Jugador jugador = new Jugador(1,"Pepe","Atleti",12,5, LocalDate.of(1999, 5, 12),"delantero");

    @Test
    void insertarJugadorSiNoExiste_devuelveTrue_siJugadorNoExiste() {
        when(jugadorDAO.buscarPorId(1)).thenReturn(Optional.empty());  // Cambiado null por Optional.empty()
        when(jugadorDAO.insertarJugador(jugador)).thenReturn(true);

        boolean resultado = gestionJugador.insertarJugadorSiNoExiste(jugador);

        assertTrue(resultado);
        verify(jugadorDAO).insertarJugador(jugador);
    }

    @Test
    void insertarJugadorSiNoExiste_devuelveFalse_siJugadorYaExiste() {
        when(jugadorDAO.buscarPorId(anyInt())).thenReturn(Optional.of(jugador));

        boolean resultado = gestionJugador.insertarJugadorSiNoExiste(jugador);

        assertFalse(resultado);
        verify(jugadorDAO, never()).insertarJugador(any());
    }

}
