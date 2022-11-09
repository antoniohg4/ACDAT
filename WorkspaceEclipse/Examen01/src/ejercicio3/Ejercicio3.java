package ejercicio3;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ejercicio2.Cliente;

public class Ejercicio3 {

	private static final String RUTA_XML = "clientes.xml";
	private static ArrayList<Cliente> listaClientes;
	
	public static void main(String[] args) {
		listaClientes = new ArrayList<Cliente>();
		ejercicio2.Ejercicio2.cargaClientes(listaClientes);
		creaFicheroXML();
	}
	
	private static void creaFicheroXML(){
		try {
			// Crea la cabecera del fichero XML en memoria:
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.getDOMImplementation().createDocument(null, "clientes", null);
			document.setXmlVersion("1.0");
			
			
			annadirElementosAlArbol(document);
			
			
			
			
			
			// Vuelca el XML a un fichero XML:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(RUTA_XML));
		} catch (TransformerConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (TransformerException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void annadirElementosAlArbol(Document document) {
		Element raiz = document.getDocumentElement();
		
		for(Cliente c: listaClientes) {
			Element elementoCliente = document.createElement("cliente");
			raiz.appendChild(elementoCliente);

			annadirElementos(elementoCliente, document, "nombre", c.getNombre());
			annadirElementos(elementoCliente, document, "apellido1", c.getApellido1());
			annadirElementos(elementoCliente, document, "apellido2", c.getApellido1());
			Element direccion = document.createElement("direccion");
			
			
			//Controla si la etiqueta es Calle o Avenida
			if (c.getDireccion().getTipoVia().equals("Calle")) {
				annadirElementos(direccion, document, "Calle", c.getDireccion().getNombreCalle());
				annadirElementos(direccion, document, "Numero",String.valueOf(c.getDireccion().getNumero()));
			}else {
				annadirElementos(direccion, document, "Avenida", c.getDireccion().getNombreCalle());
				annadirElementos(direccion, document, "Numero",String.valueOf(c.getDireccion().getNumero()));
			
			}
			
			Element aficiones = document.createElement("aficiones");
			
			for (String aficion : c.getAficiones()) {
				annadirElementos(aficiones, document, "aficion", aficion);
			}
			
		}	
		
		
		
	}

	private static void annadirElementos(Element elementoCliente ,Document document, String etiqueta, String valor) {
		Element elem = document.createElement(etiqueta);
		elementoCliente.appendChild(elem);
		elem.setTextContent("valor");
		
	}

}
