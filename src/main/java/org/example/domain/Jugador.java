package org.example.domain;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Jugador {
    private String id;
    private String nombre;
    private String equipo;
    private int goles;
    private int asistencias;
    private LocalDate fechaNac;
    private String posicion;

    public int calcularEdad();
    public double calcularPromedioGolesPorPartido(int totalPartidos);
    public void incrementarGoles(int cantidad);
    public void incrementarAsistencias(int cantidad);
    public boolean haSuperadoA(Jugador otroJugador);

}