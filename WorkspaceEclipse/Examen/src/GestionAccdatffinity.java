import java.sql.*;
import java.util.Scanner;

public class GestionAccdatffinity {
	
	static Scanner kb = new Scanner(System.in);
	
	static Connection con;
	
	public static void main(String[] args) {
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accdatffinity", "root", "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int opc = 0;
			
		while (opc != 6) {
			try {
				opc = menu();
				tratarMenu(opc);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public static int menu() {
		System.out.println("== BD ==");
		System.out.println("1.- Crea temas");
		System.out.println("2.- Cargar la Base de datos");
		System.out.println("3.- Alta de usuario");
		System.out.println("4.- Registrar valoracion");
		System.out.println("5.- Búsqueda de peliculas por valoracion");
		System.out.println("6.- Salir");
		System.out.println();
		System.out.print("Opcion: ");
		
		return Integer.parseInt(kb.nextLine());
	}
	
	public static void tratarMenu(int opc) throws SQLException {
		switch (opc) {
		case 1: {
			creaTemas();
			break;
		}
		case 2: {
			cargaBD();
			break;
		}
		case 3: {
			registraNuevoUsuario();
			break;
		}
		case 4: {
			registraValoracion();
			break;
		}
		case 5: {
			busquedaPeliculaPorValoracion();
			break;
		}
		case 6: {
			salir();
			break;
		}
		
		default:
			System.out.println("Opción inválida");
		}
	}
	
	/**
	 * Crea la tabla temas
	 * @throws SQLException
	 */
	public static void creaTemas() throws SQLException {
		Statement st = con.createStatement();

		String sql = "CREATE TABLE accdatffinity.temas (nombreTema varchar(20) NOT NULL,	CONSTRAINT temas_pk PRIMARY KEY (nombreTema));";

		int update = st.executeUpdate(sql);
		
		if (update == 0) {
			System.out.println("Tabla temas creada");
		}else {
			System.out.println("Ha habido un error");
		}
		
		//Añade la columna a peliculas
		sql = "ALTER TABLE accdatffinity.peliculas ADD nombreTema varchar(20) NOT NULL, ADD CONSTRAINT peliculas_FK FOREIGN KEY (nombreTema) REFERENCES accdatffinity.temas(nombreTema);";
	
		update = st.executeUpdate(sql);
		
		if (update == 0) {
			System.out.println("Añadida columna nombreTema a la tabla peliculas");
		}else {
			System.out.println("Ha habido un error");
		}
	}//creaTemas
	
	public static void cargaBD() throws SQLException {
		cargarTemas();
		cargarPeliculas();
		cargarUsuarios();
		cargarOpiniones();
	}//cargaBD

	/**
	 * Carga los temas de la BD
	 * @throws SQLException
	 */
	private static void cargarTemas() throws SQLException {
		Statement st = con.createStatement();

		String sql ="INSERT INTO accdatffinity.temas (nombreTema) VALUES"
				  + "('SUSPENSE'),"
				  + "('DRAMA'),"
				  + "('COMEDIA');";

		int update = st.executeUpdate(sql);
		
		if (update == 0) {
			System.out.println("Temas añadidos");
		}else {
			System.out.println("Ha habido un error");
		}
		
	}//cargarTemas

	/**
	 * Carga las Peliculas de la BD
	 * @throws SQLException
	 */
	private static void cargarPeliculas() throws SQLException {
		Statement st = con.createStatement();

		String sql ="INSERT INTO accdatffinity.peliculas (nombre, anno, nombreTema) VALUES"
				  + "('La vida es bella', 2000, 'DRAMA')"
				  + "('Agora', 2009, 'SUSPENSE'),"
				  + "('Titanic', 1997, 'DRAMA'),"
				  + "('Algo pasa con Mary', 1998, 'COMEDIA'),"
				  + "('Lo dejo cuando quiera', 2019, 'COMEDIA'),"
				  + "('El codigo da Vinci', 2006, 'SUSPENSA');";

		int update = st.executeUpdate(sql);
		
		if (update == 0) {
			System.out.println("Peliculas añadidas");
		}else {
			System.out.println("Ha habido un error");
		}
	}//cargarPeliculas
	
	/**
	 * Carga los usuarios de la BD
	 * @throws SQLException
	 */
	private static void cargarUsuarios() throws SQLException {
		Statement st = con.createStatement();

		String sql ="INSERT INTO accdatffinity.usuarios (nombreUsu, nombreLargo, edad) VALUES"
				  + "('gianPac', 'Gian Luca Pacceli', 18),"
				  + "('mieche', 'Miguel Echevarria', 23),"
				  + "('pietar', 'PietroArmani', 20);";

		int update = st.executeUpdate(sql);
		
		if (update == 0) {
			System.out.println("Usuarios añadidos");
		}else {
			System.out.println("Ha habido un error");
		}
		
	}//cargarUsuarios
	
	/**
	 * Carga las opiniones de la BD
	 * @throws SQLException
	 */
	private static void cargarOpiniones() throws SQLException {
		Statement st = con.createStatement();

		String sql ="INSERT INTO accdatffinity.opiniones (nombreUsu, codigoPeli, valoracion) VALUES"
				  + "('gianpac', 1, '9'),"
				  + "('gianpac', 2, '10'),"
				  + "('gianpac', 3, '6'),"
				  + "('mieche', 1, '9'),"
				  + "('mieche', 2, '5'),"
				  + "('mieche', 4, '4'),"
				  + "('mieche', 5, '1'),"
				  + "('pietar', 2, '10'),"
				  + "('pietar', 4, '9'),"
				  + "('pietar', 5, '6');";

		int update = st.executeUpdate(sql);
		
		if (update == 0) {
			System.out.println("Opioniones añadidas");
		}else {
			System.out.println("Ha habido un error");
		}
	}//cargarOpiniones
	
	public static void registraNuevoUsuario() throws SQLException {
		String sql = "INSERT INTO accdatffinity.usuarios (nombreUsu, nombreLargo, edad) VALUES (?,?,?);";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		System.out.print("Nombre corto: ");
		String nombreCorto = kb.nextLine();
		
		System.out.print("Nombre largo: ");
		String nombreLargo = kb.nextLine();
		
		System.out.print("Edad: ");
		int edad = Integer.parseInt(kb.nextLine());
		
		st.setString(1, nombreCorto);
		st.setString(2, nombreLargo);
		st.setInt(3, edad);
		
		int update = st.executeUpdate();
		
		if (update == 0) {
			System.out.println("Usuario añadido correctamente");
		}else {
			System.out.println("El nombre corto ya existe"); //Al ser clave primaria, es la unica que no se puede repetir
		}
	}//registraNuevoUsuario
	
	//TODO
	public static void  registraValoracion() throws SQLException {
		String sql = "INSERT INTO accdatffinity.opiniones (nombreUsu, codigoPeli, valoracion) VALUES('?', SELECT codigoPeli FROM peliculas WHERE nombre = ?, '?');";
		
		PreparedStatement st = con.prepareStatement(sql);

			System.out.print("Nombre de usuario: ");
			String nombreUsu = kb.nextLine();
			st.setString(1, nombreUsu);
				
			String nomPelicula = kb.nextLine();
			st.setString(2, nomPelicula);
			
		
		
		
		System.out.print("Valoracion: ");
		String valoracion = kb.nextLine();
		

		st.setString(3, valoracion);
		
		int update = st.executeUpdate();
		
		if (update == 0) {
			System.out.println("Usuario añadido correctamente");
		}else {
			System.out.println("El nombre corto ya existe"); //Al ser clave primaria, es la unica que no se puede repetir
		}
	}//registraValoracion
	
	public static void busquedaPeliculaPorValoracion() throws SQLException {
		String sql = "SELECT p.nombre, o.valoracion FROM peliculas p JOIN opiniones o ON p.codigoPeli = o.codigoPeli WHERE o.valoracion >= ? AND o.valoracion <= ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		System.out.print("Valoracion mínima: ");
		int min = Integer.parseInt(kb.nextLine());

		System.out.print("Valoracion máxima: ");
		int max = Integer.parseInt(kb.nextLine());
		
		st.setInt(1, min);
		st.setInt(2, max);
		
		ResultSet rs = st.executeQuery();
		
		boolean hayFilas = false;
		while (rs.next()) {
			hayFilas = true;
			System.out.println(rs.getString("tables_in_pruebaconexionbd"));
		}
		if (!hayFilas) {
			System.out.println("No hay resultados que mostrar");
		}

		// Cerramos ResultSet y Statement
		rs.close();
		st.close();
	}//busquedaPelicula
	
	public static void salir() throws SQLException {
		con.close();
	}

}














