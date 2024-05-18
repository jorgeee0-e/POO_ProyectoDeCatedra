/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_poo;

/**
 *
 * @author Jorge LG
 */
public class other extends Material {
    private int stock;
    private String location;
    private String autor;

    public other(int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id,String autor) {
        super(id_material, titulo, id_autor, tipo, curso_id);
        this.stock = stock;
        this.location = location;
        this.autor = autor;
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
