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
 * @author Santiago
 */
@ManagedBean(value = "validaroferta")
@Dependent
public class ValidarOferta {
    private String CONCEDULA;
    private String PROCODIGO;
    private String OFECODIGO;
    private String FISCEDULA;
    private String VALCRITERIO;
    private String VALOBSERVACIONES;

   private final String REGEX_CONCODIGO = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_PROCODIGO = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_OFECODIGO = "[ /A-Za-z0-9]{1,100}";
   private final String REGEX_FISCEDULA = "[ /A-Za-z0-9]{1,15}";
   private final String REGEX_VALCRITERIO = "[ /A-Za-z0-9]{1,100}";
   private final String REGEX_VALOBSERVACIONES = "[ /A-Za-z0-9]{1,150}";
    
    /**
     * Creates a new instance of prueba
     */
    public ValidarOferta() {
    }

    public String getCONCEDULA() {
        return CONCEDULA;
    }

    public void setCONCEDULA(String CONCEDULA) {
        this.CONCEDULA = CONCEDULA;
    }

    public String getPROCODIGO() {
        return PROCODIGO;
    }

    public void setPROCODIGO(String PROCODIGO) {
        this.PROCODIGO = PROCODIGO;
    }

    public String getOFECODIGO() {
        return OFECODIGO;
    }

    public void setOFECODIGO(String OFECODIGO) {
        this.OFECODIGO = OFECODIGO;
    }

    public String getFISCEDULA() {
        return FISCEDULA;
    }

    public void setFISCEDULA(String FISCEDULA) {
        this.FISCEDULA = FISCEDULA;
    }

    public String getVALCRITERIO() {
        return VALCRITERIO;
    }

    public void setVALCRITERIO(String VALCRITERIO) {
        this.VALCRITERIO = VALCRITERIO;
    }

    public String getVALOBSERVACIONES() {
        return VALOBSERVACIONES;
    }

    public void setVALOBSERVACIONES(String VALOBSERVACIONES) {
        this.VALOBSERVACIONES = VALOBSERVACIONES;
    }
   
  
    public boolean Validar() {
        if (CONCEDULA.matches(REGEX_CONCODIGO) &&
            PROCODIGO.matches(REGEX_PROCODIGO) &&
            OFECODIGO.matches(REGEX_OFECODIGO) &&
            FISCEDULA.matches(REGEX_FISCEDULA) &&
            VALCRITERIO.matches(REGEX_VALCRITERIO) &&
            VALOBSERVACIONES.matches(REGEX_VALOBSERVACIONES)) {
            return true;
        } else {
            return false;
        }
    }
}
