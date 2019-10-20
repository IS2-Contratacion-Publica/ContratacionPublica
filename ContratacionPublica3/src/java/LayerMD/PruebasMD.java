/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Prueba;
import Others.Conexion;
import Others.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Santiago
 */
@ManagedBean(value = "pruebasMD")
@Dependent
public class PruebasMD {

    /**
     * Creates a new instance of PruebasMD
     */
    public PruebasMD() {
    }
    
    
    public Connection GenerarConexion() throws SQLException{
        Properties p =  new Properties();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PruebasMD.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean Crear(Prueba pru){
        Properties p =  new Properties();

        String procod,prucod,descrip,fecha,query;

        Conexion cx = new Conexion();
        procod = pru.getProCodigo();
        prucod = pru.getPruCodigo();
        descrip = pru.getDescripcion();
        fecha = pru.getFechaRealizacion();

        query = "insert into "+
                p.prop("pru.tabla")+" ("+
                p.prop("pru.c1")+", "+
                p.prop("pru.c2")+", "+
                p.prop("pru.c3")+", "+
                p.prop("pru.c4")+", "+
                p.prop("pru.c5")+") "
                + "values ('"+procod+"','"+
                prucod+"', '"+
                descrip+"', "+"TO_DATE('"+
                fecha+"', 'YYYY/MM/DD'),1)";
    
        
        
        try {
            cx.Ejecutar(query);/*
            conn = GenerarConexion();
            System.out.println(conn);
            s = conn.createStatement();
            s.executeQuery(query);
            conn.close();*/
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return false;
        }
        
    }
    
    public boolean Modificar(Prueba pru){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String procod,prucod,descrip,fecha,query;

        
        procod = pru.getProCodigo();
        prucod = pru.getPruCodigo();
        descrip = pru.getDescripcion();
        fecha = pru.getFechaRealizacion();
        
        query = "update "+
                p.prop("pru.tabla")+" set "+
                p.prop("pru.c1")+" = '"+procod+"', "+
                p.prop("pru.c3")+" = '"+descrip+"', "+
                p.prop("pru.c4")+" = "+"TO_DATE('"+fecha+"', 'YYYY/MM/DD') "
                +" where "+
                p.prop("pru.c2")+" = '"+prucod+"'";
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
    
    public boolean Eliminar(String prucodigo){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String query;
        query = "update "+
                p.prop("pru.tabla")+" set "+
                p.prop("pru.c5")+" = '"+0+"' "
                +" where "+
                p.prop("pru.c2")+" = '"+prucodigo+"'";

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
    
    public ArrayList Consultap(Prueba prup){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Prueba pru = null;
        ArrayList resul = new ArrayList();
        String query;
        
        query = "select * from "+
                p.prop("pru.tabla")+" where "+
                p.prop("pru.c5")+" = "+
                1+" and ";
        
        if (!prup.getProCodigo().isEmpty()) {
            query += p.prop("pru.c1")+" = '"+prup.getProCodigo()+"' and ";
        }
        if (!prup.getPruCodigo().isEmpty()) {
            query += p.prop("pru.c2")+" = '"+prup.getPruCodigo()+"' and ";
        }
        if (!prup.getDescripcion().isEmpty()) {
            query += p.prop("pru.c3")+" = '"+prup.getDescripcion()+"' and ";
        }
        if (!prup.getFechaRealizacion().isEmpty()) {
            query += p.prop("pru.c4")+" = "+"TO_DATE('"+prup.getFechaRealizacion()+"', 'YYYY/MM/DD') and ";
        }
                
        query = query.substring(0, query.length()-4);

        try {
            rs = cx.Ejecutar(query);
            while (rs.next()){
                pru = new Prueba();
                pru.setProCodigo(rs.getString(1));
                pru.setPruCodigo(rs.getString(2));
                pru.setDescripcion(rs.getString(3));
                pru.setFechaRealizacion(rs.getString(4));
                resul.add(pru);
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        }
        return resul;
    }
    
    public ArrayList<Prueba> Consultag(){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        Prueba resul;
        String query;
        ArrayList<Prueba> listpruebas = new ArrayList<Prueba>();
        
        query = "select * from "+
                p.prop("pru.tabla");

        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(query);
            
            while(rs.next()){
                resul = new Prueba();
                resul.setProCodigo(rs.getString(1));
                resul.setPruCodigo(rs.getString(2));
                resul.setDescripcion(rs.getString(3));
                resul.setFechaRealizacion(rs.getString(4));
                if (rs.getInt(5)==1) {
                    listpruebas.add(resul);
                }

           
            }
            conn.close();
            
        } catch (SQLException ex) {
            resul = null;
        }
        
        return listpruebas;
    }
    
    public int Verificar(String prucodigo){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        int existe;
        String query;
        
        query = "select * from "+
                p.prop("pru.tabla")+" where "+
                p.prop("pru.c2")+" = '"+prucodigo+"'";

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
        System.out.println("Existe: " + prucodigo);
        System.out.println("Existe: " + existe);
        return existe;
    }
  
    
}
