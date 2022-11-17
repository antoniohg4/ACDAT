package ejercicios.extra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.Statement;

public class Extra {

	static Scanner kb = new Scanner(System.in);
	
	static Connection con = null;
	static String sql = null;
	
	public static void main(String[] args) {
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			int opc = 0;
			
			while (opc != 4) {
				opc = mostrarMenu();
				tratarMenu(opc);
				System.out.println("\n");
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}//main
	
	public static int mostrarMenu() {
		System.out.println("==== MENU ====");
		System.out.println("1.- Listar Profesores ordenados");
		System.out.println("2.- Listar Departamentos ordenados");
		System.out.println("3.- Listar departamentos y profesores");
		System.out.println("4.- Salir");
		System.out.print("Elige una opción: ");
		int opc = Integer.parseInt(kb.nextLine());
		
		System.out.println("\n");
		return opc;
	}//mostrarMenu
	
	public static void tratarMenu(int opc) throws SQLException{
		switch (opc) {
			case 1: {
				listarProfesores();
				break;
			}
			
			case 2: {
				listarDepartamentos();
				break;
			}
			
			case 3: {
				listarDepartamentosYProfesores();
				break;
			}
			
			case 4: {
				System.out.println("Adiós");
				con.close();
				break;
			}
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + opc);
		}
	}//tratarMenu


	/**
	 * Muestra los profesores con la edad y el nombre del departamento
	 * @throws SQLException
	 */
	private static void listarProfesores() throws SQLException{
		Statement st = con.createStatement();
		
		sql = "SELECT p.nombre FROM profesores p ORDER BY p.nombre ASC";
		
		ResultSet rs = st.executeQuery(sql);
		
		boolean hayFilas = false;
		while (rs.next()) {
			hayFilas = true;
			System.out.println(rs.getString("nombre"));
		}
		if (!hayFilas) {
			System.out.println("No hay resultados que mostrar");
		}
		
		rs.close();
		st.close();
	}//listarProfesores
	
	
	/**
	 * Muestra toda la informacion de los departamentos
	 * @throws SQLException
	 */
	private static void listarDepartamentos() throws SQLException{
		Statement st = con.createStatement();
		
		sql = "SELECT d.nombre FROM departamentos d ORDER BY d.nombre ASC";
		
		ResultSet rs = st.executeQuery(sql);
		
		boolean hayFilas = false;
		while (rs.next()) {
			hayFilas = true;
			System.out.println(rs.getString("nombre"));
		}
		if (!hayFilas) {
			System.out.println("No hay resultados que mostrar");
		}
		
		rs.close();
		st.close();		
	}//listarDepartamentos
	
	
	/**
	 * Muestra los departamentos con sus profesores
	 * @throws SQLException
	 */
	private static void listarDepartamentosYProfesores() throws SQLException{
		Statement st = con.createStatement();
		String sqlProfesores = null;
		
		sql = "SELECT d.nombre FROM departamentos d";
		ResultSet rs = st.executeQuery(sql);
		
		String nombre = null;
		Statement stProfesores = con.createStatement();
		ResultSet rsProfesores = null;
		
		boolean hayFilas = false;
		
		//Imprime los departamentos
		while (rs.next()) {
			hayFilas = true;
			nombre = rs.getString("d.nombre");
			System.out.println("*" + nombre);
			
			sqlProfesores = "SELECT nombre FROM profesores WHERE departamento = (SELECT departamento FROM departamentos WHERE nombre =\""+ nombre +"\")";
			rsProfesores = stProfesores.executeQuery(sqlProfesores);
			
			//Imprime los profesores de cada asignatura
			while (rsProfesores.next()) {
				System.out.println("\t -" + rsProfesores.getString("nombre"));
			}	
		}
		if (!hayFilas) {
			System.out.println("No hay resultados que mostrar");
		}
		
		rs.close();
		st.close();		
		rsProfesores.close();
		stProfesores.close();
		
	}//listarDEpartamentosYProfesores

}
