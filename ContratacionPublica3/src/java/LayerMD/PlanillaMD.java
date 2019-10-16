package LayerMD;
import LayerDP.PlanillaDP;
import Others.Properties;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                                            "BASEDATOS",
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
        int dia;
        codP = planillaDP.getCodigoProyecto();
        cod = planillaDP.getCodigo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dia=planillaDP.getFechaCreacion().getDate();
        planillaDP.getFechaCreacion().setDate(dia+1);
        fecha = dateFormat.format(planillaDP.getFechaCreacion());
        monto = planillaDP.getMonto();
        orden = "insert into "+
                p.prop("pla.tabla")+" ("+
                p.prop("pla.llave")+","+
                p.prop("pla.campo1")+","+
                p.prop("pla.campo2")+","+
                p.prop("pla.campo3")+")"
                + "values ('"+cod+"','"+
                codP+"',"+
                "TO_DATE('"+
                fecha+"', 'YYYY/MM/DD'),"+
                monto+")";
        planillaDP.getFechaCreacion().setDate(dia);
        planillaDP.setCodigo("");
        planillaDP.setCodigoProyecto("");
        planillaDP.setFechaCreacion(null);
        planillaDP.setMensaje("");
        planillaDP.setMonto(0);
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
        int dia;
        codP = planillaDP.getCodigoProyecto();
        cod = planillaDP.getCodigo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dia=planillaDP.getFechaCreacion().getDate();
        planillaDP.getFechaCreacion().setDate(dia+1);
        fecha = dateFormat.format(planillaDP.getFechaCreacion());
        monto = planillaDP.getMonto();
        orden = "update "+
                p.prop("pla.tabla")+" set "+
                p.prop("pla.campo1")+" = '"+
                codP+"',"+
                p.prop("pla.campo2")+" = "+
                "TO_DATE('"+
                fecha+"', 'YYYY/MM/DD'),"+
                p.prop("pla.campo3")+" = "+
                monto+" where "+p.prop("pla.llave")+" ='"+
                cod+"'";

        planillaDP.getFechaCreacion().setDate(dia);
        try {
            conn = GenerarConexion();
            System.out.println(conn);
            s = conn.createStatement();
            s.executeQuery(orden);
            conn.close();
            return "Se ha modificado correctamente los datos";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());       
            return "Comuniquese con el administrado de la base de datos";
        }
    }
    public boolean eliminar(PlanillaDP planillaDP)
    {
        String Codigo=planillaDP.getCodigo();
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        String orden;
        orden = "delete from "+
                p.prop("pla.tabla")+" where "+
                p.prop("pla.llave")+" = '"+Codigo+"'";
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
    public String consultar(PlanillaDP planillaDP)
    {
        String codigo=planillaDP.getCodigo();
      Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        Date fecha;
        String orden,mensaje="";
        
        orden = "select * from "+
                p.prop("pla.tabla")+" where "+
                p.prop("pla.llave")+" = '"+codigo+"'";
        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(orden);
            
            if(rs.next()){
                planillaDP.setCodigo(rs.getString(1));
                planillaDP.setCodigoProyecto(rs.getString(2));
                fecha=rs.getDate(3);
                planillaDP.setFechaCreacion(fecha);
                planillaDP.setMonto(rs.getFloat(4));
                mensaje=fecha.toString();
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
    public String Verificar(PlanillaDP planillaDP)
    {
        String codP,orden;
        Properties p =  new Properties();
        Connection conn;
        Statement s;
        ResultSet rs;
        codP=planillaDP.getCodigoProyecto();
        orden="Select "+p.prop("pla.campo1")+" from "+"PROYECTO where "+p.prop("pla.campo1")+"='"+codP+"'";
        try {
            conn = GenerarConexion();
            s = conn.createStatement();
            rs = s.executeQuery(orden);          
            if(rs.next()){
                mensaje=rs.getString(1);
            }
            else{
                planillaDP = null;
                mensaje="No se encontrado Proyectos";
            }
            conn.close();
            
        } catch (SQLException ex) {
            planillaDP = null;
        }      
        return mensaje;
    }   
}
