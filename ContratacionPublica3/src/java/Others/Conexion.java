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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Dell
 */
public class Conexion {
    
    Connection conexion = null;
    
    private DataSource getScpDataBase() throws NamingException {
        Properties p = new Properties();
        Context c = new InitialContext();
        return (DataSource) c.lookup(p.prop("pool"));
    }
    /*
     Genera una conexion con la base de datos
    */
    private void GenerarConexion() throws SQLException, NamingException, ClassNotFoundException{
        Connection conn;
        conn = getScpDataBase().getConnection();
        conexion = conn;
    }
    
    public ResultSet Ejecutar(String orden) throws SQLException {
        Statement s = null;
        ResultSet rs = null;
        boolean ejecutado = false;
        System.out.println(orden);
        
        try {
            GenerarConexion();
            s = conexion.createStatement();
            if (s.execute(orden)) {
                rs = s.getResultSet();
            }
            ejecutado = true;
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conexion != null) {
                conexion.close();
            }
            if (s == null) {
                System.out.println("No se pudo crear el Statement");
            }
            if (!ejecutado) {
                System.out.println("No se pudo ejecutar la sentencia SQL");
            }
        }
        return rs;
    }
    
    public void Cerrar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }


}
