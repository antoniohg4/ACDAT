package ejercicio2;

import java.io.Serializable;
import java.util.*;

public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre, apellido1, apellido2;
	private Direccion direccion;
	private ArrayList<String> aficiones;
	
	public Cliente(String nombre, String apellido1, String apellido2, String tipoVia, String nombreCalle, String numero) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = new Direccion(Integer.parseInt(tipoVia), nombreCalle, Integer.parseInt(numero));
		this.aficiones = new ArrayList<String>();
	}
	
	public  void annadirAficion(String aficion) {
		this.aficiones.add(aficion);
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " " + apellido1 + " " + apellido2 + "\n"
				+"Direccion: " + direccion + "\n"
				+ "Aficiones=" + aficiones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String imprimirAficiones() {
		StringBuilder sbaficiones = new StringBuilder();
		
		for (String string : this.aficiones) {
			sbaficiones.append(string + ", ");
		}
		
		return sbaficiones.toString();
	}

	public ArrayList<String> getAficiones() {
		return aficiones;
	}

	public void setAficiones(ArrayList<String> aficiones) {
		this.aficiones = aficiones;
	}
	
	
	
	
	
}
