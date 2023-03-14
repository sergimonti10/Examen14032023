package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Nivel;

public class ControladorNivel {
	
	/**
	 * 
	 * @return
	 */
	public static List<Nivel> findAll () {
		List<Nivel> lista = new ArrayList<Nivel>();
		try {
			// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo Statement
			Statement s = (Statement) ConnectionManager.getConexion().createStatement(); 
			
			// La ejecución de la consulta se realiza a través del objeto Statement y se recibe en forma de objeto
			// de tipo ResultSet, que puede ser navegado para descubrir todos los registros obtenidos por la consulta
			ResultSet rs = s.executeQuery ("select * from nivel");
		   
			// Navegación del objeto ResultSet
			while (rs.next()) {
				Nivel i = new Nivel(rs.getInt("id"), rs.getString("descripcion"), rs.getInt("idCentro"));
				lista.add(i);
			}
			// Cierre de los elementos
			rs.close();
			s.close();
		}
		catch (SQLException ex) {
			System.out.println("Error en la ejecución SQL: " + ex.getMessage());
			ex.printStackTrace();
		}
		return lista;
	}

	/**
	 * 
	 */
	public static List<Nivel> findByIdCentro (int idCentro) {
		List<Nivel> lista = new ArrayList<Nivel>();
		try {
			// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo Statement
			Statement s = (Statement) ConnectionManager.getConexion().createStatement(); 
			
			// La ejecución de la consulta se realiza a través del objeto Statement y se recibe en forma de objeto
			// de tipo ResultSet, que puede ser navegado para descubrir todos los registros obtenidos por la consulta
			ResultSet rs = s.executeQuery ("select * from nivel where idCentro = " + idCentro);
		   
			// Navegación del objeto ResultSet
			while (rs.next()) {
				Nivel i = new Nivel(rs.getInt("id"), rs.getString("descripcion"), rs.getInt("idCentro"));
				lista.add(i);
			}
			// Cierre de los elementos
			rs.close();
			s.close();
		}
		catch (SQLException ex) {
			System.out.println("Error en la ejecución SQL: " + ex.getMessage());
			ex.printStackTrace();
		}
		return lista;
	}

}
