package quiniela;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;



public class Quiniela {

	public static void main(String[] args) {
		String[] equipos = {null , null};
		int[] goles= {99 , 99};
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("resultados.xml"));
			
			document.getDocumentElement().normalize();			
			Node raiz = document.getDocumentElement();
			
			//Crea una lista con todos los nodos "partido". Casting a Element para que no pille nodos intermedios
			NodeList partidos = ((Element)raiz).getElementsByTagName("partido");
			Element partido;
			
			//Bucle que va de 0 hasta el ultimo partido
			for (int i = 0; i < partidos.getLength(); i++) {
				partido = (Element)partidos.item(i);
				
				//Coge una lista solo con los equipos
				NodeList equiposPartido = partido.getElementsByTagName("equipo");
				//La recorre
				for (int j = 0; j < equiposPartido.getLength(); j++) {
					//Y añade el texto al array
					equipos[j] = equiposPartido.item(j).getTextContent().replaceAll(" ", "");
				}
				
				
				//Hace una lista solo con los goles
				NodeList golesPartido = partido.getElementsByTagName("goles");
				//La recorre
				for (int j = 0; j < golesPartido.getLength(); j++) {
					//Y añade el int al array, parseandolo
					goles[j] = Integer.parseInt(golesPartido.item(j).getTextContent().replaceAll(" ", ""));
				}
				
				
				
				//Pinta el resultado
				
				for (int j = 0; j < equipos.length; j++) {
					System.out.print(equipos[j] + " - ");
				}
				
				if (goles[0] > goles[1]) {
					System.out.println("1");
				}else if(goles[0] == goles[1]){
					System.out.println("X");
				}else {
					System.out.println("2");
				}
				
			
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
