/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

import java.sql.Connection;
import LayerMD.ContratistasMD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Test {
    
    public static void main(String[] args) {
        ContratistasMD md = new ContratistasMD();
        try {
            Connection conn = md.GenerarConexion();
            System.out.println(md);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("failed connection");
        }
    }
}
