/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.conexion;
import java.sql.*;
import sistema.Factura;

/**
 *
 * @author andrea
 */
public class ConexionPostgresql {
    Connection BaseDatos = null;
    public ResultSet rs;
    public Statement st = null;
    
    public Factura[] mostrar(int idcliente){
        
        Factura[] fac = new Factura[2];
        //Donde se localiza la base de datos
        String url="jdbc:postgresql://localhost:5432/db_cessa";
 
        //Credenciales de la base de datos
        String usuario="postgres";
        String contrasena="admin";
 
        try {
            //Conexion con la base de datos
            BaseDatos = DriverManager.getConnection(url, usuario, contrasena);
             boolean valid = BaseDatos.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
 
            int i = 0;
            st = BaseDatos.createStatement();
            rs = st.executeQuery( "SELECT id, idfactura, monto FROM cessa WHERE id =" + idcliente);
            while    ( rs.next() ) {
 
                int id = rs.getInt("id");
                int idfactura = rs.getInt("idfactura");
                int monto = rs.getInt("monto");
                fac[i] = new Factura("Cessa",idfactura,(double)monto);
                i++;
                
                
            }         
            rs.close();
            st.close();
            BaseDatos.close();
            
        } catch (Exception e) {
            System.err.println( e.getMessage() );
            }
        return fac;
    }
//    public static void main(String args[]){
//        Factura[] factura = new Factura[2];
//        ConexionPostgresql conexionPostgresql = new ConexionPostgresql();
//        factura = conexionPostgresql.mostrar(1);
//        for(Factura f:factura)
//            { System.out.print(f.getEmpresa()+"   ");
//            System.out.print(f.getIdFactura()+"   ");
//            System.out.println(f.getMonto());
//            }
//
//    }
}



