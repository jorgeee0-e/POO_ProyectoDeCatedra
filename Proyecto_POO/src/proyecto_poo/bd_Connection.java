/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_poo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author Jorge LG
 */
public class bd_Connection {
    /*private static String driver="com.mysql.jdbc.Driver";
    private static String user  ="root";
    private static String pw    ="Hello7410.";
    private static String URL   ="jdbc:mysql://127.0.0.1:3308/bibiliteca_amigosdedonbosco";*/
    private static String driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String pw = "Hello7410.";
    private static String URL   ="jdbc:mysql://127.0.0.1:3308/bibiliteca_amigosdedonbosco";
    
    private Connection conexion; //conexion a db
    private ResultSet rs; //tabla interna para mostrar consulta
    private Statement stm; //sentencias sql
    
    static {
            try{
                Class.forName(driver);
                }   catch(ClassNotFoundException e){
                    JOptionPane.showMessageDialog(null, "Error en el driver"+e, "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
    public Connection getConnection(){ //metodo que se usa para asegurarse primeramente que la conexion fue exitosa. 
    try{
        conexion = DriverManager.getConnection(URL, user, pw);
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
    }
    return conexion;
}
    
   public ResultSet createQuery(String consulta){
       try{
        //usando el metodo get connection definido antes para usar la conexion establecida para poder hacer la consulta
       Connection conexion = getConnection();
       //Crea un objeto statement para ejecutar la consulta a la conexion creada anteriormente
       Statement stm = conexion.createStatement(); 
       rs = stm.executeQuery(consulta);
          
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Error en la consulta", "Error",JOptionPane.ERROR_MESSAGE);
       }
        return rs;
   }
   }
   
