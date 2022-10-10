package crearPdf;

import java.io.File;
import java.io.FileInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class CrearPDF {
	public static void main(String[] args) {
		hacerPDF();
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

            document.save("ficheros/document.pdf");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
	}
}
