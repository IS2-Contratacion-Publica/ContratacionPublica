/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import Others.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Dell
 */
@ManagedBean(value = "constratistasMD")
@Dependent
public class ContratistasMD {

    /**
     * Creates a new instance of ConstratistasMD
     */
    public ContratistasMD() {
    }
    
    
    public Connection GenerarConexion() throws SQLException{
        Properties p =  new Properties();
        Connection conn;
        System.out.println("jdbc:"+
                                            p.prop("tipo")+":thin:@"+
                                            p.prop("direccion")+":"+
                                            p.prop("puerto")+":"+
                                            "orcl");
        System.out.println(p.prop("usuario"));
        System.out.println(p.prop("contrasenia"));
        conn = DriverManager.getConnection("jdbc:"+
                                            p.prop("tipo")+":thin:@"+
                                            p.prop("direccion")+":"+
                                            p.prop("puerto")+":"+
                                            "orcl",
                                            p.prop("usuario"),
                                            p.prop("contrasenia"));

        return conn;
    }
    
}
