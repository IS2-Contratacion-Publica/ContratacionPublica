/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import LayerMD.PlanillaMD;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

/**
 *
 * @author sebad
 */
@ManagedBean(name = "planillaDP")
@RequestScoped
public class PlanillaDP {
     private String codigoProyecto;
    private String codigo;
    private String concodigo;
    private Date fechaCreacion;
    private float monto;
    private PlanillaMD planillaMD = new PlanillaMD();
    private String mensaje;
    private LinkedList<PlanillaDP> lista;
    private ArrayList consulta;
        public ArrayList getConsulta() {
        ConsultaGeneral();
        return consulta;
    }

    public String getConcodigo() {
        return concodigo;
    }

    public void setConcodigo(String concodigo) {
        this.concodigo = concodigo.trim();
    }

    public LinkedList<PlanillaDP> getLista() {
        return lista;
    }

    public void setLista(LinkedList<PlanillaDP> lista) {
        this.lista = lista;
    }
   
    public void setPlanillaMD() {

    }

    public PlanillaMD getPlanillaMD() {
        return planillaMD;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto.trim();
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo.trim();
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public PlanillaDP() 
    {
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public String getCodigo() {
        return codigo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public float getMonto() {
        return monto;
    }
    public void crear()
    {
        mensaje = planillaMD.crear(this);
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));

    }
    public void modificar()
    {
        mensaje = planillaMD.modificar(this);
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    }
    public void eliminar()
    {
        mensaje = planillaMD.eliminar(this);
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    }
    public void consultar() throws ParseException
    {
        String fecha;
        planillaMD.consultar(this);
              
    }
    public String Mensaje()
    {
       return "hola";
    }
    public void verificar()
    {
        if(!planillaMD.Verificar(this))
        {
            mensaje="Código disponible. Ingrese los demas datos";
        }
        else
        {
            mensaje="Código ya existente";
        }
        FacesContext.getCurrentInstance().addMessage("menj", new FacesMessage(mensaje, ""));
    }
    public Map consultaGeneral()
    {
        return planillaMD.consultaGeneral();
    }
        public ArrayList ConsultaGeneral()
    {
        consulta = planillaMD.ConsultaGeneral();
        return consulta;
    }
}
