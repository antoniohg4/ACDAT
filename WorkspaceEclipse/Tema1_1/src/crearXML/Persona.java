package crearXML;

public class Persona {
	String nombre;
	int edad;
	char sexo;
	char repetidor;
	
	public Persona(String nombre, int edad, char sexo, char repetidor) {
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.repetidor = repetidor;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public char getSexo() {
		return sexo;
	}

	public char getRepetidor() {
		return repetidor;
	}

	
	
	
}
