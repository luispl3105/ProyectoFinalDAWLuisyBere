<%@ page import="javax.servlet.http.HttpSession" %>

<% 
    HttpSession userSession = request.getSession(false); 
    if (userSession == null || userSession.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Galería</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Archivo de estilos externo -->
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body id="body-galeria">
        <!-- Barra de navegación superior -->
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

        <div id="galeria-container">
            <!-- Contenedor de fondo -->
            <div id="galeria-background"></div>
        </div>

        <!-- Pie de página -->
        <footer class="text-center text-white py-3">
            <p>&copy; UBA AGENCIA DE ARTISTAS 2024</p>
        </footer>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
