<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.proyecto.campusjalpa.Pintura" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" import="java.util.*, com.proyecto.campusjalpa.*" contentType="text/html" pageEncoding="UTF-8" %>

<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
    
    HttpSession userSession = request.getSession(false); 
    if (userSession == null || userSession.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Página de Inicio</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/misestilos.css"/>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Archivo de estilos externo -->
        <link href="css/styles.css" rel="stylesheet">
        <!-- Script para confirmación de eliminación -->
        <script>
            function confirmarEliminacion(event, nombre) {
                if (!confirm("¿Estás seguro de eliminar la pintura '" + nombre + "'?")) {
                    event.preventDefault();
                }
            }
        </script>
    </head>
    <%
        List<Pintura> lasPinturas = (ArrayList<Pintura>) request.getAttribute("TODASLASPINTURAS");
    %>
    <body id="body-pintura">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">Inicio</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="ServletPinturaa">Pintura</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ServletPintor">Pintor</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ServletTecnica">Técnica</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="exhibicion.jsp">Exhibición</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="exposicion.jsp">Exposición</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="galeria.jsp">Galería</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-warning text-white font-weight-bold" href="LogoutServlet">Cerrar sesión</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div id="pintura-container">
            <div id="pintura-background"></div>
            <div id="pintura-box">
                <h1>Catálogo de Pinturas</h1>
                <a href="ServletPinturaa?instruccion=nuevo" class="btn btn-info">Nueva Pintura</a>
                <%
                    if (lasPinturas != null && lasPinturas.size() != 0) { //si existen pinturas, mostramos la tabla                    
                %>
                <!-- tabla-->
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Fecha de creación</th>
                            <th>Valor estimado</th>
                            <th>ID Pintor</th>
                            <th>ID Técnica</th>
                            <th>Actualizar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Pintura tempPintura : lasPinturas) {
                                out.print("<tr>");
                                out.print("<td>" + tempPintura.getIdPintura() + "</td>");
                                out.print("<td>" + tempPintura.getNombre() + "</td>");
                                out.print("<td>" + tempPintura.getFechaCreacion() + "</td>");
                                out.print("<td>" + tempPintura.getValorEstimado() + "</td>");
                                out.print("<td>" + tempPintura.getPintor() + "</td>");
                                out.print("<td>" + tempPintura.getTecnica() + "</td>");
                                //botones
                                out.print("<td><a class='btn btn-info' href='ServletPinturaa?PIN_ID="
                                        + tempPintura.getIdPintura() + "&instruccion=actualizar'>Modificar</a></td>");
                                out.print("<td><a class='btn btn-warning' style='color: black;' href='ServletPinturaa?PIN_ID="
                                        + tempPintura.getIdPintura() + "&instruccion=eliminar' onclick='confirmarEliminacion(event, \"" + tempPintura.getNombre() + "\")'>Eliminar</a></td>");
                                out.print("</tr>");
                            }
                        %>                  
                    </tbody>
                </table>
                <%
                    } else {
                        out.print("<div class='container borde-superior text-white'>");
                        out.println("<div class='row'>");
                        out.println("<div class='col'>");
                        out.print("<h4>No existen Pinturas en la bd</h4>");
                        out.print("</div></div></div>");
                    }
                %>
            </div>
        </div>

        <script src="js/jquery.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/script.js"></script>
        <footer class="text-center text-white py-3">
            <p>&copy; UBA AGENCIA DE ARTISTAS 2024</p>
        </footer>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
