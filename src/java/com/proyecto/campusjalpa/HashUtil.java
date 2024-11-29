/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.campusjalpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase utilitaria para generar hashes MD5.
 * Autor: Berenice Ruiz González
 */
public class HashUtil {

    /**
     * Método para generar un hash MD5 a partir de una cadena de texto.
     * @param password La cadena de texto que se quiere hashear.
     * @return El hash MD5 de la cadena de texto en formato hexadecimal.
     */
    public static String hashMD5(String password) {
        try {
            // Obtiene una instancia del algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Convierte la cadena de texto en un array de bytes y genera el hash
            byte[] hashInBytes = md.digest(password.getBytes());
            
            // Convierte cada byte del hash en un formato hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            
            // Devuelve el hash MD5 en formato hexadecimal como cadena de texto
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Lanza una excepción en caso de que el algoritmo MD5 no esté disponible
            throw new RuntimeException(e);
        }
    }

}
