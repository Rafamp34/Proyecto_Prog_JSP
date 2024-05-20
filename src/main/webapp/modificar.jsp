<%@ page import="java.sql.*" %>
<%@ page import="connectionpool.ConnectionPool" %>
<%@ page import="users.DatosBancariosService" %>
<%@ page import="users.DatosBancarios" %>

<%
    Connection conn = null;
    DatosBancariosService bancariosService = null;

    try {
        // Configurar la conexión a la base de datos
        String dbuser = "RMP";
        String dbpassword = "12345";
        ConnectionPool pool = new ConnectionPool("jdbc:mysql://localhost:3306/login", dbuser, dbpassword);
        conn = pool.getConnection();
        bancariosService = new DatosBancariosService(conn);

        // Obtener los nuevos datos bancarios desde el formulario
        String numeroCuenta = request.getParameter("numeroCuenta");
        String nombreTitular = request.getParameter("nombreTitular");
        String saldoParameter = request.getParameter("saldo");
        double saldo = 0.0; // Valor por defecto en caso de que no se pueda convertir el saldo
        if (saldoParameter != null && !saldoParameter.trim().isEmpty()) {
            saldo = Double.parseDouble(saldoParameter);
        }
        String tipoTarjeta = request.getParameter("tipoTarjeta");

        // Insertar los datos bancarios en la base de datos
        bancariosService.register(nombreTitular, numeroCuenta, saldo, tipoTarjeta);

        // Mostrar un mensaje de éxito después de la inserción
        out.println("Los datos bancarios se han insertado correctamente.");
    } catch (SQLException e) {
        // Manejar cualquier excepción de SQL
        out.println("Error de base de datos: " + e.getMessage());
    } finally {
        // Cerrar la conexión y los recursos JDBC
        if (conn != null) {
            try { conn.close(); } catch (SQLException e) { /* Ignorar */ }
        }
    }
%>
