/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.conexion;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class ConexionMongo {
    DB BaseDatos;
    DBCollection coleccion;
    BasicDBObject document = new BasicDBObject();
    /**
     * Conexion a base de datos con MongoDB
     */
    public ConexionMongo() {
        try {
            Mongo mongo = new Mongo("localhost",27017);
            BaseDatos = mongo.getDB("sistemaFacturas");
            coleccion = BaseDatos.getCollection("Elapas");
            System.out.println("Coneccion a base de datos exitosa");
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConexionMongo.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    /**
     * Mostrando elementos data;
     */
    public String mostrar(int id){
       String resultado ="";
       DBCursor cursor = coleccion.find();
        
       JsonParser parser = new JsonParser();
       JSON json = new JSON();
        String dataUser = json.serialize(cursor);
       JsonArray gsonArr =parser.parse(dataUser).getAsJsonArray();
        // Obtain Array
        //
        System.out.println(gsonArr);
        
        for (JsonElement obj : gsonArr) {
            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();
            // List of primitive elements
            JsonArray data = gsonObj.get("data").getAsJsonArray();
            System.out.println(data);
            JsonArray facturas = data;            
            for (int i = 0; i < facturas.size(); i++) {
                JsonObject object = (JsonObject)facturas.get(i);
                String idcliente = object.get("idcliente").toString();
                System.err.println(idcliente);
                if(Integer.parseInt(idcliente) == id){
                    resultado += object.get("idfactura").toString()+"-"+object.get("monto").toString()+",";
                }
                
            }
            
        }
        
       return resultado;

    }
//    public static void main(String args[]){
//        ConexionMongo conexionMongo = new ConexionMongo();
//        System.out.println(conexionMongo.mostrar(1));
//    }
}







