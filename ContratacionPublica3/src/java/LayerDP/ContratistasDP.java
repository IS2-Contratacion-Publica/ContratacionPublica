/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Contratista;
import LayerMD.ContratistasMD;
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
     * Creates a new instance of ContratistasDP
     */
    public ContratistasDP() {
    }
    
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
    
    public void Verificar(){
        ContratistasMD md = new ContratistasMD();
        existe = md.Verificar(cedula);
    }
    
}
