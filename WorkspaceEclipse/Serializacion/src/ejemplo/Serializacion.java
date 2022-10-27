package ejemplo;

import java.io.*;

public class Serializacion {
	private static final String NOMBRE_FICHERO = "personas.txt";

	public static void main(String[] args) {
		crearFichero();

	}
	
	private static void crearFichero() {
		
		String nombres[] = {"Pepe", "Pepa", "Luis", "Luisa"};
		int edades[] = {20, 30, 18, 16};
		Persona persona;
		
		try (FileOutputStream flujoEscritura = new FileOutputStream(NOMBRE_FICHERO);
			 ObjectOutputStream filtroEscritura = new ObjectOutputStream(flujoEscritura);){
			
			for (int i = 0; i < edades.length; i++) {
				persona = new Persona(nombres[i], edades[i]);
				filtroEscritura.writeObject(persona);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
