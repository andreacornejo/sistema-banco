package sistema;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.sql.ResultSet;
import sistema.conexion.ConexionPostgresql;


public class ServidorCessa 
    extends UnicastRemoteObject
    implements ICessa
	 
{
    ServidorCessa() throws java.rmi.RemoteException{
	super();
    }
    
    public Factura[] pedientes(int idcliente) throws RemoteException {
        
        Factura[] aux ;
        
        ConexionPostgresql conexionPostgresql = new ConexionPostgresql();
        aux = conexionPostgresql.mostrar(idcliente);
        
        return aux;
        /**if (idcliente==1)
        {
            Factura[] aux=new Factura[2];
            aux[0]=new Factura("Cessa",154,50.00);
            aux[1]=new Factura("Cessa",326,70.00);
            
            return aux;
        }
        else if (idcliente==2)
        {
            Factura[] aux=new Factura[2];
            aux[0]=new Factura("Cessa",325,60.00);
            aux[1]=new Factura("Cessa",457,80.00);
            
            return aux;
        }
        else
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.**/
    }

        public String pagar(Factura[] facturas) throws RemoteException {
            return "SI \n";
    }    
    
    
    
    public static void main(String args[]) { 
	try {
	    ServidorCessa cessa;
	    LocateRegistry.createRegistry(1099);
	    cessa=new ServidorCessa(); 
	    Naming.bind("Cessa", cessa); 
            System.out.println("El servidor esta listo\n");
        }
	catch (Exception e){
	    e.printStackTrace();
	}
    } 
   
}


