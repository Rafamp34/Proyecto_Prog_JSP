package views;

public class LoginView {
    
    String error =  null;

    public LoginView(){
    }

    public LoginView(String error){
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder htmlBuilder = new StringBuilder();
    
        htmlBuilder.append("<section class=\"container\">") 
                    .append("<div class=\"login-container\">")
                    .append("<div class=\"circle circle-one\"></div>")
                    .append("<div class=\"form-container\">")
                    .append("<img src=\"https://i.imgur.com/7no53BD.png\" alt=\"illustration\" class=\"illustration\" style=\"width: 150px; height: 150px;\" />")
                    .append("<h1 class=\"opacity\">LOGIN</h1>")
                    .append("<form action=\"login.jsp\" method=\"post\">")
                    .append("<input type=\"text\" name=\"usuario\" placeholder=\"Usuario\" />")
                    .append("<input type=\"password\" name=\"password\" placeholder=\"Contraseña\" />");
    
        if (error != null && !error.isEmpty()) {
            htmlBuilder.append("<div class=\"alert alert-danger\" role=\"alert\">")
                        .append(error)
                        .append("</div>");
        }
        htmlBuilder.append("<button type=\"submit\" class=\"opacity\">Iniciar Sesion</button>")
                    .append("</form>")
                    .append("<div class=\"register-forget opacity\">")
                    .append("<a href=\"register.jsp\">¿No tienes cuenta?</a>")
                    .append("</div>")
                    .append("</div>")
                    .append("<div class=\"circle circle-two\"></div>")
                    .append("</div>")
                    .append("<div class=\"theme-btn-container\"></div>")
                    .append("</section>");
    
        return htmlBuilder.toString();
    }
    
}
