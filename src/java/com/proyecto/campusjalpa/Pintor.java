/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.campusjalpa;

import java.util.Date;

/**
 * Clase Pintor que representa a un pintor en el sistema.
 * Autor: Berenice Ruiz González
 */
public class Pintor {
    private int idpintor; // Identificador único del pintor
    private String nombre; // Nombre del pintor
    private String ciudad; // Ciudad de origen del pintor
    private Date nacimiento; // Fecha de nacimiento del pintor
    private String telefono; // Teléfono del pintor
    private String correo; // Correo electrónico del pintor

    /**
     * Constructor vacío de la clase Pintor.
     */
    public Pintor() {
        // Constructor vacío
    }

    /**
     * Constructor de la clase Pintor con parámetros.
     * @param idpintor Identificador único del pintor.
     * @param nombre Nombre del pintor.
     * @param cuidad Ciudad de origen del pintor.
     * @param nacimiento Fecha de nacimiento del pintor.
     * @param telefono Teléfono del pintor.
     * @param correo Correo electrónico del pintor.
     */
    public Pintor(int idpintor, String nombre, String cuidad, Date nacimiento, String telefono, String correo) {
        this.idpintor = idpintor;
        this.nombre = nombre;
        this.ciudad = cuidad;
        this.nacimiento = nacimiento;
        this.telefono = telefono;
        this.correo = correo;
    }

    /**
     * Obtener el identificador del pintor.
     * @return Identificador del pintor.
     */
    public int getIdpintor() {
        return idpintor;
    }

    /**
     * Establecer el identificador del pintor.
     * @param idpintor Identificador del pintor.
     */
    public void setIdpintor(int idpintor) {
        this.idpintor = idpintor;
    }

    /**
     * Obtener el nombre del pintor.
     * @return Nombre del pintor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establecer el nombre del pintor.
     * @param nombre Nombre del pintor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtener la ciudad de origen del pintor.
     * @return Ciudad de origen del pintor.
     */
    public String getCuidad() {
        return ciudad;
    }

    /**
     * Establecer la ciudad de origen del pintor.
     * @param cuidad Ciudad de origen del pintor.
     */
    public void setCuidad(String cuidad) {
        this.ciudad = cuidad;
    }

    /**
     * Obtener la fecha de nacimiento del pintor.
     * @return Fecha de nacimiento del pintor.
     */
    public Date getNacimiento() {
        return nacimiento;
    }

    /**
     * Establecer la fecha de nacimiento del pintor.
     * @param nacimiento Fecha de nacimiento del pintor.
     */
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    /**
     * Obtener el teléfono del pintor.
     * @return Teléfono del pintor.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establecer el teléfono del pintor.
     * @param telefono Teléfono del pintor.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtener el correo electrónico del pintor.
     * @return Correo electrónico del pintor.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establecer el correo electrónico del pintor.
     * @param correo Correo electrónico del pintor.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
