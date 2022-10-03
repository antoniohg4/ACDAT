package ejercicio5;

import java.io.*;

public class Main {
	private static final int TAM = 1024 * 16;

	public static void main(String[] args) {
		
	}
	
	public static void copiarImagen(String rutaImagenOrigen) {
		try(BufferedInputStream bin = new BufferedInputStream(new FileInputStream(rutaImagenOrigen))){
			
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("copia.xcf"));
			int cantidadBytesLeidos = 0;
			byte[] buffer = new byte[TAM];
			
			while((cantidadBytesLeidos = bin.read(buffer,0, TAM)) != -1) {
				bout.write(buffer);
			}
		
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			
		}
	}
}
