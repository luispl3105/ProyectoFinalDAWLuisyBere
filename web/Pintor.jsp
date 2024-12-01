<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.proyecto.campusjalpa.Pintor" %>
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
    </head>
    <body id="body-pintor">
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

        <%
            List<Pintor> losPintores = (ArrayList<Pintor>) request.getAttribute("TODOSLOSPINTORES");

            if (losPintores != null && losPintores.size() != 0) {
        %>
        <!-- tabla -->
        <div id="pintor-container" class="container-fluid">
            <div id="pintor-background"></div>
            <div id="pintor-box" class="container-fluid">
                <h1>Catálogo de Pintores</h1>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Ciudad</th>
                                <th>Fecha de nacimiento</th>
                                <th>Teléfono</th>
                                <th>Correo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Pintor tempPintor : losPintores) {
                                    out.print("<tr>");
                                    out.print("<td>" + tempPintor.getIdpintor() + "</td>");
                                    out.print("<td>" + tempPintor.getNombre() + "</td>");
                                    out.print("<td>" + tempPintor.getCuidad() + "</td>");
                                    out.print("<td>" + tempPintor.getNacimiento() + "</td>");
                                    out.print("<td>" + tempPintor.getTelefono() + "</td>");
                                    out.print("<td>" + tempPintor.getCorreo() + "</td>");
                                    out.print("</tr>");
                                }
                            %>                  
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%
            } else {
                out.print("<div class='container borde-superior'>");
                out.println("<div class='row'>");
                out.println("<div class='col'>");
                out.print("<h4>No existen Pintores en la bd</h4>");
                out.print("</div></div></div>");
            }
        %>     

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
