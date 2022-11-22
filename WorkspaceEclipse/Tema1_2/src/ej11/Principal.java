package ej11;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Principal {
	
	private static final String RUTA = "libros.csv";
	private static final String RUTA_SERIALIZADO = "libros.obj";

	public static void main(String[] args) {
		
		try(FileReader flujoLectura = new FileReader(RUTA);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);
			FileOutputStream flujoEscritura = new FileOutputStream(RUTA_SERIALIZADO);
			ObjectOutputStream filtroEscritura = new ObjectOutputStream(flujoEscritura);){
			
			String linea = filtroLectura.readLine();
			String[] arrayLibro;
			Libro libro;
			ArrayList<Libro> libros = new ArrayList<Libro>();
			
			while(linea != null) {
				
				arrayLibro = linea.split(";");
				libro = new Libro(arrayLibro);
				
				libros.add(libro);
				
				linea = filtroLectura.readLine();
			}
			
			filtroEscritura.writeObject(libros);
			
		}catch (Exception e) {
			
		}

	}

}
