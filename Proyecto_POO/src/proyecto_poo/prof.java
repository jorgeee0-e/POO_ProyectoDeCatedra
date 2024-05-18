/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_poo;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Jorge LG
 */
public class prof extends usuarios{
    private String name; 
    private String last_name;
    private String carrera; 
    private Timestamp created_at; 
    private String email;

    public prof(String name, String last_name, String carrera, Timestamp created_at, String email, String user_id, String user_type, String password) {
        super(user_id, user_type, password);
        this.name = name;
        this.last_name = last_name;
        this.carrera = carrera;
        this.created_at = created_at;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
