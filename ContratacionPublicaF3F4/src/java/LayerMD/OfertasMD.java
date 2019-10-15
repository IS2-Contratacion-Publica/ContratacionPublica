/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Oferta;
import Others.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Dell
 */
@ManagedBean(value = "ofertasMD")
@Dependent
public class OfertasMD {

    /**
     * Creates a new instance of ConstratistasMD
     */
    public OfertasMD() {
    }
    
    
    public Connection GenerarConexion() throws SQLException{
        Properties p =  new Properties();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OfertasMD.class.getName()).log(Level.SEVERE, null, ex);
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
        return conn;
    }
    
    public boolean Ingresar(Oferta oft){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String idof, idpro, cos, ubi,query;
        
        idof = oft.getIdoferta();
        idpro = oft.getIdproyecto();
        cos = oft.getCostoofertado();
        ubi = oft.getUbicacion();
        query = "insert into "+
                p.prop("oft.tabla")+" ("+
                p.prop("oft.llave")+", "+
                p.prop("oft.campo1")+", "+
                p.prop("oft.campo2")+", "+
                p.prop("oft.campo3")+") "
                + "values ('"+idof+"','"+
                idpro+"', '"+
                cos+"', '"+
                ubi+"')"; 
        try {
            conn = GenerarConexion();
            System.out.println(conn);
            s = conn.createStatement();
            s.executeQuery(query);
            conn.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return false;
        }
        
    }
    
    public boolean Modificar(Oferta oft){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String idof, idpro, cos, ubi,query;
        
        System.out.println("preparando para insertar");
        idof = oft.getIdoferta();
        idpro = oft.getIdproyecto();
        cos = oft.getCostoofertado();
        ubi = oft.getUbicacion();
        query = "update "+
                p.prop("oft.tabla")+" set "+
                p.prop("oft.campo1")+" = '"+idpro+"', "+
                p.prop("oft.campo2")+" = '"+cos+"', "+
                p.prop("oft.campo3")+" = '"+ubi+"' where "+
                p.prop("oft.llave")+" = '"+idof+"'";
        System.out.println(query);
        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            s.executeUpdate(query);
            conn.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean Eliminar(String idoferta){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String orden;
        orden = "delete from "+
                p.prop("oft.tabla")+" where "+
                p.prop("oft.llave")+" = '"+idoferta+"'";

        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            s.executeUpdate(orden);
            conn.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public Oferta Consultar(String idoferta){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        Oferta resul;
        String query;
        
        query = "select * from "+
                p.prop("oft.tabla")+" where "+
                p.prop("oft.llave")+" = '"+idoferta+"'";

        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(query);
            
            if(rs.next()){
                resul = new Oferta();
                resul.setIdoferta(rs.getString(1));
                resul.setIdproyecto(rs.getString(2));
                resul.setCostoofertado(rs.getString(3));
                resul.setUbicacion(rs.getString(4));
            }
            else{
                resul = null;
            }
            conn.close();
            
        } catch (SQLException ex) {
            resul = null;
        }
        
        return resul;
    }
    
    public int Verificar(String idoferta){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        int existe;
        String query;
        
        query = "select * from "+
                p.prop("oft.tabla")+" where "+
                p.prop("oft.llave")+" = '"+idoferta+"'";

        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(query);
            if(rs.next()){
                existe = 1;
            }
            else{
                existe = 0;
            }
            conn.close();
            
        } catch (SQLException ex) {
            existe = -1;
        }
        System.out.println(query);
        System.out.println("Existe: " + idoferta);
        System.out.println("Existe: " + existe);
        return existe;
    }
  
    
}
