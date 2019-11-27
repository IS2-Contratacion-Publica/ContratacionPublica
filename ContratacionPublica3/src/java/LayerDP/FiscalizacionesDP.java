/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Fiscalizaciones;
import EntityClasses.Prueba;
import LayerMD.FiscalizacionesMD;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author USER
 */
@ManagedBean(name="fiscalizaciones")
@RequestScoped
public class FiscalizacionesDP {
    
    private FiscalizadoresDP fisdp = new FiscalizadoresDP();
    private ProyectosDP prodp = new ProyectosDP();
    private PruebasDP prudp = new PruebasDP();
    private String procodigo;
    private String fiscedula;
    private String fizfecha;
    private String fizobservaciones;
    private String mensaje;
    private ArrayList consulta;
    private ArrayList filtered;
    private Map mapFiscalizadores;
    private Map mapProyectos;
    private Map mapPrueba;
    private String recparametro;
    private int recaprob;
    private String prucodigo;

    public ArrayList getFiltered() {
        return filtered;
    }

    public void setFiltered(ArrayList filtered) {
        this.filtered = filtered;
    }
    
    

    public Map getMapPrueba() {
        return prudp.getLayermd().ConsultaGeneralCombo();
    }
    public FiscalizadoresDP getFisdp() {
        return fisdp;
    }

    public void setFisdp(FiscalizadoresDP fisdp) {
        this.fisdp = fisdp;
    }

    public PruebasDP getPrudp() {
        return prudp;
    }

    public void setPrudp(PruebasDP prudp) {
        this.prudp = prudp;
    }

    public String getRecparametro() {
        return recparametro;
    }

    public void setRecparametro(String recparametro) {
        this.recparametro = recparametro;
    }

    public int getRecaprob() {
        return recaprob;
    }

    public void setRecaprob(int recaprob) {
        this.recaprob = recaprob;
    }

    public String getPrucodigo() {
        return prucodigo;
    }

    public void setPrucodigo(String prucodigo) {
        this.prucodigo = prucodigo;
    }

    public FiscalizacionesDP() {
    }
        public Map getMapFiscalizadores() {
        return fisdp.getLayermd().ConsultaGeneralCombo();
    }

    public void setMapFiscalizadores(Map mapFiscalizadores) {
        this.mapFiscalizadores = mapFiscalizadores;
    }

    public Map getMapProyectos() {
        return prodp.getLayermd().ConsultaGeneralCombo();
    }

    public void setMapProyectos(Map mapProyectos) {
        this.mapProyectos = mapProyectos;
    }
    
    public ArrayList getConsulta() {
        ConsultaParametros();
        return consulta;
    }

    public void setConsulta(ArrayList consulta) {
        this.consulta = consulta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
    
     public void Fiscalizar() {
        FiscalizacionesMD md = new FiscalizacionesMD();
        procodigo = procodigo.trim();
        fiscedula = fiscedula.trim();
        Fiscalizaciones fiz = new Fiscalizaciones(procodigo, 
                                                fiscedula, fizfecha, 
                                                fizobservaciones);

        if (fiz.Validar()) {
            mensaje = md.Fiscalizar(fiz);
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));                           
    }
    
    public void ConsultaParametros() {
        FiscalizacionesMD md = new FiscalizacionesMD();
        Fiscalizaciones fiz = new Fiscalizaciones("", 
                                                "", "", 
                                                "");
        
        consulta = md.ConsultaParametros(fiz);
                            
    }
    public void validarPrueba()
    {
        FiscalizacionesMD md = new FiscalizacionesMD();
        Fiscalizaciones fiz = new Fiscalizaciones( procodigo,  
                                        fiscedula,  prucodigo,  
                                        recparametro, recaprob
                                                    );
        if (fiz.ValidarP()) {
            mensaje = md.validarPrueba(fiz);
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    }
}
