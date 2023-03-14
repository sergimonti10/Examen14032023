package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Materia;
import model.Nivel;

public class ControladorMateria {
	
	/**
	 * 
	 * @return
	 */
	public static List<Materia> findAll () {
		List<Materia> lista = new ArrayList<Materia>();
		try {
			// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo Statement
			Statement s = (Statement) ConnectionManager.getConexion().createStatement(); 
			
			// La ejecución de la consulta se realiza a través del objeto Statement y se recibe en forma de objeto
			// de tipo ResultSet, que puede ser navegado para descubrir todos los registros obtenidos por la consulta
			ResultSet rs = s.executeQuery ("select * from materia");
		   
			// Navegación del objeto ResultSet
			while (rs.next()) {
				Materia i = new Materia(rs.getInt("id"), rs.getString("nombre"), rs.getInt("idNivel"), rs.getString("codigo")
						, rs.getString("urlClassroom"), rs.getBoolean("admiteMatricula"), rs.getString("fechaInicio"));
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
	public static List<Materia> findByIdNivel (int idNivel) {
		List<Materia> materia = new ArrayList<Materia>();
		try {
			// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo Statement
			Statement s = (Statement) ConnectionManager.getConexion().createStatement(); 
			
			// La ejecución de la consulta se realiza a través del objeto Statement y se recibe en forma de objeto
			// de tipo ResultSet, que puede ser navegado para descubrir todos los registros obtenidos por la consulta
			ResultSet rs = s.executeQuery ("select * from nivel where idMateria = " + idNivel);
		   
			// Navegación del objeto ResultSet
			while (rs.next()) {
				Materia i = new Materia(rs.getInt("id"), rs.getString("nombre"), rs.getInt("idNivel"), rs.getString("codigo")
						, rs.getString("urlClassroom"), rs.getBoolean("admiteMatricula"), rs.getString("fechaInicio"));
				materia.add(i);
			}
			// Cierre de los elementos
			rs.close();
			s.close();
		}
		catch (SQLException ex) {
			System.out.println("Error en la ejecución SQL: " + ex.getMessage());
			ex.printStackTrace();
		}
		return materia;
	}

}
