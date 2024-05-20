<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="users.UsersService"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="shortcut icon" href="../img/favi.jpg" type="image/gif" />
    <link rel="icon" type="image/x-icon" href="assets/img/favi.jpg">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/style/style.css">
</head>
<body>

<%
    String errorMessage = null;

    // Retrieve form parameters
    String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirm-password");

    // Validate passwords
    if (!password.equals(confirmPassword)) {
        errorMessage = "Las contraseñas no coinciden.";
    } else {
        // Connection details
        String dbUser = "RMP";
        String dbPassword = "12345";
        String dbURL = "jdbc:mysql://localhost:3306/login";
        
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            UsersService usersService = new UsersService(conn);

            // Check if all fields are filled
            if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                errorMessage = "Por favor, complete todos los campos.";
            } else {
                // Check if the username already exists
                if (usersService.isUserExists(username)) {
                    errorMessage = "El usuario ya existe.";
                } else {
                    // Register the user
                    usersService.register(username, password, name, surname);
                    response.sendRedirect("index.jsp");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            errorMessage = "Error al cargar el controlador de base de datos.";
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage = "Error al conectar con la base de datos.";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    if (errorMessage != null) {
        response.sendRedirect("register.jsp?error= "+errorMessage);
    }
%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
