/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.campusjalpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Clase ModeloUsuario para interactuar con la base de datos de usuarios.
 * Autor: Berenice Ruiz González
 */
public class ModeloUsuario {

    // Creamos la variable para obtener el origen de los datos
    private DataSource origenDatos;

    // Constructor con un parámetro
    public ModeloUsuario(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }

    /**
     * Método para buscar un usuario en la base de datos.
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return true si el usuario es encontrado y la contraseña coincide, de lo contrario false.
     * @throws SQLException Si ocurre un error en la operación de la base de datos.
     */
    public boolean buscarUsuario(String username, String password) throws SQLException {
        Usuario usuario = null;

        Connection miConexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            miConexion = origenDatos.getConnection();

            // Escribir la consulta SQL
            String query = "SELECT * FROM USUARIOS WHERE USU_USERNAME=?";

            stmt = miConexion.prepareStatement(query);
            stmt.setString(1, username);

            // Ejecutar la consulta y guardar el resultado
            rs = stmt.executeQuery();

            // Obtener los datos del resultado
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setUsername(rs.getString("USU_USERNAME"));
                usuario.setPassword(rs.getString("password"));
            }
        } finally {
            // Liberar recursos
            try {
                if (rs != null) rs.close();
            } catch (SQLException ex) { /* Ignorar */ }

            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) { /* Ignorar */ }

            try {
                if (miConexion != null) miConexion.close();
            } catch (SQLException ex) { /* Ignorar */ }
        }

        // Si no se encontró un usuario, devolver false
        if (usuario == null) {
            return false;
        }

        // Validar los datos
        if (usuario.getUsername().equals(username)) {
            if (usuario.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    } // Fin del método buscarUsuario

}

