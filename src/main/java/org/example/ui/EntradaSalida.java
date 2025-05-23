package org.example.ui;

import org.example.common.*;
import org.example.dao.DaoFicheros;
import org.example.domain.DatosAleatorios;
import org.example.domain.Jugador;
import org.example.domain.Usuario;
import org.example.service.GestionEquipo;
import org.example.service.GestionEquipoImplementacion;
import org.example.service.GestionJugador;
import org.example.service.GestionJugadorImplementacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EntradaSalida {
    private  GestionJugador gestionJugador;
    private GestionEquipo gestionEquipo;
    private DaoFicheros daoFicheros;
    private List<Usuario> usuariosRegistrados;
    private DatosAleatorios datosAleatorios;
    private Scanner sc;

    public EntradaSalida(GestionJugador gestionJugador, GestionEquipo gestionEquipo, DaoFicheros daoFicheros, Usuario usuariosRegistrados, Scanner sc, DatosAleatorios datosAleatorios) {
        this.gestionJugador = gestionJugador;
        this.gestionEquipo = gestionEquipo;
        this.daoFicheros = daoFicheros;
        this.usuariosRegistrados = daoFicheros.leerUsuariosBinario();
        this.sc = new Scanner(System.in);
        this.datosAleatorios = datosAleatorios;
    }
    public EntradaSalida() {
        this.gestionJugador = new GestionJugadorImplementacion();
        this.gestionEquipo = new GestionEquipoImplementacion();
        this.daoFicheros = new DaoFicheros();
        this.usuariosRegistrados = daoFicheros.leerUsuariosBinario();
        this.sc = new Scanner(System.in);
        this.datosAleatorios = new DatosAleatorios();
    }

    private int leerEntero(String mensaje) {
        int numero = -1;
        boolean valido = false;
        do {
            System.out.print(mensaje + " ");
            try {
                numero = sc.nextInt();
                valido = true;
            } catch (NumberFormatException e) {
                mostrarError(Constantes.OPCION_INVALIDA);
            }
        } while (!valido);
        return numero;
    }

    private static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private static void mostrarError(String mensaje) {
        System.out.println(mensaje);
    }

    private static void mostrarSeparador(String mensaje) {
        System.out.println("\n" + mensaje);
    }

    private static void registrarUsuario(){
        Scanner sc = new Scanner(System.in);
        List<Usuario> listaUsuarios = DaoFicheros.leerUsuariosBinario();
        mostrarMensaje(Constantes.USU);
        String nombreUsuario = sc.nextLine();
    }

    private boolean loginAdmin() {
        mostrarMensaje(Constantes.USU);
        String user = sc.nextLine();
        mostrarMensaje(Constantes.CONTRA);
        String contra = sc.nextLine();
        return user.equals("admin") && contra.equals("1234");
    }

    public void menuPrincipal() throws ExcepcionIdErroneo {
        boolean salir = false;
        mostrarMensaje(Constantes.BIENVENIDA);
        while (!salir) {
            mostrarSeparador(Constantes.SEPARADOR);
            mostrarMensaje(Constantes.MENU_ROL);
            int opc = leerEntero(Constantes.ELEGIR_OPCION);

            switch (opc) {
                case 1 :
                    if(loginAdmin()){
                        menuAdministrador();
                    }else {
                        mostrarError(Constantes.ERROR_INICIOSESION);
                    }
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

    private void menuAdministrador() throws ExcepcionIdErroneo {
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
                    try {
                        ComprobacionId.comprobarId(id);
                    } catch (ExcepcionIdErroneo e) {
                        mostrarError(Constantes.ID_INVALIDO);
                        System.out.println("Error capturado: " + e.getMessage());
                    }
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

    private void menuUsuario() throws ExcepcionIdErroneo {
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
                    try {
                        ComprobacionId.comprobarId(id);
                    } catch (ExcepcionIdErroneo e) {
                        mostrarError(Constantes.ID_INVALIDO);
                        System.out.println("Error capturado: " + e.getMessage());
                    }
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
    private void insertarJugadorManual() throws ExcepcionIdErroneo {
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

}

