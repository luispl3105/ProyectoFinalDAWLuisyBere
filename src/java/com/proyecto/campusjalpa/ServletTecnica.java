/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto.campusjalpa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet que maneja las solicitudes relacionadas con las técnicas.
 * Autor: Berenice Ruiz González
 */
@WebServlet(name = "ServletTecnica", urlPatterns = {"/ServletTecnica"})
public class ServletTecnica extends HttpServlet {

    private ModeloTecnica modeloTecnica;

    @Resource(name = "jdbc/UBA_agencia_artistas")
    private DataSource miPool;

    /**
     * Inicializa el servlet y configura el modelo de técnicas.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            modeloTecnica = new ModeloTecnica(miPool);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Procesa las solicitudes tanto de tipo GET como POST.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletTecnica</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletTecnica at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        // Si no se envía el parámetro, por defecto listar las técnicas
        if (bandera == null) {
            bandera = "obtenerTecnica";
        }

        // Redirigir al método adecuado según la instrucción
        switch (bandera) {
            case "obtenerTecnica":
                obtenerTecnica(request, response);
                break;
            case "nuevo":
                // Agregar lógica para nueva técnica
                break;
            case "insertarBBDD":
                // Agregar lógica para insertar una técnica
                break;
            case "eliminar":
                // Agregar lógica para eliminar técnica
                break;
            case "actualizar":
                // Agregar lógica para actualizar técnica
                break;
            case "actualizarTecnica":
                // Agregar lógica para guardar actualización
                break;
            default:
                obtenerTecnica(request, response);
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
        processRequest(request, response);
    }

    /**
     * Obtiene la lista de técnicas y la envía a la página JSP para su visualización.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void obtenerTecnica(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la lista de técnicas desde el modelo
        ArrayList<Tecnica> lasTecnicas;

        try {
            lasTecnicas = modeloTecnica.buscarTecnicas();

            // Agregar la lista al request
            request.setAttribute("TODASLASTECNICAS", lasTecnicas);

            // Enviar el request a la página JSP
            RequestDispatcher miDispatcher = request.getRequestDispatcher("Tecnica.jsp");
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


