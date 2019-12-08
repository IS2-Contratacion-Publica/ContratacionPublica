/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.util.Date;

/**
 *
 * @author sebad
 */
public class Pagos {
    private String PagCodigo;
    private String ProCodigo;
    private String PlaCodigo;
    private String Pla_ConCedula;
    private String FisCedula;
    private String PagFechaAutorizacion;
    private String PagFechaEnvio;
    private String PagEstado;
    public String getPagCodigo() {
        return PagCodigo;
    }

    public void setPagCodigo(String PagCodigo) {
        this.PagCodigo = PagCodigo;
    }

    public String getProCodigo() {
        return ProCodigo;
    }

    public void setProCodigo(String ProCodigo) {
        this.ProCodigo = ProCodigo;
    }

    public String getPlaCodigo() {
        return PlaCodigo;
    }

    public void setPlaCodigo(String PlaCodigo) {
        this.PlaCodigo = PlaCodigo;
    }

    public String getPla_ConCedula() {
        return Pla_ConCedula;
    }

    public void setPla_ConCedula(String Pla_ConCedula) {
        this.Pla_ConCedula = Pla_ConCedula;
    }

    public String getFisCedula() {
        return FisCedula;
    }

    public void setFisCedula(String FisCedula) {
        this.FisCedula = FisCedula;
    }

    public String getPagFechaAutorizacion() {
        return PagFechaAutorizacion;
    }

    public void setPagFechaAutorizacion(String PagFechaAutorizacion) {
        this.PagFechaAutorizacion = PagFechaAutorizacion;
    }

    public String getPagFechaEnvio() {
        return PagFechaEnvio;
    }

    public void setPagFechaEnvio(String PagFechaEnvio) {
        this.PagFechaEnvio = PagFechaEnvio;
    }

    public String getPagEstado() {
        return PagEstado;
    }

    public void setPagEstado(String PagEstado) {
        this.PagEstado = PagEstado;
    }
}
