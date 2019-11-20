/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Proyecto;
import Others.Conexion;
import Others.PopulateUtilities;
import Others.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;


@ManagedBean(value = "proyectosMD")
@Dependent

public class ProyectosMD {
    public ProyectosMD(){
    }
    
    public boolean Ingresar(Proyecto pro){
        Properties p =  new Properties();
        Connection conn;
        Conexion cx = new Conexion();
        Statement s;
        String id, nom, des, ent, tid, cos,query,tip,est;

        
        id = pro.getIdproyecto();
        nom = pro.getNombreproyecto();
        des = pro.getDescripcion();
        tip=pro.getTipo();
        ent = pro.getEntidad();
        tid = pro.getTiempoDuración();
        cos = pro.getCosto();
        est=pro.getEstado();
        
        query = "insert into "+
                p.prop("pro.tabla")+" ("+
                p.prop("pro.llave")+", "+
                p.prop("pro.campo1")+", "+
                p.prop("pro.campo2")+", "+
                p.prop("pro.campo3")+", "+
                p.prop("pro.campo4")+", "+
                p.prop("pro.campo5")+", "+
                p.prop("pro.campo6")+", "+
                p.prop("pro.campo7")+") "+
                "values ('"+id+"','"+
                nom+"', '"+
                des+"', '"+
                tip+"', '"+
                ent+"', "+
                tid+", "+
                cos+", "+
                est+")";
        try {
            cx.Ejecutar(query);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return false;
        }
    
    }
    
    public boolean Modificar(Proyecto pro){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        String id, nom, des, ent, tid, cos,query,est,tip;
        System.out.println("preparando para insertar");
        id = pro.getIdproyecto();
        nom = pro.getNombreproyecto();
        des = pro.getDescripcion();
        tip=pro.getTipo();
        ent = pro.getEntidad();
        tid = pro.getTiempoDuración();
        cos = pro.getCosto();
        est=pro.getEstado();
        query = "update "+
                p.prop("pro.tabla")+" set "+
                p.prop("pro.campo1")+" = '"+nom+"', "+
                p.prop("pro.campo2")+" = '"+des+"', "+
                p.prop("pro.campo3")+" = '"+tip+"', "+
                p.prop("pro.campo4")+" = '"+ent+"', "+
                p.prop("pro.campo5")+" = '"+tip+"', "+
                p.prop("pro.campo6")+" = '"+cos+"', "+
                p.prop("pro.campo7")+" = '"+est+"' where "+
                p.prop("pro.llave")+" = '"+id+"'";
        System.out.println(query);
        try {
            cx.Ejecutar(query);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    
    public boolean Eliminar(String idproyecto){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        String query;
        query = "delete from "+
                p.prop("con.tabla")+" where "+
                p.prop("con.llave")+" = '"+idproyecto+"'";

        try {
            cx.Ejecutar(query);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
     public Proyecto Consultar(String idproyecto){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Proyecto resul;
        String query;
        
        query = "select * from "+
                p.prop("con.tabla")+" where "+
                p.prop("con.llave")+" = '"+idproyecto+"'";

        try {
            rs = cx.Ejecutar(query);
            
            
            if(rs.next()){
                resul = new Proyecto();
                resul.setIdproyecto(rs.getString(1));
                resul.setNombreproyecto(rs.getString(2));
                resul.setDescripcion(rs.getString(3));
                resul.setTipo(rs.getString(4));
                resul.setEntidad(rs.getString(5));
                resul.setTiempoDuración(rs.getString(6));
                resul.setCosto(rs.getString(7));
                resul.setEstado(rs.getString(8));
            }
            else{
                resul = null;
            }
            cx.Cerrar();
            
        } catch (SQLException ex) {
            resul = null;
        }
        
        return resul;
    }
    
    public int Verificar(String idproyecto){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        int existe;
        String query;
        
        query = "select * from "+
                p.prop("con.tabla")+" where "+
                p.prop("con.llave")+" = '"+idproyecto+"'";

        try {
            rs = cx.Ejecutar(query);
            if(rs.next()){
                existe = 1;
            }
            else{
                existe = 0;
            }
            cx.Cerrar();
            
        } catch (SQLException ex) {
            existe = -1;
        }
        System.out.println(query);
        System.out.println("Existe: " + idproyecto);
        System.out.println("Existe: " + existe);
        return existe;
    }
     
    public Map ConsultaGeneralCombo(){
        Properties p = new Properties();
        PopulateUtilities pu = new PopulateUtilities();
        return pu.getMap(p.prop("pro.tabla"), p.prop("pro.llave"));
    }
}
