package copiarDeInternet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CopiarDeInternet {
	static final String RUTA_IMAGEN = "ficheros/imagenDescargada.jpg";
	public static void main(String[] args) {
		try {
			copiarImagenDeInternet(RUTA_IMAGEN);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Copiar imagen
	 * @param urlcad
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void copiarImagenDeInternet(String urlcad) throws MalformedURLException, IOException{
		byte[] buffer = new byte[1024];
		URL url = new URL(urlcad);
		InputStream inputStream =  url.openStream();
		
		try (BufferedInputStream bIn = new BufferedInputStream(inputStream);
				 BufferedOutputStream bOs = new BufferedOutputStream(new FileOutputStream(RUTA_IMAGEN));){

			int cantidadBytesLeidos = 0;
			
			while (cantidadBytesLeidos>=0) {
				bOs.write(buffer, 0, cantidadBytesLeidos);
				cantidadBytesLeidos=bIn.read(buffer, 0, buffer.length);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}


