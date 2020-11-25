/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaevidencia;
import DB.Database;
/**
 *
 * @author jesgu
 */
public class Sesion {
    private String user;
    private String password;

    public Sesion(String user, String password) {
        this.user = user;
        this.password = password;
    }
    
    /**
     * 
     * @param db
     * @return 
     */
    public boolean iniciarSesion(Database db){
        if(db.validarSesion(this)){
            System.out.println("Sesión iniciada");
            return true;
        } else {
            System.err.println("Usuario o contraseña incorrecto");
            return false;
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
