package pablo.tienda.model;

import java.sql.Date;

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
public class Pedido {
	
	@Id@GeneratedValue
	private int id;
	private int id_usuario;
	private String metodoPago;
	private Date fecha;
	private String estado;
	private String num_factura;
	private Double total;
	
	public Pedido(int id_usuario, Date fecha, String estado, String num_factura, Double total) {
		super();
		this.id_usuario = id_usuario;
		this.fecha = fecha;
		this.estado = estado;
		this.num_factura = num_factura;
		this.total = total;
	}
	
	
}
