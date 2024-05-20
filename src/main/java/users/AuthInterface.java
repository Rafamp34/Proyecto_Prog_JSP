package users;

import java.sql.SQLException;

public interface AuthInterface {
    public LoggedUser login(String username, String password) throws SQLException;
    public void register(String username, String password, String name, String surname) throws SQLException;
}
