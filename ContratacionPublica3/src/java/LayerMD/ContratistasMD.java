/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Contratista;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Dell
 */
@ManagedBean(value = "constratistasMD")
@Dependent
public class ContratistasMD {

    /**
     * Creates a new instance of ConstratistasMD
     */
    public ContratistasMD() {
    }
    
    
    
    /*
     *Ingresa la informacion de un nuevo contratista a la base de datos
    */
    public boolean Crear(Contratista con){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        String cod, nom, tel, cel, cor, orden, ced, nac;
        char gen;
        
        ced = con.getCedula();
        cod = con.getCodigo();
        nom = con.getNombre();
        nac = con.getFechaNac();
        gen = con.getGenero();
        tel = con.getTelefono();
        cel = con.getCelular();
        cor = con.getCorreo();
        orden = "insert into "+
                p.prop("con.tabla")+" ("+
                p.prop("con.llave")+", "+
                p.prop("con.campo1")+", "+
                p.prop("con.campo2")+", "+
                p.prop("con.campo3")+", "+
                p.prop("con.campo4")+", "+
                p.prop("con.campo5")+", "+
                p.prop("con.campo6")+", "+
                p.prop("con.campo7")+", "+
                p.prop("con.campo8")+") "
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
    public boolean Modificar(Contratista con){
        Properties p =  new Properties();
        Conexion cx = new Conexion();

        String cod, nom, tel, cel, cor, orden, ced, nac;
        char gen;
        System.out.println("preparando para insertar");
        ced = con.getCedula();
        cod = con.getCodigo();
        nom = con.getNombre();
        nac = con.getFechaNac();
        gen = con.getGenero();
        tel = con.getTelefono();
        cel = con.getCelular();
        cor = con.getCorreo();
        orden = "update "+
                p.prop("con.tabla")+" set "+
                p.prop("con.campo1")+" = '"+cod+"', "+
                p.prop("con.campo2")+" = '"+nom+"', "+
                p.prop("con.campo3")+" = "+"TO_DATE('"+nac+"', 'YYYY/MM/DD'), "+
                p.prop("con.campo4")+" = '"+gen+"', "+
                p.prop("con.campo5")+" = '"+tel+"', "+
                p.prop("con.campo6")+" = '"+cel+"', "+
                p.prop("con.campo7")+" = '"+cor+"', "+
                p.prop("con.campo8")+" = "+1+" where "+
                p.prop("con.llave")+" = '"+ced+"'";
        
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
                p.prop("con.tabla")+" set "+
                p.prop("con.campo8")+" = "+0+" where "+
                p.prop("con.llave")+" = '"+cedula+"'";

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
    public Contratista Consultar(String cedula){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Contratista resul;
        String orden;
        
        orden = "select * from "+
                p.prop("con.tabla")+" where "+
                p.prop("con.llave")+" = '"+cedula+"' and " +
                p.prop("con.campo8")+" = "+1;

        try {
            rs = cx.Ejecutar(orden);
            if(rs.next()){
                resul = new Contratista();
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
     *Consulta la informacion de uvarios contratistas en la base de datos
    */
    public ArrayList ConsultaGeneral(){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Contratista con = null;
        ArrayList resul = new ArrayList();
        String orden;
        
        orden = "select * from "+
                p.prop("con.tabla")+" where "+
                p.prop("con.campo8")+" = "+1;

        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                con = new Contratista();
                con.setCedula(rs.getString(1));
                con.setCodigo(rs.getString(2));
                con.setNombre(rs.getString(3));
                con.setFechaNac(rs.getString(4));
                con.setGenero(rs.getString(5).charAt(0));
                con.setTelefono(rs.getString(6));
                con.setCelular(rs.getString(7));
                con.setCorreo(rs.getString(8));
                resul.add(con);
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        }
        return resul;
    }
    
    
    public ArrayList ConsultaParametros(Contratista conParam){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Contratista con = null;
        ArrayList resul = new ArrayList();
        String orden;
        
        orden = "select * from "+
                p.prop("con.tabla")+" where "+
                p.prop("con.campo8")+" = "+
                1+" and ";
        
        if (!conParam.getCedula().isEmpty()) {
            orden += p.prop("con.llave")+" = '"+conParam.getCedula()+"' and ";
        }
        if (!conParam.getCodigo().isEmpty()) {
            orden += p.prop("con.campo1")+" = '"+conParam.getCodigo()+"' and ";
        }
        if (!conParam.getNombre().isEmpty()) {
            orden += p.prop("con.campo2")+" = '"+conParam.getNombre()+"' and ";
        }
        if (!conParam.getFechaNac().isEmpty()) {
            orden += p.prop("con.campo3")+" = "+"TO_DATE('"+conParam.getFechaNac()+"', 'YYYY/MM/DD') and ";
        }
        if (conParam.getGenero() != 'x') {
            orden += p.prop("con.campo4")+" = '"+conParam.getGenero()+"' and ";
        }
        if (!conParam.getTelefono().isEmpty()) {
            orden += p.prop("con.campo5")+" = '"+conParam.getTelefono()+"' and ";
        }
        if (!conParam.getCelular().isEmpty()) {
            orden += p.prop("con.campo6")+" = '"+conParam.getCelular()+"' and ";
        }
        if (!conParam.getCorreo().isEmpty()) {
            orden += p.prop("con.campo7")+" = '"+conParam.getCorreo()+"' and ";
        }
        
        orden = orden.substring(0, orden.length()-4);

        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                con = new Contratista();
                con.setCedula(rs.getString(1));
                con.setCodigo(rs.getString(2));
                con.setNombre(rs.getString(3));
                con.setFechaNac(rs.getString(4));
                con.setGenero(rs.getString(5).charAt(0));
                con.setTelefono(rs.getString(6));
                con.setCelular(rs.getString(7));
                con.setCorreo(rs.getString(8));
                resul.add(con);
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
                p.prop("con.tabla")+" where "+
                p.prop("con.llave")+" = '"+cedula+"'";

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
  
    
}
