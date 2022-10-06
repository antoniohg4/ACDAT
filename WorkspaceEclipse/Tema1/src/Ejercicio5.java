import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class Ejercicio5 {
	
	static final String RUTA_IMAGEN = "ficheros/imagenDescargada.jpg";
	
	public static void main(String[] args) {
		//String urlcad = "https://k31.kn3.net/3A5BCD31B.jpg";
		
		hacerPDF();
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
	
	
	/**
	 * 
	 */
	public static void hacerPDF() {
		String ruta = "ficheros/java.png";
		try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A6);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDType1Font fuente = new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD);

            FileInputStream fis = new FileInputStream(new File(ruta));
            
            // Text
            contentStream.beginText();
            contentStream.setFont(fuente, 32);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52);
            contentStream.showText("Hello World!");
            contentStream.endText();

            // Image
            PDImageXObject image = PDImageXObject.createFromByteArray(document, fis.readAllBytes(), "Java Logo");
            contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);

            contentStream.close();

            document.save("document.pdf");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
	}
}
