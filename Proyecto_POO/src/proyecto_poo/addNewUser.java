/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_poo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    for (usuarios u : listaUsers) {
                        System.out.println(u.getUser_id() + " - " + u.getUser_type());
                        }
                 }
                
            }else{
                 try(PreparedStatement stmtLib = materialesBD.getConnection().prepareStatement(consultaUser)){
                     stmtLib.setString(1, id);
                     resultados= stmtLib.executeQuery();
                     while(resultados.next()){
                        
                        created_at = resultados.getTimestamp("created_at");
                        id = user_id;
                        name = resultados.getString("name");
                        last_name = resultados.getString("last_name");                 
                    carrera = resultados.getString("carrera");
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
            
            return listaUsers;
        }
public ArrayList<usuarios> fromBDUser(bd_Connection materialesBD, String type) throws SQLException{
//        String consulta = "SELECT * FROM materiales";
        listaUsers.clear();
            getUsersType(materialesBD,null,type);
            return listaUsers;
    }
    
}
