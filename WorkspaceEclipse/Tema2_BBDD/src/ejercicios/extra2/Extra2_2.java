package ejercicios.extra2;

import java.sql.*;
import java.util.*;

public class Extra2_2 {
	
	static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			Statement st = con.createStatement();
			
			
			System.out.print("Edad mínima: ");
			int edad = Integer.parseInt(kb.nextLine());
			

			String sql = "SELECT * FROM profesores WHERE edad >= " + edad;

			java.sql.ResultSetMetaData rsmd = st.executeQuery(sql).getMetaData();
			
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
