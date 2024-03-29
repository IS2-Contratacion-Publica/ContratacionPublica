/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Dell
 */
@ManagedBean(value = "contratista")
@Dependent
public class Contratista {
    private String cedula;
    private String codigo;
    private String nombre;
    private String fechaNac;
    private Character genero;
    private String telefono;
    private String celular;
    private String correo;

   private final String REGEX_CEDULA = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_CODIGO = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_NOMBRE = "[ /A-Za-z0-9]{1,100}";
   private final String REGEX_FECHANAC = "[0-9]{4}\\/[0-9]{2}\\/[0-9]{2}";
   private final String REGEX_GENERO = "[/A-Za-z0-9]{1,1}";
   private final String REGEX_TELEFONO = "[/A-Za-z0-9]{1,10}";
   private final String REGEX_CELULAR = "[/A-Za-z0-9]{1,10}";
   private final String REGEX_CORREO = "([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+)";
    
    /**
     * Creates a new instance of Contratista
     */
    public Contratista() {
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
    public String getFechaNac() {
       return fechaNac;
    } 
    
    /*
     *Asigna la variable fechaNac
    */
    public void setFechaNac(String newFechaNac) {
       fechaNac = newFechaNac;
    } 
    /*
     *Obtiene la variable genero
    */ 
    public Character getGenero() {
       return genero;
    }  
    
    /*
     *Asigna la variable genero
    */
    public void setGenero(Character newGenero) {
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
    
    /*
     *Se encarga de ver que los datos tengan el formato correcto
    */
    public boolean Validar() {
        if (cedula.matches(REGEX_CEDULA) &&
            codigo.matches(REGEX_CODIGO) &&
            nombre.matches(REGEX_NOMBRE) &&
            fechaNac.matches(REGEX_FECHANAC) &&
            (genero+"").matches(REGEX_GENERO) &&
            telefono.matches(REGEX_TELEFONO) &&
            celular.matches(REGEX_CELULAR) &&
            correo.matches(REGEX_CORREO)) {
            return true;
        } else {
            return false;
        }
    }
}
