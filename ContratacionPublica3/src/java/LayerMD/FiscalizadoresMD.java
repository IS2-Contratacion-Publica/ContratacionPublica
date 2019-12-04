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
        Properties p =  new Properties();
        Conexion cx = new Conexion();
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
                p.prop("fis.campo7")+", "+
                p.prop("fis.campo8")+") "
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
                p.prop("fis.campo7")+" = '"+cor+"', "+
                p.prop("fis.campo8")+" = "+1+" where "+
                p.prop("fis.llave")+" = '"+ced+"'";
        
        try {
            cx.Ejecutar(orden);
            cx.Cerrar();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        Fiscalizador resul;
        String orden;
        
        orden = "select * from "+
                p.prop("fis.tabla")+" where "+
                p.prop("fis.llave")+" = '"+cedula+"' and " +
                p.prop("fis.campo8")+" = "+1;

        try {
            rs = cx.Ejecutar(orden);
            
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
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        }
        
        return resul;
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
        }
        return resul;
    }
    
     public ArrayList ConsultaParametros(Fiscalizador fisParam){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Fiscalizador fis = null;
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
        }
        return resul;
    }
    
    /*
     *Verifica si un contratista exista en la base de datos
    */
    public int Verificar(String cedula){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        int existe;
        String orden;
        
        orden = "select * from "+
                p.prop("fis.tabla")+" where "+
                p.prop("fis.llave")+" = '"+cedula+"'";

        try {
            rs = cx.Ejecutar(orden);
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

        return existe;
    }
    
    public List<SelectItem> getGeneros() {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        List<SelectItem> resul = new ArrayList<>();
        String orden;
        
        orden = "select * from "+
                p.prop("gen.tabla");

        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                resul.add(new SelectItem(rs.getString(1), rs.getString(2)));
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
        return pu.getMap(p.prop("fis.tabla"), p.prop("fis.llave"));
    }
}
