package users;

public class LoggedUser {
    private long id;
    private String name;
    private String surname;
    private String username;
    private DatosBancarios datosBancarios;

    public LoggedUser(Long id, String name, String surname, String username){
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public LoggedUser(String name, String surname, String username){
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DatosBancarios getDatosBancarios() {
        return datosBancarios;
    }

    public void setDatosBancarios(DatosBancarios datosBancarios) {
        this.datosBancarios = datosBancarios;
    }
    @Override
    public String toString() {
        return String.format("%s %s (%s)",getName(), getSurname(), getUsername());
    }
}
