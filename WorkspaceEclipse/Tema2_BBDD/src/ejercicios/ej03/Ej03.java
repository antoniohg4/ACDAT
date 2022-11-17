package ejercicios.ej03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej03 {
	
public static void main(String[] args) {
	Connection con = null;

	try {
		// Si trabajaramos con JDBC < 4.0 tendríamos que indicar esta línea
		// para indicar el tipo de driver que tiene que cargar DriverManager.
		// Class.forName("com.mysql.jdbc.Driver");

		// Obtenemos la conexión a partir de la URL jdbc correspondiente
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");

		// Obtenermos una instancia de un objeto que implementa la interface statement.
		Statement st = con.createStatement();

		String sql = "SELECT * FROM profesores WHERE edad%2 = 0 ORDER BY nombre, edad ASC";

		ResultSet rs = st.executeQuery(sql);

		// De esta forma vamos a saber si hay tablas o no
		boolean hayFilas = false;
		while (rs.next()) {
			hayFilas = true;
			System.out.println(rs.getString("nombre")+" "+rs.getString("edad")+" "+rs.getString("departamento"));
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
