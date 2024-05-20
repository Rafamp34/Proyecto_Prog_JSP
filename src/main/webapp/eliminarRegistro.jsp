<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="users.DatosBancariosService" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="connectionpool.ConnectionPool" %>
<%@ page import="users.DatosBancarios" %>

<%
    // Initialize error message
    String errorMessage = null;

    // Get the ID parameter from the request
    String idString = request.getParameter("id");
    if (idString != null && !idString.isEmpty()) {
        try {
            int id = Integer.parseInt(idString);

            String dbuser = "RMP";
            String dbpassword = "12345";
            ConnectionPool pool = new ConnectionPool("jdbc:mysql://localhost:3306/login", dbuser, dbpassword);
            Connection conn = pool.getConnection();

            DatosBancariosService service = new DatosBancariosService(conn);
            DatosBancarios datos = service.requestById(id);

            if (datos != null) {
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Eliminar Registro</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="icon" href="assets/img/logo.jpg" type="image/x-icon">
    <style>
        body {
            background: #461220;
            font-family: "poppins";
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header">
                        Confirmar Eliminación
                    </div>
                    <div class="card-body">
                        <p>¿Está seguro que desea eliminar el registro de <strong><%= datos.getNombreTitular() %></strong> con número de cuenta <strong><%= datos.getNumeroCuenta() %></strong>?</p>
                        <form id="deleteForm" action="eliminar.jsp" method="post" onsubmit="return confirmDeletion()">
                            <input type="hidden" name="id" value="<%= id %>">
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                            <a href="home.jsp" class="btn btn-secondary">Cancelar</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="assets/js/scriptIndex.js"></script>
</body>
</html>
<%
            } else {
                errorMessage = "Registro no encontrado.";
            }

            pool.releaseConnection(conn);
        } catch (NumberFormatException e) {
            errorMessage = "ID inválido.";
            e.printStackTrace();
        } catch (SQLException e) {
            errorMessage = "Error al acceder a la base de datos.";
            e.printStackTrace();
        }
    } else {
        errorMessage = "ID no proporcionado.";
    }

    if (errorMessage != null) {
        session.setAttribute("errorMessage", errorMessage);
        response.sendRedirect("home.jsp");
    }
%>
