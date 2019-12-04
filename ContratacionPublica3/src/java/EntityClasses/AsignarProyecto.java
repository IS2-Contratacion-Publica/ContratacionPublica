/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import javax.servlet.http.Part;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Dell
 */
public class AsignarProyecto {
    private String concedula;
    private String procodigo;
    private String fiscedula;
    private String adjanticipo;
    private String adjdocumento;
    private String adjfecha;
    private UploadedFile documento;
    
    private final String REGEX_CONCEDULA = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_PROCODIGO = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_FISCEDULA = "[/A-Za-z0-9]{1,15}";
    private final String REGEX_ADJFECHA = "[0-9]{4}\\/[0-9]{2}\\/[0-9]{2}";
    private final String REGEX_ADJANTICIPO = "[.,/A-Za-z0-9]{1,11}";


    public String getConcedula() {
        return concedula;
    }

    public void setConcedula(String concedula) {
        this.concedula = concedula.trim();
    }

    public String getProcodigo() {
        return procodigo;
    }

    public void setProcodigo(String procodigo) {
        this.procodigo = procodigo.trim();
    }

    public String getFiscedula() {
        return fiscedula;
    }

    public void setFiscedula(String fiscedula) {
        this.fiscedula = fiscedula.trim();
    }

    public String getAdjanticipo() {
        return adjanticipo;
    }

    public void setAdjanticipo(String adjanticipo) {
        this.adjanticipo = adjanticipo.trim();
    }

    public String getAdjdocumento() {
        return adjdocumento;
    }

    public void setAdjdocumento(String adjdocumento) {
        this.adjdocumento = adjdocumento.trim();
    }

    public String getAdjfecha() {
        return adjfecha;
    }

    public void setAdjfecha(String adjfecha) {
        this.adjfecha = adjfecha.trim();
    }

    public UploadedFile getDocumento() {
        return documento;
    }

    public void setDocumento(UploadedFile documento) {
        this.documento = documento;
    }

    
    public AsignarProyecto(String concedula, String procodigo, String fiscedula, String adjanticipo, String adjdocumento, String adjfecha, UploadedFile documento) {
        this.concedula = concedula;
        this.procodigo = procodigo;
        this.fiscedula = fiscedula;
        this.adjanticipo = adjanticipo;
        this.adjdocumento = adjdocumento;
        this.adjfecha = adjfecha;
        this.documento = documento;
    }
    
    public AsignarProyecto(String concedula, String procodigo, String fiscedula, String adjanticipo, String adjfecha) {
        this.concedula = concedula;
        this.procodigo = procodigo;
        this.fiscedula = fiscedula;
        this.adjanticipo = adjanticipo;
        this.adjdocumento = adjdocumento;
        this.adjfecha = adjfecha;
        this.documento = documento;
    }
    
    public boolean Validar(){
        if (concedula.matches(REGEX_CONCEDULA) &&
            procodigo.matches(REGEX_PROCODIGO) &&
            fiscedula.matches(REGEX_FISCEDULA) &&
            adjfecha.matches(REGEX_ADJFECHA) &&
            adjanticipo.matches(REGEX_ADJANTICIPO)) {
            return true;
        } else {
            return false;
        }
    }

    
}
