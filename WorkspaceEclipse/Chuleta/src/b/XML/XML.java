package b.XML;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML {
	
	private static final String RUTA = "";
	
	public static void abrirXML() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("nombre_fichero.xml"));

			document.getDocumentElement().normalize();

			Node raiz = document.getDocumentElement();	//Obtiene la raiz del archivo

			//Obtengo todas las personas
			NodeList elementoX = ((Element)raiz).getElementsByTagName("elemento");
			
			//Lógica del ejercicio			

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void creaFicheroXML() throws IOException {
		try {
			// Crea la cabecera del fichero XML en memoria:
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.getDOMImplementation().createDocument(null, "raiz", null);
			document.setXmlVersion("1.0");
			//annadirElementosAlArbol(document);
			
			Element raiz = document.getDocumentElement();
			
			Element elemento = document.createElement("elemento");
			raiz.appendChild(elemento);
			
			Element elem = document.createElement("etiqueta");
			elemento.appendChild(elem);
			elem.setTextContent("valor");

			// Vuelca el XML a un fichero XML:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(RUTA));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
