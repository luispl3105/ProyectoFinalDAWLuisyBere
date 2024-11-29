/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.campusjalpa;

/**
 * Clase Usuario que representa un usuario en el sistema.
 * Autor: Berenice Ruiz González
 */
public class Usuario {
    
    private String username; // Nombre de usuario
    private String password; // Contraseña del usuario

    /**
     * Constructor vacío de la clase Usuario.
     */
    public Usuario() {
        // Constructor vacío
    }

    /**
     * Constructor completo de la clase Usuario con parámetros.
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     */
    public Usuario(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Obtener el nombre de usuario.
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establecer el nombre de usuario.
     * @param username Nombre de usuario.
     */
    public void setUsername(String username) {
        if(username.equals("")){
            throw new IllegalArgumentException("usuario incorrecto");
        }
        this.username = username;
    }

    /**
     * Obtener la contraseña del usuario.
     * @return Contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establecer la contraseña del usuario.
     * @param password Contraseña del usuario.
     */
    public void setPassword(String password) {
        if(password.equals("")){
            throw new IllegalArgumentException("contraseña incorrecta");
        }
        this.password = password;
    }
}

