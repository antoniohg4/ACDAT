package ejercicio8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class E8 {
	static Scanner kb = new Scanner(System.in);
	private static final String RUTA_FICHEROS= "ficheros/ficheros.txt";
	private static final String RUTA_CREAR= "ficheros/";
	public static void main(String[] args) {
		try {
			crearVariosFicherosDesdeOtroFichero();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void crearVariosFicherosDesdeOtroFichero() throws IOException{
		String linea;
		File archivo = null;
		
		try(BufferedReader bIn = new BufferedReader(new FileReader(RUTA_FICHEROS));){
			linea=bIn.readLine(); 
			while (linea!=null){
				archivo = new File(RUTA_CREAR + linea);
				
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
				
				
				linea=bIn.readLine();
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
