package crearXML;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.util.ArrayList;
import java.util.List;

public class CrearXMLPersonas {
	private static final String FICHERO_XML = "personas_creado.xml";
	private static List<Persona> listaPersonas;
	
	public static void main(String[] args) {
		try {
			listaPersonas = new ArrayList<Persona>();
			cargaPersonas();
			creaFicheroXMLPersonas();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	private static void creaFicheroXMLPersonas() {
		try {
			// Crea la cabecera del fichero XML en memoria:
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.getDOMImplementation().createDocument(null, "personas", null);
			document.setXmlVersion("1.0");
			annadirElementosAlArbol(document);

			// Vuelca el XML a un fichero XML:
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(FICHERO_XML));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void annadirElementosAlArbol(Document document) {
		Element raiz = document.getDocumentElement();
		for (Persona persona : listaPersonas) {
			Element elementoPersona = document.createElement("persona");
			raiz.appendChild(elementoPersona);
			
			annadirAtributos(elementoPersona, document, persona.getSexo(), persona.getRepetidor());
			
			
			annadirElementos(elementoPersona, document, "nombre", persona.getNombre());
			annadirElementos(elementoPersona, document, "nombre", String.valueOf(persona.getEdad()));
		}
		
		
	}

	

	private static void annadirElementos(Element elementoPersona, Document document, String string, String nombre) {
		Element elem = document.createElement(string);
		elementoPersona.appendChild(elem);
		elem.setTextContent(nombre);
		
	}

	private static void annadirAtributos(Element elementoPersona, Document document, char sexo, char repetidor) {
		if(repetidor == 'S') {
			elementoPersona.setAttribute("repetidor", String.valueOf(repetidor));
		}
		
		elementoPersona.setAttribute("sexo", String.valueOf(sexo));
		
	}

	private static void cargaPersonas() {
		listaPersonas.add(new Persona("Antonio", 53, 'H', 'S'));
		listaPersonas.add(new Persona("Juan", 26, 'H', 'N'));
		listaPersonas.add(new Persona("Maria", 60, 'M', 'N'));
		listaPersonas.add(new Persona("Laura", 55, 'M', 'N'));
		listaPersonas.add(new Persona("Maria", 18, 'M', 'N'));
	}

}
