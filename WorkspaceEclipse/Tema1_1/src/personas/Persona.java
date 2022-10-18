package personas;

public class Persona implements Comparable<Persona>{
	
	String nombre;
	int edad;
	
	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + "\n" +
				"Edad: " + edad;
	}

	@Override
	public int compareTo(Persona o) {
		return Integer.compare(this.edad, o.edad);
	}
	
	
	
}
