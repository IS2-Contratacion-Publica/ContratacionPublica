/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Sesion;
import Others.Conexion;
import Others.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Ricky
 */
@ManagedBean(value = "sesionMD")
@Dependent
public class SesionMD {
    
    public SesionMD(){}
    
    public Connection GenerarConexion() throws SQLException{
        Properties pro =  new Properties();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SesionMD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("clase no encontrada");
        }
        Connection conn;
        conn = DriverManager.getConnection("jdbc:"+
                                            pro.prop("tipo")+":thin:@"+
                                            pro.prop("direccion")+":"+
                                            pro.prop("puerto")+":"+
                                            "orcl",
                                            pro.prop("usuario"),
                                            pro.prop("contrasenia"));
        return conn;
    }
    
    public int Ingresar(String usuario, String contra){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        Sesion resul;
        String query;
        int ingreso=0;
        
        query = "SELECT * FROM "+
                p.prop("ses.tabla")+" WHERE "+
                p.prop("ses.campo1")+" = '"+usuario+"' AND "+p.prop("ses.campo2")+" = '"+contra+"'";
        System.out.print(query);
        try {
            
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(query);
            
            if(rs.next()==true){
                ingreso = 1;
            }
            else{
                ingreso=0;
            }
            conn.close();
            
        } catch (SQLException ex) {
            resul = null;
            System.out.print(ex);
        }
        
        return ingreso;
    }
}
