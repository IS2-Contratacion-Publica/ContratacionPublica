/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.AsignarProyecto;
import Others.Conexion;
import Others.Properties;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class AsignarProyectoMD {
    public String Asignar(AsignarProyecto ap){
        Properties p = new Properties();
        Conexion cx = new Conexion();
        String nombreDocumento, orden;
        boolean query, archivo;
        String resul;
        
        query = false;
        archivo = false;
        
        orden = "insert into "+
                p.prop("asg.tabla")+" ("+
                p.prop("asg.campo1")+", "+
                p.prop("asg.campo2")+", "+
                p.prop("asg.campo3")+", "+
                p.prop("asg.campo4")+", "+
                p.prop("asg.campo5")+", "+
                p.prop("asg.campo6")+") "
                + "values ('"+
                ap.getConcedula()+"','"+
                ap.getProcodigo()+"', '"+
                ap.getFiscedula()+"', "+
                ap.getAdjanticipo()+", '"+
                ap.getAdjdocumento()+"', "+"TO_DATE('"+
                ap.getAdjfecha()+"', 'YYYY/MM/DD'))";
        
        try {
            cx.Ejecutar(orden);
            query = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cx.Cerrar();
        }
        try (InputStream input = ap.getDocumento().getInputStream()) {
            Files.copy(input, new File(p.prop("path.asignacion"), ap.getAdjdocumento()).toPath());
            archivo = true;
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        if (archivo&&query) {
            resul = "Ingreso exitoso";
        } 
        else if (archivo) {
            resul = "No se pudo ingresar los datos, hubo problema con la base de datos";
        }
        else if (query) {
            resul = "No se pudo subir el archivo";
        }
        else {
            resul = "No se pudo ingresar los datos, hubo problema con la conexión";
        }
        return resul;
    }
    
    public ArrayList ConsultaParametros(AsignarProyecto asgParam){
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        AsignarProyecto asg = null;
        ArrayList resul = new ArrayList();
        String orden;
        
        orden = "select * from "+
                p.prop("asg.tabla")+" where ";
        
        if (!asgParam.getConcedula().isEmpty()) {
            orden += p.prop("asg.campo1")+" = '"+asgParam.getConcedula()+"' and  ";
        }
        if (!asgParam.getProcodigo().isEmpty()) {
            orden += p.prop("asg.campo2")+" = '"+asgParam.getProcodigo()+"' and  ";
        }
        if (!asgParam.getFiscedula().isEmpty()) {
            orden += p.prop("asg.campo3")+" = '"+asgParam.getFiscedula()+"' and  ";
        }
        if (!asgParam.getAdjanticipo().isEmpty()) {
            orden += p.prop("asg.campo4")+" = '"+asgParam.getAdjanticipo()+"' and  ";
        }
        if (!asgParam.getAdjfecha().isEmpty()) {
            orden += p.prop("asg.campo6")+" = "+"TO_DATE('"+asgParam.getAdjfecha()+"', 'YYYY/MM/DD') and  ";
        }
        
        orden = orden.substring(0, orden.length()-6);

        try {
            rs = cx.Ejecutar(orden);
            while (rs.next()){
                asg = new AsignarProyecto(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(6));

                
                resul.add(asg);
            }
            cx.Cerrar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resul = null;
        }
        return resul;
    }
}
