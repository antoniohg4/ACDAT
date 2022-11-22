package ejercicios.ej06;

import java.sql.*;
import java.util.Scanner;

public class Ej06 {
	
	static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			
			String sql = "INSERT INTO profesores (nombre, edad, departamento) VALUES (?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			
			System.out.print("Nombre: ");
			String nombre = kb.nextLine();
			
			System.out.print("\nEdad: ");
			int edad = Integer.parseInt(kb.nextLine());
			
			System.out.print("\nNum departamento: ");
			int numDep = Integer.parseInt(kb.nextLine());
			
			st.setString(1, nombre);
			st.setInt(2, edad);
			st.setInt(3, numDep);
			
			
			
			if (st.executeUpdate() == 1) {
				System.out.println("Exito");
			}else {
				System.out.println("Algo ha fallao");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
	}
}
