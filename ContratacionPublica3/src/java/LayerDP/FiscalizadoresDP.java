/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Fiscalizador;
import LayerMD.FiscalizadoresMD;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "fiscalizadoresDP")
@Dependent
public class FiscalizadoresDP {
    private String cedula;
    private String codigo;
    private String nombre;
    private String fechanac;
    private String genero;
    private String telefono;
    private String celular;
    private String correo;
    private int existe;
    private String mensaje;
    private ArrayList consulta;
    private List<SelectItem> generos;
    private FiscalizadoresMD layermd = new FiscalizadoresMD();

    public FiscalizadoresMD getLayermd() {
        return layermd;
    }

    public void setLayermd(FiscalizadoresMD layermd) {
        this.layermd = layermd;
    }
    
    public List<SelectItem> getGeneros() {
        return new FiscalizadoresMD().getGeneros();
    }

    public void setGeneros(List<SelectItem> generos) {
        this.generos = generos;
    }

    public ArrayList getConsulta() {
        ConsultaParametros();
        return consulta;
    }

    public void setConsulta(ArrayList consulta) {
        this.consulta = consulta;
    }

    /*
     *Obtiene la variable existe
    */
    public int getExiste() {
        return existe;
    }

    /*
     *Asigna la variable existe
    */
    public void setExiste(int existe) {
        this.existe = existe;
    }

    /*
     *Obtiene la variable mensaje
    */
    public String getMensaje() {
        return mensaje;
    }

    /*
     *Asigna la variable mensaje
    */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje.trim();
    }
    
    /*
     *Obtiene la variable codigo
    */
    public String getCodigo() {
      return codigo;
    } 
    
    /*
     *Asigna la variable codigo
    */
    public void setCodigo(String newCodigo) {
       codigo = newCodigo.trim();
    } 
    
    /*
     *Obtiene la variable nombre
    */
    public String getNombre() {
       return nombre;
    }
    
    /*
     *Asigna la variable nombre
    */
    public void setNombre(String newNombre) {
       nombre = newNombre.trim();
    } 
    
    /*
     *Obtiene la variable fechaNac
    */
    public String getFechanac() {
       return fechanac;
    } 
    
    /*
     *Asigna la variable fechaNac
    */
    public void setFechanac(String newFechaNac) {
       fechanac = newFechaNac.trim();
    } 
    /*
     *Obtiene la variable genero
    */ 
    public String getGenero() {
       return genero;
    }  
    
    /*
     *Asigna la variable genero
    */
    public void setGenero(String newGenero) {
       genero = newGenero.trim();
    }  
    
    /*
     *Obtiene la variable telefono
    */
    public String getTelefono() {
       return telefono;
    }  
    
    /*
     *Asigna la variable telefono
    */
    public void setTelefono(String newTelefono) {
       telefono = newTelefono.trim();
    }  
    
    /*
     *Obtiene la variable celular
    */
    public String getCelular() {
       return celular;
    }  
    
    /*
     *Asigna la variable celular
    */
    public void setCelular(String newCelular) {
       celular = newCelular.trim();
    }  
    
    /*
     *Obtiene la variable correo
    */
    public String getCorreo() {
       return correo;
    }  
    
    /*
     *Asigna la variable correo
    */
    public void setCorreo(String newCorreo) {
       correo = newCorreo.trim();
    } 
    
    /*
     *Obtiene la variable cedula
    */
    public String getCedula() {
       return cedula;
    }  
    
    /*
     *Asigna la variable cedula
    */
    public void setCedula(String newCedula) {
       cedula = newCedula.trim();
    } 
    
    /**
     * Creates a new instance of FiscalizadorDP
     */
    public FiscalizadoresDP() {
    }
    
    /*
     *Recupera la informacion del fiscalizador de la interfaz, la valida y la ingresa
    */
    public void Crear(){
        FiscalizadoresMD md = new FiscalizadoresMD();
        Fiscalizador fis = new Fiscalizador();
        fis.setCedula(cedula);
        fis.setCodigo(codigo);
        fis.setNombre(nombre);
        fis.setFechaNac(fechanac);
        fis.setGenero(genero.charAt(0));
        fis.setTelefono(telefono);
        fis.setCelular(celular);
        fis.setCorreo(correo);
        if (fis.Validar()) {
            if (md.Crear(fis)) {
                mensaje = "Información ingresada exitosamente";
            } else {
                mensaje = "Hubo un problema en la base de datos. Por favor contactarse con el administrador.";
            }
            
            
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    }
    
    /*
     *Recupera la informacion del fiscalizador de la interfaz, la valida y la modifica
    */
    public void Modificar(){
        FiscalizadoresMD md = new FiscalizadoresMD();
        Fiscalizador fis = new Fiscalizador();
        fis.setCedula(cedula.trim());
        fis.setCodigo(codigo.trim());
        fis.setNombre(nombre.trim());
        fis.setFechaNac(fechanac.trim());
        fis.setGenero(genero.charAt(0));
        fis.setTelefono(telefono.trim());
        fis.setCelular(celular.trim());
        fis.setCorreo(correo.trim());
        if (fis.Validar()) {
            if (md.Modificar(fis)) {
                mensaje = "Información modificada exitosamente";
            } else {
                mensaje = "Hubo un problema en la base de datos. Por favor contactarse con el administrador.";
            }
            
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
        
    }

    /*
     *Elimina la informacion del fiscalizador, la valida y la ingresa
    */
    public void Eliminar(){
        FiscalizadoresMD md = new FiscalizadoresMD();

        if (md.Verificar(cedula) == 1) {
            if (md.Eliminar(cedula)) {
                mensaje = "Información eliminana exitosamente";
            } else {
                mensaje = "Hubo un problema en la base de datos. Por favor contactarse con el administrador.";
            }
            
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    }
    
    /*
     *Recupera la informacion del fiscalizador de la base de datos y ;a envia a la interfaz
    */
    public void Consultar(){
        FiscalizadoresMD md = new FiscalizadoresMD();
        Fiscalizador fis;
        Verificar();
        fis = md.Consultar(cedula);
        if (existe == 1 && fis != null) {
            cedula = fis.getCedula().trim();
            codigo = fis.getCodigo().trim();
            nombre = fis.getNombre().trim();
            fechanac = fis.getFechaNac().substring(0, 10).replace("-", "/");
            genero = fis.getGenero()+"";
            telefono = fis.getTelefono().trim();
            celular = fis.getCelular().trim();
            correo = fis.getCorreo().trim();
        } else {
            cedula = "";
            codigo = "";
            nombre = "";
            fechanac = "";
            genero = "";
            telefono = "";
            celular = "";
            correo = "";
        }

        
    }
    
    
    public LinkedList<Fiscalizador> ConsultaGeneral(){
        LinkedList<Fiscalizador> resul;
        FiscalizadoresMD md = new FiscalizadoresMD();
        resul = md.ConsultaGeneral();
        return resul;
    }
    
    public void ConsultaParametros(){
        ArrayList resul;
        FiscalizadoresMD md = new FiscalizadoresMD();
        Fiscalizador fis = new Fiscalizador();
        fis.setCedula("");
        fis.setCodigo("");
        fis.setNombre("");
        fis.setFechaNac("");
        fis.setGenero('x');
        fis.setTelefono("");
        fis.setCelular("");
        fis.setCorreo("");
        consulta = md.ConsultaParametros(fis);
    }
    
    /*
     *Verifica que el fiscalizador no exista en la base de datos
    */
    public void Verificar(){
        FiscalizadoresMD md = new FiscalizadoresMD();
        existe = md.Verificar(cedula);
    }
    
}
