/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

import LayerMD.ContratistasMD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Dell
 */
public class Conexion {
    
    Connection conexion;
    /*
     Genera una conexion con la base de datos
    */
    private void GenerarConexion() throws SQLException{
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
                                            "orcl",
                                            p.prop("usuario"),
                                            p.prop("contrasenia"));
        conexion = conn;
    }
    
    public ResultSet Ejecutar(String orden) throws SQLException {
        Statement s;
        ResultSet rs = null;
        
        System.out.println(orden);

        GenerarConexion();
        s = conexion.createStatement();
        if (s.execute(orden)) {
            rs = s.getResultSet();
        }
        
        return rs;
    }
    
    public void Cerrar() throws SQLException {
        conexion.close();
    }
}
