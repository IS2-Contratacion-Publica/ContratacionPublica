/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Proyecto;
import LayerMD.ProyectosMD;
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
@ManagedBean(name = "proyectosDP")
@Dependent
public class ProyectosDP {
    private String idproyecto;
    private String nombreproyecto;
    private String descripcion;
    private String entidad;
    private String tiempoDuración;
    private String costo;
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

    public String getNombreroyecto() {
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

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public String getCosto() {
        return costo;
    }
    //Setters

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
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

    public void setNombreroyecto(String nombreroyecto) {
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
    //Constructor
    public ProyectosDP(){      
    }
    public void Ingresar(){
        ProyectosMD md = new ProyectosMD();
        Proyecto pro = new Proyecto();
        pro.setIdproyecto(idproyecto);
        pro.setNombreproyecto(nombreproyecto);
        pro.setDescripcion(descripcion);
        pro.setEntidad(entidad);
        pro.setTiempoDuración(tiempoDuración);
        pro.setCosto(costo);       
        if (pro.Validar()) {
            if (md.Ingresar(pro)) {
                mensaje = "Se ha ingresado el proyecto al sistema";
            } else {
                mensaje = "No se pudo eliminar,contactarse al administrador.";
            }               
        } else {
            mensaje = "Vuelva a ingresar los datos";
        } 
    } 
    
        public void Modificar(){
        ProyectosMD md = new ProyectosMD();
        Proyecto pro = new Proyecto();
        
        pro.setIdproyecto(idproyecto.trim());
        pro.setNombreproyecto(nombreproyecto.trim());
        pro.setDescripcion(descripcion.trim());
        pro.setEntidad(entidad.trim());
        pro.setTiempoDuración(tiempoDuración.trim());
        pro.setCosto(costo.trim());
        if (pro.Validar()) {
            if (md.Modificar(pro)) {
                mensaje = "Registro modificada";
            } else {
                mensaje = "No se pudo eliminar,contactarse al administrador.";
            }
            
        } else {
            mensaje = "Vuelva a ingresar los datos";
        }      
    }
        
        public void Eliminar(){
        ProyectosMD md = new ProyectosMD();
        if (md.Verificar(idproyecto) == 1) {
            if (md.Eliminar(idproyecto)) {
                mensaje = "Registro eliminado ";
            } else {
                mensaje = "No se pudo eliminar,contactarse al administrador.";
            }
            
        } else {
            mensaje = "Vuelva a ingresar los datos";
        }
        
    }
        public void Consultar(){
        ProyectosMD md = new ProyectosMD();
        Proyecto pro;
        Verificar();
        if (existe == 1) {
            pro = md.Consultar(idproyecto);
            idproyecto = pro.getIdproyecto();
            nombreproyecto = pro.getNombreproyecto();
            descripcion = pro.getDescripcion();
            entidad = pro.getEntidad();
            tiempoDuración = pro.getTiempoDuración();
            costo = pro.getCosto();
        } else {
            idproyecto = "";
            nombreproyecto = "";
            descripcion = "";
            entidad = "";
            tiempoDuración = "";
            costo = "";

        }

        
    }
    
    public void Verificar(){
        ProyectosMD md = new ProyectosMD();
        existe = md.Verificar(idproyecto);
    }
}



