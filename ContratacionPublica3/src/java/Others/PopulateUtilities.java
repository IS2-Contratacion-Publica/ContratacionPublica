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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Conexion cx = new Conexion();
        ResultSet rs;
        List<SelectItem> resul = new ArrayList<>();
        String orden;
        
        orden = "select " + value + 
                ", " + label + 
                " from " + tabla;

        try {
            rs = cx.Ejecutar(orden);
            resul = RS2SelectItemsList2(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
            Logger.getLogger(PopulateUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cx.Cerrar();
        }
        
        return resul;
    }
    
    public List<SelectItem> getListSelectItem(String tabla, String value_label) {
        Conexion cx = new Conexion();
        ResultSet rs;
        List<SelectItem> resul = new ArrayList<>();
        String orden;
        
        orden = "select " + value_label + 
                " from " + tabla;

        try {
            rs = cx.Ejecutar(orden);
            resul = RS2SelectItemsList1(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
            Logger.getLogger(PopulateUtilities.class.getName()).log(Level.SEVERE, null, ex);
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
        Conexion cx = new Conexion();
        ResultSet rs;
        Map map = new LinkedHashMap();
        String orden;
        
        orden = "select " + key + 
                ", " + value + 
                " from " + tabla;
        
        
        try {
            rs = cx.Ejecutar(orden);
            map = RS2Map2(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PopulateUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cx.Cerrar();
        }
        return map;
    }
    
    public Map getMap(String tabla, String key_value) {
        Conexion cx = new Conexion();
        ResultSet rs;
        Map map = new LinkedHashMap();
        String orden;
        
        orden = "select " + key_value + 
                " from " + tabla;
        
        
        try {
            rs = cx.Ejecutar(orden);
            map = RS2Map1(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PopulateUtilities.class.getName()).log(Level.SEVERE, null, ex);
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
            resul = RS2SelectItemsList2(rs);
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            
            resul = null;
            Logger.getLogger(PopulateUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cx.Cerrar();
        }
        return resul;
    }
    
    public List<SelectItem> getListSelectItem(String tabla, String value_label, int estado, String campoEstado) {
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
            
            resul = RS2SelectItemsList1(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            resul = null;
            Logger.getLogger(PopulateUtilities.class.getName()).log(Level.SEVERE, null, ex);
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
        Conexion cx = new Conexion();
        ResultSet rs;
        Map map = new LinkedHashMap();
        String orden;
        
        orden = "select " + key + 
                ", " + value + 
                " from " + tabla +
                " where " + campoEstado +
                " = " + estado;
        
        try {
            rs = cx.Ejecutar(orden);
            map = RS2Map2(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PopulateUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cx.Cerrar();
        }
        return map;
    }
    
    public Map getMap(String tabla, String key_value, int estado, String campoEstado) {
        Conexion cx = new Conexion();
        ResultSet rs;
        Map map = new LinkedHashMap();
        String orden;
        
        orden = "select " + key_value + 
                " from " + tabla +
                " where " + campoEstado +
                " = " + estado;
        
        
        try {
            rs = cx.Ejecutar(orden);
            map = RS2Map1(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PopulateUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cx.Cerrar();
        }
        return map;
    }
    
    private Map RS2Map1(ResultSet rs) throws SQLException{
        LinkedHashMap map = new LinkedHashMap();
        while (rs.next()){
            map.put(rs.getString(1), rs.getString(1));
        }
        return map;
    }
    private Map RS2Map2(ResultSet rs) throws SQLException{
        LinkedHashMap map = new LinkedHashMap();
        while (rs.next()){
            map.put(rs.getString(1), rs.getString(2));
        }
        return map;
    }
    private List<SelectItem> RS2SelectItemsList1(ResultSet rs) throws SQLException{
        List<SelectItem> resul = new ArrayList<>();
        while (rs.next()){
            resul.add(new SelectItem(rs.getString(1), rs.getString(1)));
        }
        return resul;
    }
    private List<SelectItem> RS2SelectItemsList2(ResultSet rs) throws SQLException{
        List<SelectItem> resul = new ArrayList<>();
        while (rs.next()){
            resul.add(new SelectItem(rs.getString(1), rs.getString(2)));
        }
        return resul;
    }
}
