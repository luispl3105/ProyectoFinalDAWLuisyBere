<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.DriverManager" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies

%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Archivo de estilos externo -->
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body id="body-Login">
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
                            <a class="nav-link btn btn-warning text-white font-weight-bold" href="login.jsp">Cerrar sesión</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div id="login-container">
            <!-- Contenedor de fondo -->
            <div id="login-background"></div>
            <!-- Formulario -->
            <div id="login-box">
                <h2 class="text-white text-center mb-4">Inicia sesión</h2>
                <form class="form-signin" action="ServletUsuario" method="POST">
                    <input type="hidden" name="instruccion" value="obtenerUsuario">
                    <div class="mb-3">
                        <label for="inputUser" class="sr-only">Nombre de Usuario</label>
                        <input type="text" name="usuario" class="form-control" placeholder="Usuario" required autofocus>
                    </div>
                    <div class="mb-3">
                        <label for="inputPassword" class="sr-only">Contraseña</label>
                        <input type="password" name="password" class="form-control" placeholder="Contraseña" required>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-lg btn-custom">Iniciar sesión</button>
                    </div>
                    <%-- Mostrar el mensaje de error si existe --%>

                    <p style="color: red;">
                        <%= request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
                    </p>
                </form>
            </div>
        </div>

        <footer class="text-center text-white py-3">
            <p>&copy; UBA AGENCIA DE ARTISTAS 2024</p>
        </footer>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
