<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="users.LoggedUser" %>
<%@ page import="views.ModfView" %>
<%@ page import="users.DatosBancarios" %>
<%@ page import="users.DatosBancariosService" %>
<%@ page import="connectionpool.ConnectionPool"%>

<%

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar </title>
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

    <% HttpSession currentSession = request.getSession(false);
    ModfView modfview = new ModfView(currentSession);
    out.print(modfview.toString()); %>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="assets/js/scriptIndex.js"></script>

    <% // Obtener el ID de la fila seleccionada de la URL
    String idString = request.getParameter("id");
    int id = Integer.parseInt(idString);
    String dbuser = "RMP";
    String dbpassword = "12345";
    ConnectionPool pool = new ConnectionPool("jdbc:mysql://localhost:3306/login", dbuser, dbpassword);
    DatosBancariosService service = new DatosBancariosService(pool.getConnection());
    DatosBancarios datos = service.requestById(id); %>
    <script>
        document.getElementById("nombreTitular").setAttribute("placeholder", "<%= datos.getNombreTitular() %>");
        document.getElementById("numeroCuenta").setAttribute("placeholder", "<%= datos.getNumeroCuenta() %>");
        document.getElementById("saldo").setAttribute("placeholder", "<%= datos.getSaldo() %>");
        document.getElementById("tipoTarjeta").setAttribute("placeholder", "<%= datos.getTipoTarjeta() %>");
    </script>
</body>
</html>
