package a.ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leer_Fichero {

	private static final String RUTA = "";
	
	private static void imprimirFichero() {
		
		//Linea que se va a ir modificando
		String linea;
		
		try (
			FileReader flujoLectura=new FileReader (RUTA); //Abre el archivo como flujo de datos
			BufferedReader filtroLectura=new BufferedReader(flujoLectura) //Crea un filtro para leer las lineas usando el flujo
			;){
			
			linea=filtroLectura.readLine(); //Lee la primera linea del archivo
			
			while (linea!=null){
				
				System.out.println(linea); 
				
				linea=filtroLectura.readLine(); //Lee la siguiente linea
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("No existe el fichero" + RUTA);
		}
		catch (IOException e) { 
			System.out.println( e.getMessage());
		}

	}
}
