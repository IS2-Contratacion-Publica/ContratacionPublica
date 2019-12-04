/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Oferta;
import EntityClasses.Proyecto;
import LayerMD.OfertasMD;
import LayerMD.ProyectosMD;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ricky
 */
@ManagedBean(name = "ofertasDP")
@Dependent
public class OfertasDP {
    private String concedula; 
    private String idproyecto; 
    private String idoferta; 
    private String costoofertado;
    private String ubicacion;
    private String estado;
    private int existe;
    private String mensaje;
    private ArrayList consultaparametro;
    
    private LinkedList<Oferta> ofer;

    public LinkedList<Oferta> getOfer() {
        OfertasMD md = new OfertasMD();
        ofer= md.consultaGeneral();
        return ofer;
    }

    public void setOfer(LinkedList<Oferta> ofer) {
        this.ofer = ofer;
    }
    //Getters

    public String getConcedula() {
        return concedula;
    }

    public String getEstado() {
        return estado;
    }

    public int getExiste() {
        return existe;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    public String getIdproyecto() {
        return idproyecto;
    }

    public String getIdoferta() {
        return idoferta;
    }

    public String getCostoofertado() {
        return costoofertado;
    }

    public String getUbicacion() {
        return ubicacion;
    }
    public ArrayList getConsultaparametro() {
        Consultap();
        return consultaparametro;
    }

    public void setConsultaparametro(ArrayList consultaparametro) {
        this.consultaparametro = consultaparametro;
    }
    
    
    
    
    public void setIdoferta(String idoferta) {
        this.idoferta = idoferta;
    }

    public void setCostoofertado(String costoofertado) {
        this.costoofertado = costoofertado;
    }
    
    public void setConcedula(String concedula) {    
        this.concedula = concedula;
    }

    //Setters
    public void setEstado(String estado) {
        this.estado = estado;    
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setExiste(int existe) {
        this.existe = existe;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void setIdproyecto(String idproyecto) {
        this.idproyecto = idproyecto;
    }

   
    //Constructor
    public OfertasDP(){      
    }
    public void Ingresar(){
        OfertasMD md = new OfertasMD();
        Oferta oft = new Oferta();
        
        oft.setConcedula(concedula);
        oft.setIdproyecto(idproyecto);
        oft.setIdoferta(idoferta);       
        oft.setCostoofertado(costoofertado);
        oft.setUbicacion(ubicacion);
        
        
        if (oft.Validar()) {
            if (md.Ingresar(oft)) {
                mensaje = "Se ha ingresado el proyecto al sistema";
            } else {
                mensaje = "No se pudo ingresar,contactarse al administrador.";
            }               
        } else {
            mensaje = "Vuelva a ingresar los datos";
        } 
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    } 
    
        public void Modificar(){
         
        OfertasMD md = new OfertasMD();
        Oferta oft = new Oferta();
        
        oft.setConcedula(concedula.trim());
        oft.setIdproyecto(idproyecto.trim());
        oft.setIdoferta(idoferta.trim());       
        oft.setCostoofertado(costoofertado.trim());
        oft.setUbicacion(ubicacion.trim());
        
        
        if (oft.Validar()) {
            if (md.Modificar(oft)) {
                mensaje = "Registro modificado";
            } else {
                mensaje = "No se pudo eliminar,contactarse al administrador.";
            }
            
        } else {
            mensaje = "Vuelva a ingresar los datos";
        }    
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    }
        
        public void Eliminar(){
        OfertasMD md = new OfertasMD();
        if (md.Verificar(idoferta) == 1) {
            if (md.Eliminar(idoferta)) {
                mensaje = "Registro eliminado ";
            } else {
                mensaje = "No se pudo eliminar,contactarse al administrador.";
            }
            
        } else {
            mensaje = "Vuelva a ingresar los datos";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
        
    }
        public void Consultar(){
        OfertasMD md = new OfertasMD();
        Oferta oft;
        Verificar();
        if (existe == 1) {
            oft = md.Consultar(idoferta);
            concedula=oft.getConcedula();
            idproyecto = oft.getIdproyecto();
            idoferta = oft.getIdoferta().trim();
            costoofertado = oft.getCostoofertado();
            ubicacion = oft.getUbicacion();
            estado=oft.getEstado();
            
        } else {
            concedula="";
            idproyecto = "";
            idoferta = "";
            costoofertado = "";
            ubicacion = "";
            estado="";
        }

        
    }
         public void Consultap(){
        OfertasMD md = new OfertasMD();
        Oferta oft = new Oferta();
        oft.setConcedula("");
        oft.setIdproyecto("");
        oft.setIdoferta("");
        oft.setCostoofertado("");
        oft.setUbicacion("");
             
        consultaparametro = md.Consultap(oft);
    }
    
    public void Verificar(){
        OfertasMD md = new OfertasMD();
        existe = md.Verificar(idoferta);
        if(existe==1){
            mensaje = "Código Existente";
        }
        else{
            mensaje = "Código no existente";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    }
}



