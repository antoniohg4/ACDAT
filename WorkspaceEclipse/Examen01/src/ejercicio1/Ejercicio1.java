package ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ejercicio1 {
	
	private static final String RUTA_CSV = "miaplicacion.csv";
	private static final String RUTA_ACCESOS = "accesos.log";
	private static final String RUTA_ERRORES = "errores.err";
	private static final String RUTA_USUARIOS = "usuarios.usu";
	private static final String RUTA_RESUMEN = "resumen.txt";
	

	public static void main(String[] args) {
		leerCSV();
		resumen();
	}
	
	public static void leerCSV() {
		String linea;
		
		try(FileReader flujoLectura = new FileReader(RUTA_CSV);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);){
			
			linea = filtroLectura.readLine();
			
			while(linea != null) {
				
				gestionarLinea(linea);
				
				linea = filtroLectura.readLine();
			}
			
		}catch (IOException e) {
			// TODO: handle exception
		}
		
		
	}

	private static void gestionarLinea(String linea) {
		String[] partes = linea.split(";");
		
		
		if (partes[0].contains(".")) { //En caso de que sea una ip
			annadirIP(linea);
		}else if(partes[0].matches("^[A-Za-z]+ 0-9]*")) { //En caso de que sea un usuario
			annadirUsuario(linea);
		}else if(partes[0].matches("[0-9]*")){ //En caso de que sea un error
			annadirError(linea);
			
		}
		
	}

	
	/**
	 * Añade una ip al fichero de accesos
	 * @param linea
	 */
	private static void annadirIP(String linea) {
		
		try(FileWriter flujoEscritura = new FileWriter(new File(RUTA_ACCESOS));
			PrintWriter filtroEscritura=new PrintWriter(flujoEscritura);){
			
			filtroEscritura.append(linea + "\n");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * Añade un error al fichero de errores
	 * @param linea
	 */
	private static void annadirError(String linea) {
		
		try(FileWriter flujoEscritura = new FileWriter(new File(RUTA_ERRORES));
			PrintWriter filtroEscritura=new PrintWriter(flujoEscritura);){
			
			filtroEscritura.append(linea + "\n");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	
	/**
	 * Añade un usuario al fichero de usuarios
	 * @param linea
	 */
	private static void annadirUsuario(String linea) {
	
	try(FileWriter flujoEscritura = new FileWriter(new File(RUTA_USUARIOS));
		PrintWriter filtroEscritura=new PrintWriter(flujoEscritura);){
		
		filtroEscritura.append(linea + "\n");
		
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	
	}
	
	
	/**
	 * Cuenta las lineas de cada fichero e imprime el resumen
	 */
	public static void resumen() {
		int contadorAccesos = 0;
		int contadorErrores = 0;
		int contadorUsuarios = 0;
		
		
		//Contar accesos
		try(FileReader flujoLectura = new FileReader(RUTA_ACCESOS);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);){

			String linea = filtroLectura.readLine();
				
				
				while(linea != null) {
					contadorAccesos++;
					linea = filtroLectura.readLine();
				}
			}catch (IOException e) {
				System.out.println(e);
			}
	
		//Contar Errores
		try(FileReader flujoLectura = new FileReader(RUTA_ERRORES);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);){

				String linea = filtroLectura.readLine();
				
					
				while(linea != null) {
						contadorErrores++;
						linea = filtroLectura.readLine();
				}
		}catch (IOException e) {
				System.out.println(e);
		}
		
		//Contar Usuarios
		try(FileReader flujoLectura = new FileReader(RUTA_USUARIOS);
				BufferedReader filtroLectura = new BufferedReader(flujoLectura);){

					String linea = filtroLectura.readLine();
					
						
					while(linea != null) {
							contadorUsuarios++;
							linea = filtroLectura.readLine();
					}
			}catch (IOException e) {
					System.out.println(e);
			}
		
		//Crea el resumen
		try(FileWriter flujoLectura = new FileWriter(RUTA_RESUMEN);
			PrintWriter filtroLectura = new PrintWriter(flujoLectura);){

			filtroLectura.write("Se han producido: \n"
						  + "- " + contadorAccesos + "accesos remotos \n"
						  + "- " + contadorErrores + "errores \n"
						  + "- " + contadorUsuarios + "altas de usuarios \n");
					
			}catch (IOException e) {
					System.out.println(e);
			}
		
	}
	
	
	

}
