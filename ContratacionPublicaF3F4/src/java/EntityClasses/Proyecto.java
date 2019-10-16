/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

/**
 *
 * @author Ricky
 */
public class Proyecto {
    private String idproyecto;
    private String nombreproyecto;
    private String descripcion;
    private String entidad;
    private String tiempoDuración;
    private String costo;
    
    private final String REGEX_IDEPROYECTO = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_NOMBREPROYECTO = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_DESCRIPCION = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_ENTIDAD = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_TIEMPODURACION = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_COSTO = "[/A-Za-z0-9]{1,15}";
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
    //Setters
    public void setIdproyecto(String idproyecto) {
        this.idproyecto = idproyecto;
    }

    public void setNombreproyecto(String nombreroyecto) {
        this.nombreproyecto = nombreroyecto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public void setTiempoDuración(String tiempoDuración) {
        this.tiempoDuración = tiempoDuración;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }
    
        public boolean Validar() {
        if (idproyecto.matches(REGEX_IDEPROYECTO) &&
            nombreproyecto.matches(REGEX_NOMBREPROYECTO) &&
            descripcion.matches(REGEX_DESCRIPCION) &&
            entidad.matches(REGEX_ENTIDAD) &&
            tiempoDuración.matches(REGEX_TIEMPODURACION) &&            
            costo.matches(REGEX_COSTO)) {
            return true;
        } else {
            return false;
        }
    }
    
}
