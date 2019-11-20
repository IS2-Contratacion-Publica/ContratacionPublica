/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Others;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

/**
 *
 * @author Dell
 */
public class PopulateUtilities {

    public PopulateUtilities() {
    }
    
    /*
     * label es lo que se va a mostrar en pantalla
     * value es lo que va a retornar el combox
    */
    public List<SelectItem> getListSelectItem(String tabla, String value, String label) {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        List<SelectItem> resul = new ArrayList<>();
        String orden;
        
        orden = "select " + value + 
                ", " + label + 
                " from " + tabla;

        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                resul.add(new SelectItem(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        } finally {
            cx.Cerrar();
        }
        return resul;
    }
    
    public List<SelectItem> getListSelectItem(String tabla, String value_label) {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        List<SelectItem> resul = new ArrayList<>();
        String orden;
        
        orden = "select " + value_label + 
                " from " + tabla;

        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                resul.add(new SelectItem(rs.getString(1), rs.getString(1)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        } finally {
            cx.Cerrar();
        }
        return resul;
    }
    
    /*
     * key es lo que se va a mostrar en pantalla
     * value es lo que va a retornar el combox
    */
    public Map getMap(String tabla, String key, String value) {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        LinkedHashMap map = new LinkedHashMap();
        String orden;
        
        orden = "select " + key + 
                ", " + value + 
                " from " + tabla;
        
        
        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                map.put(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cx.Cerrar();
        }
        return map;
    }
    
    public Map getMap(String tabla, String key_value) {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        LinkedHashMap map = new LinkedHashMap();
        String orden;
        
        orden = "select " + key_value + 
                " from " + tabla;
        
        
        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                map.put(rs.getString(1), rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cx.Cerrar();
        }
        return map;
    }
    
    //---------------------------------------------------------------------------------------------
    
    /*
     * label es lo que se va a mostrar en pantalla
     * value es lo que va a retornar el combox
    */
    public List<SelectItem> getListSelectItem(String tabla, String value, String label, int estado, String campoEstado) {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        List<SelectItem> resul = new ArrayList<>();
        String orden;
        
        orden = "select " + value + 
                ", " + label + 
                " from " + tabla +
                " where " + campoEstado +
                " = " + estado;

        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                resul.add(new SelectItem(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        } finally {
            cx.Cerrar();
        }
        return resul;
    }
    
    public List<SelectItem> getListSelectItem(String tabla, String value_label, int estado, String campoEstado) {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        List<SelectItem> resul = new ArrayList<>();
        String orden;
        
        orden = "select " + value_label + 
                " from " + tabla +
                " where " + campoEstado +
                " = " + estado;

        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                resul.add(new SelectItem(rs.getString(1), rs.getString(1)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        } finally {
            cx.Cerrar();
        }
        return resul;
    }
    
    /*
     * key es lo que se va a mostrar en pantalla
     * value es lo que va a retornar el combox
    */
    public Map getMap(String tabla, String key, String value, int estado, String campoEstado) {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        LinkedHashMap map = new LinkedHashMap();
        String orden;
        
        orden = "select " + key + 
                ", " + value + 
                " from " + tabla +
                " where " + campoEstado +
                " = " + estado;
        
        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                map.put(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cx.Cerrar();
        }
        return map;
    }
    
    public Map getMap(String tabla, String key_value, int estado, String campoEstado) {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        LinkedHashMap map = new LinkedHashMap();
        String orden;
        
        orden = "select " + key_value + 
                " from " + tabla +
                " where " + campoEstado +
                " = " + estado;
        
        
        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                map.put(rs.getString(1), rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cx.Cerrar();
        }
        return map;
    }
}
