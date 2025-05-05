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

    public void sumarGol(){

    }
    public void sumarAsistencia(){}
   // public int getEdad(){
    //}
    public void reiniciarEstadisticas(){}

}