<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="connectionpool.ConnectionPool"%>
<%@ page import="users.LoggedUser" %>
<%@ page import="views.HomeView" %>
<%
    // Obtener la sesión actual, sin crear una nueva si no existe
    HttpSession currentSession = request.getSession(false);

    // Verificar si el usuario está autenticado
    LoggedUser loggedUser = null;
    if (currentSession != null) {
        loggedUser = (LoggedUser) currentSession.getAttribute("loggedUser");
    }

    // Redirigir a la página de inicio de sesión si el usuario no está autenticado
    if (loggedUser == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    // Deshabilitar el caché del navegador
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Banco - Pagina Principal</title>
    <link rel="icon" href="assets/img/logo.jpg" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-Z7kx3I2HCTJ6BEnEsq0glGeM7ZPh1x7w94i+uS3NvkHk3Yqyvm5vE2wxS6c9+j/tw+Bz3XsRFqhD5Lhydp0Tfg==" crossorigin="anonymous" />
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .btn-success {
        background-color: green;
        border-color: green;}
        .hidden { display: none; }
        #accordionPerfil .card-header:focus {
            outline-color: red !important; /* Cambia el color del foco a rojo */
            outline-style: solid !important; /* Cambia el estilo del foco a sólido */
        }
        body {
            background: #461220;
            font-family: "poppins";
        }
        .text-white {
            color: white !important; /* Asegura que el texto se mantenga blanco */
        }
        .thead-dark th {
            color: white;
        }
        .table-custom {
            background-color: #fffad4; /* Color de fondo personalizado para la tabla */
        }
        .table-custom td, .table-custom th {
            color: black; /* Color de texto blanco para la tabla */
        }
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: rgba(255, 255, 255, 5);
        }
        .table-striped tbody tr:nth-of-type(even) {
            background-color: rgba(255, 255, 255, 5);
        }
        .bg-custom {
            background-color: #fffad4 !important;
        }
        .text-black {
            color: black !important;
        }
        .custom-button:focus,
        .custom-button:active {
            outline: none;
            box-shadow: 0 0 0 0.2rem rgba(0, 0, 0, 0.25);
        }
        .back-acordion {
            background-color: #3e3e3e;
        }
    </style>
</head>
<body>
    <% 
        HomeView homeView = new HomeView(loggedUser, currentSession); // Instancia HomeView con el usuario y la sesión
        out.print(homeView.toString()); // Imprime la representación HTML de HomeView
    %>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="assets/js/scriptHome.js"></script>

</body>
</html>


  
