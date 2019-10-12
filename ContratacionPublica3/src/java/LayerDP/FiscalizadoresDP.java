/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Fiscalizador;
import LayerMD.FiscalizadoresMD;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

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
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Creates a new instance of FiscalizadorDP
     */
    public FiscalizadoresDP() {
    }
    
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
        
    }
    
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
        
        
    }

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
        
    }
    public void Consultar(){
        FiscalizadoresMD md = new FiscalizadoresMD();
        Fiscalizador fis;
        Verificar();
        if (existe == 1) {
            fis = md.Consultar(cedula);
            cedula = fis.getCedula();
            codigo = fis.getCodigo();
            nombre = fis.getNombre();
            fechanac = fis.getFechaNac().substring(0, 10).replace("-", "/");
            genero = fis.getGenero()+"";
            telefono = fis.getTelefono();
            celular = fis.getCelular();
            correo = fis.getCorreo();
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
    
    public void Verificar(){
        FiscalizadoresMD md = new FiscalizadoresMD();
        existe = md.Verificar(cedula);
    }
    
}
