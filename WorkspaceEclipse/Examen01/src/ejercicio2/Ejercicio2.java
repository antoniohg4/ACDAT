package ejercicio2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class Ejercicio2 {

	private static final String RUTA_SERIALIZADO = "clientes.ser";

	private static String RUTA_CSV = "clientes.csv";
	
	private static ArrayList<Cliente> listaClientes;
	
	public static void main(String[] args) {
		listaClientes = new ArrayList<Cliente>();
		cargaClientes(listaClientes);
		serializaClientes();
		deserializaClientes();
	}
	
	

	

	public static void cargaClientes(ArrayList<Cliente> listaClientes) {
		String linea;
		String[] partes;
		
		
		try(FileReader flujoLectura = new FileReader(RUTA_CSV);
			BufferedReader filtroLectura = new BufferedReader(flujoLectura);){
			
			linea = filtroLectura.readLine();
			
			while(linea != null) {
				partes = linea.split(";");
				
				//Crea el cliente
				Cliente c = new Cliente(partes[0], partes [1], partes [2], partes [3], partes [4], partes [5]);
				
				//Añade las aficiones
				for (int i = 5; i < partes.length; i++) {
					c.annadirAficion(partes[i]);
				}
				
				//Añade el cliente a la lista
				listaClientes.add(c);
				
				
				linea = filtroLectura.readLine();
			}
			
		}catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	private static void serializaClientes() {
		try (FileOutputStream flujoEscritura = new FileOutputStream(RUTA_SERIALIZADO);
			ObjectOutputStream filtroEscritura = new ObjectOutputStream(flujoEscritura);){
					
					for (Cliente c : listaClientes) {
						filtroEscritura.writeObject(c);
					}

			} catch (Exception e) {
					// TODO: handle exception
			}
		
	}
	
	private static void deserializaClientes() {
		Cliente c;
		try (FileInputStream flujoEntrada = new FileInputStream(RUTA_SERIALIZADO);
			ObjectInputStream filtroEntrada = new ObjectInputStream(flujoEntrada);){
			
					System.out.println(flujoEntrada.available());
					
					while(flujoEntrada.available() != 0) {
						c = (Cliente) filtroEntrada.readObject();
						System.out.println(c);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
		
	}

}
