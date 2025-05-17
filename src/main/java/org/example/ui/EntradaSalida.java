package org.example.ui;

import java.util.Scanner;

public class EntradaSalida {
    public static final Scanner sc = new Scanner(System.in);
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void mostrarError(String mensaje) {
        System.err.println("❌ " + mensaje);
    }

    public static void mostrarExito(String mensaje) {
        System.out.println("✅ " + mensaje);
    }

    public static void mostrarSeparador() {
        System.out.println("-----------------------------");
    }
}
