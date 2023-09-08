package controller;

public class Producto {
	
	public Integer ID;
	public String nombre;
	public String dirFoto;
	public Float precio;
	public Integer stock;
	
	
	public Producto() {
		
	}


	public Producto(Integer iD, String nombre, Float precio) {
		super();
		ID = iD;
		this.nombre = nombre;
		this.precio = precio;
		
	}


	public Integer getID() {
		return ID;
	}


	public void setID(Integer iD) {
		ID = iD;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDirFoto() {
		return dirFoto;
	}


	public void setDirFoto(String dirFoto) {
		this.dirFoto = dirFoto;
	}


	public Float getPrecio() {
		return precio;
	}


	public void setPrecio(Float precio) {
		this.precio = precio;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}
