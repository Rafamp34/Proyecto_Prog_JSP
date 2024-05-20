<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="users.UsersService" %>
<%@ page import="users.LoggedUser" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="connectionpool.ConnectionPool"%>
<%@ page import="javax.servlet.*,javax.servlet.http.*" %>

<%
    String dbuser = "RMP";
    String dbpassword = "12345";
    ConnectionPool pool = new ConnectionPool("jdbc:mysql://localhost:3306/login", dbuser, dbpassword);
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

    // Obtener los datos del formulario
    String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    String currentPassword = request.getParameter("currentPassword");

    UsersService userService = new UsersService(pool.getConnection());

    // Validar la contraseña actual
    boolean passwordCorrect = userService.checkPassword(loggedUser.getUsername(), currentPassword);

    if (passwordCorrect) {
        // Actualizar los datos del usuario
        loggedUser.setName(name);
        loggedUser.setSurname(surname);
        boolean updated = userService.update(loggedUser, currentPassword);

        if (updated) {
            // Establecer mensaje de éxito en la sesión
            currentSession.setAttribute("successMessage", "Perfil actualizado correctamente.");
        } else {
            out.println("<script>alert('Error al actualizar el perfil.')</script>");
        }
    } else {
        // Contraseña incorrecta, establecer mensaje de error en la sesión
        currentSession.setAttribute("errorMessage", "La contraseña actual es incorrecta.");
    }
    pool.releaseConnection(pool.getConnection());
    // Redireccionar de vuelta a home.jsp
    response.sendRedirect("home.jsp#");
%>
