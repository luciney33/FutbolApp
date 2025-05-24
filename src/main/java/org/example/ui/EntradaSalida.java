package org.example.ui;

import org.example.common.*;
import org.example.dao.DaoFicheros;
import org.example.domain.DatosAleatorios;
import org.example.domain.Equipo;
import org.example.domain.Jugador;
import org.example.domain.Usuario;
import org.example.service.GestionEquipo;
import org.example.service.GestionEquipoImplementacion;
import org.example.service.GestionJugador;
import org.example.service.GestionJugadorImplementacion;

import javax.swing.plaf.IconUIResource;
import java.time.LocalDate;
import java.util.*;

public class EntradaSalida {
    private static GestionJugador gestionJugador;
    private static GestionEquipo gestionEquipo;
    private static DaoFicheros daoFicheros;
    private static List<Usuario> usuariosRegistrados;
    private static DatosAleatorios datosAleatorios;
    private static Scanner sc;

    public EntradaSalida() {
        this.gestionJugador = new GestionJugadorImplementacion();
        this.gestionEquipo = new GestionEquipoImplementacion();
        this.daoFicheros = new DaoFicheros();
        this.usuariosRegistrados = daoFicheros.leerUsuariosBinario();
        this.sc = new Scanner(System.in);
        this.datosAleatorios = new DatosAleatorios();
        DaoFicheros.crearFicheroAdminPorDefecto();
    }

    private void mostrarEstadisticasJugadorUI() throws ExcepcionIdErroneo {
        int id = leerEntero("Introduce el ID del jugador para ver estadísticas:");
        String estadisticas = gestionJugador.mostrarEstadisticasJugador(id);
        mostrarMensaje(estadisticas);
    }
    private static int leerEntero(String mensaje) {
        int numero = -1;
        boolean valido = false;
        do {
            System.out.print(mensaje + " ");
            try {
                numero = sc.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                mostrarError(Constantes.OPCION_INVALIDA);
                sc.nextLine();
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
        boolean existe = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario u = listaUsuarios.get(i);
            if (u.getNombreUsu().equals(nombreUsuario)){
                existe=true;
            }
        }
        if (!existe){
            mostrarMensaje(Constantes.CONTRA);
            String contra = sc.nextLine();
            Usuario nuevo = new Usuario(nombreUsuario, contra);
            listaUsuarios.add(nuevo);
            DaoFicheros.escribirUsuariosBinario(listaUsuarios);
            mostrarMensaje(Constantes.REGISTRADO);
        }else{
            mostrarError(Constantes.ERROR_INICIOSESION2);
        }
    }

    private static Usuario iniciarSesion(){
        Scanner sc = new Scanner(System.in);
        List<Usuario> listaUsuarios = DaoFicheros.leerUsuariosBinario();
        mostrarMensaje(Constantes.USU);
        String nommbreUsu = sc.nextLine();
        mostrarMensaje(Constantes.CONTRA);
        String contra = sc.nextLine();
        for (int i = 0; i < listaUsuarios.size(); i++) {
           Usuario u = listaUsuarios.get(i);
           if (u.getNombreUsu().equals(nommbreUsu) && u.getContraseya().equals(contra)){
               mostrarMensaje(Constantes.INICIOSESION + nommbreUsu);
               return u;
           }
        }
        mostrarError(Constantes.ERROR_INICIOSESION);
        return null;

    }
    private static boolean loginAdmin() {
        Scanner sc = new Scanner(System.in);
        List<Usuario> admins = DaoFicheros.leerAdmiBinario();
        boolean coincide = false;

        if (admins.isEmpty()) {
            mostrarError(Constantes.ERROR_ADMI);
        } else {
            mostrarMensaje(Constantes.USU);
            String nombre = sc.nextLine();
            mostrarMensaje(Constantes.CONTRA);
            String contra = sc.nextLine();

            Usuario admin = admins.get(0); //solo hay uno

            if (admin.getNombreUsu().equals(nombre) && admin.getContraseya().equals(contra)) {
                mostrarMensaje(Constantes.INICIOSESION);
                coincide = true;
            } else {
                mostrarError(Constantes.ERROR_INICIOSESION);
            }
        }

        return coincide;
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
                    break;
                default :
                    mostrarError(Constantes.OPCION_INVALIDA);
                    break;
            }
        }
    }

    private void menuAdministrador() throws ExcepcionIdErroneo {
        boolean volver = false;
        while (!volver) {
            mostrarSeparador(Constantes.SEPARADOR);
            mostrarMensaje(Constantes.MENU_ADMIN1);
            int opcion = leerEntero(Constantes.ELEGIR_OPCION);

            switch (opcion) {
                case 1:
                    menuGestionJugadores();
                    break;
                case 2:
                    menuGestionEquipos();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
    }
    private void menuGestionJugadores() throws ExcepcionIdErroneo {
        boolean volver = false;
        while (!volver) {
            mostrarSeparador(Constantes.SEPARADOR);
            mostrarMensaje(Constantes.MENU_ADMIN2);
            int opcion = leerEntero(Constantes.ELEGIR_OPCION);

            switch (opcion) {
                case 1:
                    Jugador nuevo = datosAleatorios.crearJugadores();
                    gestionJugador.insertarJugador(nuevo);
                    mostrarMensaje(Constantes.JUGADOR_INSERTADO);
                    break;

                case 2:
                    gestionJugador.listarJugadores().forEach(j -> mostrarMensaje(j.toString()));
                    break;

                case 3: {
                    int id = leerEntero(Constantes.PIDE_ID_JUGADOR);
                    try {
                        ComprobacionId.comprobarId(id);
                    } catch (ExcepcionIdErroneo e) {
                        mostrarError(Constantes.ID_INVALIDO);
                        System.out.println("Error capturado: " + e.getMessage());
                        break;
                    }
                    gestionJugador.buscarPorId(id).ifPresentOrElse(j -> {
                        int goles = leerEntero(Constantes.PIDE_GOLES);
                        try {
                            j.incrementarGoles(goles);
                            mostrarMensaje(Constantes.GOLES_ACTUALIZADOS);
                        } catch (ExcepcionGoles e) {
                            mostrarError(e.getMessage());
                        }
                    }, () -> mostrarError(Constantes.JUGADOR_NO_ENCONTRADO));
                    break;
                }

                case 4:
                    insertarJugadorManual();
                    break;

                case 5: {
                    int idAsis = leerEntero(Constantes.PIDE_ID_JUGADOR);
                    try {
                        ComprobacionId.comprobarId(idAsis);
                    } catch (ExcepcionIdErroneo e) {
                        mostrarError(Constantes.ID_INVALIDO);
                        System.out.println("Error capturado: " + e.getMessage());
                        break;
                    }
                    gestionJugador.buscarPorId(idAsis).ifPresentOrElse(j -> {
                        int asistencias = leerEntero(Constantes.PIDE_ASISTENCIAS);
                        try {
                            j.incrementarAsistencias(asistencias);
                            mostrarMensaje(Constantes.ASISTENCIAS_ACTUALIZADAS);
                        } catch (ExcepcionAsistencias e) {
                            mostrarError(e.getMessage());
                        }
                    }, () -> mostrarError(Constantes.JUGADOR_NO_ENCONTRADO));
                    break;
                }

                case 6: {
                    Jugador joven = gestionJugador.obtenerJugadorMasJoven();
                    if (joven != null) {
                        mostrarMensaje("Jugador más joven:\n" + joven.toString());
                    } else {
                        mostrarError("No hay jugadores registrados.");
                    }

                    Jugador goleador = gestionJugador.obtenerJugadorMasGoleador();
                    if (goleador != null) {
                        mostrarMensaje("Jugador más goleador:\n" + goleador.toString());
                    } else {
                        mostrarError("No hay jugadores registrados.");
                    }
                    break;
                }

                case 7: {
                    int idEliminar = leerEntero(Constantes.PIDE_ID_JUGADOR);
                    try {
                        ComprobacionId.comprobarId(idEliminar);
                    } catch (ExcepcionIdErroneo e) {
                        mostrarError(Constantes.ID_INVALIDO);
                        break;
                    }
                    gestionJugador.buscarPorId(idEliminar).ifPresentOrElse(j -> {
                        boolean eliminado = gestionJugador.eliminarJugador(j);
                        if (eliminado) {
                            mostrarMensaje("Jugador eliminado correctamente.");
                        } else {
                            mostrarError("No se pudo eliminar el jugador.");
                        }
                    }, () -> mostrarError(Constantes.JUGADOR_NO_ENCONTRADO));
                    break;
                }

                case 8: {
                    Jugador joven = gestionJugador.obtenerJugadorMasJoven();
                    if (joven != null) {
                        mostrarMensaje("Jugador más joven:\n" + joven.toString());
                    } else {
                        mostrarError("No hay jugadores registrados.");
                    }
                    break;
                }

                case 9: {
                    Jugador goleador = gestionJugador.obtenerJugadorMasGoleador();
                    if (goleador != null) {
                        mostrarMensaje("Jugador más goleador:\n" + goleador.toString());
                    } else {
                        mostrarError("No hay jugadores registrados.");
                    }
                    break;
                }

                case 10: {
                    Set<Jugador> lista = gestionJugador.listarJugadoresPorEdadAscendente();
                    if (!lista.isEmpty()) {
                        lista.forEach(j -> mostrarMensaje(j.toString()));
                    } else {
                        mostrarError("No hay jugadores registrados.");
                    }
                    break;
                }

                case 11: {
                    filtrarJugadoresPorEquipo();
                    break;
                }

                case 12: {
                    int idBuscar = leerEntero(Constantes.PIDE_ID_JUGADOR);
                    try {
                        ComprobacionId.comprobarId(idBuscar);
                    } catch (ExcepcionIdErroneo e) {
                        mostrarError(Constantes.ID_INVALIDO);
                        break;
                    }
                    gestionJugador.buscarPorId(idBuscar).ifPresentOrElse(j -> mostrarMensaje(j.toString()),
                            () -> mostrarError(Constantes.JUGADOR_NO_ENCONTRADO));
                    break;
                }

                case 0:
                    volver = true;
                    break;

                default:
                    mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
    }
    private void menuGestionEquipos() throws ExcepcionIdErroneo {
        boolean volver = false;
        while (!volver) {
            mostrarSeparador(Constantes.SEPARADOR);
            mostrarMensaje(Constantes.MENU_ADMIN3);
            int opcion = leerEntero(Constantes.ELEGIR_OPCION);

            switch (opcion) {
                case 1:
                    insertarEquipoManual();
                    break;

                case 2:
                    Set<Equipo> equiposOrdenados = gestionEquipo.listarEquiposOrdenadosPorNombre();
                    if (!equiposOrdenados.isEmpty()) {
                        equiposOrdenados.forEach(e -> mostrarMensaje(e.toString()));
                    } else {
                        mostrarError("No hay equipos registrados.");
                    }
                    break;

                case 3: {
                    int id = leerEntero("Introduce ID del equipo a modificar:");
                    try {
                        ComprobacionId.comprobarId(id);
                    } catch (ExcepcionIdErroneo e) {
                        mostrarError("ID inválido.");
                        break;
                    }
                    System.out.println("Introduce nuevo entrenador:");
                    sc.nextLine(); // limpiar buffer
                    String nuevoEntrenador = sc.nextLine();

                    boolean modificado = gestionEquipo.modificarEntrenador(id, nuevoEntrenador);
                    if (modificado) {
                        mostrarMensaje("Entrenador modificado correctamente.");
                    } else {
                        mostrarError("Equipo no encontrado.");
                    }
                    break;
                }

                case 4: {
                    System.out.println("Introduce ciudad para buscar equipos:");
                    sc.nextLine(); // limpiar buffer
                    String ciudad = sc.nextLine();
                    Optional<Equipo> equipo = gestionEquipo.buscarPorCiudad(ciudad);
                    equipo.ifPresentOrElse(
                            e -> mostrarMensaje(e.toString()),
                            () -> mostrarError("No se encontró equipo en la ciudad: " + ciudad)
                    );
                    break;
                }

                case 5: {
                    int idEliminar = leerEntero("Introduce ID del equipo a eliminar:");
                    try {
                        ComprobacionId.comprobarId(idEliminar);
                    } catch (ExcepcionIdErroneo e) {
                        mostrarError("ID inválido.");
                        break;
                    }
                    Optional<Equipo> equipoEliminar = gestionEquipo.buscarPorId(idEliminar);
                    equipoEliminar.ifPresentOrElse(equipo -> {
                        boolean eliminado = gestionEquipo.eliminarEquipo(equipo);
                        if (eliminado) {
                            mostrarMensaje("Equipo eliminado correctamente.");
                        } else {
                            mostrarError("No se pudo eliminar el equipo.");
                        }
                    }, () -> mostrarError("Equipo no encontrado."));
                    break;
                }

                case 0:
                    volver = true;
                    break;

                default:
                    mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
    }
    private void menuUsuario() throws ExcepcionIdErroneo {
        boolean volver = false;
        while (!volver) {
            mostrarSeparador(Constantes.SEPARADOR);
            mostrarMensaje(Constantes.MENU_USUARIO);
            int opc = leerEntero("Selecciona una opción:");

            switch (opc) {
                case 1:
                    Usuario usuario = iniciarSesion();
                    if (usuario == null) {
                        mostrarError(Constantes.ERROR_INICIOSESION3);
                    } else {
                        menuUsuarioLogueado(usuario);
                    }
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
    }
    private static void menuUsuarioLogueado(Usuario usuario) throws ExcepcionIdErroneo {
        boolean volver = false;
        while (!volver) {
            mostrarSeparador(Constantes.SEPARADOR);
            mostrarMensaje(Constantes.MENU_USUARIO2);
            int opc = leerEntero(Constantes.ELEGIR_OPCION);

            switch (opc) {
                case 1:
                    gestionJugador.listarJugadores().forEach(j -> mostrarMensaje(j.toString()));
                    break;
                case 2:
                    int id = leerEntero(Constantes.PIDE_ID_JUGADOR);
                    try {
                        ComprobacionId.comprobarId(id);
                    } catch (ExcepcionIdErroneo e) {
                        mostrarError(Constantes.ID_INVALIDO);
                    }
                    gestionJugador.buscarPorId(id).ifPresentOrElse(j -> {
                                mostrarMensaje(j.toString());
                            }, () -> mostrarError(Constantes.JUGADOR_NO_ENCONTRADO)
                    );
                    break;
                case 3:
                    insertarJugadorManual();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
    }

    private void insertarEquipoManual() {
        System.out.println("Introduce ID del equipo:");
        int id = leerEntero("");
        sc.nextLine();
        System.out.println("Introduce nombre del equipo:");
        String nombre = sc.nextLine();
        System.out.println("Introduce ciudad:");
        String ciudad = sc.nextLine();
        System.out.println("Introduce estadio:");
        String estadio = sc.nextLine();
        System.out.println("Introduce entrenador:");
        String entrenador = sc.nextLine();

        Equipo nuevo = new Equipo(id, nombre, ciudad, estadio, entrenador);

        boolean insertado = gestionEquipo.insertarEquipo(nuevo);
        if (insertado) {
            mostrarMensaje("Equipo insertado correctamente.");
        } else {
            mostrarError("Ya existe un equipo con ese ID.");
        }
    }

    private static void insertarJugadorManual() throws ExcepcionIdErroneo {
        System.out.println("Introduce ID del jugador:");
        int id = leerEntero("");
        sc.nextLine();
        System.out.println("Nombre:");
        String nombre = sc.nextLine();
        System.out.println("Equipo:");
        String equipo = sc.nextLine();
        System.out.println("Goles:");
        int goles = leerEntero("");
        sc.nextLine();
        System.out.println("Asistencias:");
        int asistencias = leerEntero("");
        sc.nextLine();
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
    private void filtrarJugadoresPorEquipo() {
        System.out.println("Introduce nombre del equipo para filtrar:");
        String equipo = sc.nextLine();
        Set<Jugador> filtrados = gestionJugador.filtrarPorEquipo(equipo);
        if (!filtrados.isEmpty()) {
            filtrados.forEach(j -> mostrarMensaje(j.toString()));
        } else {
            mostrarError("No se encontraron jugadores para el equipo: " + equipo);
        }
    }


}

