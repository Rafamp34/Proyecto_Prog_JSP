package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/** IndividuoService
 * Clase que implementa la interfaz CRUD para la tabla Individuo
 * Funciona como un servicio de acceso a datos para el model Individuo
 */
public class UsersService {
    // Conexión activa con la base de datos
    private Connection conn;

    /** IndividuoService (Constructor)
     * Constructor que crea la instancia del servicio y
     * almacena la conexión a la base de datos 
     * @param conn Conexión con la base de datos
     */
    public UsersService(Connection conn){
        this.conn = conn;
    }


    private ArrayList<LoggedUser> _requestAll(String sql) throws SQLException{
        ArrayList<LoggedUser> result = new ArrayList<LoggedUser>();
        Statement statement = this.conn.createStatement();
        ResultSet querySet = statement.executeQuery(sql);
        // Recorremos cada uno de los registro devueltos por la consulta
        while(querySet.next()) {
            String name = querySet.getString("name");
            String surname = querySet.getString("surname");
            String username = querySet.getString("username");
            result.add(new LoggedUser(name, surname, username));
        }
        statement.close();
        return result;
    }


    public void register(String username, String password, String name, String surname) throws SQLException {
        String sql = "INSERT INTO users (username, password, name, surname) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, surname);
            stmt.executeUpdate();
        }
    }
    
    public ArrayList<LoggedUser> requestAll(String sortedBy) throws SQLException{
        String sql = "SELECT name, surname, username, id FROM users"+(sortedBy.length()>0?(" ORDER BY "+sortedBy):"");
        return this._requestAll(sql);
    }

    public LoggedUser requestById(long id) throws SQLException{
        //Varible conteniendo el Individuo a devolver
        LoggedUser result = null;
        //Construimos la consulta a realizar
        Statement statement = this.conn.createStatement();    
        String sql = String.format("SELECT name, surname, username FROM users WHERE id=%d", id);
        // Ejecución de la consulta
        ResultSet querySet = statement.executeQuery(sql);
        // Recorrido del resultado de la consulta
        if(querySet.next()) {
            String name = querySet.getString("name");
            String surname = querySet.getString("surname");
            String username = querySet.getString("username");
            result = new LoggedUser(name, surname, username);
        }
        statement.close();
        return result;
    }

    public int update(LoggedUser object) throws SQLException {
        long id = object.getId();
        String name = object.getName();
        String surname = object.getSurname();
        String username = object.getUsername();
        Statement statement = this.conn.createStatement(); 

        String sql = String.format("UPDATE users SET name = '%s', surname = '%s', username = '%s' WHERE id=%d", name, surname, username, id);
        int affectedRows = statement.executeUpdate(sql);
        statement.close();
        if (affectedRows == 0)
            throw new SQLException("Creating user failed, no rows affected.");
        else
            return affectedRows;
    }

    public boolean checkPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean update(LoggedUser user, String newPassword) throws SQLException {
        String sql = "UPDATE users SET name=?, surname=?, password=? WHERE username=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, newPassword);
            stmt.setString(4, user.getUsername());
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean updatePassword(String username, String newPassword) {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE users SET password = ? WHERE username = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, username);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public boolean isUserExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE username=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean delete(long id) throws SQLException {
        Statement statement = this.conn.createStatement();    
        String sql = String.format("DELETE FROM users WHERE id=%d", id);
        int result = statement.executeUpdate(sql);
        statement.close();
        return result==1;
    }
}
