<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="users.LoggedUser" %>
<%@ page import="views.LoginView" %>
<%
    // Verificar si el usuario ha iniciado sesión
    LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
    if (loggedUser != null) {
        // Si el usuario ya ha iniciado sesión, redirigirlo a la página principal
        response.sendRedirect("home.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesión</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="icon" href="assets/img/logo.jpg" type="image/x-icon">
    <link rel="stylesheet" href="assets/style/styleX.css">
<style>
    
</style>
</head>
<body>

    <% String error = request.getParameter("error");
        out.print(new LoginView(error)); %>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="assets/js/scriptIndex.js"></script>
     <link rel="stylesheet" href="assets/style/styleIndex.css">
</body>
</html>
