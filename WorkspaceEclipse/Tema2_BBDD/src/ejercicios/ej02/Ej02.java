package ejercicios.ej02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ej02 {
	
	private static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection con = null;

		try {
			// Si trabajaramos con JDBC < 4.0 tendríamos que indicar esta línea
			// para indicar el tipo de driver que tiene que cargar DriverManager.
			// Class.forName("com.mysql.jdbc.Driver");

			// Obtenemos la conexión a partir de la URL jdbc correspondiente
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PruebaConexionBD", "root", "");

			// Obtenermos una instancia de un objeto que implementa la interface statement.
			Statement st = con.createStatement();
			
			//Muestra las bases de datos existentes
			String sql = "SELECT schema_name FROM information_schema.schemata";

			ResultSet rs = st.executeQuery(sql);
			
			System.out.println("BASES DE DATOS");

			// Pinta las bases de datos
			boolean hayFilas = false;
			while (rs.next()) {
				hayFilas = true;
				System.out.println(rs.getString("schema_name"));
			}
			if (!hayFilas) {
				System.out.println("No hay resultados que mostrar");
			}
			
			//Usuario selecciona una
			System.out.print("\nSeleccione la base de datos: ");
			String bd = kb.nextLine();
			System.out.println();
			
			//Usa la bd seleccionada
			st.execute("use " + bd);

			//Pinta todas las tablas de la bd
			sql = "SHOW TABLES";

			rs = st.executeQuery(sql);
			
			hayFilas = false;
			while (rs.next()) {
				hayFilas = true;
				System.out.println(rs.getString("tables_in_" + bd));
			}
			if (!hayFilas) {
				System.out.println("No hay resultados que mostrar");
			}
			

			// Cerramos ResultSet y Statement
			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}
}
