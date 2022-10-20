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
			
			//Obtengo todas las personas
			NodeList personas = ((Element)raiz).getElementsByTagName("persona");
			
			String nombre = null;
			int edad;
			Element persona;
			
			//Recorro las personas
			for (int i = 0; i < personas.getLength(); i++) {
				
				//Por cada persona
				persona = (Element)personas.item(i);
				
				//Obtengo nombre y edad
				Node nodoNombre = (Node)persona.getElementsByTagName("nombre");
				Node nodoEdad = (Node)persona.getElementsByTagName("edad");
				
				nombre = nodoNombre.getTextContent();
				edad = Integer.parseInt(nodoEdad.getTextContent());
				listaPersonas.add(new Persona(nombre, edad));
				for
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
