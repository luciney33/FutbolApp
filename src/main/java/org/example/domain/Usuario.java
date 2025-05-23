package org.example.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Usuario implements Serializable {
    private String nombreUsu;
    private String contraseña;
}
