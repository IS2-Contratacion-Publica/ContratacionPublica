/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.AsignarProyecto;
import LayerMD.AsignarProyectoMD;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "asignarProyectoDP")
@Dependent
public class AsignarProyectoDP {
    
    private ContratistasDP cdp = new ContratistasDP();
    private FiscalizadoresDP fdp = new FiscalizadoresDP();
    private ProyectosDP pdp = new ProyectosDP();
    private Map mapContratistas;
    private Map mapFiscalizadores;
    private Map mapProyectos;
    private String concedula;
    private String procodigo;
    private String fiscedula;
    private String adjanticipo;
    private String adjdocumento;
    private String adjfecha;
    private String mensaje;
    private Part documento;
    private ArrayList consulta;
    
    
    //Getters y Setters

    public ArrayList getConsulta() {
        return consulta;
    }

    public void setConsulta(ArrayList consulta) {
        this.consulta = consulta;
    }
    
    
    
    public Part getDocumento() {
        return documento;
    }

    
    public void setDocumento(Part documento) {
        this.documento = documento;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    } 
    
    public Map getMapContratistas() {
        return cdp.getLayermd().ConsultaGeneralCombo();
    }

    public void setMapContratistas(Map mapContratistas) {
        this.mapContratistas = mapContratistas;
    }

    public Map getMapFiscalizadores() {
        return fdp.getLayermd().ConsultaGeneralCombo();
    }

    public void setMapFiscalizadores(Map mapFiscalizadores) {
        this.mapFiscalizadores = mapFiscalizadores;
    }

    public Map getMapProyectos() {
        return pdp.getLayermd().ConsultaGeneralCombo();
    }

    public void setMapProyectos(Map mapProyectos) {
        this.mapProyectos = mapProyectos;
    }

    public String getConcedula() {
        return concedula;
    }

    public void setConcedula(String concedula) {
        this.concedula = concedula;
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

    public String getAdjanticipo() {
        return adjanticipo;
    }

    public void setAdjanticipo(String adjanticipo) {
        this.adjanticipo = adjanticipo;
    }

    public String getAdjdocumento() {
        return adjdocumento;
    }

    public void setAdjdocumento(String adjdocumento) {
        this.adjdocumento = adjdocumento;
    }

    public String getAdjfecha() {
        return adjfecha;
    }

    public void setAdjfecha(String adjfecha) {
        this.adjfecha = adjfecha;
    }
    
    
    /**
     * Creates a new instance of AsignarProyectoDP
     */
    public AsignarProyectoDP() {
    }
    
    public void Asignar() {
        AsignarProyectoMD md = new AsignarProyectoMD();
        String aux;
        concedula = concedula.trim();
        procodigo = procodigo.trim();
        fiscedula = fiscedula.trim();
        try {
            aux = documento.getSubmittedFileName();
            adjdocumento = "c" + concedula + "p" + procodigo + "f" + fiscedula;
            adjdocumento += aux.substring(aux.lastIndexOf("."));
            AsignarProyecto ap = new AsignarProyecto(concedula, procodigo, 
                                                    fiscedula, adjanticipo, 
                                                    adjdocumento, adjfecha, 
                                                    documento);

            if (ap.Validar()) {
                mensaje = md.Asignar(ap);
            } else {
                mensaje = "Por favor ingrese correctamente todos los valores";
            }
        } catch (Exception ex) {
            mensaje = "Por favor seleccione un archivo";
        }                                
    }
    
    public void ConsultaParametros() {
        AsignarProyectoMD md = new AsignarProyectoMD();
        String aux;

        AsignarProyecto ap = new AsignarProyecto(concedula, procodigo, 
                                                fiscedula, adjanticipo, 
                                                adjfecha);
        
        consulta = md.ConsultaParametros(ap);
                            
    }
    
}