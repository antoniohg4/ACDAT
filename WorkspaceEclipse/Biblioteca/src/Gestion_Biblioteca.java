import java.sql.*;
import java.util.Iterator;
import java.util.Scanner;

public class Gestion_Biblioteca {
	static Scanner kb = new Scanner(System.in);
	static Connection con = null;
	
	/**
	 * Crea la conexion con la base de datos que se va a usar
	 */
	public static void crearConexion() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}//crearConexion
	
	
	/**
	 * Muestra el menu
	 */
	public static void menu() {
		System.out.println("==== MENU ====");
		System.out.println("1.- Alta socio");
		System.out.println("2.- Alta libro");
		System.out.println("3.- Búsqueda por nombre");
		System.out.println("4.- Realizar un préstamo");
		System.out.println("5.- Finalizar un préstamo");
		System.out.println("6.- Genera informe");
		System.out.println("7.- Pasar a histórico");
		System.out.println("8.- Salir");
	}//menu
	
	/**
	 * Trata la eleccion del menu
	 * @param opc
	 * @throws SQLException 
	 */
	public static void tratarMenu(int opc) throws SQLException {
		switch (opc) {
		case 1: {	//Alta socio
			altaSocio();
			break;
		}
		case 2: {	//Alta libro
			altaLibro();
			break;
		}
		case 3: {	//Busqueda por nombre
			busquedaNombre();
			break;
		}
		case 4: {	//Realizar préstamo
			realizarPrestamo();
			break;
		}
		case 5: {	//Finalizar un préstamo
			finalizarPrestamo();
			break;
		}
		case 6: {	//Genera informe
			generarInforme();
			break;
		}
		case 7: {	//Pasar a histórico
			pasarHistorico();
			break;
		}
		case 8: {	//Salir
			salir();
			break;
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + opc);
		}
	}//tratarMenu
	
	
	/**
	 * Da de alta un socio
	 * @throws SQLException 
	 */
	public static void altaSocio() throws SQLException {
		//TODO Controlar si existe
		System.out.println("---- Alta de socio ----");
		String nombre, dni;
		
		System.out.print("Nombre: ");
		nombre = kb.nextLine();
		System.out.print("DNI: ");
		dni = kb.nextLine();
		
		PreparedStatement st = con.prepareStatement("INSERT INTO socio (nombre, dni) VALUES (?,?);");
		
		st.setString(1, nombre);
		st.setString(2, dni);
		
		if (st.execute()) {
			System.out.println("Operación exitosa");
		}else {
			System.out.println("Algo ha salido mal");
		}
		
	}//altaSocio
	
	
	/**
	 * Da de alta un libro
	 * @throws SQLException 
	 */
	public static void altaLibro() throws SQLException {
		System.out.println("---- Alta de libro ----");
		String titulo, isbn;
		int numCopias;
		
		System.out.print("Titulo: ");
		titulo = kb.nextLine();
		System.out.print("ISBN: ");
		isbn = kb.nextLine();
		System.out.print("Numero de copias: ");
		numCopias = Integer.parseInt(kb.nextLine());
		
		PreparedStatement st = con.prepareStatement("INSERT INTO socio (nombre, dni) VALUES (?,?);");
		
		st.setString(1, isbn);
		st.setString(2, titulo);
		
		if (st.execute()) {
			System.out.println("Operación exitosa");
		}else {
			System.out.println("Algo ha salido mal");
		}
		
		Statement stCopias = con.createStatement();
		String codCopia;
		int resultadoUpdate = 0;
		
		//Intenta crear las copias
		while (resultadoUpdate == 0) {
			for (int i = 1; i <= numCopias; i++) {
				codCopia = isbn + i;
				stCopias.executeUpdate("INSERT INTO copia (codcopia, isbn) VALUES (" + codCopia + ", isbn);");
			}
		}
		
		//Comprueba si las copias se han creado correctamente
		if (resultadoUpdate == 0) {
			System.out.println("Copias creadas exitosamente");
		}else {
			System.out.println("Algo ha salido mal creando las copias");
		}
		
		
	}
	
	
	/**
	 * Busca y muestra los titulos por un nombre dado
	 */
	public static void busquedaNombre() {
		
	}
	
	
	/**
	 * Hace un prestamo
	 */
	public static void realizarPrestamo() {
		
	}
	
	
	/**
	 * Finaliza un prestamo
	 */
	public static void finalizarPrestamo() {
		
	}
	
	
	/**
	 * Genera un informe
	 */
	public static void generarInforme() {
		
	}
	
	/**
	 * Pasa al historico
	 */
	public static void pasarHistorico() {
		
	}
	
	
	/**
	 * Salir
	 * @throws SQLException 
	 */
	public static void salir() throws SQLException {
		con.close();
	}
	
}
