    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.util.List;

/**
 *
 * @author Ricky
 */
public class Proyecto {
    private String idproyecto;
    private String nombreproyecto;
    private String descripcion;
    private String tipo;
    private String entidad;
    private String tiempoDuración;
    private String costo;
    private String estado;
    
    private List<Proyecto> proye;

    public List<Proyecto> getProye() {
        return proye;
    }

    public void setProye(List<Proyecto> proye) {
        this.proye = proye;
    }
    

    
    private final String REGEX_IDEPROYECTO = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_NOMBREPROYECTO = "[ /A-Za-z0-9]{1,30}";
    private final String REGEX_DESCRIPCION = "[ /A-Za-z0-9]{1,100}";
    private final String REGEX_TIPO = "[ /A-Za-z0-9]{1,25}";
    private final String REGEX_ENTIDAD = "[ /A-Za-z0-9]{1,30}";
    private final String REGEX_TIEMPODURACION = "[.0-9]{1,15}";
    private final String REGEX_COSTO = "([0-9]+(.[0-9]{1,2})?){1,11}";
    private final String REGEX_ESTADO = "[0-9]{1,15}";
    //Getters
    public String getIdproyecto() {
        return idproyecto;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEntidad() {
        return entidad;
    }

    public String getTiempoDuración() {
        return tiempoDuración;
    }

    public String getCosto() {
        return costo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }
    
    //Setters
    public void setIdproyecto(String idproyecto) {
        this.idproyecto = idproyecto.trim();
    }

    public void setNombreproyecto(String nombreroyecto) {
        this.nombreproyecto = nombreroyecto.trim();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.trim();
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad.trim();
    }

    public void setTiempoDuración(String tiempoDuración) {
        this.tiempoDuración = tiempoDuración.trim();
    }

    public void setCosto(String costo) {
        this.costo = costo.trim();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.trim();
    }

    public void setEstado(String estado) {
        this.estado = estado.trim();
    }
    
    
        public boolean Validar() {
        if (idproyecto.matches(REGEX_IDEPROYECTO) &&
            nombreproyecto.matches(REGEX_NOMBREPROYECTO) &&
            descripcion.matches(REGEX_DESCRIPCION) &&
            tipo.matches(REGEX_TIPO) &&
            entidad.matches(REGEX_ENTIDAD) &&
            tiempoDuración.matches(REGEX_TIEMPODURACION) &&            
            costo.matches(REGEX_COSTO)) {
            
            return true;
        } else {
            return false;
        }
    }
    
}
