package Objetos;

/**
 *
 * @author jose_
 */

public class Usuario {

    private String username, password;
    private int idContrato;
    
    public Usuario(String username, String password, int idContrato){
        this.username = username;
        this.password = password;
        this.idContrato = idContrato;
    }
    
    public Usuario(){
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
    
}
