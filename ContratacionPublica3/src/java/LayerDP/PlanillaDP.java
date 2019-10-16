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
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.*;

/**
 *
 * @author sebad
 */
@ManagedBean(name = "planillaDP")
@RequestScoped
public class PlanillaDP {
     private String codigoProyecto;
    private String codigo;
    private Date fechaCreacion;
    private float monto;
    private PlanillaMD planillaMD = new PlanillaMD();
    private String mensaje;

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
       mensaje=planillaMD.crear(this);
    }
    public void modificar()
    {
        mensaje=planillaMD.modificar(this);
    }
    public void eliminar()
    {
        planillaMD.eliminar(this);
        
    }
    public void consultar() throws ParseException
    {
        String fecha;
      fecha=planillaMD.consultar(this);
      if(fecha!=null)
      {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
         fechaCreacion=dateFormat.parse(fecha);
      }
      else
      {
          mensaje="No se encontro planilla";
      }
    }
    public String Mensaje()
    {
       return "hola";
    }
    public void verificar()
    {
        codigoProyecto=planillaMD.Verificar(this);
        
    }
}
