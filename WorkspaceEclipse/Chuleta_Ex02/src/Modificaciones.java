import java.sql.*;

public class Modificaciones {
	public static void main(String[] args) {
		Connection con = null;

		try {
			//Conexion y statement
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			Statement st = con.createStatement();
			
			//Consulta
			String sql = "INSERT INTO profesores (nombre, edad) VALUES ('Fran', 32)";
			
			//Una modificacion necesita ejecutarse con el metodo executeUpdate y devuelve un int
			int resultado = st.executeUpdate(sql);

			if (resultado == 1) {
				System.out.println("Actualización exitosa"); //Si el resultado es 1, todo ok
			}else {
				System.out.println("Algo ha salido mal"); //Si es algo distinto, ha habido un error
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
	}
}
