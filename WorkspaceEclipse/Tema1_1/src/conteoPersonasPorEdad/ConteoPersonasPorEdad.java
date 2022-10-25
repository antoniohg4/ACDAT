package conteoPersonasPorEdad;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConteoPersonasPorEdad {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("personas.xml"));
			
			document.getDocumentElement().normalize();			
			Node raiz = document.getDocumentElement();
			
			//Obtengo todas las personas
			NodeList personas = ((Element)raiz).getElementsByTagName("persona");
			
			Element persona = null;
			
			System.out.println("Cantidad Hombres: " + contarPersonas(personas, persona, "H"));
			System.out.println("Cantidad Mujeres: " + contarPersonas(personas, persona, "M"));
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//main
	
	private static int contarPersonas(NodeList personas,Element persona, String sexo) {
		int contador = 0;
		
		for (int i = 0; i < personas.getLength(); i++) {
			persona = (Element)personas.item(i);

			if (persona.hasAttribute("sexo")) {
				if (persona.getAttribute("sexo").equals(sexo)) {
					contador++;
				}
			}else {
				System.out.println("No tiene atributo");
			}//if else
		}//for
		
		return contador;
	}

}
