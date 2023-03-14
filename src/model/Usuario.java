package model;

public class Usuario {
	
	private int id;
	private String email;
	private String usuario;
	private String password;
	private int idIdioma;
	
	/*
	 * 
	 */
	public Usuario() {
		super();
		
	}
	/*
	 * 
	 */
	public Usuario(int id, String email, String usuario, String password, int idIdioma) {
		super();
		this.id = id;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.idIdioma = idIdioma;
	}
	
	/*
	 * Getter & Setter
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdIdioma() {
		return idIdioma;
	}
	public void setIdIdioma(int idIdioma) {
		this.idIdioma = idIdioma;
	}
	
	/*
	 * 	ToString
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", usuario=" + usuario + ", password=" + password
				+ ", idIdioma=" + idIdioma + "]";
	}
	
	

}
