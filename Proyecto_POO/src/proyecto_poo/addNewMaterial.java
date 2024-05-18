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
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge LG
 */
public class addNewMaterial {
    
    ArrayList <Material> listaMaterial = new ArrayList<>();
    //metodo para extraer el titulo de la tabla materiales con base en el id
    private String getTitulo(bd_Connection materialesBD, String consulta, String id) throws SQLException{
        String titulo="";
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
    //metodo para extraer el autor con base en el id_autor de la tabla autor
     private List<String> getAutor(bd_Connection materialesBD, String consulta,String autorConsulta, String id) throws SQLException{
        List<String> autorInfo = new ArrayList<>();
        String id_autor="";
        String autor="";
       //String idAutor ="SELECT id_autor FROM materiales WHERE id_material=?"; 
       //String autorConsulta ="SELECT nombre_autor FROM autor WHERE id_autor=?";
        try (PreparedStatement stmt = materialesBD.getConnection().prepareStatement(consulta)){
            stmt.setString(1, id);
            try (ResultSet resAll = stmt.executeQuery()){
                if (resAll.next()) {
                    id_autor = resAll.getString("id_autor");
                    resAll.close();
                }
            }
            if(!id_autor.isEmpty()){
                try(PreparedStatement stmtAutor = materialesBD.getConnection().prepareStatement(autorConsulta)){
                    stmtAutor.setString(1, id_autor);
                    try(ResultSet resAutor = stmtAutor.executeQuery()){
                        if(resAutor.next()){
                            autor= resAutor.getString("nombre_autor");
                        }
                        
                    }
                }
            }
            
        }
        autorInfo.add(id_autor);
        autorInfo.add(autor);
        return autorInfo;
    }
    
    
    
    public void getLibros(bd_Connection materialesBD,String id){
       String consultaLIB = "";
       
       if (id==null) {
              consultaLIB = "SELECT * FROM libros";
           }else {
              consultaLIB = "SELECT * FROM libros WHERE id_libro = ?";
           }
       String tituloConsulta ="SELECT titulo FROM materiales WHERE id_material=?"; 
       String idAutor ="SELECT id_autor FROM materiales WHERE id_material=?"; 
       String autorConsulta ="SELECT nombre_autor FROM autor WHERE id_autor=?"; 
       String tipo="Libro";
       String titulo="";
       String id_libro ="";
       String autor= "";
       String id_autor= "";
       String curso_id= "";
       String location= "";
       String editorial= "";
       String ISBN= "";
       int  stock;
       ResultSet resultados = null;
        try {
             if (id==null) {
                resultados = materialesBD.createQuery(consultaLIB);
                 while (resultados.next()) {
                    
                    Date fecha_publicacion = resultados.getDate("fecha_publicacion");
                    ISBN = resultados.getString("ISBN");
                    editorial = resultados.getString("editorial");
                    stock = Integer.parseInt(resultados.getString("stock")); 
                    location = resultados.getString("location");                 
                    id_libro = resultados.getString("id_libro");
                    titulo = getTitulo(materialesBD,tituloConsulta,id_libro);
                    List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_libro);
                    id_autor= autorInfo.get(0);
                    curso_id = resultados.getString("id_libro");
                    autor= autorInfo.get(1);
                    listaMaterial.add(new libros(fecha_publicacion,ISBN, editorial,stock, location, id_libro, titulo, id_autor, tipo,curso_id,autor));     
                 }
                
            }else{
                 try(PreparedStatement stmtLib = materialesBD.getConnection().prepareStatement(consultaLIB)){
                     stmtLib.setString(1, id);
                     resultados= stmtLib.executeQuery();
                     while(resultados.next()){
                        
                        Date fecha_publicacion = resultados.getDate("fecha_publicacion");
                        ISBN = resultados.getString("ISBN");
                        editorial = resultados.getString("location");
                        stock = Integer.parseInt(resultados.getString("stock")); 
                        location = resultados.getString("editorial");     
                        id_libro = id;
                        titulo = getTitulo(materialesBD,tituloConsulta,id_libro);
                        List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_libro);
                        id_autor= autorInfo.get(0);
                        curso_id = resultados.getString("id_libro");
                        autor= autorInfo.get(1);
                        listaMaterial.add(new libros(fecha_publicacion,ISBN, editorial,stock, location, id_libro, titulo, id_autor, tipo,curso_id,autor));
                         
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
    
    public void getCd(bd_Connection materialesBD,String id){
       String consultaCd = "";
       
       if (id==null) {
              consultaCd = "SELECT * FROM cds";
           }else {
              consultaCd = "SELECT * FROM cds WHERE id_cd = ?";
           }
       String tituloConsulta ="SELECT titulo FROM materiales WHERE id_material=?"; 
       String idAutor ="SELECT id_autor FROM materiales WHERE id_material=?"; 
       String autorConsulta ="SELECT nombre_autor FROM autor WHERE id_autor=?"; 
       String tipo="CD";
       String titulo="";
       String id_cd ="";
       String autor= "";
       String id_autor= "";
       String curso_id= "";
       String location= "";
       String editorial= "";
       String ISBN= "";
       int  stock;
       ResultSet resultados = null;
        try {
             if (id==null) {
                resultados = materialesBD.createQuery(consultaCd);
                 while (resultados.next()) {
                    
                    Time duracionSQL = resultados.getTime("duracion");
                    Date utilDate = new Date(duracionSQL.getTime());
                    LocalTime duracion = utilDate.toInstant().atOffset(ZoneOffset.UTC).toLocalTime();
                    stock = Integer.parseInt(resultados.getString("stock")); 
                    location = resultados.getString("location"); 
                    id_cd = resultados.getString("id_cd");
                    titulo = getTitulo(materialesBD,tituloConsulta,id_cd);
                    List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_cd);
                    id_autor= autorInfo.get(0);
                    curso_id = resultados.getString("id_cd");
                    autor= autorInfo.get(1);                 

                    
                    //CD(LocalTime duracion, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id,String autor)
                    listaMaterial.add(new CD(duracion,stock, location, id_cd, titulo, id_autor, tipo,curso_id,autor));     
                 }
                
            }else{
                 try(PreparedStatement stmtLib = materialesBD.getConnection().prepareStatement(consultaCd)){
                     stmtLib.setString(1, id);
                     resultados= stmtLib.executeQuery();
                     while(resultados.next()){
                        
                        Time duracionSQL = resultados.getTime("duracion");
                        Date utilDate = new Date(duracionSQL.getTime());
                        LocalTime duracion = utilDate.toInstant().atOffset(ZoneOffset.UTC).toLocalTime();
                        stock = Integer.parseInt(resultados.getString("stock")); 
                        location = resultados.getString("location");      
                        id_cd = id;
                        titulo = getTitulo(materialesBD,tituloConsulta,id_cd);
                        List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_cd);
                        id_autor= autorInfo.get(0);
                        curso_id = resultados.getString("id_cd");
                        autor= autorInfo.get(1); 
                        listaMaterial.add(new CD(duracion,stock, location, id_cd, titulo, id_autor, tipo,curso_id,autor));
                         
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
    public void getObras(bd_Connection materialesBD,String id){
       String consultaCd = "";
       
       if (id==null) {
              consultaCd = "SELECT * FROM obras";
           }else {
              consultaCd = "SELECT * FROM obras WHERE id_obra = ?";
           }
       String tituloConsulta ="SELECT titulo FROM materiales WHERE id_material=?"; 
       String idAutor ="SELECT id_autor FROM materiales WHERE id_material=?"; 
       String autorConsulta ="SELECT nombre_autor FROM autor WHERE id_autor=?"; 
       String tipo="Obra";
       String titulo="";
       String id_obra ="";
       String autor= "";
       String id_autor= "";
       String curso_id= "";
       String location= "";
       String editorial= "";
       String ISBN= "";
       int  stock;
       ResultSet resultados = null;
        try {
             if (id==null) {
                resultados = materialesBD.createQuery(consultaCd);
                 while (resultados.next()) {
                    
                    Date fecha_publicacion = resultados.getDate("fecha_publicacion");
                    ISBN = resultados.getString("ISBN");
                    stock = Integer.parseInt(resultados.getString("stock")); 
                    location = resultados.getString("location"); 
                    editorial = resultados.getString("editorial");
                    id_obra = resultados.getString("id_obra");
                    titulo = getTitulo(materialesBD,tituloConsulta,id_obra);
                    List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_obra);
                    id_autor= autorInfo.get(0);
                    curso_id = resultados.getString("id_obra");
                    autor= autorInfo.get(1);
                    
                    //CD(LocalTime duracion, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id,String autor)
                    listaMaterial.add(new obras(fecha_publicacion, ISBN, stock, location, id_obra,  titulo,  id_autor, tipo, curso_id, autor));     
                 }
                
            }else{
                 try(PreparedStatement stmtLib = materialesBD.getConnection().prepareStatement(consultaCd)){
                     stmtLib.setString(1, id);
                     resultados= stmtLib.executeQuery();
                     while(resultados.next()){
                        
                        Date fecha_publicacion = resultados.getDate("fecha_publicacion");
                        ISBN = resultados.getString("ISBN");
                        stock = Integer.parseInt(resultados.getString("stock")); 
                        location = resultados.getString("location"); 
                        editorial = resultados.getString("editorial");     
                        id_obra = id;
                        titulo = getTitulo(materialesBD,tituloConsulta,id_obra);
                        List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_obra);
                        id_autor= autorInfo.get(0);
                        curso_id = resultados.getString("id_obra");
                        autor= autorInfo.get(1);
                        listaMaterial.add(new obras(fecha_publicacion, ISBN, stock, location, id_obra,  titulo,  id_autor, tipo, curso_id, autor)); 
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
    public void getOther(bd_Connection materialesBD,String id){
       String consultaCd = "";
       
       if (id==null) {
              consultaCd = "SELECT * FROM other";
           }else {
              consultaCd = "SELECT * FROM other WHERE id_other = ?";
           }
       String tituloConsulta ="SELECT titulo FROM materiales WHERE id_material=?"; 
       String idAutor ="SELECT id_autor FROM materiales WHERE id_material=?"; 
       String autorConsulta ="SELECT nombre_autor FROM autor WHERE id_autor=?"; 
       String tipo="other";
       String titulo="";
       String id_other ="";
       String autor= "";
       String id_autor= "";
       String curso_id= "";
       String location= "";
       String editorial= "";
       String ISBN= "";
       int  stock;
       ResultSet resultados = null;
        try {
             if (id==null) {
                resultados = materialesBD.createQuery(consultaCd);
                 while (resultados.next()) {
                    
                    stock = Integer.parseInt(resultados.getString("stock")); 
                    location = resultados.getString("location"); 
                    id_other = resultados.getString("id_other");
                    titulo = getTitulo(materialesBD,tituloConsulta,id_other);
                    List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_other);
                    id_autor= autorInfo.get(0);
                    curso_id = resultados.getString("id_other");
                    autor= autorInfo.get(1);
                    
                    //CD(LocalTime duracion, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id,String autor)
                    listaMaterial.add(new other( stock, location, id_other, titulo, id_autor, tipo, curso_id,autor) );     
                 }
                
            }else{
                 try(PreparedStatement stmtLib = materialesBD.getConnection().prepareStatement(consultaCd)){
                     stmtLib.setString(1, id);
                     resultados= stmtLib.executeQuery();
                     while(resultados.next()){
                        
                        stock = Integer.parseInt(resultados.getString("stock")); 
                        location = resultados.getString("location");  
                        id_other = id;
                        titulo = getTitulo(materialesBD,tituloConsulta,id_other);
                        List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_other);
                        id_autor= autorInfo.get(0);
                        curso_id = resultados.getString("id_other");
                        autor= autorInfo.get(1);
                        listaMaterial.add(new other( stock, location, id_other, titulo, id_autor, tipo, curso_id,autor)); 
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
    
    public void getRevistas(bd_Connection materialesBD,String id){
       String consultarev = "";
       
       if (id==null) {
              consultarev = "SELECT * FROM revistas";
           }else {
              consultarev = "SELECT * FROM revistas WHERE id_revista = ?";
           }
       String tituloConsulta ="SELECT titulo FROM materiales WHERE id_material=?"; 
       String idAutor ="SELECT id_autor FROM materiales WHERE id_material=?"; 
       String autorConsulta ="SELECT nombre_autor FROM autor WHERE id_autor=?"; 
       String tipo="revista";
       String titulo="";
       String id_revista ="";
       String autor= "";
       String id_autor= "";
       String curso_id= "";
       String location= "";
       String editorial= "";
       String ISBN= "";
       int  stock;
       ResultSet resultados = null;
        try {
             if (id==null) {
                resultados = materialesBD.createQuery(consultarev);
                 while (resultados.next()) {
                     
                    Date fecha_publicacion = resultados.getDate("fecha_publicacion");
                    ISBN = resultados.getString("ISBN");
                    stock = Integer.parseInt(resultados.getString("stock")); 
                    location = resultados.getString("location"); 
                    editorial = resultados.getString("editorial");
                    id_revista = resultados.getString("id_revista");
                    titulo = getTitulo(materialesBD,tituloConsulta,id_autor);
                    List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_autor);
                    id_autor= autorInfo.get(0);
                    curso_id = resultados.getString("id_revista");
                    autor= autorInfo.get(1);
                    
                    //CD(LocalTime duracion, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id,String autor)
                    listaMaterial.add(new revistas(fecha_publicacion,ISBN, stock, location,id_revista, titulo, id_autor,  tipo, curso_id, autor));     
                 }
                
            }else{
                 try(PreparedStatement stmtLib = materialesBD.getConnection().prepareStatement(consultarev)){
                     stmtLib.setString(1, id);
                     resultados= stmtLib.executeQuery();
                     while(resultados.next()){
                        
                        Date fecha_publicacion = resultados.getDate("fecha_publicacion");
                        ISBN = resultados.getString("ISBN");
                        stock = Integer.parseInt(resultados.getString("stock")); 
                        location = resultados.getString("location"); 
                        editorial = resultados.getString("editorial");   
                        id_revista = id;
                        titulo = getTitulo(materialesBD,tituloConsulta,id_revista);
                        List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_revista);
                        id_autor= autorInfo.get(0);
                        curso_id = resultados.getString("id_revista");
                        autor= autorInfo.get(1);
                        listaMaterial.add(new revistas(fecha_publicacion,ISBN, stock, location,id_revista, titulo, id_autor,  tipo, curso_id, autor)); 
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
    
    public void getTesis(bd_Connection materialesBD,String id){
       String consultarev = "";
       
       if (id==null) {
              consultarev = "SELECT * FROM tesis";
           }else {
              consultarev = "SELECT * FROM tesis WHERE id_tesis = ?";
           }
       String tituloConsulta ="SELECT titulo FROM materiales WHERE id_material=?"; 
       String idAutor ="SELECT id_autor FROM materiales WHERE id_material=?"; 
       String autorConsulta ="SELECT nombre_autor FROM autor WHERE id_autor=?"; 
       String tipo="tesis";
       String titulo="";
       String id_tesis ="";
       String autor= "";
       String id_autor= "";
       String curso_id= "";
       String location= "";
       String editorial= "";
       String ISBN= "";
       int  stock;
       ResultSet resultados = null;
        try {
             if (id==null) {
                resultados = materialesBD.createQuery(consultarev);
                 while (resultados.next()) {
                     
                    Date fecha_publicacion = resultados.getDate("fecha_publicacion");
                    stock = Integer.parseInt(resultados.getString("stock")); 
                    location = resultados.getString("location"); 
                    id_tesis = resultados.getString("id_tesis");
                    titulo = getTitulo(materialesBD,tituloConsulta,id_tesis);
                    List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_tesis);
                    id_autor= autorInfo.get(0);
                    curso_id = resultados.getString("id_tesis");
                    autor= autorInfo.get(1);
                    
                    //CD(LocalTime duracion, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id,String autor)
                    listaMaterial.add(new tesis(stock, location, fecha_publicacion, id_tesis, titulo, id_autor, tipo, curso_id,autor));     
                 }
                
            }else{
                 try(PreparedStatement stmtLib = materialesBD.getConnection().prepareStatement(consultarev)){
                     stmtLib.setString(1, id);
                     resultados= stmtLib.executeQuery();
                     while(resultados.next()){
                        
                        Date fecha_publicacion = resultados.getDate("fecha_publicacion");
                        stock = Integer.parseInt(resultados.getString("stock")); 
                        location = resultados.getString("location"); 
                        id_tesis = id;
                        titulo = getTitulo(materialesBD,tituloConsulta,id_tesis);
                        List<String> autorInfo = getAutor(materialesBD,idAutor,autorConsulta,id_tesis);
                        id_autor= autorInfo.get(0);
                        curso_id = resultados.getString("id_tesis");
                        autor= autorInfo.get(1);
                        listaMaterial.add(new tesis(stock, location, fecha_publicacion, id_tesis, titulo, id_autor, tipo, curso_id,autor)); 
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
    public ArrayList<Material> fromBD(bd_Connection materialesBD) throws SQLException{
//        String consulta = "SELECT * FROM materiales";
        listaMaterial.clear();
        getLibros(materialesBD,null);
        getRevistas(materialesBD,null);
        getCd(materialesBD,null);
        getObras(materialesBD,null);
        getOther(materialesBD,null);
        getTesis(materialesBD,null);
        
        return listaMaterial;
    }
    public ArrayList<Material>  buscarDato(bd_Connection materialesBD,String id) throws SQLException{
            listaMaterial.clear();
            getLibros(materialesBD,id);
            getRevistas(materialesBD,id);
            getCd(materialesBD,id);
            getObras(materialesBD,id);
            getOther(materialesBD,id);
            getTesis(materialesBD,id);
            
            return listaMaterial;
        }
    //CD(LocalTime duracion, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id)
    public void insertToCds(LocalTime duracion, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id){
        bd_Connection materialesBD = new bd_Connection();
        PreparedStatement pStm = null;
        PreparedStatement pStmM = null;
        
        try{
            pStm = materialesBD.getConnection().prepareStatement("INSERT INTO cds VALUES (?,?,?,?,?)");
            pStmM = materialesBD.getConnection().prepareStatement("INSERT INTO materiales VALUES (?,?,?,?)");
            //CD(artist, genero, duracion, canciones, unidadesC, L2, hierarchy, id, titulo))
            //tabla material
            pStmM.setString(1, id_material);
            pStmM.setString(2, titulo);
            pStmM.setString(3, id_autor);
            pStmM.setString(4, tipo);   
            
            //tabla cds
            pStm.setString(1, id_material);
            pStm.setString(2, curso_id);
            pStm.setTime(3, Time.valueOf(duracion));
            pStm.setInt(4, stock);
            pStm.setString(5, location);

            pStmM.executeUpdate();
            pStm.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "CD registrado exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
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
    //    public libros(Date date, String ISBN, String editorial, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id,String autor) {

    public void insertToLibros(Date date, String ISBN, String editorial, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id,String autor){
        bd_Connection materialesBD = new bd_Connection();
        PreparedStatement pStm = null;
        PreparedStatement pStmM = null;
        
        try{
            pStm = materialesBD.getConnection().prepareStatement("INSERT INTO libros VALUES (?,?,?,?,?,?,?)");
            pStmM = materialesBD.getConnection().prepareStatement("INSERT INTO materiales VALUES (?,?,?,?)");
            //CD(artist, genero, duracion, canciones, unidadesC, L2, hierarchy, id, titulo))
            //tabla material
            pStmM.setString(1, id_material);
            pStmM.setString(2, titulo);
            pStmM.setString(3, id_autor);
            pStmM.setString(4, tipo);   
            
            //tabla libros
            pStm.setString(1, id_material);
            pStm.setString(2, curso_id);
            pStm.setDate(3, (java.sql.Date) date);
            pStm.setString(4, ISBN);
            pStm.setString(5, editorial);
            pStm.setInt(6, stock);
            pStm.setString(7, location);

            pStmM.executeUpdate();
            pStm.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Libro registrado exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
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
    public void insertToObras(Date date, String ISBN, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id){
        bd_Connection materialesBD = new bd_Connection();
        PreparedStatement pStm = null;
        PreparedStatement pStmM = null;
        
        try{
            pStm = materialesBD.getConnection().prepareStatement("INSERT INTO obras VALUES (?,?,?,?,?,?)");
            pStmM = materialesBD.getConnection().prepareStatement("INSERT INTO materiales VALUES (?,?,?,?)");
            //CD(artist, genero, duracion, canciones, unidadesC, L2, hierarchy, id, titulo))
            //tabla material
            pStmM.setString(1, id_material);
            pStmM.setString(2, titulo);
            pStmM.setString(3, id_autor);
            pStmM.setString(4, tipo);   
            
            //tabla obras
            pStm.setString(1, id_material);
            pStm.setString(2, curso_id);
            pStm.setDate(3, (java.sql.Date) date);
            pStm.setString(4, ISBN);
            pStm.setInt(5, stock);
            pStm.setString(6, location);

            pStmM.executeUpdate();
            pStm.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Obra registrada exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
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
    public void insertToOther(int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id){
        bd_Connection materialesBD = new bd_Connection();
        PreparedStatement pStm = null;
        PreparedStatement pStmM = null;
        
        try{
            pStm = materialesBD.getConnection().prepareStatement("INSERT INTO other VALUES (?,?,?,?)");
            pStmM = materialesBD.getConnection().prepareStatement("INSERT INTO materiales VALUES (?,?,?,?)");
            //CD(artist, genero, duracion, canciones, unidadesC, L2, hierarchy, id, titulo))
            //tabla material
            pStmM.setString(1, id_material);
            pStmM.setString(2, titulo);
            pStmM.setString(3, id_autor);
            pStmM.setString(4, tipo);   
            
            //tabla obras
            pStm.setString(1, id_material);
            pStm.setString(2, curso_id);
            pStm.setInt(3, stock);
            pStm.setString(4, location);


            pStmM.executeUpdate();
            pStm.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Otro tipo de documento registrado exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
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
    public void insertToRevistas (Date date, String ISBN, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id){
        bd_Connection materialesBD = new bd_Connection();
        PreparedStatement pStm = null;
        PreparedStatement pStmM = null;
        
        try{
            pStm = materialesBD.getConnection().prepareStatement("INSERT INTO revistas VALUES (?,?,?,?,?,?)");
            pStmM = materialesBD.getConnection().prepareStatement("INSERT INTO materiales VALUES (?,?,?,?)");
            //CD(artist, genero, duracion, canciones, unidadesC, L2, hierarchy, id, titulo))
            //tabla material
            pStmM.setString(1, id_material);
            pStmM.setString(2, titulo);
            pStmM.setString(3, id_autor);
            pStmM.setString(4, tipo);   
            
            //tabla obras
            pStm.setString(1, id_material);
            pStm.setString(2, curso_id);
            pStm.setDate(3, (java.sql.Date) date);
            pStm.setString(4, ISBN);
            pStm.setInt(5, stock);
            pStm.setString(6, location);


            pStmM.executeUpdate();
            pStm.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Revista registrada exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
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
    public void insertToTesis (int paginas, int stock, String location, Date date, String id_material, String titulo, String id_autor, String tipo, String curso_id){
        bd_Connection materialesBD = new bd_Connection();
        PreparedStatement pStm = null;
        PreparedStatement pStmM = null;
        
        try{
            pStm = materialesBD.getConnection().prepareStatement("INSERT INTO tesis VALUES (?,?,?,?)");
            pStmM = materialesBD.getConnection().prepareStatement("INSERT INTO materiales VALUES (?,?,?,?)");
            //CD(artist, genero, duracion, canciones, unidadesC, L2, hierarchy, id, titulo))
            //tabla material
            pStmM.setString(1, id_material);
            pStmM.setString(2, titulo);
            pStmM.setString(3, id_autor);
            pStmM.setString(4, tipo);   
            
            //tabla obras
            pStm.setString(1, id_material);
            pStm.setString(2, curso_id);
            pStm.setInt(3, stock);
            pStm.setString(4, location);


            pStmM.executeUpdate();
            pStm.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Tesis registrada exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
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
