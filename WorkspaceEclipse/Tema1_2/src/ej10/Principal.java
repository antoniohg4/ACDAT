package ej10;
import java.io.*;
import java.util.*;

public class Principal {
	
	private static final String RUTA_FICHERO = "alumnos.ser";

	public static void main(String[] args) {
		ArrayList<Alumno> listaAlumnos = crearLista();
		serializar(listaAlumnos);
		deserializar(RUTA_FICHERO);
	}
	
	
	public static ArrayList<Alumno> crearLista() {
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		
		Alumno a1 = new Alumno("Hermosin", "Garcia", "Antonio", 21, "04082001");
		Alumno a2 = new Alumno("Jimenez", "Risquez", "Ismael", 22, "31102022");
		ModuloProfesional m1 = new ModuloProfesional("Modulo1", 10);
		ModuloProfesional m2 = new ModuloProfesional("Modulo2", 20);
		
		a1.annadirModulos(m1);
		a1.annadirModulos(m2);
		
		a2.annadirModulos(m2);
		
		listaAlumnos.add(a1);
		listaAlumnos.add(a2);
		
		return listaAlumnos;

	}
	
	
	public static void serializar(ArrayList<Alumno> listaAlumnos) {
		
		try (FileOutputStream flujoEscritura = new FileOutputStream(RUTA_FICHERO);
			 ObjectOutputStream filtroEscritura = new ObjectOutputStream(flujoEscritura);){
			
			for (Alumno alumno : listaAlumnos) {
				filtroEscritura.writeObject(alumno);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}//serializar
	
	public static void deserializar(String ruta) {
		
		
		try (FileInputStream flujoEntrada = new FileInputStream(ruta);
			 ObjectInputStream filtroEntrada = new ObjectInputStream(flujoEntrada);){
			
				while(flujoEntrada.available() != 0) {
					System.out.println((Alumno) filtroEntrada.readObject());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	

}
