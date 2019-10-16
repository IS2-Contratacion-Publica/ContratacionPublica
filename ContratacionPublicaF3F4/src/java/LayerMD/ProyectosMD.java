/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Proyecto;
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


@ManagedBean(value = "proyectosMD")
@Dependent

public class ProyectosMD {
    public ProyectosMD(){
    }
    public Connection GenerarConexion() throws SQLException{
        Properties p =  new Properties();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProyectosMD.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean Ingresar(Proyecto pro){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String id, nom, des, ent, tid, cos,query;

        
        id = pro.getIdproyecto();
        nom = pro.getNombreproyecto();
        des = pro.getDescripcion();
        ent = pro.getEntidad();
        tid = pro.getTiempoDuración();
        cos = pro.getCosto();
        query = "insert into "+
                p.prop("pro.tabla")+" ("+
                p.prop("pro.llave")+", "+
                p.prop("pro.campo1")+", "+
                p.prop("pro.campo2")+", "+
                p.prop("pro.campo3")+", "+
                p.prop("pro.campo4")+", "+
                p.prop("pro.campo5")+", "+
                "values ('"+id+"','"+
                nom+"', '"+
                des+"', "+
                ent+"', "+
                tid+"', '"+
                cos+"')";
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
    
    public boolean Modificar(Proyecto pro){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String id, nom, des, ent, tid, cos,query;
        System.out.println("preparando para insertar");
        id = pro.getIdproyecto();
        nom = pro.getNombreproyecto();
        des = pro.getDescripcion();
        ent = pro.getEntidad();
        tid = pro.getTiempoDuración();
        cos = pro.getCosto();
        query = "update "+
                p.prop("pro.tabla")+" set "+
                p.prop("pro.campo2")+" = '"+nom+"', "+
                p.prop("pro.campo3")+" = '"+des+"', "+
                p.prop("pro.campo4")+" = '"+tid+"', "+
                p.prop("pro.campo5")+" = '"+cos+"' where "+
                p.prop("pro.llave")+" = '"+id+"'";
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
    
    public boolean Eliminar(String idproyecto){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String query;
        query = "delete from "+
                p.prop("con.tabla")+" where "+
                p.prop("con.llave")+" = '"+idproyecto+"'";

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
    
     public Proyecto Consultar(String idproyecto){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        Proyecto resul;
        String query;
        
        query = "select * from "+
                p.prop("con.tabla")+" where "+
                p.prop("con.llave")+" = '"+idproyecto+"'";

        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(query);
            
            if(rs.next()){
                resul = new Proyecto();
                resul.setIdproyecto(rs.getString(1));
                resul.setNombreproyecto(rs.getString(2));
                resul.setDescripcion(rs.getString(3));
                resul.setEntidad(rs.getString(4));
                resul.setTiempoDuración(rs.getString(5));
                resul.setCosto(rs.getString(6));
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
    
    public int Verificar(String idproyecto){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        int existe;
        String query;
        
        query = "select * from "+
                p.prop("con.tabla")+" where "+
                p.prop("con.llave")+" = '"+idproyecto+"'";

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
        System.out.println("Existe: " + idproyecto);
        System.out.println("Existe: " + existe);
        return existe;
    }
     
    
}
