/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Prueba;
import LayerMD.PruebasMD;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Santiago
 */
@ManagedBean(name = "PruebasDP")
@Dependent
public class PruebasDP {
    
    private String procodigo;
    private String prucodigo;
    private String descripcion;
    private String fecharealizacion;
    private int existe;
    private String mensaje;
    private ArrayList consultaparametro;

    public ArrayList getConsultap() {
        return consultaparametro;
    }

    public void setConsultap(ArrayList consultap) {
        this.consultaparametro = consultap;
    }
    
    public int getExiste() {
        return existe;
    }

    public void setExiste(int existe) {
        this.existe = existe;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
     public String getProCodigo() {
      return procodigo;
    } 
    public String getPruCodigo() {
      return prucodigo;
    } 
    public void setProCodigo(String newCodigo) {
       procodigo = newCodigo;
    }
    public void setPruCodigo(String newCodigo) {
       prucodigo = newCodigo;
    }
    
    public String getDescripcion() {
       return descripcion;
    } 
    public void setDescripcion(String newDescripcion) {
       descripcion = newDescripcion;
    } 
    public String getFechaRealizacion() {
       return fecharealizacion;
    } 
    public void setFechaRealizacion(String newFecha) {
       fecharealizacion = newFecha;
    } 
    
       
    /**
     * Creates a new instance of PruebasDP
     */
    public PruebasDP() {
    }
    
    public void Crear(){
        PruebasMD md = new PruebasMD();
        Prueba pru = new Prueba();
        pru.setProCodigo(procodigo);
        pru.setPruCodigo(prucodigo);
        pru.setDescripcion(descripcion);
        pru.setFechaRealizacion(fecharealizacion);
        
        if (pru.Validar()) {
            if (md.Crear(pru)) {
                mensaje = "Información ingresada exitosamente";
            } else {
                mensaje = "Hubo un problema en la base de datos. Por favor contactarse con el administrador.";
            }
            
            
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        
    }
    
    public void Modificar(){
        PruebasMD md = new PruebasMD();
        Prueba pru = new Prueba();
        pru.setProCodigo(procodigo.trim());
        pru.setPruCodigo(prucodigo.trim());
        pru.setDescripcion(descripcion.trim());
        pru.setFechaRealizacion(fecharealizacion.trim());
        
        if (pru.Validar()) {
            if (md.Modificar(pru)) {
                mensaje = "Información modificada exitosamente";
            } else {
                mensaje = "Hubo un problema en la base de datos. Por favor contactarse con el administrador.";
            }
            
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        
        
    }

    public void Eliminar(){
        PruebasMD md = new PruebasMD();

        if (md.Verificar(prucodigo) == 1) {
            if (md.Eliminar(prucodigo)) {
                mensaje = "Información eliminana exitosamente";
            } else {
                mensaje = "Hubo un problema en la base de datos. Por favor contactarse con el administrador.";
            }
            
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        
    }
        
    public ArrayList<Prueba> Consultag(){
        PruebasMD PMD = new PruebasMD();
        ArrayList<Prueba> listpruebas = PMD.Consultag();
        return listpruebas;
    }
    
    public void Consultacod(){
        PruebasMD md = new PruebasMD();
        Prueba pru;
        Verificar();
        pru = md.Consultacod(prucodigo);
        if (existe == 1 && pru != null) {
            procodigo = pru.getProCodigo().trim();
            prucodigo = pru.getPruCodigo().trim();
            descripcion = pru.getDescripcion().trim();
            fecharealizacion = pru.getFechaRealizacion().trim();
        }

        
    }
    
     public void Consultap(){
        PruebasMD md = new PruebasMD();
        Prueba pru = new Prueba();
        pru.setProCodigo(procodigo);
        pru.setPruCodigo(prucodigo);
        pru.setDescripcion(descripcion);
        pru.setFechaRealizacion(fecharealizacion);
        consultaparametro = md.Consultap(pru);
    }
    
    public void Verificar(){
        PruebasMD md = new PruebasMD();
        existe = md.Verificar(prucodigo);
    }
    
}
