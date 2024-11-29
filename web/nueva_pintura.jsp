<%@ page import="java.util.ArrayList" %>
<%@ page import="com.proyecto.campusjalpa.Pintor" %>
<%@ page import="com.proyecto.campusjalpa.Tecnica" %>
<%@ page import="java.util.*, com.proyecto.campusjalpa.*" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Alta de Pinturas</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/misestilos.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body id="body-nueva-pintura">
        <div id="nueva-pintura-container">
            <div id="nueva-pintura-background"></div>
            <div id="nueva-pintura-box">
                <h1 id="encabezado-pinturas">Alta de Pinturas</h1>

                <form method="post" action="ServletPinturaa" onsubmit="return validarFormulario()">
                    <input type="hidden" name="instruccion" value="insertarBBDD">

                    <div class="form-group">
                        <label for="nombre">Nombre de la Pintura</label>
                        <input type="text" class="form-control" name="nombre" id="nombre" pattern="[a-zA-Z\s]+" title="El nombre solo debe contener letras y espacios." required>
                    </div>

                    <div class="form-group">
                        <label for="fechaCreacion">Fecha de Creación</label>
                        <input type="date" class="form-control" name="fechaCreacion" id="fechaCreacion" max="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())%>" required>
                    </div>

                    <div class="form-group">
                        <label for="valorEstimado">Valor Estimado</label>
                        <input type="number" class="form-control" min="0" name="valorEstimado" id="valorEstimado" step="0.01" required>
                    </div>

                    <!-- Lista de pintores obtenidos desde la base de datos -->
                    <div class="form-group">
                        <label for="pintor">Pintor:</label>
                        <select name="pintor" class="custom-select" required>
                            <option value="" selected disabled>Selecciona un Pintor</option>
                            <%
                                ArrayList<Pintor> lstPintores = (ArrayList<Pintor>) request.getAttribute("TODOSLOSPINTORES");
                                for (Pintor tempPintor : lstPintores) {
                            %>
                            <option value="<%= tempPintor.getIdpintor()%>"><%= tempPintor.getNombre()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <!-- Lista de técnicas obtenidas desde la base de datos -->
                    <div class="form-group">
                        <label for="tecnica">Técnica:</label>
                        <select name="tecnica" class="custom-select" required>
                            <option value="" selected disabled>Selecciona una Técnica</option>
                            <%
                                ArrayList<Tecnica> lstTecnicas = (ArrayList<Tecnica>) request.getAttribute("TODASLASTECNICAS");
                                for (Tecnica tempTecnica : lstTecnicas) {
                            %>
                            <option value="<%= tempTecnica.getIdTecnica()%>"><%= tempTecnica.getTecNombre()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-info">Guardar</button>
                    <button type="reset" class="btn btn-light">Reestablecer</button>
                </form>            
            </div>
        </div>
        <script src="js/jquery.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/script.js"></script>
        <script>
                    function validarFormulario() {
                        const nombre = document.getElementById('nombre').value;
                        const fechaCreacion = document.getElementById('fechaCreacion').value;
                        const valorEstimado = document.getElementById('valorEstimado').value;

                        // Validar el nombre para que no contenga números
                        const nombrePattern = /^[a-zA-Z\s]+$/;
                        if (!nombrePattern.test(nombre)) {
                            alert('El nombre solo debe contener letras y espacios.');
                            return false;
                        }

                        // Validar que la fecha no sea futura
                        const fechaActual = new Date().toISOString().split('T')[0];
                        if (fechaCreacion > fechaActual) {
                            alert('La fecha de creación no puede ser futura.');
                            return false;
                        }

                        // Validar el valor estimado para que no sea negativo
                        if (valorEstimado < 0) {
                            alert('El valor estimado no puede ser negativo.');
                            return false;
                        }

                        return true;
                    }
        </script>
    </body>
</html>
