package users;

public class DatosBancarios {
    
    String nombreTitular;
    String numeroCuenta;
    double saldo;
    String tipoTarjeta;

    public DatosBancarios(String nombreTitular, String numeroCuenta, double saldo, String tipoTarjeta) {
        this.nombreTitular = nombreTitular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    @Override
    public String toString() {
        return "DatosBancarios [nombreTitular=" + nombreTitular + ", numeroCuenta=" + numeroCuenta + ", saldo=" + saldo
                + ", tipoTarjeta=" + tipoTarjeta + "]";
    }
    
}
