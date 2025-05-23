package org.example.dao;

import org.example.common.Constantes;
import org.example.domain.Jugador;
import org.example.domain.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DaoFicheros {
    public static Set<Jugador> leerJugadores() {
        return null;
    }

    public static List<Usuario> leerUsuariosBinario(){
        List<Usuario> lista = new ArrayList<>();
        File fichero = new File(Constantes.FICHERO_USUARIOS);
        if(!fichero.exists()){
            return lista;
        }
        try{
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(fichero));
            lista = (List<Usuario>) os.readObject();
            os.close();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public static void escribirUsuariosBinario(List<Usuario> lista){
        File fichero = new File(Constantes.FICHERO_USUARIOS);
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fichero));
            os.writeObject(lista);
            os.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
