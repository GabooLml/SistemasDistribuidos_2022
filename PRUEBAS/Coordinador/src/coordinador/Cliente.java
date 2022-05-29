/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;

/**
 *
 * @author gabri
 */
public class Cliente extends Thread {
    public Cliente(String name) {
        super(name);
    }
    
    @Override
    public void run() {
        /*if(getName().equals("DEPOSITO")) {
            
        }*/
        System.out.println("Prueba");
    }
}
