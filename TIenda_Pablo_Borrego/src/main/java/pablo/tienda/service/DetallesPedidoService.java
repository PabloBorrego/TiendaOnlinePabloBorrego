package pablo.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pablo.tienda.model.DetallesPedido;
import pablo.tienda.model.Producto;
import pablo.tienda.repositories.DetallesPedidoRepository;

@Service
public class DetallesPedidoService {

	@Autowired
	DetallesPedidoRepository dr;
	
	public double calcularTotal(DetallesPedido dp, Producto p) {
		
		double result = (double) (dp.getUnidades() * p.getPrecio());
		return result;
		
	}
	

	public List<DetallesPedido> getListaDetallesPedido() {
		return dr.findAll();
	}

	public List<DetallesPedido> getListaDetallesPedidoXIdPedido(int id) {
		return dr.buscarDetallesPedidoxPedido(id);
	}
	
	public void addDetallesPedido(DetallesPedido dp) {
		dr.save(dp);
		
	}
	
	public void delDetallesPedido(int id) {
		DetallesPedido dp = dr.getById(id);
		dr.delete(dp);
	}
	
	public void editDetallesProducto(DetallesPedido dp) {
		dr.save(dp);
	}
	
	public DetallesPedido getById(int id) {
		return dr.getById(id);
	}
	
	
}
