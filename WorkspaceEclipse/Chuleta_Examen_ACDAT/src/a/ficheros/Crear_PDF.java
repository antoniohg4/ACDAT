package a.ficheros;

import java.io.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class Crear_PDF {

	private static final String RUTA = "";
	
	public static void CrearPDF() {
		
		try (
			 PDDocument document = new PDDocument(); 	//Crea el objeto PDDocument, el falso PDF para modificar
			){
			
             PDPage page = new PDPage(PDRectangle.A6);	//Crea una página del PDF
             document.addPage(page);					//Añade la pagina al PDF

            PDPageContentStream contentStream = new PDPageContentStream(document, page); //Modifica el contenido de la página

            PDType1Font fuente = new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD);   //Indica la fuente que se va a usar

            FileInputStream fis = new FileInputStream(new File(RUTA));					 //Abre un flujo de datos donde va a ir el PDF
            
            // Crea todo el texto que va a ir en el PDF
            contentStream.beginText();
            contentStream.setFont(fuente, 32);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52);
            contentStream.showText("Hello World!");
            contentStream.endText();

            // Crea la imagen que se va a introducir en el PDF
            PDImageXObject image = PDImageXObject.createFromByteArray(document, fis.readAllBytes(), "Java Logo");
            contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
            contentStream.close();

            document.save("ficheros/document.pdf");  //Guarda el PDF
            
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
	}
}
