import java.io.*;
import java.util.*;
import java.text.*;

public class Coordinador {
	public static void main(String[] args) {
		String cuenta = "";
		float saldo = 0.0f;
		float deposito = 0.0f;
		int retiro = 0;
		int opcion;
		String memoria = "";

		Hashtable<String, Float> Cuentas = new Hashtable<String, Float>();
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		GuardarMemoria registro = new GuardarMemoria();
		
		Cuentas.put("314141712", 100.53f);

		Scanner sc = new Scanner(System.in);
		System.out.println("\nBIENVENIDO A BANCO ROBARIN XD");
		System.out.println("DIGITE SU NUMERO DE CUENTA");
		System.out.print("=>");
		cuenta = sc.nextLine();
		System.out.println(cuenta);

		do{
			System.out.println("\nQUE ACCION DESEA REALIZAR");
			System.out.println("1: CONSULTA DE SALDO");
			System.out.println("2: DEPOSITO");
			System.out.println("3: RETIRO");
			System.out.println("4: TERMINAR TRANSACCION");
			System.out.print("=>");

			opcion = sc.nextInt();
			switch(opcion){
			case 1:
				//Consulta de saldo
				saldo = Cuentas.get(cuenta);
				System.out.println("EL SALDO DE SU CUENTA ES DE " + saldo);
				memoria = "[" + hourFormat.format(date) + "]" + " CONSULTA DE SALDO ";
				registro.guardar(memoria);
				break;

			case 2:
				//Deposito a la cuenta
				saldo = Cuentas.get(cuenta);
				System.out.println("INGRESE LA CANTIDAD A DEPOSITAR");
				System.out.print("=>");
				deposito = sc.nextFloat();
				Cuentas.put(cuenta, saldo + deposito);
				System.out.println("USTED DEPOSITO $" + deposito);
				memoria = "[" + hourFormat.format(date) + "]" + " DEPOSITO DE " + Float.toString(deposito);
				registro.guardar(memoria);
				break;

			case 3:
				//Retiro de la cuenta
				saldo = Cuentas.get(cuenta);
				System.out.println("INGRESE LA CANTIDAD DEL RETIRO");
				retiro = sc.nextInt();
				Cuentas.put(cuenta, saldo - retiro);
				System.out.print("=>");
				System.out.println("USTED RETIRO " + retiro);
				memoria = "[" + hourFormat.format(date) + "]" + " RETIRO DE " + Float.toString(deposito);
				registro.guardar(memoria);
				break;


			default:
				System.out.println("GRACIAS POR OCUPAR NUESTRO SERVICIO");
					continue;
			}
		} while (opcion != 4);
		sc.close();
	}
}