/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.campusjalpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * Clase ModeloPintor para interactuar con la base de datos de pintores.
 * Autor: Berenice Ruiz González
 */
public class ModeloPintor {

    private DataSource origenDatos;

    /**
     * Constructor de ModeloPintor que inicializa el origen de datos.
     * @param origenDatos El origen de datos para obtener conexiones a la base de datos.
     */
    public ModeloPintor(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }

    /**
     * Método para buscar todos los pintores en la base de datos.
     * @return Una lista de objetos Pintor.
     * @throws SQLException Si ocurre un error en la operación de la base de datos.
     */
    public ArrayList<Pintor> buscarPintor() throws SQLException {
        ArrayList<Pintor> listaPintores = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            conexion = origenDatos.getConnection();

            // Consulta SQL para obtener todos los pintores
            String query = "SELECT * FROM PINTOR";
            stmt = conexion.prepareStatement(query);

            // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Iterar sobre el resultado y poblar la lista de pintores
            while (rs.next()) {
                Pintor pintor = new Pintor();
                pintor.setIdpintor(rs.getInt("PINTOR_ID"));
                pintor.setNombre(rs.getString("PINTOR_NOM"));
                pintor.setCuidad(rs.getString("PINTOR_CIU_ORIGEN"));
                pintor.setNacimiento(rs.getDate("PINTOR_FECH_NACIMIENTO"));
                pintor.setTelefono(rs.getString("PINTOR_TELEFONO"));
                pintor.setCorreo(rs.getString("PINTOR_CORR_ELECTRONICO"));

                listaPintores.add(pintor);
            }
        } finally {
            // Liberar recursos
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }

        return listaPintores;
    }
    
}
