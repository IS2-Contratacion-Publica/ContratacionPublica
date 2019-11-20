/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Oferta;
import EntityClasses.Proyecto;
import Others.Conexion;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

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
        String idof, idpro, cos, ubi,query,conced,estado;
        
        conced=oft.getConcedula();
        idpro = oft.getIdproyecto();
        idof = oft.getIdoferta();
        cos = oft.getCostoofertado();
        ubi = oft.getUbicacion();
        estado="1";
        
        query = "insert into "+
                p.prop("oft.tabla")+" ("+
                p.prop("oft.campo1")+", "+
                p.prop("oft.campo2")+", "+
                p.prop("oft.campo3")+", "+
                p.prop("oft.campo4")+", "+
                p.prop("oft.campo5")+", "+
                p.prop("oft.campo6")+") "
                + "values ('"+conced+"','"+
                idpro+"', '"+
                idof+"', '"+
                cos+"', '"+
                ubi+"', '"+
                estado+"')"; 
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
        String idof, idpro, cos, ubi,query,conced;
        
        conced=oft.getConcedula();
        idpro = oft.getIdproyecto();
        idof = oft.getIdoferta();
        cos = oft.getCostoofertado();
        ubi = oft.getUbicacion();

        query = "update "+
                p.prop("oft.tabla")+" set "+
                p.prop("oft.campo1")+" = '"+conced+"', "+
                p.prop("oft.campo2")+" = '"+idpro+"', "+
                p.prop("oft.campo4")+" = '"+cos+"', "+
                p.prop("oft.campo5")+" = '"+ubi+"' where "+
                p.prop("oft.campo3")+" = '"+idof+"'";
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
        Conexion cx = new Conexion();
        String orden;
        orden = "update "+
                p.prop("oft.tabla")+" set "+
                p.prop("oft.campo6")+ " = 0 where " +
                p.prop("oft.campo3")+" = '"+idoferta+"'";

        try {
            cx.Ejecutar(orden);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
        public LinkedList consultaGeneral ()
    {
        Connection conn;
        Statement s;
        ResultSet rs;
        Properties p =  new Properties();
        
        Oferta result;
        String query;
        LinkedList<Oferta> registros = new LinkedList<Oferta>();
        
        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            
            query = "Select * from "+ p.prop("oft.tabla") +" where "+p.prop("oft.campo6")+ "=1";
            
            rs = s.executeQuery(query);
            while(rs.next()) {
                result =new Oferta();
                result.setConcedula(rs.getString(1));
                result.setIdproyecto(rs.getString(2));
                result.setIdoferta(rs.getString(3));
                result.setCostoofertado(rs.getString(4));
                result.setUbicacion(rs.getString(5));
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
        }
        return registros;
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
                p.prop("oft.campo3")+" = '"+idoferta+"'";

        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(query);
            
            if(rs.next()){
                resul = new Oferta();
                resul.setConcedula(rs.getString(1));
                resul.setIdoferta(rs.getString(3));
                resul.setIdproyecto(rs.getString(2));
                resul.setCostoofertado(rs.getString(4));
                resul.setUbicacion(rs.getString(5));
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
                p.prop("oft.campo3")+" = '"+idoferta+"'";

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
