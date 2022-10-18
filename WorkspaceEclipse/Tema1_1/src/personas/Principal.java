package personas;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Principal {

	public static void main(String[] args) {
		LinkedList<Persona> listaPersonas = new LinkedList<Persona>(); 
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("personasDOM.xml"));
			
			document.getDocumentElement().normalize();			
			Node raiz = document.getDocumentElement();
			
			NodeList personas = ((Element)raiz).getElementsByTagName("persona");
			
			String nombre = null;
			int edad;
			Element persona;
			
			for (int i = 0; i < personas.getLength(); i++) {
				persona = (Element)personas.item(i);
				
				NodeList nombres = persona.getElementsByTagName("nombre");
				NodeList edades = persona.getElementsByTagName("edad");
				
				for (int j = 0; j < nombres.getLength(); j++) {
					nombre = nombres.item(j).getTextContent();
					edad = Integer.parseInt(edades.item(j).getTextContent());
					listaPersonas.add(new Persona(nombre, edad));
				}
				
			}
			
			Collections.sort(listaPersonas);
			for (int i = 0; i < listaPersonas.size(); i++) {
				System.out.println("Persona " + (i+1) +": ");
				System.out.println(listaPersonas.get(i));
				System.out.println();
			}

			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
