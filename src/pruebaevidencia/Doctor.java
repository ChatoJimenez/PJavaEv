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
public class Doctor extends User{
    String especialidad;

    public Doctor(String name, int id, String especialidad) {
        super(name, id);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
}
