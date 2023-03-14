package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Idioma;

public class ControladorIdioma {
	
	
	/*
	 * 
	 */
	
	public static List<Idioma> obtenerIdiomasPorPais(int idPais) {
		
	    List<Idioma> idiomas = new ArrayList<Idioma>();
	    
	    try {
	    	Connection conn = ConnectionManager.getConexion();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from idioma where idPais = " + idPais);       
	        while (rs.next()) {
	        	
	            Idioma u = new Idioma ();
	            u.setId(rs.getInt("id"));
	            u.setDescripcion(rs.getString("descripcion"));
				u.setIdPais(rs.getInt("idPais"));
	            idiomas.add(u);
	        }
	        conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return idiomas;
	}
	
	/*
	 * 
	 */
	public static List<Idioma> findAll() {

		List<Idioma> idiomas = new ArrayList<Idioma>();

		try {
			Connection conn = ConnectionManager.getConexion();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from idioma where idPais = ?");

			while (rs.next()) {
				Idioma u = new Idioma();
				u.setId(rs.getInt("id"));
				u.setDescripcion(rs.getString("descripcion"));
				u.setIdPais(rs.getInt("idPais"));
				idiomas.add(u);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return idiomas;
	}

	/**
	 * 
	 * @param rs
	 * @return
	 */
	private static Idioma getEntidadFromResultSet(String sql) {

		Idioma idioma = null;

		try {
			Connection conn = ConnectionManager.getConexion();
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);

			if (rs != null && rs.next()) {
				idioma = new Idioma();
				idioma.setId(rs.getInt("id"));
				idioma.setDescripcion(rs.getString("descripcion"));
				idioma.setIdPais(rs.getInt("idPais"));

			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idioma;
	}

	
	
//	Métodos para encontrar primero, anterior, siguiente y último 
	/**
	 * 
	 * @return Primero
	 */
	public static Idioma findFirst() {
		return getEntidadFromResultSet("SELECT * FROM altausuarios.idioma order by id limit 1");
	}

	/**
	 * Último
	 * 
	 * @return
	 */
	public static Idioma findLast() {
		return getEntidadFromResultSet("SELECT * FROM altausuarios.idioma order by id desc limit 1");
	}

	/**
	 * Siguiente
	 * 
	 * @return
	 */
	public static Idioma findNext(int actualId) {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.idioma where id > " + actualId + " order by id limit 1");
	}

	/**
	 * Anterior
	 * 
	 * @return
	 */
	public static Idioma findPrevious(int actualId) {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.idioma where id < " + actualId + " order by id desc limit 1");
	}

	/**
	 * 
	 * @param u
	 * @return
	 */
	public static int modificar(Idioma l) {

		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn.prepareStatement("update idioma set descripcion = ?, idPais = ? where id = ?");

			ps.setString(1, l.getDescripcion());
			ps.setInt(2, l.getIdPais());
			ps.setInt(3, l.getId());

			int rows = ps.executeUpdate();
			ps.close();
			conn.close();

			return rows;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @param u
	 * @return
	 */
	public static int insertar(Idioma l) {

		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn
					.prepareStatement("insert into idioma (id, descripcion, idPais) " + " values (?, ?, ? )");

			int siguienteIdValido = getSiguientIdValido();
			ps.setInt(1, siguienteIdValido);
			ps.setString(2, l.getDescripcion());
			ps.setInt(3, l.getIdPais());

			ps.executeUpdate();
			ps.close();
			conn.close();

			return siguienteIdValido;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static int eliminar(int id) {

		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn.prepareStatement("delete from idioma where id = ?");

			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			ps.close();
			conn.close();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	private static int getSiguientIdValido() {
		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn.prepareStatement("select max(id) from idioma");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int maximoIdActual = rs.getInt(1);
				return maximoIdActual + 1;
			}

			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
