package zPracticaEx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej01 {
	public static void main(String[] args) {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			Statement st = con.createStatement();
			String sql = "SHOW TABLES";
			ResultSet rs = st.executeQuery(sql);

			// De esta forma vamos a saber si hay tablas o no
			boolean hayFilas = false;
			while (rs.next()) {
				hayFilas = true;
				System.out.println(rs.getString("tables_in_instituto"));
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
