/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coordinador;

import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class Coordinador {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Conector cliente = new Conector();
        Conector servidor = new Conector();
        
        servidor.conectar('S');
        cliente.conectar('C');
        cliente.consulta("1");
        cliente.deposito("1", (float) 100.0);
        cliente.retiro("1", (float) 50.0);
        cliente.cerrar('C');
        
        servidor.consulta("2");
        servidor.conectar('S');
        servidor.consulta("2");
        servidor.cerrar('S');
    }
    
}
