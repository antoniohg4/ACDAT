package a.ficheros;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Copiar_Imagen {
	
	private static final String RUTA_ORIGINAL = "";
	private static final String RUTA_COPIA = "";
	private static final String RUTA = "";

	
	public static void copiarImagen() {
		byte[] buffer = new byte[1024];
		
		try (BufferedInputStream bIn = new BufferedInputStream(new FileInputStream(RUTA_ORIGINAL)); //Recibe la imagen como flujo de datos
			 BufferedOutputStream bOs = new BufferedOutputStream(new FileOutputStream(RUTA_COPIA)); //Escribe los datos de la copia
			){
			
			int cantidadBytesLeidos = 0;
			
			while ((cantidadBytesLeidos = bIn.read(buffer, 0, 1024))>0) { // (byte[], offset, longitud) >> Offset es el indice donde empieza 
				bOs.write(buffer);										  //							>> Longitud es la cantidad de bytes que quieres leer a la vez, el byte[].length 
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}//copiarImagen
	
	
	public static void copiarImagenDeInternet(String urlcad) throws MalformedURLException, IOException{
		byte[] buffer = new byte[1024];
		
		URL url = new URL(urlcad); //Crea el objeto URL
		
		
		try (InputStream inputStream =  url.openStream(); 					 //Abre la url como flujo de datos
			 BufferedInputStream bIn = new BufferedInputStream(inputStream); //Crea el filtro de la url, para leer del flujo
				
			 FileOutputStream fOs = new FileOutputStream(RUTA); 			 //Crea el flujo de datos de tu imagen copia local
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
