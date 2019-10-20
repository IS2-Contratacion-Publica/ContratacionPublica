/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerDP;

import EntityClasses.ValidarOferta;
import LayerMD.ValidarOfertasMD;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Santiago
 */
@ManagedBean(name = "ValidarOfertasDP")
@Dependent
public class ValidarOfertasDP {
    
    private String CONCEDULA;
    private String PROCODIGO;
    private String OFECODIGO;
    private String FISCEDULA;
    private String VALCRITERIO;
    private String VALOBSERVACIONES;
    private int existe;
    private String mensaje;
    private ArrayList consultaparametro;

    public ArrayList getConsultaparametro() {
        return consultaparametro;
    }

    public void setConsultaparametro(ArrayList consultaparametro) {
        this.consultaparametro = consultaparametro;
    }

    public String getCONCEDULA() {
        return CONCEDULA;
    }

    public void setCONCEDULA(String CONCEDULA) {
        this.CONCEDULA = CONCEDULA;
    }

    public String getPROCODIGO() {
        return PROCODIGO;
    }

    public void setPROCODIGO(String PROCODIGO) {
        this.PROCODIGO = PROCODIGO;
    }

    public String getOFECODIGO() {
        return OFECODIGO;
    }

    public void setOFECODIGO(String OFECODIGO) {
        this.OFECODIGO = OFECODIGO;
    }

    public String getFISCEDULA() {
        return FISCEDULA;
    }

    public void setFISCEDULA(String FISCEDULA) {
        this.FISCEDULA = FISCEDULA;
    }

    public String getVALCRITERIO() {
        return VALCRITERIO;
    }

    public void setVALCRITERIO(String VALCRITERIO) {
        this.VALCRITERIO = VALCRITERIO;
    }

    public String getVALOBSERVACIONES() {
        return VALOBSERVACIONES;
    }

    public void setVALOBSERVACIONES(String VALOBSERVACIONES) {
        this.VALOBSERVACIONES = VALOBSERVACIONES;
    }

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
    
              
    /**
     * Creates a new instance of PruebasDP
     */
    public ValidarOfertasDP() {
    }
    
   
    
    public void Validar(){
        ValidarOfertasMD md = new ValidarOfertasMD();
        ValidarOferta vofe = new ValidarOferta();
        vofe.setCONCEDULA(CONCEDULA);
        vofe.setPROCODIGO(PROCODIGO);
        vofe.setOFECODIGO(OFECODIGO);
        vofe.setFISCEDULA(FISCEDULA);
        vofe.setVALCRITERIO(VALCRITERIO);
        vofe.setVALOBSERVACIONES(VALOBSERVACIONES);
        
        if (vofe.Validar()) {
            if (md.Validar(vofe)) {
                mensaje = "Validación ingresada exitosamente";
            } else {
                mensaje = "Hubo un problema en la base de datos. Por favor contactarse con el administrador.";
            }
            
            
        } else {
            mensaje = "Por favor ingrese correctamente todos los valores";
        }
        
    }
    
       
    public void Consultap(){
        ValidarOfertasMD md = new ValidarOfertasMD();
        ValidarOferta vofe = new ValidarOferta();
        vofe.setCONCEDULA(CONCEDULA);
        vofe.setPROCODIGO(PROCODIGO);
        vofe.setOFECODIGO(OFECODIGO);
        vofe.setFISCEDULA(FISCEDULA);
        vofe.setVALCRITERIO(VALCRITERIO);
        vofe.setVALOBSERVACIONES(VALOBSERVACIONES);
        consultaparametro = md.Consultap(vofe);
    }
            
}
