/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coordinador;

import javax.swing.*;
/**
 *
 * @author gabri
 */
public class Coordinador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Ventana ventana1 = new Ventana();
        ventana1.crearVentana(640, 480);
        ventana1.setVisible(true);
        ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Hola mundo");
    }
    
}
