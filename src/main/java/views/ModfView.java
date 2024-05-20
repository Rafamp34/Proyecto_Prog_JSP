package views;


import javax.servlet.http.HttpSession;

import users.DatosBancarios;
import users.LoggedUser;


public class ModfView {
    private HttpSession session;

    public ModfView(HttpSession session){
        this.session = session;
    }

    @Override
    public String toString() {
        StringBuilder htmlBuilder = new StringBuilder();
        LoggedUser loggedUser = (LoggedUser) session.getAttribute("loggedUser");

        // Obtener datos bancarios del usuario de la sesión
        String nombreTitular = "";
        String numeroCuenta = "";
        Double saldo = 0.0;
        String tipoTarjeta = "";

        DatosBancarios datosBancarios = obtenerDatosBancariosPorUsuario(loggedUser);
        if (datosBancarios != null) {
            nombreTitular = datosBancarios.getNombreTitular();
            numeroCuenta = datosBancarios.getNumeroCuenta();
            saldo = datosBancarios.getSaldo();
            tipoTarjeta = datosBancarios.getTipoTarjeta();
        }

        // Construir el formulario con valores de placeholder
        htmlBuilder.append("<div class=\"container mt-5\">");
        htmlBuilder.append("    <div class=\"row justify-content-center\">");
        htmlBuilder.append("        <div class=\"col-md-6\">");
        htmlBuilder.append("            <div class=\"card\">");
        htmlBuilder.append("                <div class=\"card-header\">");
        htmlBuilder.append("                    <h4>Modificar Datos Bancarios</h4>");
        htmlBuilder.append("                </div>");
        htmlBuilder.append("                <div class=\"card-body\">");
        htmlBuilder.append("                    <form action=\"modificar.jsp\" method=\"post\">");
        htmlBuilder.append("                        <div class=\"form-group\">");
        htmlBuilder.append("                            <label for=\"nombreTitular\">Nombre del Titular</label>");
        htmlBuilder.append("                            <input type=\"text\" class=\"form-control\" id=\"nombreTitular\" name=\"nombreTitular\" placeholder=\"Ingrese el nuevo nombre del titular\" value=\"" + nombreTitular + "\">");
        htmlBuilder.append("                        </div>");
        htmlBuilder.append("                        <div class=\"form-group\">");
        htmlBuilder.append("                            <label for=\"numeroCuenta\">Número de Cuenta</label>");
        htmlBuilder.append("                            <input type=\"text\" class=\"form-control\" id=\"numeroCuenta\" name=\"numeroCuenta\" placeholder=\"Ingrese el nuevo número de cuenta\" value=\"" + numeroCuenta + "\">");
        htmlBuilder.append("                        </div>");
        htmlBuilder.append("                        <div class=\"form-group\">");
        htmlBuilder.append("                            <label for=\"saldo\">Saldo</label>");
        htmlBuilder.append("                            <input type=\"text\" class=\"form-control\" id=\"saldo\" name=\"saldo\" placeholder=\""+ saldo+"\">");
        htmlBuilder.append("                        </div>");
        htmlBuilder.append("                        <div class=\"form-group\">");
        htmlBuilder.append("                            <label for=\"tipoTarjeta\">Tipo de Tarjeta</label>");
        htmlBuilder.append("                            <input type=\"text\" class=\"form-control\" id=\"tipoTarjeta\" name=\"tipoTarjeta\" placeholder=\"Ingrese el nuevo tipo de tarjeta\" value=\"" + tipoTarjeta + "\">");
        htmlBuilder.append("                        </div>");
        htmlBuilder.append("                        <button type=\"submit\" class=\"btn btn-primary btn-block\">Guardar Cambios</button>");
        htmlBuilder.append("                    </form>");
        htmlBuilder.append("                    <div class=\"mt-3 text-center\">");
        htmlBuilder.append("                        <a href=\"home.jsp\">Cancelar</a>");
        htmlBuilder.append("                    </div>");
        htmlBuilder.append("                </div>");
        htmlBuilder.append("            </div>");
        htmlBuilder.append("        </div>");
        htmlBuilder.append("    </div>");
        htmlBuilder.append("</div>");
        return htmlBuilder.toString();
    }

    // Método para obtener los datos bancarios del usuario actual
    private DatosBancarios obtenerDatosBancariosPorUsuario(LoggedUser loggedUser) {
        // Implementa la lógica para obtener los datos bancarios del usuario actual
        // Supongamos que tienes un método en tu aplicación llamado obtenerDatosBancariosPorUsuario(LoggedUser usuario) que devuelve un objeto DatosBancarios
        // Implementa la lógica según las necesidades de tu aplicación
            
        return null; // Cambia esto para devolver los datos bancarios del usuario actual
    }
}
