import java.sql.*;
import java.util.Scanner;

public class PreparedStatements {
	
	static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			
			//Consulta con interrogantes
			String sql = "INSERT INTO profesores (nombre, edad, departamento) VALUES (?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			
			System.out.print("Nombre: ");
			String nombre = kb.nextLine();
			
			System.out.print("\n Edad: ");
			int edad = Integer.parseInt(kb.nextLine());
			
			System.out.print("\n Num departamento: ");
			int numDep = Integer.parseInt(kb.nextLine());
			
			//Sustituye los interrogantes por strings, ints...(IMPORTANTE EMPIEZAN EN 1, NO EN 0)
			st.setString(1, nombre);
			st.setInt(2, edad);
			st.setInt(3, numDep);
			
			//Ejecuta la update, 1 si es exitoso, otra cosa si no
			
			//Importante que el executeUpdate no lleve (sql), porque se rompe
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
