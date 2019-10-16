/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Oferta;
import LayerMD.OfertasMD;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ricky
 */
@ManagedBean(name = "ofertasDP")
@Dependent
public class OfertasDP {
    private String idoferta;
    private String idproyecto;  
    private String costoofertado;
    private String ubicacion;
    private int existe;
    private String mensaje;
    //Getters

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
    
    
    public void setIdoferta(String idoferta) {
        this.idoferta = idoferta;
    }

    public void setCostoofertado(String costoofertado) {
        this.costoofertado = costoofertado;
    }

    //Setters
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
        oft.setIdoferta(idoferta);
        oft.setIdproyecto(idproyecto);
        oft.setCostoofertado(costoofertado);
        oft.setUbicacion(ubicacion);
        if (oft.Validar()) {
            if (md.Ingresar(oft)) {
                mensaje = "Se ha ingresado el proyecto al sistema";
            } else {
                mensaje = "No se pudo eliminar,contactarse al administrador.";
            }               
        } else {
            mensaje = "Vuelva a ingresar los datos";
        } 
    } 
    
        public void Modificar(){
         
        OfertasMD md = new OfertasMD();
        Oferta oft = new Oferta();
        
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
        
    }
        public void Consultar(){
        OfertasMD md = new OfertasMD();
        Oferta oft;
        Verificar();
        if (existe == 1) {
            oft = md.Consultar(idoferta);
            idoferta = oft.getIdoferta();
            idproyecto = oft.getIdproyecto();
            costoofertado = oft.getCostoofertado();
            ubicacion = oft.getUbicacion();
            
        } else {
            idproyecto = "";
            idoferta = "";
            costoofertado = "";
            ubicacion = "";
        }

        
    }
    
    public void Verificar(){
        OfertasMD md = new OfertasMD();
        existe = md.Verificar(idoferta);
    }
}



