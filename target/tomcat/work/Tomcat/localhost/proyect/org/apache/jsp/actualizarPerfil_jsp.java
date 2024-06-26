/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-05-20 06:46:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import users.UsersService;
import users.LoggedUser;
import java.io.*;
import java.util.*;
import connectionpool.ConnectionPool;
import javax.servlet.*;
import javax.servlet.http.*;

public final class actualizarPerfil_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

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

      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
