/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.campusjalpa;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase Pintura que representa una pintura en el sistema.
 * Autor: Berenice Ruiz González
 */
public class Pintura {

    private int idPintura; // ID de la pintura
    private String nombre; // Nombre de la pintura
    private Date fechaCreacion; // Fecha de creación de la pintura
    private BigDecimal valorEstimado; // Valor estimado de la pintura
    private int pintor; // ID del pintor
    private int tecnica; // ID de la técnica

    /**
     * Constructor vacío de la clase Pintura.
     */
    public Pintura() {
        // Constructor vacío
    }

    /**
     * Constructor completo de la clase Pintura con parámetros.
     * @param idPintura ID de la pintura.
     * @param nombre Nombre de la pintura.
     * @param fechaCreacion Fecha de creación de la pintura.
     * @param valorEstimado Valor estimado de la pintura.
     * @param pintor ID del pintor.
     * @param tecnica ID de la técnica.
     */
    public Pintura(int idPintura, String nombre, Date fechaCreacion, BigDecimal valorEstimado, int pintor, int tecnica) {
        this.idPintura = idPintura;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.valorEstimado = valorEstimado;
        this.pintor = pintor;
        this.tecnica = tecnica;
    }

    /**
     * Obtener el ID de la pintura.
     * @return ID de la pintura.
     */
    public int getIdPintura() {
        return idPintura;
    }

    /**
     * Establecer el ID de la pintura.
     * @param idPintura ID de la pintura.
     */
    public void setIdPintura(int idPintura) {
        this.idPintura = idPintura;
    }

    /**
     * Obtener el nombre de la pintura.
     * @return Nombre de la pintura.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establecer el nombre de la pintura.
     * @param nombre Nombre de la pintura.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtener la fecha de creación de la pintura.
     * @return Fecha de creación de la pintura.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establecer la fecha de creación de la pintura.
     * @param fechaCreacion Fecha de creación de la pintura.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtener el valor estimado de la pintura.
     * @return Valor estimado de la pintura.
     */
    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    /**
     * Establecer el valor estimado de la pintura.
     * @param valorEstimado Valor estimado de la pintura.
     */
    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    /**
     * Obtener el ID del pintor.
     * @return ID del pintor.
     */
    public int getPintor() {
        return pintor;
    }

    /**
     * Establecer el ID del pintor.
     * @param pintor ID del pintor.
     */
    public void setPintor(int pintor) {
        this.pintor = pintor;
    }

    /**
     * Obtener el ID de la técnica.
     * @return ID de la técnica.
     */
    public int getTecnica() {
        return tecnica;
    }

    /**
     * Establecer el ID de la técnica.
     * @param tecnica ID de la técnica.
     */
    public void setTecnica(int tecnica) {
        this.tecnica = tecnica;
    }
}
