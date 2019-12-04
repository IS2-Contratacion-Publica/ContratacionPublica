/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Fiscalizador;
import Others.Conexion;
import Others.PopulateUtilities;
import Others.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

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
    
    /*
     *Ingresa la informacion de un nuevo contratista a la base de datos
    */
    public boolean Crear(Fiscalizador fis){
        Properties pro =  new Properties();
        Conexion cx = new Conexion();
        String cod, nom, tel, cel, cor, orden, ced, nac;
        char gen;
        
        ced = fis.getCedula(); 
        cod = fis.getCodigo();
        nom = fis.getNombre();
        gen = fis.getGenero();
        nac = fis.getFechaNac();
        cel = fis.getCelular();
        tel = fis.getTelefono();
        cor = fis.getCorreo();
        orden = "insert into "+
                pro.prop("fis.tabla")+" ("+
                pro.prop("fis.llave")+", "+
                pro.prop("fis.campo1")+", "+
                pro.prop("fis.campo2")+", "+
                pro.prop("fis.campo3")+", "+
                pro.prop("fis.campo4")+", "+
                pro.prop("fis.campo5")+", "+
                pro.prop("fis.campo6")+", "+
                pro.prop("fis.campo7")+", "+
                pro.prop("fis.campo8")+") "
                + "values ('"+
                ced+"','"+
                cod+"', '"+
                nom+"', "+"TO_DATE('"+
                nac+"', 'YYYY/MM/DD'), '"+
                gen+"', '"+
                tel+"', '"+
                cel+"', '"+
                cor+"', "+
                1+")";
        
        
        
        try {
            cx.Ejecutar(orden);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiscalizadoresMD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    /*
     *Modifica la informacion de un contratista en la base de datos
    */
    public boolean Modificar(Fiscalizador fis){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        String cod, nom, tel, cel, cor, orden, ced, nac;
        char gen;
        
        ced = fis.getCedula();
        cod = fis.getCodigo();
        nom = fis.getNombre();
        tel = fis.getTelefono();
        nac = fis.getFechaNac();
        cel = fis.getCelular();
        cor = fis.getCorreo();
        gen = fis.getGenero();
        
        orden = "update "+
                p.prop("fis.tabla")+" set "+
                p.prop("fis.campo1")+" = '"+cod+"', "+
                p.prop("fis.campo2")+" = '"+nom+"', "+
                p.prop("fis.campo3")+" = "+"TO_DATE('"+nac+"', 'YYYY/MM/DD'), "+
                p.prop("fis.campo4")+" = '"+gen+"', "+
                p.prop("fis.campo5")+" = '"+tel+"', "+
                p.prop("fis.campo6")+" = '"+cel+"', "+
                p.prop("fis.campo7")+" = '"+cor+"', "+
                p.prop("fis.campo8")+" = "+1+" where "+
                p.prop("fis.llave")+" = '"+ced+"'";
        
        try {
            cx.Ejecutar(orden);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiscalizadoresMD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /*
     *Elimina la informacion de un contratista en la base de datos
    */
    public boolean Eliminar(String cedula){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        String orden;
        orden = "update "+
                p.prop("fis.tabla")+" set "+
                p.prop("fis.campo8")+" = "+0+" where "+
                p.prop("fis.llave")+" = '"+cedula+"'";

        try {
            cx.Ejecutar(orden);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiscalizadoresMD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /*
     *Consulta la informacion de un contratista en la base de datos
    */
    public Fiscalizador Consultar(String cedula){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Fiscalizador res;
        String orden;
        
        orden = "select * from "+
                p.prop("fis.tabla")+" where "+
                p.prop("fis.llave")+" = '"+cedula+"' and " +
                p.prop("fis.campo8")+" = "+1;

        try {
            rs = cx.Ejecutar(orden);
            
            if(rs.next()){
                res = new Fiscalizador();
                res.setCedula(rs.getString(1));
                res.setCodigo(rs.getString(2));
                res.setNombre(rs.getString(3));
                res.setFechaNac(rs.getString(4));
                res.setGenero(rs.getString(5).charAt(0));
                res.setTelefono(rs.getString(6));
                res.setCelular(rs.getString(7));
                res.setCorreo(rs.getString(8));
            }
            else{
                res = null;
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            res = null;
            Logger.getLogger(FiscalizadoresMD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    /*
     *Consulta la informacion de los contratistas en la base de datos
    */
    public LinkedList<Fiscalizador> ConsultaGeneral(){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        LinkedList<Fiscalizador> resul = new LinkedList<Fiscalizador>();
        Fiscalizador fis;
        String orden;
        
        orden = "select * from "+
                p.prop("fis.tabla")+" where "+
                p.prop("fis.campo8")+" = "+1;

        try {
            rs = cx.Ejecutar(orden);
            
            while (rs.next()){
                fis = new Fiscalizador();
                fis.setCedula(rs.getString(1));
                fis.setCodigo(rs.getString(2));
                fis.setNombre(rs.getString(3));
                fis.setFechaNac(rs.getString(4));
                fis.setGenero(rs.getString(5).charAt(0));
                fis.setTelefono(rs.getString(6));
                fis.setCelular(rs.getString(7));
                fis.setCorreo(rs.getString(8));
                resul.add(fis);
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
            Logger.getLogger(FiscalizadoresMD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resul;
    }
    
     public ArrayList ConsultaParametros(Fiscalizador fisParam){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Fiscalizador fisc = null;
        ArrayList resul = new ArrayList();
        String orden;
        
        orden = "select * from "+
                p.prop("fis.tabla")+" where "+
                p.prop("fis.campo8")+" = "+
                1+" and ";
        
        if (!fisParam.getCedula().isEmpty()) {
            orden += p.prop("fis.llave")+" = '"+fisParam.getCedula()+"' and ";
        }
        if (!fisParam.getCodigo().isEmpty()) {
            orden += p.prop("fis.campo1")+" = '"+fisParam.getCodigo()+"' and ";
        }
        if (!fisParam.getNombre().isEmpty()) {
            orden += p.prop("fis.campo2")+" = '"+fisParam.getNombre()+"' and ";
        }
        if (!fisParam.getFechaNac().isEmpty()) {
            orden += p.prop("fis.campo3")+" = "+"TO_DATE('"+fisParam.getFechaNac()+"', 'YYYY/MM/DD') and ";
        }
        if (fisParam.getGenero() != 'x') {
            orden += p.prop("fis.campo4")+" = '"+fisParam.getGenero()+"' and ";
        }
        if (!fisParam.getTelefono().isEmpty()) {
            orden += p.prop("fis.campo5")+" = '"+fisParam.getTelefono()+"' and ";
        }
        if (!fisParam.getCelular().isEmpty()) {
            orden += p.prop("fis.campo6")+" = '"+fisParam.getCelular()+"' and ";
        }
        if (!fisParam.getCorreo().isEmpty()) {
            orden += p.prop("fis.campo7")+" = '"+fisParam.getCorreo()+"' and ";
        }
        
        orden = orden.substring(0, orden.length()-4);

        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                fisc = new Fiscalizador();
                fisc.setCedula(rs.getString(1));
                fisc.setCodigo(rs.getString(2));
                fisc.setNombre(rs.getString(3));
                fisc.setFechaNac(rs.getString(4));
                fisc.setGenero(rs.getString(5).charAt(0));
                fisc.setTelefono(rs.getString(6));
                fisc.setCelular(rs.getString(7));
                fisc.setCorreo(rs.getString(8));
                resul.add(fisc);
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
            Logger.getLogger(FiscalizadoresMD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resul;
    }
    
    /*
     *Verifica si un contratista exista en la base de datos
    */
    public int Verificar(String cedula){
        Properties pr =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        int hay;
        String query;
        
        query = "select * from "+
                pr.prop("fis.tabla")+" where "+
                pr.prop("fis.llave")+" = '"+cedula+"'";

        try {
            rs = cx.Ejecutar(query);
            if(rs.next()){
                hay = 1;
            }
            else{
                hay = 0;
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            hay = -1;
            Logger.getLogger(FiscalizadoresMD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hay;
    }
    
    public List<SelectItem> getGeneros() {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        List<SelectItem> res = new ArrayList<>();
        String query;
        
        query = "select * from "+
                p.prop("gen.tabla");

        try {
            rs = cx.Ejecutar(query);
            while (rs.next()){
                res.add(new SelectItem(rs.getString(1), rs.getString(2)));
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            res = null;
            Logger.getLogger(FiscalizadoresMD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public Map ConsultaGeneralCombo(){
        Properties pr = new Properties();
        PopulateUtilities pu = new PopulateUtilities();
        return pu.getMap(pr.prop("fis.tabla"), pr.prop("fis.llave"));
    }
}
