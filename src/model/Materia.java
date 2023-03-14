package model;

public class Materia {
	
	private int id;
	private String nombre;
	private int idNivel;
	private String codigo;
	private String urlClassroom;
	private boolean admiteMateria;
	private String fechaInicio;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the idNivel
	 */
	public int getIdNivel() {
		return idNivel;
	}
	/**
	 * @param idNivel the idNivel to set
	 */
	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the urlClassroom
	 */
	public String getUrlClassroom() {
		return urlClassroom;
	}
	/**
	 * @param urlClassroom the urlClassroom to set
	 */
	public void setUrlClassroom(String urlClassroom) {
		this.urlClassroom = urlClassroom;
	}
	/**
	 * @return the admiteMateria
	 */
	public boolean isAdmiteMateria() {
		return admiteMateria;
	}
	/**
	 * @param admiteMateria the admiteMateria to set
	 */
	public void setAdmiteMateria(boolean admiteMateria) {
		this.admiteMateria = admiteMateria;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	@Override
	public String toString() {
		return "materia [id=" + id + ", nombre=" + nombre + ", idNivel=" + idNivel + ", codigo=" + codigo
				+ ", urlClassroom=" + urlClassroom + ", admiteMateria=" + admiteMateria + ", fechaInicio=" + fechaInicio
				+ "]";
	}
	/**
	 * @param id
	 * @param nombre
	 * @param idNivel
	 * @param codigo
	 * @param urlClassroom
	 * @param admiteMateria
	 * @param fechaInicio
	 */
	public Materia(int id, String nombre, int idNivel, String codigo, String urlClassroom, boolean admiteMateria,
			String fechaInicio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idNivel = idNivel;
		this.codigo = codigo;
		this.urlClassroom = urlClassroom;
		this.admiteMateria = admiteMateria;
		this.fechaInicio = fechaInicio;
	}
	/**
	 * 
	 */
	public Materia() {
		super();
	}
	
	
	

}
