import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	private static final int NUMERO_LINEAS = 4;
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		//imprimirFicheroPorLineas("file.txt");
		//escribirEnFicheroPorLineas("file.txt");
		copiarContenido("origen.txt", "destino.txt");

	}
	
	private static void imprimirFicheroPorLineas(String nombreFichero) { 
		String linea;

		try (FileReader flujoLectura=new FileReader (nombreFichero);
			BufferedReader filtroLectura=new BufferedReader(flujoLectura);)
		{
			linea=filtroLectura.readLine(); 
			while (linea!=null){
				System.out.println(linea); 
				linea=filtroLectura.readLine();
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("No existe el fichero" + nombreFichero);
		}
		catch (IOException e) { 
			System.out.println( e.getMessage());
		}

	}
	
	private static void escribirEnFicheroPorLineas (String nombre) { 
		String cadena;
		
		try (PrintWriter filtroEscritura=new PrintWriter(new FileWriter (nombre));){
			for (int i = 1; i <= NUMERO_LINEAS; i++) {
				System.out.println("Introduce texto: ");
				cadena = teclado.nextLine(); 
				filtroEscritura.println(cadena);
			}
		}catch (IOException e) { 
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * Ejercicio 2
	 * @param nombreFicheroOrigen
	 * @param nombreFicheroDestino
	 */
	private static void copiarContenido(String nombreFicheroOrigen, String nombreFicheroDestino) {
		String linea;
		
		//Leer archivo
		try (FileReader flujoLectura=new FileReader (nombreFicheroOrigen);
			BufferedReader filtroLectura=new BufferedReader(flujoLectura);
			PrintWriter filtroEscritura=new PrintWriter(new FileWriter (nombreFicheroDestino));)
			{
				linea=filtroLectura.readLine(); 
				while (linea!=null){
					filtroEscritura.println(linea);
					linea=filtroLectura.readLine();
				}
				System.out.println("Copiado correctamente");
			}
			catch (FileNotFoundException e) {
				System.out.println("No existe el fichero" + nombreFicheroDestino);
			}
			catch (IOException e) { 
				System.out.println( e.getMessage());
			}
		
	}
	
	
	private static void ejercicio3(String nombreFicheroOrigen, String nombreFicheroDestino) {
		String linea;
		
		
		
		
		//Leer archivo
		try (FileReader flujoLectura=new FileReader (nombreFicheroOrigen);
			BufferedReader filtroLectura=new BufferedReader(flujoLectura);
			PrintWriter filtroEscritura=new PrintWriter(new FileWriter (nombreFicheroDestino));)
			{
				linea=filtroLectura.readLine(); 
				while (linea!=null){
					filtroEscritura.println(linea);
					linea=filtroLectura.readLine();
				}
				System.out.println("Copiado correctamente");
			}
			catch (FileNotFoundException e) {
				System.out.println("No existe el fichero" + nombreFicheroDestino);
			}
			catch (IOException e) { 
				System.out.println( e.getMessage());
			}
		
	}

}
