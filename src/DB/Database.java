/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;
import java.sql.*;
import pruebaevidencia.Cita;
import pruebaevidencia.Doctor;
import pruebaevidencia.Paciente;
import pruebaevidencia.Sesion;
/**
 *
 * @author jesgu
 */
public class Database {
    String db="db\\dataCitas.db";
    Connection connection;
    
    public void Database(){}
    
    public void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+db);
        } catch (SQLException ex){
            System.err.println("Database\\connect(): " + ex.getMessage());
        }
    }
    
    public void close(){
        try{
            connection.close();
        } catch (SQLException ex){
            System.err.println("Database\\close(): " + ex.getMessage());
        }
    }
    
    /**
     * Método para validar el usuario y contraseña de sesión
     * @param sesion 
     * @return true si el los datos de sesion se encuentran en la base de datos
     */
    public boolean validarSesion(Sesion sesion){
        String query = "select * from sesion";
        boolean validation=false;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                if(sesion.getUser().equals(rs.getString(1)) && sesion.getPassword().equals(rs.getString(2))){
                    validation=true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Database\\validarSesion(): " + ex.getMessage());
        }
        return validation;
    }
    
    /**
     * 
     * @param paciente
     * @return 
     */
    public boolean agregarPaciente (Paciente paciente){
        String query ="Insert into Pacientes (idPaciente, nombe, descProb)"
                + "values('"+paciente.getId()+
                "','"+paciente.getName()+"','"
                +paciente.getDescProb()+"');";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
            System.out.println("\nEl paciente fue dado de alta exitosamente");
            return true;
        } catch (SQLException ex) {
            if (ex.getMessage().contains("UNIQUE")){
                System.err.println("\nEl ID ya fue ingresado anteriormente,"
                        + " ingrese otro\n");
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     */
    public void mostrarPacientes(){
        String query ="select * from Pacientes";
        int count=0;
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                System.out.printf("\n%s%s\n%s%s\n%s%s\n", "ID: ", rs.getInt(1),
                "Nombre: ", rs.getString(2), "Descripción del problema: "
                ,rs.getString(3));
                count+=1;
            }
            if(count==0){
                System.err.println("\nNo hay pacientes registrados");
            }
        } catch (SQLException ex){
            System.err.println("Database\\mostrarPacientes()" + ex.getMessage());
        }
    }
    
        public void mostrarDoctores(){
        String query ="select * from Doctores";
        int count=0;
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                System.out.printf("\n%s%s\n%s%s\n%s%s\n", "ID: ", rs.getInt(1),
                "Nombre: ", rs.getString(2), "Especialidad: ",rs.getString(3));
                count+=1;
            }
            if(count==0){
                System.err.println("\nNo hay doctores registrados");
            }
        } catch (SQLException ex){
            System.err.println("Database\\mostrarDoctores()" + ex.getMessage());
        }
    }
        
        public boolean agregarDoctor (Doctor doctor){
        String query ="Insert into Doctores (idDoctores, Nombre, Especialidad)"
                + "values('"+doctor.getId()+
                "','"+doctor.getName()+"','"
                +doctor.getEspecialidad()+"');";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
            System.out.println("\nEl doctor fue dado de alta exitosaente");
            return true;
        } catch (SQLException ex) {
            if (ex.getMessage().contains("UNIQUE")){
                System.err.println("\nEl ID ya fue ingresado anteriormente,"
                        + " ingrese otro\n");
                return false;
            }
        }
        return true;
    }
        
    public void mostrarCitas(){
        String query ="select * from Citas";
        int count=0;
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                System.out.printf("\n%s%s\n%s%s\n%s%s\n", "ID: ", rs.getInt(1),
                "Fecha: ", rs.getString(2), "Hora: ",rs.getString(3));
                count+=1;
            }
            if(count==0){
                System.err.println("\nNo hay citas registradas");
            }
        } catch (SQLException ex){
            System.err.println("Database\\mostrarCitas()" + ex.getMessage());
        }
    }
        
    public boolean agregarCita (Cita cita){
        String query ="Insert into Citas (idCita, fecha, hora)"
                + "values('"+cita.getIdCita()+
                "','"+cita.getFecha()+"','"
                +cita.getHora()+"');";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
            System.out.println("\nLa cita fue agendada exitosamente");
            return true;
        } catch (SQLException ex) {
            if (ex.getMessage().contains("UNIQUE")){
                System.err.println("\nEl ID ya fue ingresado anteriormente,"
                        + " ingrese otro\n");
                return false;
            }
        }
        return true;
    }
    
    private boolean verificarID(int id, String idTabla,String tabla){
        int count = 0;
        String query= "Select * from "+tabla +" where " + idTabla + "=" + id;
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                count++;
            }
            if (count>0){
                return true;
            } else {
                return false;
            } 
        } catch (SQLException ex){
            System.err.println("Database\\VerificarID: " + ex.getMessage());
        }
        return false;
    }
    
    private boolean verificarTodoID(int idCita, int idDoctor, int idPaciente){
        if(verificarID(idCita, "idCita", "citas")==false){
            System.err.println("El ID de la cita es incorrecto");
            return false;
        } else if (verificarID(idDoctor, "idDoctores", "Doctores")==false){
            System.err.println("El ID del doctor es incorrecto");
            return false;
        } else if(verificarID(idPaciente, "idPaciente", "Pacientes")==false){
            System.err.println("El ID del paciente es incorrecto");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean relacionarCitas (int idCita, int idDoctor, int idPaciente){
        //Verificar que los ID se enceuntren en la DB
        if (verificarTodoID(idCita, idDoctor, idPaciente)){
            String query="Insert into CitasRelacionadas(idCita,idDoctores,idPaciente)"
                + "values('"+idCita+"','"+idDoctor+"','"+idPaciente+"');";
            try {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.executeUpdate();
                System.out.println("\nLa cita fue relacionada exitosamente");
                return true;
            } catch (SQLException ex) {
                System.err.println("Database\\relacionarCitas(): " + ex.getMessage());
                    return false;
            }
        } else {
            return false;
        }
    }
    
    public void mostrarCitasRelacionadas(){
        String query ="select Doctores.Nombre,Pacientes.nombe,Citas.fecha,Citas.hora\n" +
        "from Doctores, Pacientes, Citas, CitasRelacionadas\n" +
        "where CitasRelacionadas.idDoctores = Doctores.idDoctores\n" +
        "and CitasRelacionadas.idPaciente = Pacientes.idPaciente\n" +
        "and CitasRelacionadas.idCita = Citas.idCita";
        int count = 0;
        try {
             PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs= stmt.executeQuery();
            while(rs.next()){
                System.out.printf("\n%s%s\n%s%s\n%s%s\n%s%s\n", "Doctor: ", rs.getString(1),
                "Paciente: ", rs.getString(2), "Fecha: ",rs.getString(3), "Hora: ", rs.getString(4));
                count+=1;
            }
            if(count==0){
                System.err.println("\nNo hay citas relacionadas");
            }
        } catch (SQLException ex){
            System.err.println("Database\\mostrarCitasRelacionadas(): " + ex.getMessage());
        }
    }
    
}
