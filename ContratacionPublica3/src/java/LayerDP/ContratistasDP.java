/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Contratista;
import LayerMD.ContratistasMD;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "contratistasDP")
@Dependent
public class ContratistasDP {
    
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
    private ContratistasMD layermd = new ContratistasMD();

    public ContratistasMD getLayermd() {
        return layermd;
    }

    public void setLayermd(ContratistasMD layermd) {
        this.layermd = layermd;
    }
    
    

    public List<SelectItem> getGeneros() {
        return new ContratistasMD().getGeneros();
    }

    public void setGeneros(List<SelectItem> newgeneros) {
        this.generos = newgeneros;
    }

    public ArrayList getConsulta() {
        ConsultaParametros();
        return consulta;
    }

    public void setConsulta(ArrayList newconsulta) {
        this.consulta = newconsulta;
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
    public void setExiste(int newexiste) {
        this.existe = newexiste;
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
    public void setMensaje(String newmensaje) {
        this.mensaje = newmensaje.trim();
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
     * Creates a new instance of ContratistasDP
     */
    public ContratistasDP() {
    }
    
    /*
     *Recupera la informacion del contratista de la interfaz, la valida y la ingresa
    */
    public void Crear(){
        ContratistasMD md = new ContratistasMD();
        Contratista con = new Contratista();
        con.setCedula(cedula);
        con.setCodigo(codigo);
        con.setNombre(nombre);
        con.setFechaNac(fechanac);
        con.setGenero(genero.charAt(0));
        con.setTelefono(telefono);
        con.setCelular(celular);
        con.setCorreo(correo);
        if (con.Validar()) {
            if (md.Crear(con)) {
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
     *Recupera la informacion del contratista de la interfaz, la valida y la modifica
    */
    public void Modificar(){
        ContratistasMD md = new ContratistasMD();
        Contratista con = new Contratista();
        con.setCedula(cedula.trim());
        con.setCodigo(codigo.trim());
        con.setNombre(nombre.trim());
        con.setFechaNac(fechanac.trim());
        con.setGenero(genero.charAt(0));
        con.setTelefono(telefono.trim());
        con.setCelular(celular.trim());
        con.setCorreo(correo.trim());
        if (con.Validar()) {
            if (md.Modificar(con)) {
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
     *Elimina la informacion del contratista, la valida y la ingresa
    */
    public void Eliminar(){
        ContratistasMD md = new ContratistasMD();

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
     *Recupera la informacion del contratista de la base de datos y ;a envia a la interfaz
    */
    public void Consultar(){
        ContratistasMD md = new ContratistasMD();
        Contratista con;
        Verificar();
        con = md.Consultar(cedula);
        if (existe == 1 && con != null) {
            cedula = con.getCedula().trim();
            codigo = con.getCodigo().trim();
            nombre = con.getNombre().trim();
            fechanac = con.getFechaNac().substring(0, 10).replace("-", "/");
            genero = con.getGenero()+"";
            telefono = con.getTelefono().trim();
            celular = con.getCelular().trim();
            correo = con.getCorreo().trim();
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
    
    public ArrayList ConsultaGeneral(){
        ArrayList resul;
        ContratistasMD md = new ContratistasMD();
        return md.ConsultaGeneral();
    }
    
    public void ConsultaParametros(){
        ArrayList resul;
        ContratistasMD md = new ContratistasMD();
        Contratista con = new Contratista();
        con.setCedula("");
        con.setCodigo("");
        con.setNombre("");
        con.setFechaNac("");
        con.setGenero('x');
        con.setTelefono("");
        con.setCelular("");
        con.setCorreo("");
        consulta = md.ConsultaParametros(con);
    }
    
    /*
     *Verifica que el contratista no exista en la base de datos
    */
    public void Verificar(){
        ContratistasMD md = new ContratistasMD();
        existe = md.Verificar(cedula);
        if (existe == 1) {
            mensaje = "Código Existente";
        }
        else {
            mensaje = "Código no existente";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    }
    
}
