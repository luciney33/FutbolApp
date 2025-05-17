package org.example.dao;

import lombok.Data;
import org.example.domain.Equipo;
import org.example.domain.Jugador;
import org.example.domain.Partido;

import java.util.HashSet;
import java.util.Set;

@Data
public class Liga {
    private Set<Jugador> jugadores = new HashSet<>();//lo puedo hacer con faker
    private Set<Equipo> equipos = new HashSet<>();//lo puedo hacer con fichero
    private Set<Partido> partidos = new HashSet<>();
    public Liga(){
        //o fichero o faker
       // jugadores = DaoFicheros.leerJugadores();

    }
}
