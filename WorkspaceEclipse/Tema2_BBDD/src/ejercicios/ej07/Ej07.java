package ejercicios.ej07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

public class Ej07{

	private static final int SALIR=7;
	
	private static Scanner teclado=new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conexion=null;
		conexion=conexionBBDD();
		
		//Creamos la base de datos
		crearBBDD(conexion);
		
		//Conectamos y creamos las tablas
		conectarBBDDYCrearTablas(conexion);
		
		int opcion;
		
		do {
			//Mostramos el menu y pedimos una opcion
			opcion=mostrarYTratarMenu("Inserte una opcion: ");
			//Comprobamos si es correcta
			comprobarOpcion(opcion);
			//Y tratamos esa opcion
			tratarOpcion(opcion,conexion);
			
		}while(opcion!=SALIR); //Mientras la opcion insertada sea diferente a la de salir, seguirá pediendo una opcion y tratándola.
		
		//Cerrar la conexion
        if (conexion != null)
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		System.out.println("Programa finalizado");
	}

	private static Connection conexionBBDD() {
		Connection conexion=null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexion;
	}

	private static void crearBBDD(Connection conexion) {
		try {
			//Nos conectamos a nuestro servidores
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");

			//Sql para la creacion de la base de datos
			String sql="CREATE DATABASE  IF NOT EXISTS ejercicio7 CHARACTER SET utf8;";
			
			Statement declaracion= conexion.createStatement();
			
			//Comprobar si se ha creado o no
			int resultado=declaracion.executeUpdate(sql);
			
			//Nos devuelve 1 si se ha creado, otro valor si no se ha creado
			if(resultado==1) {
				System.out.println("Base de datos creada correctamente");
			}else {
				System.out.println("La base de datos no ha podido ser creada o ya estaba creada");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * Método que le pasamos la conexion con la bbdd creada anteriormente y creamos las tablas
	 * @param conexion
	 * @return resultado
	 */
	private static ResultSet conectarBBDDYCrearTablas(Connection conexion) {
		ResultSet resultado = null;

		try {

			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio7", "root", "");
			Statement declaracion = conexion.createStatement();

			//Mensaje para informar que se ha conectado o no a la BBDD
			
				System.out.println("Conexion a la base de datos establecida");
				
				//Declaraciones SQL para crear las tablas si no existen
				String sqlCrearAlumnos = "CREATE TABLE IF NOT EXISTS alumnos (dni varchar(9) NOT NULL,nombre varchar(45) NULL,apellido1 varchar(45) NULL, apellido2 varchar(45) NULL, email varchar(45) NULL,edad INT(2) NULL, CONSTRAINT alumnos_pk PRIMARY KEY (dni))";
				String sqlCrearModulos = "CREATE TABLE IF NOT EXISTS modulos (codigo INT(6) NOT NULL,descripcion VARCHAR(60) NOT NULL,CONSTRAINT PRIMARY KEY (codigo))";
				String sqlMatriculas = "CREATE TABLE IF NOT EXISTS matriculas (dni varchar(9) NOT NULL,codigo INT(6) NOT NULL,CONSTRAINT matriculas_FK1 FOREIGN KEY(dni) REFERENCES alumnos(dni),CONSTRAINT matriculas_FK2 FOREIGN KEY(codigo) REFERENCES modulos(codigo))";
				
				//Ejecuta creacion de Tabla alumnos si no existe e imprime mensaje de tabla creada
				comprobarTabla(declaracion, sqlCrearAlumnos); 
				
				//Ejecuta creacion de Tabla modulos si no existe e imprime mensaje de tabla creada
				comprobarTabla(declaracion, sqlCrearModulos);
				
				//Ejecuta creacion de Tabla matriculas si no existe e imprime mensaje de tabla creada
				comprobarTabla(declaracion, sqlMatriculas);
				
		} catch (SQLSyntaxErrorException e) {
			System.out.println("ERROR: Sintaxis SQL introducida erronea");
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("No mames me corri");
		}
		return resultado;
		
	}

	private static void comprobarTabla(Statement declaracion, String sql) throws SQLException {
		int estadoSql;
		estadoSql = declaracion.executeUpdate(sql);
		if (estadoSql == 1) {
			System.out.println("Tabla"+sql+ "creada");
		}
	}

	private static void tratarOpcion(int opcion, Connection conexion) {
		
		//Dependiendo de la opcion la tratamos de una manera u otra
		
		if(opcion==1) {
			//Dar de alta a un alumno
			tratarOpcion1(conexion);
		}
		
		if(opcion==2) {
			//Dar de alta modulo profesional
			tratarOpcion2(conexion);
		}
		
		if(opcion==3) {
			//Matricular alumno modulo profesional
			tratarOpcion3(conexion);
		}
		
		if(opcion==4) {
			//Desmatricular alumno de modulo profesional
			tratarOpcion4(conexion);
		}
		
		if(opcion==5) {
			//Listar alumnos de un modulo profesional
			tratarOpcion5(conexion);
		}
		
		if (opcion==6) {
			//Listar modulos de un alumno
			tratarOpcion6(conexion);
		}
	}

	/**
	 * Método que trata la opcion 6
	 * @param conexion 
	 */
	private static void tratarOpcion6(Connection conexion) {

		try {
		
			//Insertamos un dni y nos muestra los códigos de los modulos que esta matriculado ese DNI
			String sql="SELECT codigo FROM ejercicio7.matriculas WHERE dni=(?)";
			PreparedStatement st=conexion.prepareStatement(sql);
			
			
			String dni=pedirCadena("Introduzca el dni del alumno");
			
			//Insertamos el valor que hemos introducido
			st.setString(1, dni);
			
			ResultSet rs = st.executeQuery();

			System.out.println(("Modulos en los que esta matriculado: "));
			
			//Mostramos los datos
			boolean hayFilas = false;
			while (rs.next()) {
				hayFilas = true;
				System.out.println(rs.getString("codigo"));
			}
			
			if (!hayFilas) {
				System.out.println("No hay modulos que mostrar");
			}
		
			st.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

	/**
	 * Método que trata la opcion 5
	 * @param conexion 
	 */
	private static void tratarOpcion5(Connection conexion) {

		try {

			//Selecionamos el nombre del alumno y el codigo de la matricula donde coincidan dni de alumno y matriculas por el codigo que
			//hemos insertado
						
			String sql="SELECT a.nombre, m.codigo FROM ejercicio7.alumnos a INNER JOIN ejercicio7.matriculas m ON a.dni = m.dni WHERE m.codigo=(?)";
			PreparedStatement st=conexion.prepareStatement(sql);
			
			//Pedimos el codigo
			int codigo=pedirEntero("Introduzca el codigo del modulo profesional");
			
			//Lo insertamos en el sql
			st.setInt(1, codigo);
			
			ResultSet rs = st.executeQuery();

			//String auxiliar para no repetir codigo posteriormente
			String aux="";
			
			boolean hayFilas = false;
			while (rs.next()) {
				hayFilas = true;
				//Si no se ha mostrado el codigo del modulo todavia, se muestra junto con el primer alumno
				if (!aux.equals(rs.getString("codigo"))) {
					aux=rs.getString("codigo");
					System.out.println("************************");
					System.out.println("Codigo del modulo: ");
					System.out.println(rs.getString("codigo"));
					System.out.println("************************");
					System.out.println("Datos alumno:");
					System.out.println(rs.getString("nombre"));
					System.out.println("------------------");
				}else {// Si ya se ha mostrado, solo se añaden datos de alumnos a la consola
					System.out.println("Alumno:");
//					System.out.println(rs.getString("dni"));
					System.out.println(rs.getString("nombre"));
					System.out.println("------------------");
					System.out.println("");
//					System.out.println(rs.getString("apellido1"));
//					System.out.println(rs.getString("apellido2"));
//					System.out.println(rs.getString("email"));
//					System.out.println(rs.getString("edad"));
//					
				}		

			}
			if (!hayFilas) {
				System.out.println("No hay resultados que mostrar");
			}
		
			st.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que trata la opcion 4
	 * @param conexion 
	 */
	private static void tratarOpcion4(Connection conexion) {

		try {

			//Sql donde borramos las matriculas con el dni y codigo que introduzcamos
			String sql="DELETE FROM ejercicio7.matriculas\r\n"
					+ "WHERE dni=? AND codigo=?;\r\n"
					+ "";
			PreparedStatement st = conexion.prepareStatement(sql);
			
			//Pedimos dni y codigo
			String dni=pedirCadena("Introduzca el dni del alumno");
			int codigo=pedirEntero("Introduzca el codigo del modulo");
			
			//Y lo insertamos en la sql
			st.setString(1,dni);
			st.setInt(2, codigo);

			int rs = st.executeUpdate();
			
			//Si el int del resultado nos devuelve 1, se ha borrado correctamente, sino no.
			if (rs==1) {
				System.out.println("Matricula borrada");
			}else {
				System.out.println("La matricula no se ha podido borrar");
			}
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Método para tratar la opcion 3
	 * @param conexion 
	 */
	private static void tratarOpcion3(Connection conexion) {

		try {

			//SQL para mostrar todos los alumnos
			String sqlAlumnos="SELECT * FROM ejercicio7.alumnos";
			//SQL para mostrar todos los modulos
			String sqlMp="SELECT * FROM ejercicio7.modulos";
			
			Statement mostrarAlumnos=conexion.createStatement();
			Statement mostrarMp=conexion.createStatement();
			
			ResultSet resultadoAlumnos=mostrarAlumnos.executeQuery(sqlAlumnos);
			ResultSet resultadoModulos=mostrarMp.executeQuery(sqlMp);
			
			boolean hayFilasAlumnos = false;
			
			//Mostramos los datos de los alumnos
			System.out.println("Alumnos en lista: ");
			while (resultadoAlumnos.next()) {
				hayFilasAlumnos = true;
				System.out.print(resultadoAlumnos.getString("nombre"));
				System.out.print(" ");
				System.out.print(resultadoAlumnos.getString("dni"));
				System.out.println("\n");
			}
			if (!hayFilasAlumnos) {
				System.out.println("No hay alumnos que mostrar");
			}
			
			System.out.println("Modulos profesionales disponibles");
			//Mostramos todos los datos de los modulos
			boolean hayFilasModulos = false;
			while (resultadoModulos.next()) {
				hayFilasModulos = true;
				System.out.println(resultadoModulos.getString("codigo"));
				System.out.println("\n");
			}
			if (!hayFilasModulos) {
				System.out.println("No hay modulos disponibles");
			}

			//Insertamos en las matriculas los valores que pedimos
			String sql="INSERT INTO ejercicio7.matriculas (dni,codigo) VALUES (?,?)";
			PreparedStatement st = conexion.prepareStatement(sql);
			
			//Solicitamos valores
			String dni=pedirCadena("Introduzca el dni del alumno");
			int codigo=pedirEntero("Introduzca el codigo del modulo");
			
			//E insertamos
			st.setString(1,dni);
			st.setInt(2, codigo);

			int rs = st.executeUpdate();
			
			//Recogemos valor y definimos resultado
			if (rs!=1) {
				System.out.println("La matricula no se ha podido registrar");
			}else {
				System.out.println("Matricula registrada");
			}
			
			st.close();
			resultadoAlumnos.close();
			resultadoModulos.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

	/**
	 * Método que trata la opcion 2
	 * @param conexion 
	 */
	private static void tratarOpcion2(Connection conexion) {

		try {
			
			//Insertamos en los modulos los valores que pedimos
			String sql="INSERT INTO ejercicio7.modulos (codigo,descripcion) VALUES (?,?)";
			PreparedStatement st = conexion.prepareStatement(sql);
			
			//Pedimos los datos
			int codigo=pedirEntero("Introduzca el codigo del modulo");
			String descripcion=pedirCadena("Introduzca la descripcion del modulo");
			
			//E insertamos en el sql
			st.setInt(1,codigo);
			st.setString(2, descripcion);

			int rs = st.executeUpdate();
			
			//Recogemos valor y definimos resultado
			if (rs!=1) {
				System.out.println("El modulo no se ha podido registrar");
			}else {
				System.out.println("Modulo registrada");
			}
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Método que trata la opcion 1
	 */
	private static void tratarOpcion1(Connection conexion) {

		try {
			
			//Pedimos los datos
			String dni=pedirCadena("Introduce un DNI");
			String nombre=pedirCadena("Introduce un nombre");
			String apellido1=pedirCadena("Introduce el primer apellido");
			String apellido2=pedirCadena("Introduce el segundo apellido");
			String email=pedirCadena("Introduce un email");
			int edad=pedirEntero("Introduzca una edad");
			

			
			String sql="INSERT INTO ejercicio7.alumnos (dni,nombre,apellido1,apellido2,email,edad) VALUES (?,?,?,?,?,?)";
			PreparedStatement st = conexion.prepareStatement(sql);
			
			//Insertamos los valores definiendo sus posiciones
			st.setString(1,dni);
			st.setString(2, nombre);
			st.setString(3, apellido1);
			st.setString(4, apellido2);
			st.setString(5, email);
			st.setInt(6, edad);
			
			//Recogemos el valor para saber si se ha insertado correctamente
			int rs = st.executeUpdate();
			
			if (rs!=1) {
				System.out.println("Error al insertar alumno");
			}else {
				System.out.println("Alumno insertado correctamente");
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

	/**
	 *  Método para pedir un entero
	 * @param string
	 * @return entero
	 */
	private static int pedirEntero(String string) {
		System.out.println(string);
		int entero=Integer.parseInt(teclado.nextLine());
		return entero;
	}

	/**
	 * Método para pedir una cadena
	 * @param string
	 * @return cadena
	 */
	private static String pedirCadena(String string) {
		System.out.println(string);
		String cadena=teclado.nextLine();
		return cadena;
	}

	/**
	 * Comprobamos que la opcion esta dentro del rango de las opciones correctas
	 * @param opcion
	 */
	private static void comprobarOpcion(int opcion) {
		if(opcion<=0 || opcion>7) {
			System.out.println("Fuera de rango");
		}
	}

	/**
	 * Método que nos muestra el menu y devuelve una entero
	 * @param string
	 * @return opcion que hemos insertado
	 */
	private static int mostrarYTratarMenu(String string) {
		System.out.println("1.- Dar de alta a un alumno");
		System.out.println("2.- Dar de alta MP");
		System.out.println("3.- Matricular alumno MP");
		System.out.println("4.- Desmatricular alumno MP");
		System.out.println("5.- Listar alumnos de un MP");
		System.out.println("6.- Listar modulos de un alumnado");
		System.out.println("7.- SALIR");
		int opcionInsertada=Integer.parseInt(teclado.nextLine());
		return opcionInsertada;
	}
}
