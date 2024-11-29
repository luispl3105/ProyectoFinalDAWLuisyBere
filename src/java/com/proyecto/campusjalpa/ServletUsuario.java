/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto.campusjalpa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet que maneja las solicitudes relacionadas con los usuarios.
 * Autor: Berenice Ruiz González
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/ServletUsuario"})
public class ServletUsuario extends HttpServlet {
    private ModeloUsuario modeloUsuario;

    @Resource(name = "jdbc/UBA_agencia_artistas")
    private DataSource miPool;

    /**
     * Inicializa el servlet y configura el modelo de usuarios.
     */
    @Override
    public void init() throws ServletException {
        super.init();

        try {
            modeloUsuario = new ModeloUsuario(miPool);
        } catch (Exception ex) {
            throw new ServletException(ex);
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
            out.println("<title>Servlet ServletUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletUsuario at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        // Leer el parámetro de instrucción
        String bandera = request.getParameter("instruccion");

        // Si no se envía el parámetro, por defecto listar los usuarios
        if (bandera == null) {
            bandera = "obtenerUsuario";
        }

        // Redirigir al método adecuado según la instrucción
        switch (bandera) {
            case "obtenerUsuario":
                obtenerUsuario(request, response);
                break;
            case "nuevo":
                // Agregar lógica para nuevo usuario
                break;
            default:
                obtenerUsuario(request, response);
        }
    } // Fin del método doPost

    /**
     * Obtiene un usuario de la base de datos y maneja la autenticación.
     * @param request El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta para el cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void obtenerUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Declaramos variables para obtener los parámetros
        String username = request.getParameter("usuario");
        String password = request.getParameter("password");

        // Generar el hash de la contraseña
        String hashedPassword = HashUtil.hashMD5(password);

        try {
            boolean usuario = modeloUsuario.buscarUsuario(username, hashedPassword);

            if (usuario) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("index.jsp");
            } else {
                // Si es falso, se manda un error mediante un request
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

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
}

