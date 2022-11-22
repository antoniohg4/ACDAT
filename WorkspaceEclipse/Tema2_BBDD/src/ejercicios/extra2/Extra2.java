package ejercicios.extra2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Extra2 {
	public static void main(String[] args) {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");

			Statement st = con.createStatement();

			String sql = "SELECT * FROM profesores";

			ResultSetMetaData rsmd = st.executeQuery(sql).getMetaData();
			
			List<String> listaNombresColumnas = new ArrayList<String>();
			
			//NOMBRES DE LAS COLUMNAS
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.print(rsmd.getColumnName(i) + "\t\t\t");
				listaNombresColumnas.add(rsmd.getColumnName(i));
			}
			
			System.out.println();
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				for (int i = 1; i <= listaNombresColumnas.size(); i++) {
					System.out.print(rs.getString(i) + "\t\t\t");
				}
				System.out.println();
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
