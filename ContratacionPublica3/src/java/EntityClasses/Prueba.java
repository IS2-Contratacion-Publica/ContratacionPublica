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
 * @author Santiago
 */
@ManagedBean(value = "prueba")
@Dependent
public class Prueba {
    private String procodigo;
    private String prucodigo;
    private String descripcion;
    private String fecharealizacion;


   private final String REGEX_PROCODIGO = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_PRUCODIGO = "[/A-Za-z0-9]{1,15}";
   private final String REGEX_DESCRIPCION = "[ /A-Za-z0-9]{1,100}";
   private final String REGEX_FECHAREALIZACION = "[0-9]{4}\\/[0-9]{2}\\/[0-9]{2}";

    
    /**
     * Creates a new instance of prueba
     */
    public Prueba() {
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
    public boolean Validar() {
        if (procodigo.matches(REGEX_PROCODIGO) &&
            prucodigo.matches(REGEX_PRUCODIGO) &&
            descripcion.matches(REGEX_DESCRIPCION) &&
            fecharealizacion.matches(REGEX_FECHAREALIZACION)) {
            return true;
        } else {
            return false;
        }
    }
}
