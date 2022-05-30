/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gabri
 */
public class Conector {
    private final String urlSQLite = "jdbc:sqlite:Banco.db";
    private Driver driverSQLite;
    private Connection conSQLite;
    private double idTransaccion; 
    private static boolean write = false;
    private static boolean status = false;
    
    public void conectar(char origen) {
       idTransaccion = (int)(Math.random()*((2000-1)+1))+1; 
       status = true;
       
       try {
            driverSQLite = new org.sqlite.JDBC();
	    DriverManager.registerDriver(driverSQLite );
            System.out.println("Transaccion iniciada ["+origen+"-"+idTransaccion + "]");
       } catch (SQLException e) {
            System.out.println("Problem with download driver for SQLite: " + e.getMessage());
        }        
        
       try {
           conSQLite= DriverManager.getConnection(urlSQLite);
           System.out.println("Conexion a base de datos exitosa" );           
       } catch (SQLException e) {
           System.out.println("Problem with connection to SQLite: " + e.getMessage());
       }
    }
    
    public String consulta(String id) throws SQLException {
        ResultSet resultset;
        String resultCons = null;
        if(write == false) {
            try{
                Connection connection = DriverManager.getConnection(urlSQLite);
                Statement statement = connection.createStatement();
                String select = "select nombre, saldo from cuentas where num_cuenta = " + id + ";";
                resultset = statement.executeQuery(select);
                resultCons = ("Cliente: "+ resultset.getString(1)+ "\n" + "Saldo: " +resultset.getString(2));
                resultset.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Operacion no disponible");
        }
        return resultCons;
    }
    
    public float deposito(String id, float deposito) throws SQLException {
        ResultSet resultset;
        float acumulado = 0;
        
        if(status == true && write == false) {
            write = true;
            try{
                Connection connection = DriverManager.getConnection(urlSQLite);
                Statement statement = connection.createStatement();
                String select = "select saldo from cuentas where num_cuenta = " + id + ";";
                resultset = statement.executeQuery(select);
                acumulado = resultset.getFloat(1) + deposito;
                resultset.close();
                statement.close();
                Statement statement1 = connection.createStatement();
                String update = "UPDATE CUENTAS SET SALDO = " + acumulado + " WHERE NUM_CUENTA = " + id + ";";
                statement1.executeUpdate(update);
                statement1.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            write = false;
        }
        if(status == true && write == true) {
            System.out.println("Operacion disponible");
        }
        return acumulado;
    }
    
    public float retiro(String id, float retiro) throws SQLException {
        ResultSet resultset;
        float acumulado = 0;
        if(status == true && write == false) {
            write = true;
            try{
                Connection connection = DriverManager.getConnection(urlSQLite);
                Statement statement = connection.createStatement();
                String select = "select saldo from cuentas where num_cuenta = " + id + ";";
                resultset = statement.executeQuery(select);
                acumulado = resultset.getFloat(1) - retiro;
                resultset.close();
                statement.close();
                Statement statement1 = connection.createStatement();
                String update = "UPDATE CUENTAS SET SALDO = " + acumulado + " WHERE NUM_CUENTA = " + id + ";";
                //String update = "UPDATE CUENTAS SET SALDO = ? WHERE NUM_CUENTA = ?;";
                statement1.executeUpdate(update);
                statement1.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
            write = false;
        }
        if(status == true && write == true) {
            System.out.println("Operacion disponible");
        }
        return acumulado;
    }
    
    public void cerrar(char origen) {
        status = false;
        try{
            if(!conSQLite.isClosed()) { 
                conSQLite.close();
                System.out.println("Transaccion finalizada ["+origen+"-"+idTransaccion + "]");
            }
          } catch (SQLException e) {
            System.out.println("Problem with close connection of SQLite" );
          }
    }
}