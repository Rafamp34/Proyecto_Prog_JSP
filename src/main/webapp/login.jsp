<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="users.AuthService" %>
<%@ page import="users.LoggedUser" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio de Sesión</title>
</head>
<body>

    <%
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        // Establecer la conexión a la base de datos
        String dbURL = "jdbc:mysql://localhost:3306/login";
        String dbUser = "RMP";
        String dbPassword = "12345";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("Error al cargar el controlador de base de datos");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error al conectar con la base de datos");
        }

        // Procesar inicio de sesión si la conexión es válida
        if (conn != null) {
            AuthService authService = new AuthService(conn);
            LoggedUser loggedUser = authService.login(usuario, password);
            if (loggedUser != null) {
                session.setAttribute("loggedUser", loggedUser);
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("index.jsp?error=Usuario o contraseña no válido");
            }
            try {
                conn.close(); // Cerrar la conexión a la base de datos
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Error al cerrar la conexión a la base de datos");
            }
        } else {
            out.println("Error al conectar con la base de datos");
        }
    %>

</body>
</html>
