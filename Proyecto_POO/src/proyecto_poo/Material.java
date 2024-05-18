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
public class Material {
        private String id_material;
        private String titulo;
        private String id_autor;
        private String tipo;

    public Material(String id_material, String titulo, String id_autor, String tipo) {
        this.id_material = id_material;
        this.titulo = titulo;
        this.id_autor = id_autor;
        this.tipo = tipo;
    }

    public String getId_material() {
        return id_material;
    }

    public void setId_material(String id_material) {
        this.id_material = id_material;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId_autor() {
        return id_autor;
    }

    public void setId_autor(String id_autor) {
        this.id_autor = id_autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


 }
