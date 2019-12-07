/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerMD;

import EntityClasses.Pagos;
import EntityClasses.Planilla;
import LayerDP.PlanillaDP;
import Others.Conexion;
import Others.Properties;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebad
 */
public class PagosMD {
    public Map<String,Pagos> cargar()
    {
        Map<String,Pagos> lista=new HashMap<>();
       Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        String Orden, auxdate;
        Timestamp auxtime;
        Orden ="Select * from "+ p.prop("pag.tabla");
        try {
            rs = cx.Ejecutar(Orden);
            while (rs.next()) {
                Pagos nuevo= new Pagos();
                nuevo.setPagCodigo(rs.getString(1));
                nuevo.setProCodigo(rs.getString(2));
                nuevo.setPlaCodigo(rs.getString(3));
                nuevo.setPla_ConCedula(rs.getString(4));
                nuevo.setFisCedula(rs.getString(5));
                auxdate = rs.getDate(6).toString();
                auxdate = auxdate.replace("-", "/");
                nuevo.setPagFechaAutorizacion(auxdate);
                auxtime = rs.getTimestamp(7);
                if (auxtime != null) {
                    auxdate = rs.getDate(7).toString();
                    auxdate = auxdate.replace("-", "/");
                    nuevo.setPagFechaEnvio(auxdate);
                }
                else {
                    nuevo.setPagFechaEnvio("N/A");
                }

                nuevo.setPagEstado(rs.getInt(8)+"");
                lista.put(rs.getString(1), nuevo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PagosMD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cx.Cerrar();
        }
         return lista;

    }
    public String ingresarMD(Pagos ingreso)
    {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        String Orden, m;
        String PagCodigo=ingreso.getPagCodigo();
        String ProCodigo=ingreso.getProCodigo();
        String PlaCodigo=ingreso.getPlaCodigo();
        String Pla_ConCedula=ingreso.getPla_ConCedula();
        String FisCedula=ingreso.getFisCedula();
        String PagFechaAutorizacion=ingreso.getPagFechaAutorizacion();
        String PagFechaEnvio=ingreso.getPagFechaAutorizacion();
        String PagEstado=ingreso.getPagEstado();
        Orden="Insert into "+p.prop("pag.tabla")+" ("+p.prop("pag.c1")+","
                                                    +p.prop("pag.c2")+","
                                                    +p.prop("pag.c3")+","
                                                    +p.prop("pag.c4")+","
                                                    +p.prop("pag.c5")+","
                                                    +p.prop("pag.c6")+","
                                                    +p.prop("pag.c8")
                                                    +") values ("
                                                    +"'"+PagCodigo.trim()+"',"
                                                    +"'"+ProCodigo.trim()+"',"
                                                    +"'"+PlaCodigo.trim()+"',"
                                                    +"'"+Pla_ConCedula.trim()+"',"
                                                    +"'"+FisCedula+"',"
                                                    +"TO_DATE('"+PagFechaAutorizacion+"', 'YYYY/MM/DD'),"
                                                    +PagEstado
                                                    +")";

        try {
            rs = cx.Ejecutar(Orden); 
            m="Pago ingresado correctamente";
        }
         catch (SQLException ex) {
             m="No se pudo ingresar el pago, el código ya existe o hubo un problema con la bas de datos.";
             System.out.println(ex.getMessage());
            Logger.getLogger(PagosMD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cx.Cerrar();
        }
        return m;
    }
    public Planilla crearPlanilla(String codigo)
    {
        Planilla agregar = new Planilla();
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        String Orden;
        Orden ="Select * from "+ p.prop("pla.tabla")+" where "+p.prop("pla.llave")+"='"+codigo+"'";
        try {
            rs = cx.Ejecutar(Orden); 
            rs.next();
            agregar.setCodigo(rs.getString(2));
            agregar.setCodigoProyecto(rs.getString(1));
            agregar.setConCedula(rs.getString(3)); 
            agregar.setFechaCreacion(rs.getDate(4));
            agregar.setMonto(rs.getFloat(5));
            
            
        } catch (SQLException ex) {
            agregar= null;
            System.out.println(ex.getMessage());
            Logger.getLogger(PagosMD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cx.Cerrar();
        }
        return agregar;
    }
    public String validarMD(Pagos validar)
    {
        Properties p =  new Properties();
        Conexion cx = new Conexion();
        ResultSet rs;
        String Orden, m;
        String PagCodigo=validar.getPagCodigo();
        String PagFechaEnvio=validar.getPagFechaEnvio();
        String PagEstado=validar.getPagEstado();
        Orden="Update "+p.prop("pag.tabla")+" set "
                                                    +p.prop("pag.c7")+" = TO_DATE('"+PagFechaEnvio+"', 'YYYY/MM/DD'),"
                                                    +p.prop("pag.c8")+" = "+PagEstado+" where "
                                                    +p.prop("pag.c1")+" = '"+PagCodigo+"'";
        

        try {
            rs = cx.Ejecutar(Orden); 
            m = "Información guardada correctamente";
        }
         catch (SQLException ex) {
            m = "Error en la base de datos";
            System.out.println(ex.getMessage());
            Logger.getLogger(PagosMD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cx.Cerrar();
        }
        return m;
    }
}
