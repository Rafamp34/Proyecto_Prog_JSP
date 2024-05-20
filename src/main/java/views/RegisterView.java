package views;

public class RegisterView {

    private String errorMessage;

    public RegisterView(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<div class=\"container mt-5\">")
                .append("<div class=\"row justify-content-center\">")
                .append("    <div class=\"col-md-6\">")
                .append("        <div class=\"card\">")
                .append("            <div class=\"card-header\">")
                .append("                <h4>Registro</h4>")
                .append("            </div>")
                .append("            <div class=\"card-body transparent\">") // Aplicando la clase 'transparent'
                .append(errorMessage != null ? "<div class=\"alert alert-danger\" role=\"alert\">" + errorMessage + "</div>" : "")
                .append("                <form action=\"registerr.jsp\" method=\"post\">")
                .append("                    <div class=\"form-group\">")
                .append("                        <label for=\"name\">Nombre</label>")
                .append("                        <input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" placeholder=\"Ingrese su nombre\">")
                .append("                    </div>")
                .append("                    <div class=\"form-group\">")
                .append("                        <label for=\"surname\">Apellido</label>")
                .append("                        <input type=\"text\" class=\"form-control\" id=\"surname\" name=\"surname\" placeholder=\"Ingrese su apellido\">")
                .append("                    </div>")
                .append("                    <div class=\"form-group\">")
                .append("                        <label for=\"username\">Usuario</label>")
                .append("                        <input type=\"text\" class=\"form-control\" id=\"username\" name=\"username\" placeholder=\"Ingrese su usuario\">")
                .append("                    </div>")
                .append("                    <div class=\"form-group\">")
                .append("                        <label for=\"password\">Contraseña</label>")
                .append("                        <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" placeholder=\"Ingrese su contraseña\">")
                .append("                    </div>")
                .append("                    <div class=\"form-group\">")
                .append("                        <label for=\"confirm-password\">Confirmar Contraseña</label>")
                .append("                        <input type=\"password\" class=\"form-control\" id=\"confirm-password\" name=\"confirm-password\" placeholder=\"Confirme su contraseña\">")
                .append("                    </div>")
                .append("                    <button type=\"submit\" class=\"btn btn-primary btn-block\">Registrarse</button>")
                .append("                </form>")
                .append("                <div class=\"mt-3 text-center\">")
                .append("                    ¿Ya tienes una cuenta? <a href=\"index.jsp\">Inicia sesión</a>")
                .append("                </div>")
                .append("            </div>")
                .append("        </div>")
                .append("    </div>")
                .append("</div>")
                .append("</div>");

        return htmlBuilder.toString();
    }
}
