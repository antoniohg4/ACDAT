package crearXML;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CrearXML {
	private static final String FICHERO_XML = "productos_creado.xml";
	private static List<Producto> listaProductos;

	public static void main(String[] args) {
		try {
			listaProductos = new ArrayList<Producto>();
			cargaProductos();
			creaFicheroXMLProductos(); // a partir del fichero binario de objetos, crea un fichero xml
			System.out.println("Se ha creado el fichero " + FICHERO_XML);
		}catch ( IOException e) {
			e.printStackTrace();
		}
	}

	private static void cargaProductos() {
		listaProductos.add(new Producto("Pan", 1));
		listaProductos.add(new Producto("Coca-Cola", 3));
		listaProductos.add(new Producto("Chopped", 2));
		listaProductos.add(new Producto("Agua", 1));
	}
	
	private static void creaFicheroXMLProductos() throws IOException {
		try {
			// Crea la cabecera del fichero XML en memoria:
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.getDOMImplementation().createDocument(null, "productos", null);
			document.setXmlVersion("1.0");
			annadirElementosAlArbol(document);

			// Vuelca el XML a un fichero XML:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(FICHERO_XML));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void annadirElementosAlArbol(Document document) {
		Element raiz = document.getDocumentElement();
		for(Producto p: listaProductos) {
			Element elementoProducto = document.createElement("producto");
			raiz.appendChild(elementoProducto);

			annadirElementos(elementoProducto, document, "nombre", p.getNombre());
			annadirElementos(elementoProducto, document, "precio", String.valueOf(p.getPrecio()));
		}	
		
	}

	private static void annadirElementos(Element elementoProducto, Document document, String etiqueta, String valor) {
		Element elem = document.createElement(etiqueta);
		elementoProducto.appendChild(elem);
		elem.setTextContent(valor);
		
	}

	
	
	
}
