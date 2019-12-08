/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import LayerMD.SesionMD;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ricky
 */
@ManagedBean(name = "SesionDP")
@Dependent
public class SesionDP {
    private String usuario; 
    private String contrasena;
    private String mensaje="";
    private int ingre;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIngre() {
        return ingre;
    }
    
    //setter

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setIngre(int ingre) {
        this.ingre = ingre;
    }
    
    public SesionDP(){}
    
    public String Ingresar(){
        //return "Aprovado";
        SesionMD md = new SesionMD();
        ingre = md.Ingresar(usuario,contrasena);
        if(ingre==1){
            //mensaje = "Codigo Existente";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("cedula.cuenta", usuario);
            return "pagprincipal.xhtml";
        }
        else{
            //mensaje = "Codigo no existente";
            mensaje = "**Datos Incorrectos**";
            return "index.xhtml";
        }
    }
    
    
}
