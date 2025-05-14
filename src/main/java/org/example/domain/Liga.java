package org.example.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Liga {
    private Set<Jugador> jugadores = new HashSet<>();
    private Set<Equipo> equipos = new HashSet<>();
    private Set<Partido> partidos = new HashSet<>();
}
