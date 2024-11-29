/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.campusjalpa;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

/**
 * Clase ModeloTecnica para interactuar con la base de datos de técnicas.
 * Autor: Berenice Ruiz González
 */
public class ModeloTecnica {

    private DataSource origenDatos;

    /**
     * Constructor de ModeloTecnica que inicializa el origen de datos.
     * @param origenDatos El origen de datos para obtener conexiones a la base de datos.
     */
    public ModeloTecnica(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }

    /**
     * Método para buscar todas las técnicas en la base de datos.
     * @return Una lista de objetos Tecnica.
     * @throws SQLException Si ocurre un error en la operación de la base de datos.
     */
    public ArrayList<Tecnica> buscarTecnicas() throws SQLException {
        ArrayList<Tecnica> listaTecnicas = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            conexion = origenDatos.getConnection();

            // Consulta SQL para obtener todas las técnicas
            String query = "SELECT * FROM TECNICA";
            stmt = conexion.prepareStatement(query);

            // Ejecutar la consulta
            rs = stmt.executeQuery();

            // Iterar sobre el resultado y poblar la lista de técnicas
            while (rs.next()) {
                Tecnica tecnica = new Tecnica();
                tecnica.setIdTecnica(rs.getInt("TEC_ID"));
                tecnica.setTecNombre(rs.getString("TEC_NOM"));

                listaTecnicas.add(tecnica);
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

        return listaTecnicas;
    }
}
