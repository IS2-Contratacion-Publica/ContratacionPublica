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
   private final String REGEX_CORREO = "[/A-Za-z0-9]{1,50}";
    
    /**
     * Creates a new instance of Contratista
     */
    public Contratista() {
    }
    
        
    
    public String getCodigo() {
      return codigo;
    } 
    public void setCodigo(String newCodigo) {
       codigo = newCodigo;
    } 
    public String getNombre() {
       return nombre;
    } 
    public void setNombre(String newNombre) {
       nombre = newNombre;
    } 
    public String getFechaNac() {
       return fechaNac;
    } 
    public void setFechaNac(String newFechaNac) {
       fechaNac = newFechaNac;
    } 
    public Character getGenero() {
       return genero;
    } 
    public void setGenero(Character newGenero) {
       genero = newGenero;
    } 
    public String getTelefono() {
       return telefono;
    } 
    public void setTelefono(String newTelefono) {
       telefono = newTelefono;
    } 
    public String getCelular() {
       return celular;
    } 
    public void setCelular(String newCelular) {
       celular = newCelular;
    } 
    public String getCorreo() {
       return correo;
    } 
    public void setCorreo(String newCorreo) {
       correo = newCorreo;
    } 
    public String getCedula() {
       return cedula;
    } 
    public void setCedula(String newCedula) {
       cedula = newCedula;
    }
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
