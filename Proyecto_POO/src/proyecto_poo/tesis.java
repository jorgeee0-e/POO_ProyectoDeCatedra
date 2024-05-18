/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_poo;

import java.util.Date;

/**
 *
 * @author Jorge LG
 */
public class tesis extends Material{
    private int paginas;
    private int stock;
    private String location;
    private Date date;

    public tesis(int paginas, int stock, String location, Date date, String id_material, String titulo, String id_autor, String tipo, String curso_id) {
        super(id_material, titulo, id_autor, tipo, curso_id);
        this.paginas = paginas;
        this.stock = stock;
        this.location = location;
        this.date = date;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
