package org.example.ui;

import org.example.common.Constantes;
import org.example.common.ExcepcionAsistencias;
import org.example.common.ExcepcionGoles;
import org.example.dao.*;
import org.example.domain.DatosAleatorios;
import org.example.domain.Jugador;
import org.example.service.GestionJugador;
import org.example.service.GestionJugadorImplementacion;

import java.time.LocalDate;
import java.util.Scanner;

public class EntradaSalida {
    private  GestionJugador gestionJugador;
    private DatosAleatorios datosAleatorios;
    private Scanner sc;

    public EntradaSalida(GestionJugador gestionJugador, Scanner sc, DatosAleatorios datosAleatorios) {
        this.gestionJugador = gestionJugador;
        this.sc = new Scanner(System.in);
        this.datosAleatorios = datosAleatorios;
    }
    public EntradaSalida() {
        this.gestionJugador = new GestionJugadorImplementacion();
        this.sc = new Scanner(System.in);
        this.datosAleatorios = new DatosAleatorios();
    }

    public void menuPrincipal() {
        boolean salir = false;
        mostrarMensaje(Constantes.BIENVENIDA);
        while (!salir) {
            mostrarSeparador(Constantes.SEPARADOR);
            mostrarMensaje(Constantes.MENU_ROL);
            int opc = leerEntero(Constantes.ELEGIR_OPCION);

            switch (opc) {
                case 1 :
                    menuAdministrador();
                    break;
                case 2 :
                    menuUsuario();
                    break;
                case 0 :
                    salir = true;
                    mostrarMensaje(Constantes.SALIR_APP);
                default :
                    mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
        sc.close();
    }

    private void menuAdministrador() {
        boolean volver = false;
        while (!volver) {
            mostrarSeparador(Constantes.SEPARADOR);
            mostrarMensaje(Constantes.MENU_ADMIN);
            int opcion = leerEntero(Constantes.ELEGIR_OPCION);

            switch (opcion) {
                case 1 :
                    Jugador nuevo = datosAleatorios.crearJugadores();
                    gestionJugador.insertarJugador(nuevo);
                    mostrarMensaje(Constantes.JUGADOR_INSERTADO);
                    break;
                case 2 :
                    gestionJugador.listarJugadores().forEach(j -> mostrarMensaje(j.toString()));
                    break;
                case 3 :
                    int id = leerEntero(Constantes.PIDE_ID_JUGADOR);
                    gestionJugador.buscarPorId(id).ifPresentOrElse(j -> {
                        int goles = leerEntero(Constantes.PIDE_GOLES);
                        int asistencias = leerEntero(Constantes.PIDE_ASISTENCIAS);
                        try {
                            j.incrementarGoles(goles);
                            mostrarMensaje(Constantes.GOLES_ACTUALIZADOS);
                        } catch (ExcepcionGoles e) {
                            mostrarError(e.getMessage());
                        }
                        try {
                            j.incrementarAsistencias(asistencias);
                            mostrarMensaje(Constantes.ASISTENCIAS_ACTUALIZADAS);
                        } catch (ExcepcionAsistencias e) {
                            mostrarError(e.getMessage());
                        }
                    }, () -> mostrarError(Constantes.JUGADOR_NO_ENCONTRADO));
                    break;
                case 4:
                    insertarJugadorManual();
                case 0 :
                    volver = true;
                    break;
                default :
                    mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
    }

    private void menuUsuario() {
        boolean volver = false;
        while (!volver) {
            mostrarSeparador(Constantes.SEPARADOR);
            mostrarMensaje(Constantes.MENU_USUARIO);
            int opc = leerEntero(Constantes.ELEGIR_OPCION);

            switch (opc) {
                case 1 :
                    gestionJugador.listarJugadores().forEach(j -> mostrarMensaje(j.toString()));
                    break;
                case 2:
                    int id = leerEntero(Constantes.PIDE_ID_JUGADOR);
                    gestionJugador.buscarPorId(id).ifPresentOrElse(j -> {mostrarMensaje(j.toString());},() -> mostrarError(Constantes.JUGADOR_NO_ENCONTRADO)
                    );
                    break;
                case 3 :
                    insertarJugadorManual();
                    break;
                case 0 :
                    volver = true;
                    break;
                default :
                    mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
    }
    private void insertarJugadorManual() {
        System.out.println("Introduce ID del jugador:");
        int id = leerEntero("");

        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        System.out.println("Equipo:");
        String equipo = sc.nextLine();

        System.out.println("Goles:");
        int goles = leerEntero("");

        System.out.println("Asistencias:");
        int asistencias = leerEntero("");

        System.out.println("Posición:");
        String posicion = sc.nextLine();

        LocalDate fechaNac = LocalDate.of(2000, 1, 1);

        Jugador nuevo = new Jugador(id, nombre, equipo, goles, asistencias, fechaNac, posicion);

        boolean insertado = gestionJugador.insertarJugador(nuevo);
        if (insertado) {
            mostrarMensaje("Jugador insertado correctamente.");
        } else {
            mostrarError("Ya existe un jugador con ese ID.");
        }
    }


    private int leerEntero(String mensaje) {
        int numero = -1;
        boolean valido = false;
        do {
            System.out.print(mensaje + " ");
            try {
                numero = Integer.parseInt(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                mostrarError(Constantes.OPCION_INVALIDA);
            }
        } while (!valido);
        return numero;
    }

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private void mostrarError(String mensaje) {
        System.err.println(mensaje);
    }

    private void mostrarSeparador(String mensaje) {
        System.out.println("\n" + mensaje);
    }
}

