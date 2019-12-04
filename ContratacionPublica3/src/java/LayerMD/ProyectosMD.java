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
import java.util.ArrayList;
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
        Conexion cx;
        String id, nom, des, ent, tid, cos,query,tip,est="1";

        id = pro.getIdproyecto();
        nom = pro.getNombreproyecto();
        des = pro.getDescripcion();
        tip=pro.getTipo();
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
                p.prop("pro.campo6")+", "+
                p.prop("pro.campo7")+") "+
                "values ('"+id+"','"+
                nom+"', '"+
                des+"', '"+
                tip+"', '"+
                ent+"',"+
                tid+", "+
                cos+", "+
                est+")";
        try {
            cx = new Conexion();
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
        Conexion cx;
        String id, nom, des, ent, tid, cos,query,tip;
        System.out.println("preparando para insertar");
        id = pro.getIdproyecto();
        nom = pro.getNombreproyecto();
        des = pro.getDescripcion();
        tip=pro.getTipo();
        ent = pro.getEntidad();
        tid = pro.getTiempoDuración();
        cos = pro.getCosto();

        
        query = "update "+
                p.prop("pro.tabla")+" set "+
                p.prop("pro.campo1")+" = '"+nom+"', "+
                p.prop("pro.campo2")+" = '"+des+"', "+
                p.prop("pro.campo3")+" = '"+tip+"', "+
                p.prop("pro.campo4")+" = '"+ent+"', "+
                p.prop("pro.campo5")+" = "+tid+", "+
                p.prop("pro.campo6")+" = "+cos+" where "+
                p.prop("pro.llave")+" = '"+id+"'";
        System.out.println(query);
        try {
            cx = new Conexion();
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

        query = "update "+
                p.prop("pro.tabla")+" set "+
                p.prop("pro.campo7")+ " = 0 where " +
                p.prop("pro.llave")+" = '"+idproyecto+"'";

        try {
            cx.Ejecutar(query);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public LinkedList consultaGeneral ()
    {
        Conexion cx = new Conexion();
        ResultSet rs;
        Properties p =  new Properties();
        
        Proyecto result;
        String query;
        LinkedList<Proyecto> registros = new LinkedList<Proyecto>();
        
        try {
            
            query = "Select * from "+ p.prop("pro.tabla") +" where "+p.prop("pro.campo7")+ "=1";
            
            rs = cx.Ejecutar(query);
            while(rs.next()) {
                result =new Proyecto();
                result.setIdproyecto(rs.getString(1));
                result.setNombreproyecto(rs.getString(2));
                result.setDescripcion(rs.getString(3));
                result.setTipo(rs.getString(4));
                result.setEntidad(rs.getString(5));
                result.setTiempoDuración(rs.getString(6));
                result.setCosto(rs.getString(7));
                /*
                registro[0]= rs.getString(1);
                registro[1]= rs.getString(2);
                registro[2]= rs.getString(3);
                registro[3]= rs.getString(4);
                registro[4]= rs.getString(5);
                registro[5]= rs.getString(6);
                registro[6]= rs.getString(7);*/
                
                
                                
                registros.add(result);
                System.out.println(registros);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cx.Cerrar();
        }
        return registros;
    }
     public Proyecto Consultar(String idproyecto){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Proyecto resul;
        String query;
        
        //query = "select * from PROYECTOS where proESTADO != 0 AND PROCODIGO = '3'";
        query = "select * from "+
                p.prop("pro.tabla")+ " where " +
                p.prop("pro.campo7")+ " != 0 and "+
                p.prop("pro.llave")+ 
                " = '"+idproyecto+"'";

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
        Conexion cx;
        ResultSet rs;
        int existe;
        String query;
        
        query = "select * from "+
                p.prop("pro.tabla")+" where "+
                p.prop("pro.llave")+" = '"+idproyecto+"'";


        try {
            cx = new Conexion();
            rs = cx.Ejecutar(query);
            if(rs.next()){
                existe = 1;
            }
            else{
                existe = 0;
            }
            cx.Cerrar();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            existe = -1;
        }
        System.out.println("Existe: " + idproyecto);
        System.out.println("Existe: " + existe);
        return existe;
    }
    public ArrayList Consultap(Proyecto proy){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Proyecto pro=null;
        ArrayList resul = new ArrayList();
        String query;
        
        query = "select * from "+
                p.prop("pro.tabla")+" where "+
                p.prop("pro.campo7")+" = "+
                1+" and ";
        
        if (!proy.getIdproyecto().isEmpty()) {
            query += p.prop("pro.llave")+" = '"+proy.getIdproyecto()+"' and ";
        }
        if (!proy.getNombreproyecto().isEmpty()) {
            query += p.prop("pro.campo1")+" = '"+proy.getNombreproyecto()+"' and ";
        }
        if (!proy.getDescripcion().isEmpty()) {
            query += p.prop("pro.campo2")+" = '"+proy.getDescripcion()+"' and ";
        }
        if (!proy.getTipo().isEmpty()) {
            query += p.prop("pro.campo3")+" = '"+proy.getTipo()+"' and ";
        }
        if (!proy.getEntidad().isEmpty()) {
            query += p.prop("pro.campo4")+" = '"+proy.getEntidad()+"' and ";
        }
        if (!proy.getTiempoDuración().isEmpty()) {
            query += p.prop("pro.campo5")+" = '"+proy.getTiempoDuración()+"' and ";
        }
        if (!proy.getCosto().isEmpty()) {
            query += p.prop("pro.campo6")+" = '"+proy.getCosto()+"' and ";
        }   
              
        query = query.substring(0, query.length()-4);
        System.out.println(query);
        
        try {
            rs = cx.Ejecutar(query);
            while (rs.next()){
                pro = new Proyecto();
                pro.setIdproyecto(rs.getString(1));
                pro.setNombreproyecto(rs.getString(2));
                pro.setDescripcion(rs.getString(3));
                pro.setTipo(rs.getString(4));
                pro.setEntidad(rs.getString(5));
                pro.setTiempoDuración(rs.getString(6));
                pro.setCosto(rs.getString(7));
                resul.add(pro);
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        }
        return resul;
    }
    
    public Map ConsultaGeneralCombo(){
        Properties p = new Properties();
        PopulateUtilities pu = new PopulateUtilities();
        return pu.getMap(p.prop("pro.tabla"), p.prop("pro.llave"), 1, p.prop("pro.campo7"));
    }
    
}
