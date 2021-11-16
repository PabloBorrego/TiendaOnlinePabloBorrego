package pablo.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
public class DetallesPedido {
	
	@Id @GeneratedValue
	private int id;
	private String nombreprod;
	private int	id_pedido;
	private int	id_producto;
	private int	precio_unidad;
	private int unidades;
	private float impuesto;
	private double total;
	
	public DetallesPedido(int id_producto, int precio_unidad, int unidades, float impuesto,double total) {
		super();
		
		this.id_producto = id_producto;
		this.precio_unidad = precio_unidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		this.total = total;
	}
	
	
}
