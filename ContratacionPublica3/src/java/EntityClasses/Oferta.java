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
    private String concedula;
    private String idproyecto;
    private String idoferta;
    private String costoofertado;
    private String ubicacion;
    private String estado;
    
   private final String REGEX_CONCEDULA = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_IDPROYECTO = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_IDOFERTA = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_COSTOOFERTADO = "[ /A-Za-z0-9]{1,100}";
   private final String REGEX_UBICACION = "[/A-Za-z0-9]{1,100}";
   private final String REGEX_ESTADO = "[/A-Za-z0-9]{1,15}";
    
    /**
     * Creates a new instance of Contratista
     */
    public Oferta() {
    }
    //GETTERS

    public String getConcedula() {
        return concedula;
    }

    public String getEstado() {
        return estado;
    }

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
    
    public void setConcedula(String concedula) {
        this.concedula = concedula;
    }

    //SETTERS
    public void setEstado(String estado) {
        this.estado = estado;
    }

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
        if (concedula.matches(REGEX_CONCEDULA) &&
            idproyecto.matches(REGEX_IDPROYECTO) &&
            idoferta.matches(REGEX_IDOFERTA) &&
            costoofertado.matches(REGEX_COSTOOFERTADO) &&
            ubicacion.matches(REGEX_UBICACION)&& 
                estado.matches(REGEX_ESTADO)) {
            return true;
        } else {
            return false;
        }
    }
}
