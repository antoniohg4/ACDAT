package ejercicio6;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class E6 {
	static Scanner kb = new Scanner(System.in);
	private static final String RUTA_FICHEROS= "ficheros/";
	public static void main(String[] args) {
		try {
			crearVariosFicheros();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void crearVariosFicheros() throws IOException{
		System.out.print("nNumero de ficheros: ");
		int numeroFicheros = Integer.parseInt(kb.nextLine());
		String nombre;
		File archivo = null;
		
		for (int i = 0; i < numeroFicheros; i++) {
			System.out.println("Nombre para el fichero " + (i+1));
			nombre = kb.nextLine();
			archivo = new File(RUTA_FICHEROS + nombre);
			
			if (archivo.exists()) {
				System.out.println("El archivo existe, sobreescribir? (Y/N)");
				if (kb.nextLine().toUpperCase().equals("Y")) {
					archivo.delete();
					archivo.createNewFile();
				}else {
					System.out.println("no se creara el archivo");
				}
			}else {
				archivo.createNewFile();
			}
		}
		
	}

}
