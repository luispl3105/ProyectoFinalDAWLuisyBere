/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.campusjalpa;

/**
 * Clase Tecnica que representa una técnica artística en el sistema.
 * Autor: Berenice Ruiz González
 */
public class Tecnica {

    private int idTecnica; // ID de la técnica
    private String tecNombre; // Nombre de la técnica

    /**
     * Constructor vacío de la clase Tecnica.
     */
    public Tecnica() {
        // Constructor vacío
    }

    /**
     * Constructor completo de la clase Tecnica con parámetros.
     * @param idTecnica ID de la técnica.
     * @param tecNombre Nombre de la técnica.
     */
    public Tecnica(int idTecnica, String tecNombre) {
        this.idTecnica = idTecnica;
        this.tecNombre = tecNombre;
    }

    /**
     * Obtener el ID de la técnica.
     * @return ID de la técnica.
     */
    public int getIdTecnica() {
        return idTecnica;
    }

    /**
     * Establecer el ID de la técnica.
     * @param idTecnica ID de la técnica.
     */
    public void setIdTecnica(int idTecnica) {
        this.idTecnica = idTecnica;
    }

    /**
     * Obtener el nombre de la técnica.
     * @return Nombre de la técnica.
     */
    public String getTecNombre() {
        return tecNombre;
    }

    /**
     * Establecer el nombre de la técnica.
     * @param tecNombre Nombre de la técnica.
     */
    public void setTecNombre(String tecNombre) {
        this.tecNombre = tecNombre;
    }
}
