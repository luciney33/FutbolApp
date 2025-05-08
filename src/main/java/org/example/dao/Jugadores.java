package org.example.dao;

import org.example.domain.Jugador;

import java.util.List;

public class Jugadores {
    private final List<Jugador> jugadores;
    public Jugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    //duda a gema: esta clase es necesaria hacerla, o se puede hacer en conjunto con la de equipos ya que al crear el array
    //se puede generar tambien datos aleatorios de jugadores con el datafaker
}
