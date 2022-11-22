package a.ficheros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Escribir_Fichero {

	private static Scanner teclado = new Scanner(System.in);
	private static final String RUTA = "";
	private static final int NUM_LINEAS = 0;
	
	private static void escribirEnFicheroPorLineas () { 
		String cadena;
		
		try (
			FileWriter flujoEscritura = new FileWriter(RUTA);
			PrintWriter filtroEscritura=new PrintWriter(flujoEscritura); //Abre un archivo para escribir en el
			){
			
			for (int i = 1; i <= NUM_LINEAS; i++) {
				System.out.println("Introduce texto: ");
				cadena = teclado.nextLine();
				filtroEscritura.println(cadena); //Escribe en el fichero
			}
			
		}catch (IOException e) { 
			System.out.println(e.getMessage());
		}

	}
}
