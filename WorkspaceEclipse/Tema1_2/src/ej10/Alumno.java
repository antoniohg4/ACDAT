package ej10;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Alumno  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String apellido1, apellido2, nombre;
	int edad;
	Date fMatriculacion;
	List<ModuloProfesional> matricula;
	
	public Alumno(String apellido1, String apellido2, String nombre, int edad, String fecha) {
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nombre = nombre;
		
		this.edad = edad;
		
		try {
			this.fMatriculacion = new SimpleDateFormat("ddMMyyyy").parse(fecha);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		matricula = new ArrayList<ModuloProfesional>();
	}
	
	public  void annadirModulos (ModuloProfesional m) {
		matricula.add(m);
	}

	@Override
	public String toString() {
		return "Alumno"  + "\n"
				+ "apellido1=" + apellido1 + "\n"
				+ "apellido2=" + apellido2 + "\n"
				+ "nombre=" + nombre + "\n"
				+ "edad=" + edad + "\n"
				+ "fMatriculacion=" + fMatriculacion + "\n"
				+ "matricula=" + matricula + "\n";
	}

	
	
	
	
}
