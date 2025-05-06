package org.example.domain;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Data
public class Jugador {
    private String id;
    private String nombre;
    private String equipo;
    private int goles;
    private int asistencias;
    private LocalDate fechaNac;
    private String posicion;

    public int calcularEdad(){
        LocalDate fechaAct = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(fechaAct, fechaNac);
    }
    public double calcularPromedioGolesPorPartido(int totalPartidos){
        double promedio = 0;
        if(totalPartidos > 0){
            promedio = (double) goles / totalPartidos;
        }else{
            System.out.println("Ingrese los partidos");
        }
        return promedio;
    }
    public void incrementarGoles(int cantidad){}
    public void incrementarAsistencias(int cantidad){}

    public boolean haSuperadoA(Jugador otroJugador){
        boolean superado = false;
        if(goles.)
    }

}