import java.util.Scanner;

public class Coordinador {
	public static void main(String[] args) {
		float cuenta = 0.0f;
		int opcion;
		float deposito = 0.0f;

		Scanner sc = new Scanner(System.in);

		do{
			System.out.println("Bienvenido al BANCO GRM");
			System.out.println("QUE ACCION DESEA REALIZAR");
			opcion = sc.nextInt();
			switch(opcion){
			case 1:
				//Consulta de saldo
				System.out.println("El saldo en la cuenta es de $" + cuenta);
				break;

			case 2:
				//Deposito a la cuenta
				System.out.println("Ingrese la cantidad a depositar");
				deposito = sc.nextFloat();
				System.out.println("usted deposito $" + deposito);
				break;

			default:
				System.out.println("OPCION INVALIDA");
					continue;
			}
		} while (opcion != 3);
		sc.close();
	}
}