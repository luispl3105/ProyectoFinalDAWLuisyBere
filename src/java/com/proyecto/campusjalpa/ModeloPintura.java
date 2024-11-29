/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.campusjalpa;

import java.math.BigDecimal;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

/**
 * Clase ModeloPintura para interactuar con la base de datos de pinturas.
 * Autor: Berenice Ruiz González
 */
public class ModeloPintura {

    private DataSource origenDatos;

    /**
     * Constructor de ModeloPintura que inicializa el origen de datos.
     * @param origenDatos El origen de datos para obtener conexiones a la base de datos.
     */
    public ModeloPintura(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }

    /**
     * Método para buscar todas las pinturas en la base de datos.
     * @return Una lista de objetos Pintura.
     * @throws SQLException Si ocurre un error en la operación de la base de datos.
     */
    public ArrayList<Pintura> buscarPinturas() throws SQLException {
        ArrayList<Pintura> listaPinturas = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            conexion = origenDatos.getConnection();

            // Consulta SQL para obtener todas las pinturas con sus pintores y técnicas
            String query = "SELECT PINTURA.PIN_ID, PINTURA.PIN_NOM, PINTURA.PIN_FECH_CREACION, "
                    + "PINTURA.PIN_VALOR_ESTIMADO, PINTURA.PINTOR_ID, PINTURA.TEC_ID "
                    + "FROM PINTURA "
                    + "JOIN PINTOR ON PINTURA.PINTOR_ID = PINTOR.PINTOR_ID "
                    + "JOIN TECNICA ON PINTURA.TEC_ID = TECNICA.TEC_ID";

            stmt = conexion.prepareStatement(query);
            rs = stmt.executeQuery();

            // Iterar sobre el resultado y poblar la lista de pinturas
            while (rs.next()) {
                Pintura pintura = new Pintura();
                pintura.setIdPintura(rs.getInt("PIN_ID"));
                pintura.setNombre(rs.getString("PIN_NOM"));
                pintura.setFechaCreacion(rs.getDate("PIN_FECH_CREACION"));
                pintura.setValorEstimado(rs.getBigDecimal("PIN_VALOR_ESTIMADO"));
                pintura.setPintor(rs.getInt("PINTOR_ID"));
                pintura.setTecnica(rs.getInt("TEC_ID"));

                listaPinturas.add(pintura);
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

        return listaPinturas;
    }

    /**
     * Método para insertar una nueva pintura en la base de datos.
     * @param nuevaPintura El objeto Pintura a insertar.
     * @throws SQLException Si ocurre un error en la operación de la base de datos.
     */
    public void insertarPintura(Pintura nuevaPintura) throws SQLException {
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            // Obtener una conexión a la base de datos
            conexion = origenDatos.getConnection();
            String sql = "INSERT INTO PINTURA (PIN_NOM, PIN_FECH_CREACION, PIN_VALOR_ESTIMADO, PINTOR_ID, TEC_ID) "
                    + "VALUES (?, ?, ?, ?, ?)";
            stmt = conexion.prepareStatement(sql);

            stmt.setString(1, nuevaPintura.getNombre());
            // Convertir java.util.Date a java.sql.Date
            java.sql.Date fechaSql = new java.sql.Date(nuevaPintura.getFechaCreacion().getTime());
            stmt.setDate(2, fechaSql);
            stmt.setBigDecimal(3, nuevaPintura.getValorEstimado());
            stmt.setInt(4, nuevaPintura.getPintor());
            stmt.setInt(5, nuevaPintura.getTecnica());

            stmt.execute();
        } finally {
            // Liberar recursos
            if (stmt != null) {
                stmt.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    /**
     * Método para obtener una pintura específica de la base de datos por su ID.
     * @param idPintura El ID de la pintura a obtener.
     * @return El objeto Pintura encontrado.
     * @throws SQLException Si ocurre un error en la operación de la base de datos.
     */
    public Pintura obtenerPintura(int idPintura) throws SQLException {
        Pintura pintura = null;
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            conexion = origenDatos.getConnection();
            String sql = "SELECT * FROM PINTURA WHERE PIN_ID = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idPintura);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("PIN_NOM");
                java.sql.Date fechaCreacion = rs.getDate("PIN_FECH_CREACION");
                BigDecimal valorEstimado = rs.getBigDecimal("PIN_VALOR_ESTIMADO");
                int pintorId = rs.getInt("PINTOR_ID");
                int tecnicaId = rs.getInt("TEC_ID");

                pintura = new Pintura(idPintura, nombre, fechaCreacion, valorEstimado, pintorId, tecnicaId);
            } else {
                throw new SQLException("No se encontró la pintura con el ID: " + idPintura);
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
        return pintura;
    }

    /**
     * Método para actualizar una pintura existente en la base de datos.
     * @param pinturaActualizada El objeto Pintura con los datos actualizados.
     * @throws SQLException Si ocurre un error en la operación de la base de datos.
     */
    public void actualizarPintura(Pintura pinturaActualizada) throws SQLException {
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            // Obtener una conexión a la base de datos
            conexion = origenDatos.getConnection();
            String sql = "UPDATE PINTURA SET PIN_NOM = ?, PIN_FECH_CREACION = ?, PIN_VALOR_ESTIMADO = ?, PINTOR_ID = ?, TEC_ID = ? WHERE PIN_ID = ?";
            stmt = conexion.prepareStatement(sql);

            // Asignar los valores a los parámetros de la consulta
            stmt.setString(1, pinturaActualizada.getNombre());

            // Convertir java.util.Date a java.sql.Date
            java.sql.Date fechaSql = new java.sql.Date(pinturaActualizada.getFechaCreacion().getTime());
            stmt.setDate(2, fechaSql);

            stmt.setBigDecimal(3, pinturaActualizada.getValorEstimado());
            stmt.setInt(4, pinturaActualizada.getPintor());
            stmt.setInt(5, pinturaActualizada.getTecnica());
            stmt.setInt(6, pinturaActualizada.getIdPintura());

            // Ejecutar la consulta de actualización
            stmt.executeUpdate();
        } finally {
            // Liberar recursos
            if (stmt != null) {
                stmt.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    /**
     * Método para eliminar una pintura de la base de datos.
     * @param idPintura El ID de la pintura a eliminar.
     * @throws SQLException Si ocurre un error en la operación de la base de datos.
     */
    public void eliminarPintura(int idPintura) throws SQLException {
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            // Obtener una conexión a la base de datos
            conexion = origenDatos.getConnection();
            String sql = "DELETE FROM PINTURA WHERE PIN_ID = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idPintura);

            // Ejecutar la consulta de eliminación
            stmt.executeUpdate();
        } finally {
            // Liberar recursos
            if (stmt != null) {
                stmt.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
}

