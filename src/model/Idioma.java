package model;

public class Idioma {
	
	private int id;
	private String descripcion;
	private int idPais;
	
	/*
	 * 
	 */
	public Idioma() {
		super();
	}

	public Idioma(int id, String descripcion, int idPais) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.idPais = idPais;
	}

	/*
	 * 	Getter & Setter
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	/*
	 * 		ToString
	 */
	@Override
	public String toString() {
		return  descripcion ;
	}
	
	
	
	
}
