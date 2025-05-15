package org.example.domain;
import lombok.Data;
@Data
public class Equipo {
        private int id;
        private String nombre;
        private String estadio;
        private String entrenador;

        public Equipo(int id, String nombre, String estadio, String entrenador) {
                this.id = id;
                this.nombre = nombre;
                this.estadio = estadio;
                this.entrenador = entrenador;
        }
}
