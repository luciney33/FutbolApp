package org.example.ui;

import org.example.common.Constantes;
import org.example.common.ExcepcionAsistencias;
import org.example.common.ExcepcionGoles;
import org.example.dao.*;
import org.example.domain.DatosAleatorios;
import org.example.domain.Jugador;

public class App {
    public static void main(String[] args) {
        Liga liga = new Liga();
        Jugador jugador = new Jugador();
        JugadorDAO jugadorDAO = new JugadorDaoImplementacion(liga);
        EquipoDAO equipoDAO = new EquipoDaoImplementacion(liga);
        DatosAleatorios generador = new DatosAleatorios();
        boolean salir = false;

        EntradaSalida.mostrarMensaje(Constantes.BIENVENIDA);
        while(!salir){
            EntradaSalida.mostrarSeparador(Constantes.SEPARADOR);
            EntradaSalida.mostrarMensaje(Constantes.MENU_ROL);
            int opc = EntradaSalida.leerEntero(Constantes.ELEGIR_OPCION);
            switch (opc) {
                case 1: // Administrador
                    menuAdministrador(jugadorDAO, equipoDAO, generador);
                    break;
                case 2: // Usuario
                    menuUsuario(jugadorDAO, equipoDAO);
                    break;
                case 0:
                    salir = true;
                    EntradaSalida.mostrarMensaje(Constantes.SALIR_APP);
                    break;
                default:
                    EntradaSalida.mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
            EntradaSalida.cerrar();
    }

        private static void menuAdministrador(JugadorDAO jugadorDAO, EquipoDAO equipoDAO, DatosAleatorios generador) {
            boolean volver = false;
            while (!volver) {
                EntradaSalida.mostrarSeparador(Constantes.SEPARADOR);
                EntradaSalida.mostrarMensaje(Constantes.MENU_ADMIN);
                int opcion = EntradaSalida.leerEntero(Constantes.ELEGIR_OPCION);

                switch (opcion) {
                    case 1: // Insertar jugador aleatorio
                        Jugador nuevo = generador.crearJugadores();
                        jugadorDAO.insertarJugador(nuevo);
                        EntradaSalida.mostrarMensaje(Constantes.JUGADOR_INSERTADO);
                        break;
                    case 2: // Mostrar jugadores
                        jugadorDAO.getJugadores().forEach(j ->
                                EntradaSalida.mostrarMensaje(j.toString())
                        );
                        break;
                    case 3: // Modificar goles y asistencias de jugador
                        int id = EntradaSalida.leerEntero(Constantes.PIDE_ID_JUGADOR);
                        jugadorDAO.buscarPorId(id).ifPresentOrElse(j ->{
                            int goles = EntradaSalida.leerEntero(Constantes.PIDE_GOLES);
                            int asistencias = EntradaSalida.leerEntero(Constantes.PIDE_ASISTENCIAS);

                            try {
                                j.incrementarGoles(goles);
                                EntradaSalida.mostrarMensaje(Constantes.GOLES_ACTUALIZADOS);
                            } catch (ExcepcionGoles e) {
                                EntradaSalida.mostrarError(e.getMessage());
                            }

                            try {
                                j.incrementarAsistencias(asistencias);
                                EntradaSalida.mostrarMensaje(Constantes.ASISTENCIAS_ACTUALIZADAS);
                            } catch (ExcepcionAsistencias e) {
                                EntradaSalida.mostrarError(e.getMessage());
                            }
                        }, () -> EntradaSalida.mostrarError(Constantes.JUGADOR_NO_ENCONTRADO));
                        break;
                    case 0:
                        volver = true;
                        break;
                    default:
                        EntradaSalida.mostrarError(Constantes.OPCION_INVALIDA);
                }
            }
        }

    private static void menuUsuario(JugadorDAO jugadorDAO, EquipoDAO equipoDAO) {
        boolean volver = false;
        while (!volver) {
            EntradaSalida.mostrarSeparador(Constantes.SEPARADOR);
            EntradaSalida.mostrarMensaje(Constantes.MENU_USUARIO);
            int opc = EntradaSalida.leerEntero(Constantes.ELEGIR_OPCION);

            switch (opc) {
                case 1: // Ver jugadores
                    jugadorDAO.getJugadores().forEach(j ->
                            EntradaSalida.mostrarMensaje(j.toString())
                    );
                    break;
                case 2: // Buscar por ID
                    int id = EntradaSalida.leerEntero(Constantes.PIDE_ID_JUGADOR);
                    jugadorDAO.buscarPorId(id).ifPresentOrElse(
                            j -> EntradaSalida.mostrarMensaje(j.toString()),
                            () -> EntradaSalida.mostrarError(Constantes.JUGADOR_NO_ENCONTRADO)
                    );
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    EntradaSalida.mostrarError(Constantes.OPCION_INVALIDA);
            }
        }
    }
}

