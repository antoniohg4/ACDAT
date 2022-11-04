package c.Serialización;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serializacion {

	private static final String RUTA_SERIALIZADO = "";
	
	private static List<Elemento> nombre_lista = null;
	
	public static void serializar() {
		try (FileOutputStream flujoEscritura = new FileOutputStream(RUTA_SERIALIZADO);
			 ObjectOutputStream filtroEscritura = new ObjectOutputStream(flujoEscritura);){
				
				for (Elemento nombre : nombre_lista) {		//La clase que se use en Elemento debe implementar Serializable
					filtroEscritura.writeObject(nombre);
				}

		} catch (Exception e) {
				// TODO: handle exception
		}
	}
}
