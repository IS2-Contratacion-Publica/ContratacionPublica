/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

/**
 *
 * @author USER
 */
public class Fiscalizaciones {
    
    private String procodigo;
    private String fiscedula;
    private String fizfecha;
    private String fizobservaciones;

    private final String REGEX_PROCODIGO = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_FISCEDULA = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_FIZFECHA = "[0-9]{4}\\/[0-9]{2}\\/[0-9]{2}";
    private final String REGEX_FIZOBSERVACIONES = "[ .,/A-Za-z0-9]{1,200}";

    public boolean Validar(){
        if (procodigo.matches(REGEX_PROCODIGO) &&
            fiscedula.matches(REGEX_FISCEDULA) &&
            fizfecha.matches(REGEX_FIZFECHA) &&
            fizobservaciones.matches(REGEX_FIZOBSERVACIONES)) {
            return true;
        } else {
            return false;
        }
    }

    public Fiscalizaciones(String procodigo, String fiscedula, String fizfecha, String fizobservaciones) {
        this.procodigo = procodigo;
        this.fiscedula = fiscedula;
        this.fizfecha = fizfecha;
        this.fizobservaciones = fizobservaciones;
    }
      
    public String getProcodigo() {
        return procodigo;
    }

    public void setProcodigo(String procodigo) {
        this.procodigo = procodigo;
    }

    public String getFiscedula() {
        return fiscedula;
    }

    public void setFiscedula(String fiscedula) {
        this.fiscedula = fiscedula;
    }

    public String getFizfecha() {
        return fizfecha;
    }

    public void setFizfecha(String fizfecha) {
        this.fizfecha = fizfecha;
    }

    public String getFizobservaciones() {
        return fizobservaciones;
    }

    public void setFizobservaciones(String fizobservaciones) {
        this.fizobservaciones = fizobservaciones;
    }
    
    
    
}
