package testInicial;
import java.util.Scanner;

public class NumeroMonroy {

	static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {
		int n = pedirNumero();
		if (comprobarNumero(n)) {
			System.out.println("El numero es Monroy");
		}else {
			System.out.println("El número no es Monroy");
		}
	}
	
	/**
	 * Pide el numero a comprobar
	 * @return int
	 */
	public static int pedirNumero() {
		System.out.println("Introduce un número");
		int n = Integer.parseInt(kb.nextLine());
		return n;
	}
	
	
	/**
	 * Comprueba si el numero es Monroy (La suma de sus divisores es par)
	 * @param numero
	 * @return boolean
	 */
	public static boolean comprobarNumero(int numero) {
		int sumaDivisores = 0;
		boolean esMonroy;
		
		//Pruebo con todos los numeros desde el 1 (porque no se puede dividir por 0) hasta el inmediato anterior a ese numero (un numero es divisor de si mismo)
		//El numero está dividido entre 2 porque a partir de la mitad de un numnero, no existen divisores, asi se ahorran ciclos del bucle
		for (int i = 1; i < (numero/2); i++) {
			
			//Si el modulo es 0, es divisor
			if (numero%i == 0) {
				sumaDivisores = sumaDivisores + i; //Se suma a la variable
			}
		}
		
		//Compruebo si esa variable que suma los divisores es par y devuelvo lo que corresponda
		if (sumaDivisores % 2 == 0) {
			esMonroy=true;
		}else {
			esMonroy=false;
		}
		return esMonroy;
	}

}
