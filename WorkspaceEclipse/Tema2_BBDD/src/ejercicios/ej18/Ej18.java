package ejercicios.ej18;

import java.sql.*;

public class Ej18 {

	static Connection conexion=null;
	public static void main(String[] args) {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto", "root", "");
			DatabaseMetaData mt = conexion.getMetaData();
			
			//Comprueba si acepta ejecucion por lotes
			System.out.println(mt.supportsBatchUpdates());
			
			conexion.setAutoCommit(false);
			
			asd();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}
	
	public static void asd() {
		try {
			PreparedStatement preps = conexion.prepareStatement("UPDATE alumno SET edad=?  WHERE nombre=?");
			
			preps.setInt(1, 22);
			preps.setString(2, "Antonio");
			preps.addBatch();

			preps.setInt(1, 20);
			preps.setString(2, "Alumno1");
			preps.addBatch();

			
			int [] numUpdates=preps.executeBatch();
			for (int i=0; i < numUpdates.length; i++) { 
			  if (numUpdates[i] == Statement.SUCCESS_NO_INFO) {
				  System.out.println("Execution " + i + ": unknown number of rows updated");
			    }else {
			    	System.out.println("Execution " + i + "successful: " + numUpdates[i] + " rows updated");
			    }
			  }
			conexion.commit(); 
		} catch(BatchUpdateException b) {
		  // process BatchUpdateException
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
