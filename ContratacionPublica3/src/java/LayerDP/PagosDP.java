/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.Pagos;
import LayerMD.PagosMD;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import EntityClasses.Planilla;
import javax.faces.bean.RequestScoped;
/**
 *
 * @author sebad
 */
@ManagedBean(name="pagosdp")
@RequestScoped
public class PagosDP {
    private String PagCodigo;
    private String ProCodigo;
    private String PlaCodigo;
    private String Pla_ConCedula;
    private String FisCedula;
    private String PagFechaAutorizacion;
    private String PagFechaEnvio;
    private String PagEstado;
    private double monto=0;
    private Map planilla;
    private Map <String,Pagos> lista;
    private PlanillaDP planillaDP= new PlanillaDP();
    private PagosMD pagosMD = new PagosMD();
    private Pagos pago= new Pagos();
    private ArrayList array;

    
    public ArrayList getArray() {
        lista=pagosMD.cargar();
        array = new ArrayList<>(lista.values());
        return array;
    }

    /**
     * Creates a new instance of PagosDP
     */
    public void setArray(ArrayList array) {    
        this.array = array;
    }

    public PagosDP() {
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }

    public Map<String, Pagos> getLista() {
        lista=pagosMD.cargar();
        return lista;
    }

    public void setLista(Map<String, Pagos> lista) {
        this.lista = lista;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Map getPlanilla() {
        planilla=planillaDP.consultaGeneral();
        System.out.print(planilla.keySet().toString());
        return planilla;
    }

    public void setPlanilla(Map planilla) {
        this.planilla = planilla;
    }

    public PlanillaDP getPlanillaDP() {
        return planillaDP;
    }

    public void setPlanillaDP(PlanillaDP planillaDP) {
        this.planillaDP = planillaDP;
    }

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
    public void verificar()
    {
        Planilla nuevo=pagosMD.crearPlanilla(PlaCodigo);
        this.ProCodigo=nuevo.getCodigoProyecto();
        this.PlaCodigo= nuevo.getCodigo();
        this.Pla_ConCedula= nuevo.getConCedula();
        this.monto=nuevo.getMonto();
    }
    public void ingresar()
    {
        verificar();
        Pagos nuevo = new Pagos();
        FisCedula="1804279048"; //Aqui se ingre el fiscalizador que viene del login que ni idea como hacer xd
        nuevo.setFisCedula(this.FisCedula);
        nuevo.setPagCodigo(this.PagCodigo);
        nuevo.setPagEstado(this.PagEstado);
        nuevo.setPagFechaAutorizacion(this.PagFechaAutorizacion);
        nuevo.setPagFechaEnvio(this.PagFechaEnvio);
        nuevo.setPlaCodigo(this.PlaCodigo);
        nuevo.setPla_ConCedula(this.Pla_ConCedula);
        nuevo.setProCodigo(this.ProCodigo);
        System.out.println(ProCodigo);
        System.out.println(PlaCodigo);
        System.out.println(Pla_ConCedula);
        pagosMD.ingresarMD(nuevo);
    }
    public void verificar2()
    {
        pago=lista.get(PagCodigo);
        ProCodigo=pago.getProCodigo();
        PlaCodigo=pago.getPlaCodigo();
        Pla_ConCedula=pago.getPla_ConCedula();
        FisCedula=pago.getFisCedula();
        PagFechaAutorizacion=pago.getPagFechaAutorizacion();
    }
    public void validar()
    {
        Pagos nuevo = new Pagos();
        FisCedula="1804279048";
        nuevo.setFisCedula(this.FisCedula);
        nuevo.setPagCodigo(this.PagCodigo);
        nuevo.setPagEstado(this.PagEstado);
        nuevo.setPagFechaAutorizacion(this.PagFechaAutorizacion);
        nuevo.setPagFechaEnvio(this.PagFechaEnvio);
        nuevo.setPlaCodigo(this.PlaCodigo);
        nuevo.setPla_ConCedula(this.Pla_ConCedula);
        nuevo.setProCodigo(this.ProCodigo);
        System.out.println(ProCodigo);
        System.out.println(PlaCodigo);
        System.out.println(Pla_ConCedula);
        pagosMD.validarMD(nuevo);
        verificar2();
    }
}
