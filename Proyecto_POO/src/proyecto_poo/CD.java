/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_poo;

import java.time.LocalTime;
import proyecto_poo.utilities;




/** *
 * @author Jorge LG
 */
public class CD extends Material {
    private LocalTime duracion;
    private int stock;
    private String location;

    public CD(LocalTime duracion, int stock, String location, String id_material, String titulo, String id_autor, String tipo, String curso_id) {
        super(id_material, titulo, id_autor, tipo, curso_id);
        this.duracion = duracion;
        this.stock = stock;
        this.location = location;
    }



    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
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
