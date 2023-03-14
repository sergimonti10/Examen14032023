package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Usuario;

public class ControladorUsuario {

	public static List<Usuario> findAll() {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			Connection conn = ConnectionManager.getConexion();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from usuario");
			
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setEmail(rs.getString("email"));					
				u.setUsuario(rs.getString("usuario"));
				u.setPassword(rs.getString("password"));
				u.setIdIdioma(rs.getInt("idIdioma"));
				usuarios.add(u);
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return usuarios;
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 */
	private static Usuario getEntidadFromResultSet(String sql) {
		
		Usuario usuario = null;
		
		try {
			Connection conn = ConnectionManager.getConexion();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(sql);

			if (rs != null && rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setEmail(rs.getString("email"));				
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setPassword(rs.getString("password"));
				usuario.setIdIdioma(rs.getInt("idIdioma"));
			
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	
//	Métodos para encontrar primero, anterior, siguiente y último 
	/**
	 * 
	 * @return			
	 * 				Primero
	 */
	public static Usuario findFirst () {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.usuario order by id limit 1");
	}

	/**
	 * 				Último
	 * @return
	 */
	public static Usuario findLast () {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.usuario order by id desc limit 1");
	}

	/**
	 * 				Siguiente
	 * @return
	 */
	public static Usuario findNext (int actualId) {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.usuario where id > " + actualId + " order by id limit 1");
	}

	/**
	 * 				Anterior
	 * @return
	 */
	public static Usuario findPrevious (int actualId) {
		return getEntidadFromResultSet(
				"SELECT * FROM altausuarios.usuario where id < " + actualId + " order by id desc limit 1");
	}
 
	
	
	/**
	 * 			
	 * @param u
	 * @return
	 */
	public static int modificar (Usuario u) {
		
		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn.prepareStatement(
					"update usuario set email = ?, usuario = ?, password = ?, idIdioma = ? where id = ?");
		
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getUsuario());
			ps.setString(3, u.getPassword());
			ps.setInt(4, u.getIdIdioma());
			ps.setInt(5, u.getId());
	
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
	public static int insertar (Usuario u) {
		
		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn.prepareStatement(
					"insert into usuario (id, email, usuario, password, idIdioma) "
					+ " values (?, ?, ?, ?, ? )");
		
			int siguienteIdValido = getSiguientIdValido();
			ps.setInt(1, siguienteIdValido);
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getUsuario());
			ps.setString(4, u.getPassword());
			ps.setInt(5, u.getIdIdioma());
			
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
	public static int eliminar (int id) {
		
		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn.prepareStatement(
					"delete from usuario where id = ?");
		
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
	private static int getSiguientIdValido () {
		try {
			Connection conn = ConnectionManager.getConexion();
			PreparedStatement ps = conn.prepareStatement(
					"select max(id) from usuario");
		
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
