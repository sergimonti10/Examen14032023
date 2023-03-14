package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Acuerdo;

public class ControladorAcuerdo {

	public static List<Acuerdo> findAll() {

		List<Acuerdo> acuerdos = new ArrayList<Acuerdo>();

		try {
			Connection conn = ConnectionManager.getConexion();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from acuerdo");

			while (rs.next()) {
				Acuerdo a = new Acuerdo();
				a.setId(rs.getInt("id"));
				a.setDescripcion(rs.getString("descripcion"));
				a.setIdIdioma(rs.getInt("idIdioma"));
				acuerdos.add(a);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return acuerdos;
	}

	/**
	 * 
	 * @param rs
	 * @return
	 */
	private static Acuerdo getEntidadFromResultSet(String sql) {

		Acuerdo acuerdo = null;

		try {
			Connection conn = ConnectionManager.getConexion();
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);

			if (rs != null && rs.next()) {
				acuerdo = new Acuerdo();
				acuerdo.setId(rs.getInt("id"));
				acuerdo.setDescripcion(rs.getString("descripcion"));
				acuerdo.setIdIdioma(rs.getInt("idIdioma"));

			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return acuerdo;
	}

//	Métodos para encontrar primero, anterior, siguiente y último 
	/**
	 * 
	 * @return Primero
	 */
	public static Acuerdo findFirst() {
		return getEntidadFromResultSet("SELECT * FROM altausuarios.acuerdo order by id limit 1");
	}

	/**
	 * Último
	 * 
	 * @return
	 */
	public static Acuerdo findLast() {
		return getEntidadFromResultSet("SELECT * FROM altausuarios.acuerdo order by id desc limit 1");
	}

	/**
	 * Siguiente
	 * 
	 * @return
	 */
	public static Acuerdo findNext(int actualId) {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.acuerdo where id > " + actualId + " order by id limit 1");
	}

	/**
	 * Anterior
	 * 
	 * @return
	 */
	public static Acuerdo findPrevious(int actualId) {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.acuerdo where id < " + actualId + " order by id desc limit 1");
	}

	/**
	 * 
	 * @param u
	 * @return
	 */
	public static int modificar(Acuerdo a) {

		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn.prepareStatement("update idioma set descripcion = ?, idIdioma = ? where id = ?");

			ps.setString(1, a.getDescripcion());
			ps.setInt(2, a.getIdIdioma());
			ps.setInt(3, a.getId());

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
	public static int insertar(Acuerdo a) {

		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn
					.prepareStatement("insert into acuerdo (id, descripcion, idIdioma) " + " values (?, ?, ? )");

			int siguienteIdValido = getSiguientIdValido();
			ps.setInt(1, siguienteIdValido);
			ps.setString(2, a.getDescripcion());
			ps.setInt(3, a.getIdIdioma());

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
			PreparedStatement ps = conn.prepareStatement("delete from acuerdo where id = ?");

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
			PreparedStatement ps = conn.prepareStatement("select max(id) from acuerdo");

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
