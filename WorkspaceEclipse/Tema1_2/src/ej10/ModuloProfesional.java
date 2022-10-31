package ej10;

import java.io.Serializable;

public class ModuloProfesional implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int numHoras;
	String nombreModulo;
	
	public ModuloProfesional(String nombreModulo, int numHoras) {
		this.nombreModulo = nombreModulo;
		this.numHoras = numHoras;
	}

	public int getNumHoras() {
		return numHoras;
	}

	public void setNumHoras(int numHoras) {
		this.numHoras = numHoras;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	@Override
	public String toString() {
		return "ModuloProfesional [numHoras=" + numHoras + ", nombreModulo=" + nombreModulo + "]";
	}
	
	
	
}
