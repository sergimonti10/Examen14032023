package model;

public class Pais {

	private int id;
	private String descripcion;
	
	/*
	 * 
	 */
	public Pais(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	/*
	 * 
	 */
	public Pais() {
		super();
		
	}
	/*
	 *  Getter & Setters
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
	
	/*
	 *  ToString
	 */
	@Override
	public String toString() {
		return descripcion;
	}
	
	
}
