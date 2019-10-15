/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Dell
 */
@ManagedBean(value = "oferta")
@Dependent
public class Oferta {
    private String idoferta;
    private String idproyecto;
    private String costoofertado;
    private String ubicacion;

   private final String REGEX_IDOFERTA = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_IDPROYECTO = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_COSTOOFERTADO = "[ /A-Za-z0-9]{1,100}";
   private final String REGEX_UBICACION = "[/A-Za-z0-9]{1,100}";
    
    /**
     * Creates a new instance of Contratista
     */
    public Oferta() {
    }
    //GETTERS

    public String getIdoferta() {
        return idoferta;
    }

    public String getIdproyecto() {
        return idproyecto;
    }

    public String getCostoofertado() {
        return costoofertado;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    
    //SETTERS

    public void setIdoferta(String idoferta) {
        this.idoferta = idoferta;
    }

    public void setIdproyecto(String idproyecto) {
        this.idproyecto = idproyecto;
    }

    public void setCostoofertado(String costoofertado) {
        this.costoofertado = costoofertado;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public boolean Validar() {
        if (idoferta.matches(REGEX_IDOFERTA) &&
            idproyecto.matches(REGEX_IDPROYECTO) &&
            costoofertado.matches(REGEX_COSTOOFERTADO) &&
            ubicacion.matches(REGEX_UBICACION)) {
            return true;
        } else {
            return false;
        }
    }
}
