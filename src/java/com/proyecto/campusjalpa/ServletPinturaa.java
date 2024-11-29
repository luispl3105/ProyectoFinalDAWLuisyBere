/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.proyecto.campusjalpa;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet que maneja las solicitudes relacionadas con las pinturas.
 * Autor: Berenice Ruiz González
 */
@WebServlet(name = "ServletPinturaa", urlPatterns = {"/ServletPinturaa"})
public class ServletPinturaa extends HttpServlet {

    private ModeloPintura modeloPintura;
    private ModeloPintor modeloPintor;
    private ModeloTecnica modeloTecnica;

    @Resource(name = "jdbc/UBA_agencia_artistas")
    private DataSource miPool;

    /**
     * Inicializa el servlet y configura los modelos de pinturas, pintores y técnicas.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloPintura = new ModeloPintura(miPool);
            modeloPintor = new ModeloPintor(miPool);
            modeloTecnica = new ModeloTecnica(miPool);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Maneja las solicitudes HTTP GET.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Leer el parámetro de instrucción
        String bandera = request.getParameter("instruccion");

        // Si no se envía el parámetro, por defecto listar las pinturas
        if (bandera == null) {
            bandera = "listar";
        }

        // Redirigir al método adecuado según la instrucción
        switch (bandera) {
            case "listar":
                obtenerPinturas(request, response);
                break;
            case "nuevo":
                // Obtener las listas de pintores y técnicas antes de redirigir
                try {
                    ArrayList<Pintor> listaPintores = modeloPintor.buscarPintor();
                    ArrayList<Tecnica> listaTecnicas = modeloTecnica.buscarTecnicas();
                    request.setAttribute("TODOSLOSPINTORES", listaPintores);
                    request.setAttribute("TODASLASTECNICAS", listaTecnicas);
                } catch (SQLException ex) {
                    mensajeDeError(ex.getMessage(), "error.jsp", request, response);
                }

                // Redirigir a nueva_pintura.jsp
                RequestDispatcher miDispatcher = request.getRequestDispatcher("nueva_pintura.jsp");
                miDispatcher.forward(request, response);
                break;
            case "insertarBBDD":
                insertarPintura(request, response);
                break;
            case "eliminar":
                eliminarPintura(request, response);
                break;
            case "actualizar":
                obtenerPinturaPorId(request, response);
                break;
            default:
                obtenerPinturas(request, response);
        }
    }

    /**
     * Maneja las solicitudes HTTP POST.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bandera = request.getParameter("instruccion");
        if ("insertarBBDD".equals(bandera)) {
            insertarPintura(request, response);
        } else if ("actualizarPintura".equals(bandera)) {
            actualizarPintura(request, response);
        } else {
            doGet(request, response);
        }
    }

    /**
     * Elimina una pintura de la base de datos.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void eliminarPintura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPintura = Integer.parseInt(request.getParameter("PIN_ID"));

        try {
            modeloPintura.eliminarPintura(idPintura);
            obtenerPinturas(request, response);
        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }

    /**
     * Obtiene una pintura por su ID y redirige a la página de actualización.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void obtenerPinturaPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPintura = Integer.parseInt(request.getParameter("PIN_ID"));

        try {
            Pintura pinturaActual = modeloPintura.obtenerPintura(idPintura);
            ArrayList<Pintor> listaPintores = modeloPintor.buscarPintor();
            ArrayList<Tecnica> listaTecnicas = modeloTecnica.buscarTecnicas();

            request.setAttribute("pintura", pinturaActual);
            request.setAttribute("TODOSLOSPINTORES", listaPintores);
            request.setAttribute("TODASLASTECNICAS", listaTecnicas);

            RequestDispatcher miDispatcher = request.getRequestDispatcher("actualizar_pintura.jsp");
            miDispatcher.forward(request, response);
        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }

    /**
     * Actualiza una pintura existente en la base de datos.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void actualizarPintura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPintura = Integer.parseInt(request.getParameter("idPintura"));
        String nombre = request.getParameter("nombre");
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
        Date fechaSql = null;
        try {
            java.util.Date fechaUtil = formatter.parse(request.getParameter("fechaCreacion"));
            fechaSql = new Date(fechaUtil.getTime());
        } catch (ParseException e) {
            mensajeDeError("Error al parsear la fecha", "error.jsp", request, response);
            return;
        }
        BigDecimal valorEstimado = new BigDecimal(request.getParameter("valorEstimado"));
        int pintorId = Integer.parseInt(request.getParameter("pintor"));
        int tecnicaId = Integer.parseInt(request.getParameter("tecnica"));

        Pintura pinturaActualizada = new Pintura(idPintura, nombre, fechaSql, valorEstimado, pintorId, tecnicaId);

        try {
            modeloPintura.actualizarPintura(pinturaActualizada);
            obtenerPinturas(request, response);
        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }

        /**
     * Inserta una nueva pintura en la base de datos.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void insertarPintura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String fechaStr = request.getParameter("fechaCreacion");
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

        Date fechaSql = null;
        try {
            java.util.Date fechaUtil = formatter.parse(fechaStr);
            fechaSql = new Date(fechaUtil.getTime());
        } catch (ParseException e) {
            mensajeDeError("Error al parsear la fecha", "error.jsp", request, response);
            return;
        }

        BigDecimal valorEstimado = new BigDecimal(request.getParameter("valorEstimado"));
        int pintorId = Integer.parseInt(request.getParameter("pintor"));
        int tecnicaId = Integer.parseInt(request.getParameter("tecnica"));

        Pintura nuevaPintura = new Pintura(0, nombre, fechaSql, valorEstimado, pintorId, tecnicaId);

        try {
            modeloPintura.insertarPintura(nuevaPintura);
            obtenerPinturas(request, response);
        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }

    /**
     * Obtiene la lista de pinturas y la envía a la página JSP para su visualización.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void obtenerPinturas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la lista de pinturas desde el modelo
        ArrayList<Pintura> lasPinturas;

        try {
            lasPinturas = modeloPintura.buscarPinturas();

            // Agregar la lista al request
            request.setAttribute("TODASLASPINTURAS", lasPinturas);

            // Enviar el request a la página JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("Pintura.jsp");
            miDispatcher.forward(request, response);

        } catch (SQLException ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        } catch (Exception ex) {
            mensajeDeError(ex.getMessage(), "error.jsp", request, response);
        }
    }

    /**
     * Maneja el mensaje de error y redirige a una página de error.
     * @param ex El mensaje de error.
     * @param errorjsp La página JSP de error.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void mensajeDeError(String ex, String errorjsp, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mensaje", ex);

        request.getRequestDispatcher(errorjsp).forward(request, response);
    }

    /**
     * Devuelve una breve descripción del servlet.
     * @return Una cadena de texto que contiene la descripción del servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
