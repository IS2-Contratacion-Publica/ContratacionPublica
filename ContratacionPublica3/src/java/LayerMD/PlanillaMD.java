package LayerMD;
import LayerDP.PlanillaDP;
import Others.Properties;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
/**
 *
 * @author sebad
 */

public class PlanillaMD {
    private String mensaje="";
    public PlanillaMD() 
    {
    }
    public Connection GenerarConexion() throws SQLException{
        Properties p =  new Properties();
        

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlanillaMD.class.getName()).log(Level.SEVERE, null, ex);
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
    public String crear(PlanillaDP planillaDP)
    {
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String codP,cod,fecha,orden;
        float monto;
        int dia,estado;
        estado=1;
        codP = planillaDP.getCodigoProyecto();
        cod = planillaDP.getCodigo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        dia=planillaDP.getFechaCreacion().getDate();
        planillaDP.getFechaCreacion().setDate(dia+1);
        fecha = dateFormat.format(planillaDP.getFechaCreacion());
        monto = planillaDP.getMonto();
        orden = "insert into "+
                p.prop("pla.tabla")+" ("+
                p.prop("pla.llave")+","+
                p.prop("pla.campo1")+","+
                p.prop("pla.campo2")+","+
                p.prop("pla.campo3")+","+
                p.prop("pla.campo4")+")"
                + "values ('"+codP+"','"+
                cod+"',"+
                "TO_DATE('"+
                fecha+"', 'YYYY/MM/DD'),"+
                monto+","+estado+")";
        planillaDP.getFechaCreacion().setDate(dia);
        planillaDP.setCodigo("");
        planillaDP.setCodigoProyecto("");
        planillaDP.setFechaCreacion(null);
        planillaDP.setMensaje("");
        planillaDP.setMonto(0);
        mensaje=orden;
        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            //mensaje=orden;
            s.executeQuery(orden);
            conn.close();
            return "Ingresado Correctamente";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return "Error En La conexion";
        }
    }
    public String modificar(PlanillaDP planillaDP)
    {
         Properties p =  new Properties();
        Connection conn;
        Statement s;
        String codP,cod,fecha,orden,mensaje;
        float monto;
        int dia,estado;
        estado=1;
        codP = planillaDP.getCodigoProyecto();
        cod = planillaDP.getCodigo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        dia=planillaDP.getFechaCreacion().getDate();
        planillaDP.getFechaCreacion().setDate(dia+1);
        fecha = dateFormat.format(planillaDP.getFechaCreacion());
        monto = planillaDP.getMonto();
        orden = "update "+
                p.prop("pla.tabla")+" set "+
                p.prop("pla.llave")+" = '"+
                codP+"',"+
                p.prop("pla.campo2")+" = "+
                "TO_DATE('"+
                fecha+"', 'YYYY/MM/DD'),"+
                p.prop("pla.campo3")+" = "+
                monto+","+p.prop("pla.campo4")+" = "+estado +
                " where "+p.prop("pla.campo1")+" ='"+
                cod+"'";

        planillaDP.getFechaCreacion().setDate(dia);
        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            s.executeQuery(orden);
            conn.close();
            return "Se ha modificado correctamente los datos";
        } catch (SQLException ex) {       
            return "Comuniquese con el administrado de la base de datos";
        }
    }
    public boolean eliminar(PlanillaDP planillaDP)
    {
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String cod,orden,mensaje;
        int estado;
        estado=0;
        cod = planillaDP.getCodigo();
        orden = "update "+
                p.prop("pla.tabla")+" set "+
                p.prop("pla.campo4")+" = "+estado +
                " where "+p.prop("pla.campo1")+" ='"+
                cod+"'";
        planillaDP.setCodigo("");
        planillaDP.setCodigoProyecto("");
        planillaDP.setFechaCreacion(null);
        planillaDP.setMensaje("");
        planillaDP.setMonto(0);
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
    public String consultar(PlanillaDP planillaDP) throws ParseException
    {
        String codigo=planillaDP.getCodigo();
      Properties p =  new Properties();
      String[] datosFecha;
      int estado=1;
        Connection conn;
        Statement s;
        ResultSet rs;
        String orden,mensaje="";
        Date fecha;
        int dia,mes,año;
        orden = "select * from "+
                p.prop("pla.tabla")+" where "+
                p.prop("pla.campo1")+" = '"+codigo+"' and "+p.prop("pla.campo4")+"="+estado;
        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(orden);
            
            if(rs.next()){
                planillaDP.setCodigo(rs.getString(2));
                planillaDP.setCodigoProyecto(rs.getString(1));
                mensaje=rs.getDate(3).toString();
                datosFecha = mensaje.split("-");
                año=Integer.parseInt(datosFecha[0]);
                mes=Integer.parseInt(datosFecha[1]);
                dia=Integer.parseInt(datosFecha[2]);
                fecha= new Date(año-1900,0,dia,0,mes,0);
                planillaDP.setFechaCreacion(fecha);
                planillaDP.setMonto(rs.getFloat(4));
            }
            else{
                planillaDP = null;
                mensaje=null;
            }
            conn.close();
            
        } catch (SQLException ex) {
            planillaDP = null;
        }      
        return mensaje;
    }
    public boolean Verificar(PlanillaDP planillaDP)
    {
        String orden,cod;
        boolean verificar = false;
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        cod=planillaDP.getCodigo();
        orden="Select "+p.prop("pla.campo1")+" from "+p.prop("pla.tabla")+" where "+p.prop("pla.campo1")+"='"+cod+"'";
        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(orden);          
            if(rs.next()){
                verificar=true;
            }
            else{
                verificar=false;
            }
            conn.close();
            
        } catch (SQLException ex) {
            planillaDP = null;
        }      
        return verificar;
    }   
    public LinkedList<PlanillaDP> ConsultaGeneral()
    {
        LinkedList<PlanillaDP> lista = new LinkedList<>();
        PlanillaDP agregar;
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        String Orden;
        Orden ="Select * from "+ p.prop("pla.tabla");
        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(Orden);          
            while(rs.next()){
                agregar= new PlanillaDP();
                agregar.setCodigo(rs.getString(1));
                agregar.setCodigoProyecto(rs.getString(2));
                agregar.setFechaCreacion(rs.getDate(3));
                agregar.setMonto(rs.getFloat(4));
                lista.add(agregar);
            }
            conn.close();
            
        } catch (SQLException ex) {
            lista= null;
        }      
        return lista;
    }
}
