/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import LayerMD.PlanillaMD;
import java.sql.Date;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sebad
 */
@javax.faces.bean.ManagedBean(name = "planillaPD")
@Dependent
public class PlanillaDP {
     private String codigoProyecto;
    private String codigo;
    private Date fechaCreacion;
    private float monto;
    private PlanillaMD planillaMD= new PlanillaMD();;
    private String mensaje="hola";

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
        this.codigoProyecto = codigoProyecto;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
       planillaMD.crear(this);
    }
    public void modificar()
    {
        planillaMD.modificar();
    }
    public void eliminar()
    {
        planillaMD.eliminar();
    }
    public void consultar()
    {
       //return planillaMD.consultar();
    }
}
