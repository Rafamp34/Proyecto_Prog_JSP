package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthService implements AuthInterface{
    Connection conn = null;

    public AuthService(Connection conn){
        this.conn = conn;
    }

    @Override
    public LoggedUser login(String username, String password) throws SQLException{
        //Varible conteniendo el Individuo a devolver
        LoggedUser result = null;
        //Construimos la consulta a realizar
        Statement statement = this.conn.createStatement();    
        String sql = String.format("SELECT name, surname FROM users WHERE username='%s' AND password='%s'", username, password);
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if(querySet.next()) {
            //Obtenemos los datos del Individuo
            String name = querySet.getString("name");
            String surname = querySet.getString("surname");
            result = new LoggedUser(name, surname, username);
        }
        //Cerramos la consulta
        statement.close();    
        //Devolvemos el individuo
        return result;
    }

    public void register(String username, String password, String name, String surname) throws SQLException {
        String sql = "INSERT INTO users (username, password, name, surname) VALUES (?, ?, ?, ?)";
        
        PreparedStatement statement = this.conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, name);
        statement.setString(4, surname);
        
        statement.executeUpdate();
        statement.close();
    }
    
}
