package views;

import javax.servlet.http.HttpSession;
import connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import users.LoggedUser;

public class HomeView {

    LoggedUser user = null;
    HttpSession session = null;
    Connection conn = null;

    public HomeView() {
    }

    public HomeView(LoggedUser user, HttpSession session){
        this.user = user;
        this.session = session;
    }

    @Override
    public String toString() {
        StringBuilder htmlBuilder = new StringBuilder();
        LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");
        String dbuser = "RMP";
        String dbpassword = "12345";
        ConnectionPool pool = new ConnectionPool("jdbc:mysql://localhost:3306/login", dbuser, dbpassword);
        Connection conn = pool.getConnection();
        String username = "";
        String surname = "";
        String name = "";
    
        if (loggedUser != null) {
            username = loggedUser.getUsername();
            name = loggedUser.getName();
            surname = loggedUser.getSurname();
        }
    
        htmlBuilder.append("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">");
        htmlBuilder.append("    <a class=\"navbar-brand\" href=\"#\">");
        htmlBuilder.append("      <img src=\"assets/img/logo.jpg\" alt=\"Mi Marca\" width=\"30\" height=\"30\" class=\"d-inline-block align-top\" loading=\"lazy\">");
        htmlBuilder.append("      Banca Online");
        htmlBuilder.append("    </a>");
        htmlBuilder.append("    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
        htmlBuilder.append("      <span class=\"navbar-toggler-icon\"></span>");
        htmlBuilder.append("    </button>");
        htmlBuilder.append("    <div class=\"collapse navbar-collapse\" id=\"navbarNav\">");
        htmlBuilder.append("      <ul class=\"navbar-nav mr-auto\">");
        htmlBuilder.append("        <li class=\"nav-item active\">");
        htmlBuilder.append("          <a class=\"nav-link\" href=\"#\" onclick=\"showContent('home')\">Home</a>");
        htmlBuilder.append("        </li>");
        htmlBuilder.append("        <li class=\"nav-item\">");
        htmlBuilder.append("          <a class=\"nav-link\" href=\"#\" onclick=\"showContent('perfil')\">Perfil</a>");
        htmlBuilder.append("        </li>");
        htmlBuilder.append("      </ul>");
        htmlBuilder.append("      <div class=\" text-right\">");
        htmlBuilder.append("        <span class=\"text-white\">Bienvenido ").append(username).append("</span>");
        htmlBuilder.append("        <a class=\"btn btn-danger ml-3\" href=\"logout.jsp\">✘</a>");
        htmlBuilder.append("      </div>");
        htmlBuilder.append("    </div>");
        htmlBuilder.append("  </nav>");

        htmlBuilder.append("  <div class=\"container mt-4 text-white\">");
        htmlBuilder.append("    <div class=\"content\" id=\"home\">");
        htmlBuilder.append("      <h1>Home</h1>");
        htmlBuilder.append("      <p>Bienvenido a la página principal. Aquí puedes encontrar información importante y recursos.</p>");
        
        String sql = "SELECT ID, NumeroCuenta, NombreTitular, saldo, TipoTarjeta FROM datosbancarios";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            htmlBuilder.append("<div class=\"table-responsive\">");
            htmlBuilder.append("<table class=\"table table-bordered table-custom text-center\">"); // Añadimos la clase text-center
            htmlBuilder.append("<thead class=\"thead-dark\">");
            htmlBuilder.append("<tr><th>Numero de Cuenta</th><th>Titular</th><th>Saldo</th><th>Tipo Tarjeta</th><th>Acciones</th></tr>");
            htmlBuilder.append("</thead>");
            htmlBuilder.append("<tbody>");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String NumeroCuenta = rs.getString("NumeroCuenta");
                String NombreTitular = rs.getString("NombreTitular");
                Double saldo = rs.getDouble("saldo");
                String TipoTarjeta = rs.getString("TipoTarjeta");
                htmlBuilder.append("<tr><td>").append(NumeroCuenta).append("</td><td>").append(NombreTitular).append("</td><td>").append(saldo).append("</td><td>").append(TipoTarjeta).append("</td>");
                htmlBuilder.append("<td>");
                htmlBuilder.append("<a href='modficar.jsp?id=").append(id).append("' class='btn btn-warning btn-sm'>Modificar</a> ");
                htmlBuilder.append("<a href='eliminar.jsp?id=").append(id).append("' class='btn btn-danger btn-sm'>Eliminar</a>");
                htmlBuilder.append("</td></tr>");
            }
            htmlBuilder.append("</tbody>");
            htmlBuilder.append("</table>");
            htmlBuilder.append("</div>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        String errorMessage = (String) session.getAttribute("errorMessage");
        if (errorMessage != null) {
            htmlBuilder.append("<div class=\"alert alert-danger\" role=\"alert\">");
            htmlBuilder.append(errorMessage);
            htmlBuilder.append("</div>");
            session.removeAttribute("errorMessage");
        }
    
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            htmlBuilder.append("<div class=\"alert alert-success\" role=\"alert\">");
            htmlBuilder.append(successMessage);
            htmlBuilder.append("</div>");
            session.removeAttribute("successMessage");
        }
    
        htmlBuilder.append("    </div>");
        htmlBuilder.append("    <div class=\"content hidden\" id=\"perfil\">");
        htmlBuilder.append("      <div class=\"accordion bg-light\" id=\"accordionPerfil\">"); 
        htmlBuilder.append("        <div class=\"card bg-custom\">");
        htmlBuilder.append("          <div class=\"card-header back-acordion text-white\" id=\"headingActualizar\">");
        htmlBuilder.append("            <h2 class=\"mb-0\">");
        htmlBuilder.append("              <button class=\"btn btn-link text-white custom-button\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapseActualizar\" aria-expanded=\"true\" aria-controls=\"collapseActualizar\">");
        htmlBuilder.append("                Actualizar Perfil");
        htmlBuilder.append("              </button>");
        htmlBuilder.append("            </h2>");
        htmlBuilder.append("          </div>");
        htmlBuilder.append("          <div id=\"collapseActualizar\" class=\"collapse show\" aria-labelledby=\"headingActualizar\" data-parent=\"#accordionPerfil\">");
        htmlBuilder.append("            <div class=\"card-body text-black\">");
        htmlBuilder.append("              <form action=\"actualizarPerfil.jsp\" method=\"post\" onsubmit=\"return validateForm()\">");
        htmlBuilder.append("                  <div class=\"form-group\">");
        htmlBuilder.append("                      <label for=\"name\">Nombre:</label>");
        htmlBuilder.append("                      <input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" value=\"" + name + "\">");
        htmlBuilder.append("                  </div>");
        htmlBuilder.append("                  <div class=\"form-group\">");
        htmlBuilder.append("                      <label for=\"surname\">Apellido:</label>");
        htmlBuilder.append("                      <input type=\"text\" class=\"form-control\" id=\"surname\" name=\"surname\" value=\"" + surname + "\">");
        htmlBuilder.append("                  </div>");
        htmlBuilder.append("                  <div class=\"form-group\">");
        htmlBuilder.append("                      <label for=\"currentPassword\">Contraseña Actual:</label>");
        htmlBuilder.append("                      <input type=\"password\" class=\"form-control\" id=\"currentPassword\" name=\"currentPassword\">");
        htmlBuilder.append("                  </div>");
        htmlBuilder.append("                  <button type=\"submit\" class=\"btn btn-primary\">Actualizar Perfil</button>");
        htmlBuilder.append("              </form>");
        htmlBuilder.append("            </div>");
        htmlBuilder.append("          </div>");
        htmlBuilder.append("        </div>");
        htmlBuilder.append("        <div class=\"card bg-custom\">");
        htmlBuilder.append("          <div class=\"card-header back-acordion text-white\" id=\"headingContrasena\">");
        htmlBuilder.append("            <h2 class=\"mb-0\">");
        htmlBuilder.append("              <button class=\"btn btn-link collapsed text-white custom-button\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapseContrasena\" aria-expanded=\"false\" aria-controls=\"collapseContrasena\">");
        htmlBuilder.append("                Cambiar Contraseña");
        htmlBuilder.append("              </button>");
        htmlBuilder.append("            </h2>");
        htmlBuilder.append("          </div>");
        htmlBuilder.append("          <div id=\"collapseContrasena\" class=\"collapse\" aria-labelledby=\"headingContrasena\" data-parent=\"#accordionPerfil\">");
        htmlBuilder.append("            <div class=\"card-body text-black\">");
        htmlBuilder.append("              <form action=\"cambiarContrasena.jsp\" method=\"post\" onsubmit=\"return validatePasswordChangeForm()\">");
        htmlBuilder.append("                  <div class=\"form-group\">");
        htmlBuilder.append("                      <label for=\"currentPassword\">Contraseña Actual:</label>");
        htmlBuilder.append("                      <input type=\"password\" class=\"form-control\" id=\"currentPassword\" name=\"currentPassword\">");
        htmlBuilder.append("                  </div>");
        htmlBuilder.append("                      <div class=\"form-group\">");
        htmlBuilder.append("                          <label for=\"newPassword\">Nueva Contraseña:</label>");
        htmlBuilder.append("                          <input type=\"password\" class=\"form-control\" id=\"newPassword\" name=\"newPassword\">");
        htmlBuilder.append("                      </div>");
        htmlBuilder.append("                      <div class=\"form-group\">");
        htmlBuilder.append("                          <label for=\"confirmPassword\">Confirmar Contraseña:</label>");
        htmlBuilder.append("                          <input type=\"password\" class=\"form-control\" id=\"confirmPassword\" name=\"confirmPassword\">");
        htmlBuilder.append("                          <span id=\"passwordMismatchError\" style=\"color: red;\"></span>");
        htmlBuilder.append("                      </div>");
        htmlBuilder.append("                      <button type=\"submit\" class=\"btn btn-primary\">Cambiar Contraseña</button>");
        htmlBuilder.append("                  </form>");
        htmlBuilder.append("            </div>");
        htmlBuilder.append("          </div>");
        htmlBuilder.append("        </div>");
        htmlBuilder.append("      </div>");
        htmlBuilder.append("    </div>");
        htmlBuilder.append("  </div>");
        return htmlBuilder.toString();
                
    }
}
