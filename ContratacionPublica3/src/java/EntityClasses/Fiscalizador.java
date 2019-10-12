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
@ManagedBean(value = "fiscalizador")
@Dependent
public class Fiscalizador {
    private String cedula;

    private String codigo;

    private String nombre;

    private java.util.Date fechaNac;

    private Character genero;

    private String telefono;

    private String celular;

    private String correo;
    
    /**
     * Creates a new instance of Fiscalizador
     */
    public Fiscalizador() {
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
    public java.util.Date getFechaNac() {
       return fechaNac;
    } 
    public void setFechaNac(java.util.Date newFechaNac) {
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
    
}
