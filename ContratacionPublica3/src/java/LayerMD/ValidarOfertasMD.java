/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.ValidarOferta;
import Others.Conexion;
import Others.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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

        Conexion cx = new Conexion();
        conced = vofe.getCONCEDULA();
        procod = vofe.getPROCODIGO();
        ofecod = vofe.getOFECODIGO();
        fisced = vofe.getFISCEDULA();
        valcri = vofe.getVALCRITERIO();
        valobs = vofe.getVALOBSERVACIONES();
        

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
                valobs+"', 1)";
    
        
        
        try {
            cx.Ejecutar(query);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return false;
        }
        
    }
    
   
    public LinkedList<ValidarOferta> Consultag(){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        ValidarOferta resul;
        String query;
        LinkedList<ValidarOferta> listvalidaciones = new LinkedList<ValidarOferta>();
        
        query = "select * from "+
                p.prop("val.tabla");

        try {
            
            rs = cx.Ejecutar(query);
            
            while(rs.next()){
                resul = new ValidarOferta();
                resul.setCONCEDULA(rs.getString(1));
                resul.setPROCODIGO(rs.getString(2));
                resul.setOFECODIGO(rs.getString(3));
                resul.setFISCEDULA(rs.getString(4));
                resul.setVALCRITERIO(rs.getString(5));
                resul.setVALOBSERVACIONES(rs.getString(6));
                                

                if (rs.getInt(7)==1) {
                    listvalidaciones.add(resul);
                }

           
            }
            cx.Cerrar();
            
        } catch (SQLException ex) {
            resul = null;
        }
        
        return listvalidaciones;
    }
        
}
