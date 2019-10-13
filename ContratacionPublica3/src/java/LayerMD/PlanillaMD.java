/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import LayerDP.PlanillaDP;
import Others.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author sebad
 */
public class PlanillaMD {

    public PlanillaMD() 
    {
    }
    public Connection GenerarConexion() throws SQLException{
        Properties p =  new Properties();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContratistasMD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("clase no encontrada");
        }
        Connection conn;
        conn = DriverManager.getConnection("jdbc:"+
                                            p.prop("tipo")+":thin:@"+
                                            p.prop("direccion")+":"+
                                            p.prop("puerto")+":"+
                                            "BASEDATOS",
                                            p.prop("usuario"),
                                            p.prop("contrasenia"));
        return conn;
    }
    public boolean crear(PlanillaDP planillaDP)
    {
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String codP,cod,fecha,orden;
        float monto;
        codP = planillaDP.getCodigoProyecto();
        cod = planillaDP.getCodigo();
        fecha = planillaDP.getFechaCreacion().toString();
        monto = planillaDP.getMonto();
        orden = "insert into "+
                p.prop("pla.tabla")+" ("+
                p.prop("pla.llave")+", "+
                p.prop("pla.campo1")+", "+
                p.prop("pla.campo2")+", "+
                p.prop("pla.campo3")+") "
                + "values ('"+codP+"','"+
                cod+"',"+
                "TO_DATE('"+
                fecha+"', 'YYYY/MM/DD'),"+
                monto+")";

        try {
            conn = GenerarConexion();
            System.out.println(conn);
            s = conn.createStatement();
            s.executeQuery(orden);
            conn.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return false;
        }
    }
    public void modificar()
    {
        
    }
    public void eliminar()
    {
        
    }
    public void consultar()
    {
      // return planillaDP.consultar();
    }
}
