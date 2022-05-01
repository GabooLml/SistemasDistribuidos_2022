import java.text.*;
import java.util.*;
import java.io.*;

    class GuardarMemoria {
        public void guardar(String registro) {
            try {
                FileWriter memoria = new FileWriter("memoria.txt", true);
                for (int i = 0; i < registro.length(); i++) {
                    memoria.write(registro.charAt(i));
                }
                memoria.write("\n");
                memoria.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Servidor extends Thread {
        String name;
        float deposito;
        String memoria;
    
        Servidor(String name, float cantidad) {
            setName(name);
            deposito = cantidad;
        }
    
        public void run() {
            Date fecha = new Date();
            DateFormat  hourFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            GuardarMemoria registro = new GuardarMemoria();
            System.out.println("[" + hourFormat.format(fecha) + "] " + getName() + "-" + getId());
            if (getName().equals("S_DEPOSITO")) {
                Cuenta.saldo += deposito;
                System.out.println(Cuenta.saldo);
                memoria = "[" + hourFormat.format(fecha) + "] " + getName() + "-" + getId() + " - DEPOSITO $" + deposito + " - SALDO FINAL $" + Cuenta.saldo;
                registro.guardar(memoria);
            }
            if (getName().equals("S_RETIRO")) {
                try {
                    if (Cuenta.saldo <= 0) {
                        System.out.println("Fondos insuficientes");
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                Cuenta.saldo -= deposito;
                memoria = "[" + hourFormat.format(fecha) + "] " + getName() + "-" + getId() + " - RETIRO $" + deposito + " - SALDO FINAL $" + Cuenta.saldo;
                registro.guardar(memoria);
            }
        }
    }

    class Cliente extends Thread {
        String name;
        float deposito;
        String memoria;
    
        Cliente(String name, float cantidad) {
            setName(name);
            deposito = cantidad;
        }
    
        public void run() {
            Date fecha = new Date();
            DateFormat  hourFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            GuardarMemoria registro = new GuardarMemoria();
            System.out.println("[" + hourFormat.format(fecha) + "] " + getName() + "-" + getId());
            if (getName().equals("C_DEPOSITO")) {
                Cuenta.saldo += deposito;
                System.out.println(Cuenta.saldo);
                memoria = "[" + hourFormat.format(fecha) + "] " + getName() + "-" + getId() + " - DEPOSITO $" + deposito + " - SALDO FINAL $" + Cuenta.saldo;
                registro.guardar(memoria);
            }
            if (getName().equals("C_RETIRO")) {
                try {
                    if (Cuenta.saldo <= 0) {
                        System.out.println("Fondos insuficientes");
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                Cuenta.saldo -= deposito;
                memoria = "[" + hourFormat.format(fecha) + "] " + getName() + "-" + getId() + " - RETIRO $" + deposito + " - SALDO FINAL $" + Cuenta.saldo;
                registro.guardar(memoria);
            }
        }
    }

    class Cuenta {
        int cuenta = 314141712;
        static float saldo = 0.0f;
    }

    class Coordinador {
        public static void main(String[] args) {
            Cliente cl1 = new Cliente("C_DEPOSITO", 100.00f);
            Servidor sv1 = new Servidor("S_DEPOSITO", 200.0f);
            Cliente cl2 = new Cliente("C_RETIRO", 100.00f);
            Servidor sv2 = new Servidor("S_RETIRO", 200.0f);
            cl1.start();
            sv1.start();
            System.out.println(Cuenta.saldo);
            cl2.start();
            sv2.start();
            System.out.println(Cuenta.saldo);
        }
    }