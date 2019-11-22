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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Santiago
 */
@ManagedBean(name = "PruebasDP")
@Dependent
public class PruebasDP {
    
    private String procodigo;
    private String prucodigo;
    private String prudescripcion;
    private String plafecharealizacion;
    private int existe;
    private String mensaje;
    private ArrayList consultaparametro;

    public String getProcodigo() {
        return procodigo;
    }

    public void setProcodigo(String procodigo) {
        this.procodigo = procodigo;
    }

    public String getPrucodigo() {
        return prucodigo;
    }

    public void setPrucodigo(String prucodigo) {
        this.prucodigo = prucodigo;
    }

    public String getPrudescripcion() {
        return prudescripcion;
    }

    public void setPrudescripcion(String prudescripcion) {
        this.prudescripcion = prudescripcion;
    }

    public String getPlafecharealizacion() {
        return plafecharealizacion;
    }

    public void setPlafecharealizacion(String plafecharealizacion) {
        this.plafecharealizacion = plafecharealizacion;
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

    public ArrayList getConsultaparametro() {
        return consultaparametro;
    }

    public void setConsultaparametro(ArrayList consultaparametro) {
        this.consultaparametro = consultaparametro;
    }

    

    


    
       
    /**
     * Creates a new instance of PruebasDP
     */
    public PruebasDP() {
    }
    
    public void Crear(){
        PruebasMD md = new PruebasMD();
        Prueba pru = new Prueba();
        pru.setProcodigo(procodigo);
        pru.setPrucodigo(prucodigo);
        pru.setDescripcion(prudescripcion);
        pru.setFecharealizacion(plafecharealizacion);
        
        if (pru.Validar()) {
            if (md.Crear(pru)) {
                mensaje = "Información ingresada exitosamente";
            } else {
                mensaje = "Hubo un problema en la base de datos. Por favor contactarse con el administrador.";
            }
            
            
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
        
    }
    
    public void Modificar(){
        PruebasMD md = new PruebasMD();
        Prueba pru = new Prueba();
        pru.setProcodigo(procodigo.trim());
        pru.setPrucodigo(prucodigo.trim());
        pru.setDescripcion(prudescripcion.trim());
        pru.setFecharealizacion(plafecharealizacion.trim());
        
        if (pru.Validar()) {
            if (md.Modificar(pru)) {
                mensaje = "Información modificada exitosamente";
            } else {
                mensaje = "Hubo un problema en la base de datos. Por favor contactarse con el administrador.";
            }
            
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
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
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
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
            procodigo = pru.getProcodigo().trim();
            prucodigo = pru.getPrucodigo().trim();
            prudescripcion = pru.getDescripcion().trim();
            plafecharealizacion = pru.getFecharealizacion().trim();
        }

        
    }
    
     public void Consultap(){
        PruebasMD md = new PruebasMD();
        Prueba pru = new Prueba();
        pru.setProcodigo(procodigo);
        pru.setPrucodigo(prucodigo);
        pru.setDescripcion(prudescripcion);
        pru.setFecharealizacion(plafecharealizacion);
        consultaparametro = md.Consultap(pru);
    }
    
    public void Verificar(){
        PruebasMD md = new PruebasMD();
        existe = md.Verificar(prucodigo);
    }
    
}
