<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="users.DatosBancariosService" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="connectionpool.ConnectionPool" %>

<%
    String idString = request.getParameter("id");
    if (idString != null && !idString.isEmpty()) {
        try {
            int id = Integer.parseInt(idString);

            String dbuser = "RMP";
            String dbpassword = "12345";
            ConnectionPool pool = new ConnectionPool("jdbc:mysql://localhost:3306/login", dbuser, dbpassword);
            Connection conn = pool.getConnection();

            DatosBancariosService service = new DatosBancariosService(conn);

            try {
                service.delete(id);
                session.setAttribute("successMessage", "Registro eliminado correctamente.");
            } catch (SQLException e) {
                session.setAttribute("errorMessage", "Error al eliminar el registro.");
                e.printStackTrace();
            }

            pool.releaseConnection(conn);
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "ID inválido.");
            e.printStackTrace();
        }
    } else {
        session.setAttribute("errorMessage", "ID no proporcionado.");
    }

    response.sendRedirect("home.jsp");
%>
