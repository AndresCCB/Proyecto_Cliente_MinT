/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base_datos;

import clases.Cliente;
import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author andre
 */
public class ClienteDao {
    //crear cliente en la base de datos.
    public boolean guardar_cliente(Cliente cliente){
        //Crear el ingreso a base de datos de Cliente.
        Connection conexionBaseDatos = null;
        boolean guardo_datos = true;
        
        //abrir conexion
        conexionBaseDatos = Conexion.getConexion();
        PreparedStatement prdStmt = null;
        
        try{
            String strSentencia =  "insert into tiendag74.cliente" +
                                   "(id_cliente, nombre, direccion, telefono)" +
                                   "values(?, ?, ?, ?)";
            
            prdStmt = conexionBaseDatos.prepareStatement(strSentencia);
            prdStmt.setInt(1, cliente.getId_cliente());
            prdStmt.setString(2, cliente.getNombre());
            prdStmt.setString(3, cliente.getDireccion());
            prdStmt.setString(4, cliente.getTelefono());
            guardo_datos = prdStmt.execute();
            System.out.println("guardo los datos en dao" + guardo_datos);
        }
        catch(Exception e){
            System.out.println("Se presento error al guardar el cliente" + e);            
        }
        finally{
            Conexion.cloConexion();
            try{
                prdStmt.close();
            }
            catch(Exception e){
                System.out.println("Se presento error al cerrar prepared statement " +  e);                
            }
        }
        
        return guardo_datos;
    }
    
    //consulta
    public Cliente consultar_cliente(Cliente cliente){
        //Crear el ingreso a base de datos de Cliente.
        Connection conexionBaseDatos = null;
        
        //abrir conexion
        conexionBaseDatos = Conexion.getConexion();
        PreparedStatement prdStmt = null;
        Cliente objeto_cliente = new Cliente();
        
        try{
            String strSentencia = "select id_cliente, nombre, direccion, telefono"
                                  + "from tiendag74.cliente"
                                  + "where id_cliente=?     ";
            prdStmt = conexionBaseDatos.prepareStatement(strSentencia);
            prdStmt.setInt(1, cliente.getId_cliente());
            ResultSet resultado = prdStmt.executeQuery(); //para consultas se usa el ResultSet
            while(resultado.next()){
                int idTemp = resultado.getInt(1);
                String nombreTemp = resultado.getString(2);
                String DireccionTemp = resultado.getString(3);
                String TelefonoTemp = resultado.getString(4);
                objeto_cliente = new Cliente(idTemp, nombreTemp, DireccionTemp, TelefonoTemp);
                

            }
        }
        catch(Exception e){
            System.out.println("Se presento error al consultar el cliente" + e);            
        }
        finally{
            Conexion.cloConexion();
            try{
                prdStmt.close();
            }
            catch(Exception e){
                System.out.println("Se presento error al cerrar prepared statement 2 " +  e);                
            }
        }
         
        return objeto_cliente;
    }
    
    //Consultar mas de un cliente se hace con un return ArrayLisr<Cliente>
    
    //modificar
    
    //eliminarlo
    
}
