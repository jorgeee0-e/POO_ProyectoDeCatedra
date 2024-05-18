/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_poo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge LG
 */
public class addNewUser {
    ArrayList <usuarios> listaUsers = new ArrayList<>();
    
    private List<String> getCredenciales(bd_Connection materialesBD, String consulta, String id) throws SQLException{
        List<String> userInfo = new ArrayList<>();
        String user_type="";
        String password="";
       //String idAutor ="SELECT id_autor FROM materiales WHERE id_material=?"; 
       //String autorConsulta ="SELECT nombre_autor FROM autor WHERE id_autor=?";
        try (PreparedStatement stmt = materialesBD.getConnection().prepareStatement(consulta)){
            stmt.setString(1, id);
            try (ResultSet resAll = stmt.executeQuery()){
                if (resAll.next()) {
                    user_type = resAll.getString("user_type");
                    password = resAll.getString("password");
                    resAll.close();
                }
            }
           
        }
        userInfo.add(user_type);
        userInfo.add(password);
        return userInfo;
    }
    
    public void getUsersType(bd_Connection materialesBD,String user_id,String type){
       String consultaUser = "";
       
       if (user_id==null) {
              consultaUser = "SELECT * FROM user_details WHERE  user_type= '"+type+"'";
              System.out.println(consultaUser);
           }else {
              consultaUser = "SELECT * FROM user_details WHERE user_id = ?";
              System.out.println(consultaUser);
           }
       String credencialesConsulta ="SELECT user_type, password FROM users WHERE user_id=?"; 
       String id="";
       String name ="";
       String last_name= "";
       String carrera= "";
       Timestamp created_at;
       String email= "";
       String user_type= "";
       String password= "";

       ResultSet resultados = null;
        try {
             if (user_id==null) {
                resultados = materialesBD.createQuery(consultaUser);
                 while (resultados.next()) {
                    
                    created_at = resultados.getTimestamp("created_at");
                     System.out.println(created_at);
                    id = resultados.getString("user_id");
                    System.out.println(id);
                    name = resultados.getString("name");
                    System.out.println(name);
                    last_name = resultados.getString("last_name"); 
                    System.out.println(last_name);
                    carrera = resultados.getString("carrera_id");
                    email = resultados.getString("email");
                    List<String> userInfo = getCredenciales(materialesBD,credencialesConsulta,id);
                    user_type= userInfo.get(0);
                    password= userInfo.get(1);
                    listaUsers.add(new student(name, last_name, carrera, created_at, email, id, user_type, password));
                 }
                
            }else{
                 try(PreparedStatement stmtLib = materialesBD.getConnection().prepareStatement(consultaUser)){
                     stmtLib.setString(1, user_id);
                     resultados= stmtLib.executeQuery();
                     while(resultados.next()){
                        
                        created_at = resultados.getTimestamp("created_at");
                        id = user_id;
                        name = resultados.getString("name");
                        last_name = resultados.getString("last_name");                 
                    carrera = resultados.getString("carrera_id");
                    email = resultados.getString("email");
                    List<String> userInfo = getCredenciales(materialesBD,credencialesConsulta,id);
                    user_type= userInfo.get(0);
                    password= userInfo.get(1);
                        listaUsers.add(new student(name, last_name, carrera, created_at, email, id, user_type, password));
                         
                     }
                 }
                 
             }
            
             }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error al procesar los resultados: "+ e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
             }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al convertir un número: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
             }
            finally{
                try{
                    if (resultados!= null) {
                    resultados.close();
                    }if (materialesBD.getConnection()!=null) {
                    materialesBD.getConnection().close();
                    }
                    }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    }       
    }
public ArrayList<usuarios>  buscarUser(bd_Connection materialesBD,String id,String type) throws SQLException{
            listaUsers.clear();
            getUsersType(materialesBD,id, type);
            for (usuarios u : listaUsers) {
            System.out.println(u);
        }
            return listaUsers;
        }
public ArrayList<usuarios> fromBDUser(bd_Connection materialesBD, String type) throws SQLException{
//        String consulta = "SELECT * FROM materiales";
        listaUsers.clear();
            getUsersType(materialesBD,null,type);
            return listaUsers;
    }

public void updatePass(String user_id,String newPw){
        bd_Connection materialesBD = new bd_Connection();
        PreparedStatement pStm = null;
        
        try{
            String updatedQuery = "UPDATE users SET password = ? WHERE user_id = ?";
            pStm = materialesBD.getConnection().prepareStatement(updatedQuery);
           
            pStm.setString(1, newPw);
            pStm.setString(2, user_id);

            
            pStm.executeUpdate();

            JOptionPane.showMessageDialog(null, "La contraseña se actualizó exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }catch (ClassCastException e) {
        JOptionPane.showMessageDialog(null, "Error de conversión de tipos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            try{
                if (pStm != null) {
                pStm.close();
                }if (materialesBD.getConnection()!=null) {
                materialesBD.getConnection().close();
                }
                }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                } 
        
    }

public void insertToUsers(String id,String name, String last_name,String carrera_id, Timestamp created_at,String email, String user_type, String password){
        bd_Connection materialesBD = new bd_Connection();
        PreparedStatement pStm = null;
        PreparedStatement pStmM = null;
        
        try{
            pStm = materialesBD.getConnection().prepareStatement("INSERT INTO user_details VALUES (?,?,?,?,?,?,?)");
            pStmM = materialesBD.getConnection().prepareStatement("INSERT INTO users VALUES (?,?,?)");
            pStmM.setString(1, id);
            pStmM.setString(2, user_type);
            pStmM.setString(3, password);
            pStmM.executeUpdate();
            
            pStm.setString(1, id);
            pStm.setString(2, name);
            pStm.setString(3, last_name);
            pStm.setString(4, carrera_id);
            pStm.setTimestamp(5,created_at);
            pStm.setString(6,email);
            pStm.setString(7,user_type);
            pStm.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Nuevo usuario registrado exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }finally{
            try{
                if (pStm != null) {
                pStm.close();
                }if (materialesBD.getConnection()!=null) {
                materialesBD.getConnection().close();
                }
                }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                }
                }           
    }
              

    
}
