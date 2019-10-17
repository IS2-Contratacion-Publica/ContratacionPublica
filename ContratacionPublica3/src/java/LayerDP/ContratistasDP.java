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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

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
        this.mensaje = mensaje;
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
       codigo = newCodigo;
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
       nombre = newNombre;
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
       fechanac = newFechaNac;
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
       genero = newGenero;
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
       telefono = newTelefono;
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
       celular = newCelular;
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
       correo = newCorreo;
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
       cedula = newCedula;
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
        
    }
    
    /*
     *Recupera la informacion del contratista de la base de datos y ;a envia a la interfaz
    */
    public void Consultar(){
        ContratistasMD md = new ContratistasMD();
        Contratista con;
        Verificar();
        if (existe == 1) {
            con = md.Consultar(cedula);
            cedula = con.getCedula();
            codigo = con.getCodigo();
            nombre = con.getNombre();
            fechanac = con.getFechaNac().substring(0, 10).replace("-", "/");
            genero = con.getGenero()+"";
            telefono = con.getTelefono();
            celular = con.getCelular();
            correo = con.getCorreo();
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
    
    /*
     *Verifica que el contratista no exista en la base de datos
    */
    public void Verificar(){
        ContratistasMD md = new ContratistasMD();
        existe = md.Verificar(cedula);
    }
    
}
