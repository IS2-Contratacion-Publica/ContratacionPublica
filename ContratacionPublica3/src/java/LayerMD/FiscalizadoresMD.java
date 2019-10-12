/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Fiscalizador;
import Others.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "fiscalizadoresMD")
@Dependent
public class FiscalizadoresMD {

    /**
     * Creates a new instance of FiscalizadorMD
     */
    public FiscalizadoresMD() {
    }
    
     public Connection GenerarConexion() throws SQLException{
        Properties p =  new Properties();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FiscalizadoresMD.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean Crear(Fiscalizador fis){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String cod, nom, tel, cel, cor, orden, ced, nac;
        char gen;
        
        ced = fis.getCedula();
        cod = fis.getCodigo();
        nom = fis.getNombre();
        nac = fis.getFechaNac();
        gen = fis.getGenero();
        tel = fis.getTelefono();
        cel = fis.getCelular();
        cor = fis.getCorreo();
        orden = "insert into "+
                p.prop("fis.tabla")+" ("+
                p.prop("fis.llave")+", "+
                p.prop("fis.campo1")+", "+
                p.prop("fis.campo2")+", "+
                p.prop("fis.campo3")+", "+
                p.prop("fis.campo4")+", "+
                p.prop("fis.campo5")+", "+
                p.prop("fis.campo6")+", "+
                p.prop("fis.campo7")+") "
                + "values ('"+ced+"','"+
                cod+"', '"+
                nom+"', "+"TO_DATE('"+
                nac+"', 'YYYY/MM/DD'), '"+
                gen+"', '"+
                tel+"', '"+
                cel+"', '"+
                cor+"')";
        
        
        
        try {
            conn = GenerarConexion();
            System.out.println(conn);
            s = conn.createStatement();
            s.executeQuery(orden);
            conn.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return false;
        }
        
    }
    
    public boolean Modificar(Fiscalizador fis){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String cod, nom, tel, cel, cor, orden, ced, nac;
        char gen;
        System.out.println("preparando para insertar");
        ced = fis.getCedula();
        cod = fis.getCodigo();
        nom = fis.getNombre();
        nac = fis.getFechaNac();
        gen = fis.getGenero();
        tel = fis.getTelefono();
        cel = fis.getCelular();
        cor = fis.getCorreo();
        orden = "update "+
                p.prop("fis.tabla")+" set "+
                p.prop("fis.campo1")+" = '"+cod+"', "+
                p.prop("fis.campo2")+" = '"+nom+"', "+
                p.prop("fis.campo3")+" = "+"TO_DATE('"+nac+"', 'YYYY/MM/DD'), "+
                p.prop("fis.campo4")+" = '"+gen+"', "+
                p.prop("fis.campo5")+" = '"+tel+"', "+
                p.prop("fis.campo6")+" = '"+cel+"', "+
                p.prop("fis.campo7")+" = '"+cor+"' where "+
                p.prop("fis.llave")+" = '"+ced+"'";
        System.out.println(orden);
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
    
    public boolean Eliminar(String cedula){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String orden;
        orden = "delete from "+
                p.prop("fis.tabla")+" where "+
                p.prop("fis.llave")+" = '"+cedula+"'";

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
    
    public Fiscalizador Consultar(String cedula){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        Fiscalizador resul;
        String orden;
        
        orden = "select * from "+
                p.prop("fis.tabla")+" where "+
                p.prop("fis.llave")+" = '"+cedula+"'";

        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(orden);
            
            if(rs.next()){
                resul = new Fiscalizador();
                resul.setCedula(rs.getString(1));
                resul.setCodigo(rs.getString(2));
                resul.setNombre(rs.getString(3));
                resul.setFechaNac(rs.getString(4));
                resul.setGenero(rs.getString(5).charAt(0));
                resul.setTelefono(rs.getString(6));
                resul.setCelular(rs.getString(7));
                resul.setCorreo(rs.getString(8));
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
    
    public int Verificar(String cedula){
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        int existe;
        String orden;
        
        orden = "select * from "+
                p.prop("fis.tabla")+" where "+
                p.prop("fis.llave")+" = '"+cedula+"'";

        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(orden);
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
        System.out.println(orden);
        System.out.println("Existe: " + cedula);
        System.out.println("Existe: " + existe);
        return existe;
    }
}
