/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_poo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge LG
 */
public class newPrestamo {
    ArrayList <prestamos> listaPrestamo = new ArrayList<>();
    
    private String getTitulo(bd_Connection materialesBD, String consulta, String id) throws SQLException{
        List<String> userInfo = new ArrayList<>();
        String titulo="";
        String password="";
       //String consultaTitulo ="SELECT id_material FROM materiales WHERE id_material=?"; 

        try (PreparedStatement stmt = materialesBD.getConnection().prepareStatement(consulta)){
            stmt.setString(1, id);
            try (ResultSet resAll = stmt.executeQuery()){
                if (resAll.next()) {
                    titulo = resAll.getString("titulo");
                    resAll.close();
                }
            }
           
        }
        return titulo;
    }
    //usuario = registro.buscarUser(materialesBD, id,tipo)
    
     private List<String> getName(bd_Connection materialesBD, String consulta, String id) throws SQLException{
        List<String> userInfo = new ArrayList<>();
        String name="";
        String lastName="";
       //String consultaTitulo ="SELECT name,last_name FROM user_details WHERE user_id=?"; 

        try (PreparedStatement stmt = materialesBD.getConnection().prepareStatement(consulta)){
            stmt.setString(1, id);
            try (ResultSet resAll = stmt.executeQuery()){
                if (resAll.next()) {
                    name = resAll.getString("name");
                    lastName = resAll.getString("last_name");
                    resAll.close();
                }
            }
           
        }
        userInfo.add(name);
        userInfo.add(lastName);
        return userInfo;
    }
     
}
