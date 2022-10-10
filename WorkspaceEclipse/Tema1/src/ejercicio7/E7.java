package ejercicio7;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class E7 {
	static Scanner kb = new Scanner(System.in);
	private static final String RUTA_FICHEROS= "ficheros";
	public static void main(String[] args) {
		try {
			crearVariosFicheros();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void crearVariosFicheros() throws IOException{
		System.out.print("Numero de directorios: ");
		int numeroFicheros = Integer.parseInt(kb.nextLine());
		String nombre;
		File directorio = null;
		String ruta = RUTA_FICHEROS;
		
		for (int i = 0; i < numeroFicheros; i++) {
			System.out.println("Nombre para el directorio " + (i+1));
			nombre = kb.nextLine();
			directorio = new File(ruta + "/" + nombre);
			
			directorio.mkdir();
			ruta = ruta + "/" + nombre;
		}
		
	}
}
