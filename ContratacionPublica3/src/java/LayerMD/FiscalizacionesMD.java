/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Fiscalizaciones;
import Others.Conexion;
import Others.Properties;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author USER
 */
public class FiscalizacionesMD {
    
    public String Fiscalizar(Fiscalizaciones fiz){
        Properties p = new Properties();
        Conexion cx = new Conexion();
        String query;
        String resul;
        
        
        query = "insert into "+
                p.prop("fiz.tabla")+" ("+
                p.prop("fiz.campo1")+", "+
                p.prop("fiz.campo2")+", "+
                p.prop("fiz.campo3")+", "+
                p.prop("fiz.campo4")+") "
                + "values ('"+
                fiz.getProcodigo()+"', '"+
                fiz.getFiscedula()+"', "+
                "TO_DATE('"+
                fiz.getFizfecha()+"', 'YYYY/MM/DD'),'"+
                fiz.getFizobservaciones()+"')";
        
        try {
            cx.Ejecutar(query);
            resul = "Ingreso exitoso";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = "No se pudo ingresar los datos, hubo problema con la base de datos";
        } finally {
            cx.Cerrar();
        }
               
        return resul;
    }
    
    public ArrayList ConsultaParametros(Fiscalizaciones fizParam){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        Fiscalizaciones fiz = null;
        ArrayList resul = new ArrayList();
        String query;
        
        query = "select * from "+
                p.prop("fiz.tabla")+" where ";
        
        if (!fizParam.getProcodigo().isEmpty()) {
            query += p.prop("fiz.campo1")+" = '"+fizParam.getProcodigo()+"' and  ";
        }
        if (!fizParam.getFiscedula().isEmpty()) {
            query += p.prop("fiz.campo2")+" = '"+fizParam.getFiscedula()+"' and  ";
        }
        if (!fizParam.getFizfecha().isEmpty()) {
            query += p.prop("fiz.campo3")+" = "+"TO_DATE('"+fizParam.getFizfecha()+"', 'YYYY/MM/DD') and  ";
        }
        if (!fizParam.getFizobservaciones().isEmpty()) {
            query += p.prop("fiz.campo4")+" = '"+fizParam.getFizobservaciones()+"' and  ";
        }
        
        query = query.substring(0, query.length()-6);

        try {
            rs = cx.Ejecutar(query);
            while (rs.next()){
                fiz = new Fiscalizaciones(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4));

                
                resul.add(fiz);
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        }
        return resul;
    }
    public String validarPrueba(Fiscalizaciones fiz)
    {
        Properties p = new Properties();
        Conexion cx = new Conexion();
        String query;
        String resul;
        query = "insert into "+
                p.prop("vap.tabla")+" ("+
                p.prop("vap.c1")+", "+
                p.prop("vap.c2")+", "+
                p.prop("vap.c3")+", "+
                p.prop("vap.c4")+", "+
                p.prop("vap.c5")+") "
                + "values ('"+
                fiz.getProcodigo()+"', '"+
                fiz.getPrucodigo()+"', '"+
                fiz.getFiscedula()+"', '"+
                fiz.getRecparametro()+"',"+
                fiz.getRecaprob()+")";
        try {
            cx.Ejecutar(query);
            resul = "Ingreso exitoso";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = "No se pudo ingresar los datos, hubo problema con la base de datos";
        } finally {
            cx.Cerrar();
        }
               
        return resul;
    }
    
}
