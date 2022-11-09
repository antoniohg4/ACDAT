package ejercicio2;

import java.io.Serializable;

public class Direccion  implements Serializable{
	private String tipoVia;
	private String nombreCalle;
	private int numero;
	
	public Direccion(int tipo, String calle, int numero) {
		if (tipo == 1) {
			this.tipoVia = "Calle";
		}else {
			this.tipoVia = "Avenida";
		}
		this.nombreCalle = calle;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return tipoVia + "," + nombreCalle + "," + numero;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getNombreCalle() {
		return nombreCalle;
	}

	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
	
}
