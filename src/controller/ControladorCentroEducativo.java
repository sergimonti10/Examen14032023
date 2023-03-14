package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CentroEducativo;
import model.Nivel;

public class ControladorCentroEducativo {
	
	/**
	 * 
	 * @return
	 */
	protected static int siguienteIdEnTabla() {
		try {
			Statement s = ConnectionManager.getConexion().createStatement();
			ResultSet rs = s.executeQuery("Select max(id) from centroeducativo");
			
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1; 		
	}

	/**
	 * 
	 */
	public static int nuevo (CentroEducativo c) {
		int registrosAfectados = 0;
		try {
			PreparedStatement ps = ConnectionManager.getConexion().prepareStatement(
					"insert into centroeducativo (id, descripcion) "
							+ "values (?, ?)");
			c.setId(siguienteIdEnTabla());

			ps.setInt(1, c.getId());
			ps.setString(2, c.getDescripcion());
			registrosAfectados = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return registrosAfectados;
	}
	
	public static List<CentroEducativo> findAll () {
		List<CentroEducativo> ce = new ArrayList<CentroEducativo>();
		try {
			// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo Statement
			Statement s = (Statement) ConnectionManager.getConexion().createStatement(); 
			
			// La ejecución de la consulta se realiza a través del objeto Statement y se recibe en forma de objeto
			// de tipo ResultSet, que puede ser navegado para descubrir todos los registros obtenidos por la consulta
			ResultSet rs = s.executeQuery ("select * from centroeducativo");
		   
			// Navegación del objeto ResultSet
			while (rs.next()) {
				CentroEducativo i = new CentroEducativo(rs.getInt("id"), rs.getString("descripcion"));
				ce.add(i);
			}
			// Cierre de los elementos
			rs.close();
			s.close();
		}
		catch (SQLException ex) {
			System.out.println("Error en la ejecución SQL: " + ex.getMessage());
			ex.printStackTrace();
		}
		return ce;
	}

}
