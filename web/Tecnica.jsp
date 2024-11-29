<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.proyecto.campusjalpa.Tecnica" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" import="java.util.*, com.proyecto.campusjalpa.*" contentType="text/html" pageEncoding="UTF-8" %>

<% 
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
    <%
        List<Tecnica> lasTecnicas = (ArrayList<Tecnica>) request.getAttribute("TODASLASTECNICAS");
    %>
    <body id="body-tecnica">
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
            //verificamos que existan técnicas en la base de datos
            if (lasTecnicas != null && lasTecnicas.size() != 0) { //si existen técnicas, mostramos la tabla                    
        %>
        <!-- tabla-->
        <div id="tecnica-container">
            <div id="tecnica-background"></div>
            <div id="tecnica-box">
                <h1>Catálogo de Técnicas</h1>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Tecnica tempTecnica : lasTecnicas) {
                                out.print("<tr>");
                                out.print("<td>" + tempTecnica.getIdTecnica() + "</td>");
                                out.print("<td>" + tempTecnica.getTecNombre() + "</td>");
                                out.print("</tr>");
                            }
                        %>                  
                    </tbody>
                </table>
            </div>
        </div>
        <%
            } else {
                out.print("<div class='container borde-superior'>");
                out.println("<div class='row'>");
                out.println("<div class='col'>");
                out.print("<h4>No existen Técnicas en la bd</h4>");
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
