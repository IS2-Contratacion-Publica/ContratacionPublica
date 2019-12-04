/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.ValidarOferta;
import Others.Conexion;
import Others.Properties;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Santiago
 */
@ManagedBean(value = "validarOfertasMD")
@Dependent
public class ValidarOfertasMD {

    /**
     * Creates a new instance of PruebasMD
     */
    public ValidarOfertasMD() {
    }
    
    

    public boolean Validar(ValidarOferta vofe){
        Properties p =  new Properties();

        String conced,procod,ofecod,fisced,valcri,valobs,query;
        int valval;

        Conexion cx = new Conexion();
        conced = vofe.getCONCEDULA();
        procod = vofe.getPROCODIGO();
        ofecod = vofe.getOFECODIGO();
        fisced = vofe.getFISCEDULA();
        valcri = vofe.getVALCRITERIO();
        valobs = vofe.getVALOBSERVACIONES();
        valval = vofe.getVALVALIDO();
        

        query = "insert into "+
                p.prop("val.tabla")+" ("+
                p.prop("val.c1")+", "+
                p.prop("val.c2")+", "+
                p.prop("val.c3")+", "+
                p.prop("val.c4")+", "+
                p.prop("val.c5")+", "+
                p.prop("val.c6")+", "+
                p.prop("val.c7")+") "
                + "values ('"+
                conced+"','"+
                procod+"', '"+
                ofecod+"', '"+
                fisced+"', '"+
                valcri+"', '"+
                valobs+"', '"+
                valval+"')";
    
        
        
        try {
            cx.Ejecutar(query);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ValidarOfertasMD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
   
    public ArrayList<ValidarOferta> Consultap(ValidarOferta vofe){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        ValidarOferta vo = null;
        ArrayList resul = new ArrayList();
        String query;
        
        query = "select * from "+
                p.prop("val.tabla")+" where ";
        
        if (!vofe.getCONCEDULA().isEmpty()) {
            query += p.prop("val.c1")+" = '"+vofe.getCONCEDULA()+"' and  ";
        }
        if (!vofe.getPROCODIGO().isEmpty()) {
            query += p.prop("val.c2")+" = '"+vofe.getPROCODIGO()+"' and  ";
        }
        if (!vofe.getOFECODIGO().isEmpty()) {
            query += p.prop("val.c3")+" = '"+vofe.getOFECODIGO()+"' and  ";
        }
        if (!vofe.getFISCEDULA().isEmpty()) {
            query += p.prop("val.c4")+" = '"+vofe.getFISCEDULA()+"' and  ";
        }
        if (!vofe.getVALCRITERIO().isEmpty()) {
            query += p.prop("val.c5")+" = '"+vofe.getVALCRITERIO()+"' and  ";
        }
        if (!vofe.getVALOBSERVACIONES().isEmpty()) {
            query += p.prop("val.c6")+" = '"+vofe.getVALOBSERVACIONES()+"' and  ";
        }
        

                
        query = query.substring(0, query.length()-5);

        try {
            rs = cx.Ejecutar(query);
            while (rs.next()){
                vo = new ValidarOferta();
                vo.setCONCEDULA(rs.getString(1));
                vo.setPROCODIGO(rs.getString(2));
                vo.setOFECODIGO(rs.getString(3));
                vo.setFISCEDULA(rs.getString(4));
                vo.setVALCRITERIO(rs.getString(5));
                vo.setVALOBSERVACIONES(rs.getString(6));
                vo.setVALVALIDO(rs.getInt(7));
                resul.add(vo);
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
            Logger.getLogger(ValidarOfertasMD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resul;
    }
        
}
