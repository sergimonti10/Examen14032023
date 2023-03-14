package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Idioma;
import model.Pais;

public class ControladorPais {

	/*
	 * 
	 */
	
	public List<Pais> obtenerPaises() {
	    List<Pais> paises = new ArrayList<>();
	    try {
	        Connection conn = ConnectionManager.getConexion();
	        Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from pais");
			
	        while (rs.next()) {
	            Pais p = new Pais();
	            p.setId(rs.getInt("id"));
	            p.setDescripcion(rs.getString("descripcion"));
	            paises.add(p);
	        }
	        conn.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return paises;
	}
	






	public static List<Pais> findAll() {

		List <Pais> paises = new ArrayList<Pais>();

		try {
			Connection conn = ConnectionManager.getConexion();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from pais");

			while (rs.next()) {
				Pais p = new Pais();
				p.setId(rs.getInt("id"));
				p.setDescripcion(rs.getString("descripcion"));
				paises.add(p);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return paises; 
	}
	/**
	 * 
	 * @param rs
	 * @return
	 */
	private static Pais getEntidadFromResultSet(String sql) {

		Pais pais = null;

		try {
			Connection conn = ConnectionManager.getConexion();
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);

			if (rs != null && rs.next()) {
				pais = new Pais();
				pais.setId(rs.getInt("id"));
				pais.setDescripcion(rs.getString("descripcion"));
			}
			
			rs.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return pais;

	}
	//	Métodos para encontrar primero, anterior, siguiente y último 
	/**
	 * 
	 * @return Primero
	 */
	public static Pais findFirst() {
		return getEntidadFromResultSet("SELECT * FROM altausuarios.pais order by id limit 1");
	}

	/**
	 * Último
	 * 
	 * @return
	 */
	public static Pais findLast() {
		return getEntidadFromResultSet("SELECT * FROM altausuarios.pais order by id desc limit 1");
	}

	/**
	 * Siguiente
	 * 
	 * @return
	 */
	public static Pais findNext(int actualId) {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.pais where id > " + actualId + " order by id limit 1");
	}

	/**
	 * Anterior
	 * 
	 * @return
	 */
	public static Pais findPrevious(int actualId) {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.pais where id < " + actualId + " order by id desc limit 1");
	}

	/**
	 * 
	 * @param u
	 * @return
	 */
	public static int modificar(Pais p) {

		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn.prepareStatement("update pais set descripcion = ? where id = ?");

			ps.setString(1, p.getDescripcion());
			ps.setInt(2, p.getId());

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
	public static int insertar(Pais p) {

		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn
					.prepareStatement("insert into pais (id, descripcion) " + " values (?, ? )");

			int siguienteIdValido = getSiguientIdValido();
			ps.setInt(1, siguienteIdValido);
			ps.setString(2, p.getDescripcion());

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
			PreparedStatement ps = conn.prepareStatement("delete from pais where id = ?");

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
			PreparedStatement ps = conn.prepareStatement("select max(id) from pais");

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
