package copiarImagen;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EjercicioCopiaImagen {
	private static final String RUTA_GOKU = "ficheros/original.png";
	private static final String RUTA_COPIA = "ficheros/copia_goku.png";
	
	public static void main(String[] args) {
		byte[] buffer = new byte[1024];
		
		try (BufferedInputStream bIn = new BufferedInputStream(new FileInputStream(RUTA_GOKU));
			 BufferedOutputStream bOs = new BufferedOutputStream(new FileOutputStream(RUTA_COPIA));){
			
			int cantidadBytesLeidos = 0;
			
			while ((cantidadBytesLeidos=bIn.read(buffer, 0, 1024))>0) {
				bOs.write(buffer);
				
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	
	}
}
