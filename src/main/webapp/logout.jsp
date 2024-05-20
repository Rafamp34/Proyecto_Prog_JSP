<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cerrar Sesión</title>
</head>
<body>
<%
    session.invalidate(); // Invalidar la sesión actual
    response.sendRedirect("index.jsp"); // Redirigir al inicio de sesión
%>
    <script>
        window.addEventListener("pageshow" , function (event) {
        if (event.persisted) {
        window.location.reload();
        }
    });
</script>
</body>
</html>
