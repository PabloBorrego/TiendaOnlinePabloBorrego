package pablo.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pablo.tienda.model.DetallesPedido;
import pablo.tienda.model.Pedido;

import pablo.tienda.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pr;
	
	public List<Pedido> getListaPedidos() {
		return pr.findAll();
	}
	
	public void addPedido(Pedido pedido) {
		pr.save(pedido);
		
	}
	
	public void delPedio(int id) {
		Pedido p = pr.getById(id);
		pr.delete(p);
	}
	
	public void editPedido(Pedido p){
		pr.save(p);
	}
	
	
	public Pedido getPedidoXId(int id) {
		Pedido p = pr.getById(id);
		return p;
	}
	
	public double calcularTotalPedido(ArrayList <DetallesPedido> dp) {
		double resultado = 0;
		
		for(DetallesPedido d: dp) {
			resultado += d.getTotal();
		}
		
		return resultado;
	}

	public List<Pedido> getListaPedidosUsuario(int id) {
		return pr.buscarPedidosUsuario(id);
	}
	
}
