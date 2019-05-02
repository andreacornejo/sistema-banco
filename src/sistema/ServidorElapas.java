package sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import sistema.conexion.ConexionMongo;

public class ServidorElapas {
    public static ConexionMongo conexionMongo = new ConexionMongo();
    public static void main(String[] args) {
        int port = 5001; // puerto en el que escuchara el socket
        String Respuesta="";
        try 
        {
            ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
            System.out.println("El servidor esta listo\n");
//            Respuesta = Respuesta + conexionMongo.mostrar();
//            System.out.println(Respuesta);
            Socket client;
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura
            while (true) {   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // aceptala conexion
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String cadena;
                cadena = fromClient.readLine(); //cadena obtenida desde el lector
                toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
                System.out.println("Comando Recibido"+cadena);
                if (cadena.substring(3, 4).equals("-")) {
                    String  comando = cadena.substring(0, 3);
                    
                    if (comando.equals("fac")) {
                        Integer idcliente  = Integer.parseInt(cadena.substring(4));
                        Respuesta = Respuesta + conexionMongo.mostrar(idcliente);
                        }
                    if (comando.equals("pag")) {
                            Respuesta="SI";
                    }
                    
                    toClient.flush();
                    toClient.println(Respuesta);
                } else {
                    toClient.flush();
                    toClient.println("No se reconoce el comando");
                }
            }

        }
        catch(IOException e

    
        ){
            System.out.println(e.getMessage());
    }
    }
}
