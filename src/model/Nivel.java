package model;

public class Nivel {
	
	private int id;
	private String descripcion;
	private int idCentro;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the idCentro
	 */
	public int getIdCentro() {
		return idCentro;
	}
	/**
	 * @param idCentro the idCentro to set
	 */
	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}
	@Override
	public String toString() {
		return "nivel [id=" + id + ", descripcion=" + descripcion + ", idCentro=" + idCentro + "]";
	}
	/**
	 * @param id
	 * @param descripcion
	 * @param idCentro
	 */
	public Nivel(int id, String descripcion, int idCentro) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.idCentro = idCentro;
	}
	/**
	 * 
	 */
	public Nivel() {
		super();
	}
	
	

}
