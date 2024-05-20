<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="users.DatosBancariosService" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="connectionpool.ConnectionPool" %>
<%@ page import="users.DatosBancarios" %>

<%
    // Inicializar mensaje de error
    String errorMessage = null;

    // Obtener los parámetros del formulario
    String nombreTitular = request.getParameter("nombreTitular");
    String numeroCuenta = request.getParameter("numeroCuenta");
    String saldoStr = request.getParameter("saldo");
    String tipoTarjeta = request.getParameter("tipoTarjeta");

    // Verificar si todos los campos están presentes y no están vacíos
    if (nombreTitular != null && !nombreTitular.isEmpty() && 
        numeroCuenta != null && !numeroCuenta.isEmpty() &&
        saldoStr != null && !saldoStr.isEmpty() &&
        tipoTarjeta != null && !tipoTarjeta.isEmpty()) {
        
        // Intentar convertir el saldo a double
        double saldo = 0.0;
        try {
            saldo = Double.parseDouble(saldoStr);
        } catch (NumberFormatException e) {
            errorMessage = "El saldo debe ser un número válido.";
        }

        // Verificar si no hubo errores en la conversión del saldo
        if (errorMessage == null) {
            try {
                // Establecer conexión con la base de datos
                String dbuser = "RMP";
                String dbpassword = "12345";
                ConnectionPool pool = new ConnectionPool("jdbc:mysql://localhost:3306/login", dbuser, dbpassword);
                Connection conn = pool.getConnection();

                // Crear una instancia de DatosBancariosService
                DatosBancariosService service = new DatosBancariosService(conn);

                // Registrar los nuevos datos bancarios
                service.register(nombreTitular, numeroCuenta, saldo, tipoTarjeta);



                // Redireccionar a la página principal con un mensaje de éxito
                response.sendRedirect("home.jsp?successMessage=Datos bancarios agregados correctamente");
            } catch (SQLException e) {
                errorMessage = "Error al acceder a la base de datos: " + e.getMessage();
            }
        }
    } else {
        errorMessage = "Todos los campos son obligatorios.";
    }

    // Si hay un mensaje de error, redireccionar a la página principal con el mensaje
    if (errorMessage != null) {
        session.setAttribute("errorMessage", errorMessage);
        response.sendRedirect("home.jsp");
    }
%>
