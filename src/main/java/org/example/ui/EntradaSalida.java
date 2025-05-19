package org.example.ui;

import org.example.common.Constantes;

import java.util.Scanner;

public class EntradaSalida {
    public static final Scanner sc = new Scanner(System.in);
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void mostrarError(String mensaje) {
        System.out.println(mensaje);
    }

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje + " ");
        return sc.nextLine().trim();
    }


    public static int leerEntero(String mensaje) {
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

    public static void mostrarSeparador(String mensaje) {
        System.out.println(mensaje);
    }

    public static boolean leerConfirmacion(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String respuesta = sc.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("sí") || respuesta.equals("si");
    }

    public static void esperarEnter() {
        System.out.println("Presiona Enter para continuar...");
        sc.nextLine();
    }

    public static void cerrar() {
        sc.close();
    }

    private EntradaSalida() {}
}
