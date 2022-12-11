import java.sql.*;

public class Consultas {
	public static void main(String[] args) {
		Connection con = null;
		
		try {
			// Obtenemos la conexión a partir de la URL jdbc correspondiente
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PruebaConexionBD", "root", "");
																		//NombreBD			usuario   Contraseña
			// Obtenermos una instancia de un objeto que implementa la interface statement.
			Statement st = con.createStatement();

			//Consulta
			String sql = "SHOW TABLES";

			//Objeto con los resultados
			ResultSet rs = st.executeQuery(sql);

			// De esta forma vamos a saber si hay tablas o no
			boolean hayFilas = false;
			
			//Bucle que recorre los resultados
			while (rs.next()) {
				hayFilas = true;
									//getString("nombre de la columna")
				System.out.println(rs.getString("tables_in_pruebaconexionbd"));
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
			//Cierra la conexión
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
