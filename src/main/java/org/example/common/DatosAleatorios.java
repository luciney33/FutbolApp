package org.example.common;

import net.datafaker.Faker;

import java.util.Random;

public class DatosAleatorios {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Random r = new Random();

        String id = String.valueOf(r.nextInt(20));
        String nombre = faker.football().players();
        String equipo = faker.football().teams();
        int goles = r.nextInt(0,40);

    }
}
