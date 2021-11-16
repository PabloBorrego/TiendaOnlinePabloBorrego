package pablo.tienda.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Producto {
	
	@Id @GeneratedValue
	private int id;
	private int id_categoria;
	private String nombre;
	private String descripcion; 
	private double precio;
	private int stock;
	private Date fecha_alta;
	private Date fecha_baja ;
	private float impuesto ;
	private String imagen ;
	
	
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Producto(String nombre, String desc) {
		super();
		this.nombre = nombre;
		this.descripcion = desc;
	}
	

	public Producto(int id_categoria, String nombre, String desc, double precio, int stock, Date fecha_alta,
			Date fecha_baja, float impuesto, String imagen) {
		super();
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = desc;
		this.precio = precio;
		this.stock = stock;
		this.fecha_alta = fecha_alta;
		this.fecha_baja = fecha_baja;
		this.impuesto = impuesto;
		this.imagen = imagen;
	}


	

	public int getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public Date getFecha_alta() {
		return fecha_alta;
	}


	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}


	public Date getFecha_baja() {
		return fecha_baja;
	}


	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}


	public float getImpuesto() {
		return impuesto;
	}


	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	} 
	
	
	
}
