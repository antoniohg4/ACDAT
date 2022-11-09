package ejercicio4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Ejercicio4 {

	private static final String RUTA_COPIA_1 = "copia1.jpeg";
	private static final String RUTA_COPIA_2 = "copia2.jpeg";

	public static void main(String[] args) {
		copiarImagen("http://idocentic.website/servicios_para_explotar/images/imagen1.jpeg");

	}
	
	public static void copiarImagen(String urlcad1, String urlcad2) {
		byte[] buffer = new byte[1024];
		
		URL url1 = new URL(urlcad1); //Crea el objeto URL
		URL url2 = new URL(urlcad2); //Crea el objeto URL
		
		
		try (InputStream inputStream =  url1.openStream(); 					 //Abre la url como flujo de datos
			 BufferedInputStream bIn = new BufferedInputStream(inputStream); //Crea el filtro de la url, para leer del flujo
				
			 FileOutputStream fOs = new FileOutputStream(RUTA_COPIA_1); 			 //Crea el flujo de datos de tu imagen copia local
			 BufferedOutputStream bOs = new BufferedOutputStream(fOs); 		 //Crea el filtro para escribir en el flujo
			){

			int cantidadBytesLeidos = 0;
			
			while (cantidadBytesLeidos>=0) {
				bOs.write(buffer, 0, cantidadBytesLeidos);					 //Escribe los bytes en la imagen local
				cantidadBytesLeidos=bIn.read(buffer, 0, buffer.length);		 //Lee los siguientes buffer.length bytes
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		try (InputStream inputStream =  url2.openStream(); 					 //Abre la url como flujo de datos
				 BufferedInputStream bIn = new BufferedInputStream(inputStream); //Crea el filtro de la url, para leer del flujo
					
				 FileOutputStream fOs = new FileOutputStream(RUTA_COPIA_2); 			 //Crea el flujo de datos de tu imagen copia local
				 BufferedOutputStream bOs = new BufferedOutputStream(fOs); 		 //Crea el filtro para escribir en el flujo
				){

				int cantidadBytesLeidos = 0;
				
				while (cantidadBytesLeidos>=0) {
					bOs.write(buffer, 0, cantidadBytesLeidos);					 //Escribe los bytes en la imagen local
					cantidadBytesLeidos=bIn.read(buffer, 0, buffer.length);		 //Lee los siguientes buffer.length bytes
				}
				
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	}

}
