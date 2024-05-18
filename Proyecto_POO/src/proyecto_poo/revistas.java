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
public class revistas extends Material{
    private Date date;
    private String ISBN;
    private int stock;
    private String location;

    public revistas(Date date, String ISBN, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id) {
        super(id_material, titulo, id_autor, tipo, curso_id);
        this.date = date;
        this.ISBN = ISBN;
        this.stock = stock;
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
    
    
}
