import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MetaData {
	public static void main(String[] args) {
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");

			Statement st = con.createStatement();

			String sql = "SELECT * FROM profesores";
			
			//ResultSetMetaData es un objeto que guarda los metadatos de la consulta
			ResultSetMetaData rsmd = st.executeQuery(sql).getMetaData();
			
			List<String> listaNombresColumnas = new ArrayList<String>();
			
			//SACAR NOMBRES DE LAS COLUMNAS
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.print(rsmd.getColumnName(i) + "\t\t\t");
				listaNombresColumnas.add(rsmd.getColumnName(i));
			}
			
			System.out.println();
			
			//Continua como de normal
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
