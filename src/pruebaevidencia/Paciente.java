/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaevidencia;

/**
 *
 * @author jesgu
 */
public class Paciente extends User{
    
    private String descProb;

    public Paciente(String name, int id, String descProb) {
        super(name, id);
        this.descProb = descProb;
    }

    public String getDescProb() {
        return descProb;
    }

    public void setDescProb(String descProb) {
        this.descProb = descProb;
    }
    
    
    
}
