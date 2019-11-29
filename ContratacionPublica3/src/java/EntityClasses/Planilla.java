/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;
import java.sql.Date;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;
/**
 *
 * @author sebad
 */
@ManagedBean(value = "planilla")
@Dependent
public class Planilla {
    private String codigoProyecto;
    private String codigo;
    private Date fechaCreacion;
    private float monto;
    public Planilla() {
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
}
