package ejercicios.ej05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej05 {
	public static void main(String[] args) {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			Statement st = con.createStatement();
			
			String sql = "INSERT INTO profesores (nombre, edad) VALUES ('Fran', 32)";
			int resultado = st.executeUpdate(sql);

			if (resultado == 1) {
				System.out.println("Actualización exitosa");
			}else {
				System.out.println("Algo ha salido mal");
			}
			
			// Cerramos ResultSet y Statement
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
	}//main
}
