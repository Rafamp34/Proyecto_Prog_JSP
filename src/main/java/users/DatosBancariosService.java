package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatosBancariosService {
    
    private Connection conn;

    /** IndividuoService (Constructor)
     * Constructor que crea la instancia del servicio y
     * almacena la conexión a la base de datos 
     * @param conn Conexión con la base de datos
     */
    public DatosBancariosService(Connection conn){
        this.conn = conn;
    }

    public void register(String nombreTitular, String numeroCuenta, Double saldo, String tipoTarjeta) throws SQLException {
        String sql = "INSERT INTO datosbancarios (numeroCuenta, nombreTitular, saldo, tipoTarjeta) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreTitular);
            stmt.setString(2, numeroCuenta);
            stmt.setDouble(3, saldo);
            stmt.setString(4, tipoTarjeta);
            stmt.executeUpdate();
        }
    }

    private ArrayList<DatosBancarios> _requestAll(String sql) throws SQLException{
        ArrayList<DatosBancarios> result = new ArrayList<DatosBancarios>();
        Statement statement = this.conn.createStatement();
        ResultSet querySet = statement.executeQuery(sql);
        // Recorremos cada uno de los registro devueltos por la consulta
        while(querySet.next()) {
            String nombreTitular = querySet.getString("nombreTitular");
            String numeroCuenta = querySet.getString("numeroCuenta");
            Double saldo = querySet.getDouble("saldo");
            String tipoTarjeta = querySet.getString("tipoTarjeta");
            result.add(new DatosBancarios(nombreTitular, numeroCuenta, saldo, tipoTarjeta));
        }
        statement.close();
        return result;
    }

    public ArrayList<DatosBancarios> requestAll(String sortedBy) throws SQLException{
        String sql = "SELECT nombreTitular, numeroCuenta, saldo, tipoTarjeta FROM datosbancarios"+(sortedBy.length()>0?(" ORDER BY "+sortedBy):"");
        return this._requestAll(sql);
    }

    public DatosBancarios requestById(int id) throws SQLException {
        DatosBancarios result = null;
        Statement statement = this.conn.createStatement();    
        String sql = String.format("SELECT nombreTitular, numeroCuenta, saldo, tipoTarjeta FROM datosbancarios WHERE id=%d", id);
        ResultSet querySet = statement.executeQuery(sql);
        if (querySet.next()) {
            String nombreTitular = querySet.getString("nombreTitular");
            String numeroCuenta = querySet.getString("numeroCuenta");
            Double saldo = querySet.getDouble("saldo");
            String tipoTarjeta = querySet.getString("tipoTarjeta");
            result = new DatosBancarios(nombreTitular, numeroCuenta, saldo, tipoTarjeta);
        }
        statement.close();
        return result;
    }


    public int update(DatosBancarios object) throws SQLException {
        String sql = "UPDATE datosbancarios SET nombreTitular=?, numeroCuenta=?, saldo=?, tipoTarjeta=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, object.getNombreTitular());
            stmt.setString(2, object.getNumeroCuenta());
            stmt.setDouble(3, object.getSaldo());
            stmt.setString(4, object.getTipoTarjeta());
            stmt.executeUpdate();
            return stmt.getUpdateCount();
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM datosbancarios WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    
}