package pablo.tienda.controller;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pablo.tienda.model.DetallesPedido;
import pablo.tienda.model.Pedido;
import pablo.tienda.model.Producto;
import pablo.tienda.model.Usuario;
import pablo.tienda.service.DetallesPedidoService;
import pablo.tienda.service.PedidoService;
import pablo.tienda.service.ProductoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	ProductoService ps;
	@Autowired
	DetallesPedidoService ds;
	@Autowired
	PedidoService pds;
	
	@GetMapping("")
	public String observarCarrito(Model mod ,HttpSession session) {
		
		mod.addAttribute("listaProductos", ps.getListaProductos());
		
		ArrayList <DetallesPedido> listaD = (ArrayList <DetallesPedido>) session.getAttribute("carrito");
		mod.addAttribute("listaDetalles",listaD);
		mod.addAttribute("pedido",new Pedido());
		return "pedido/miCarrito";
	}
	
	
	@GetMapping("/historialPedidos")
	public String HistorialPedidos(Model mod ,HttpSession session) {
		
		
		int rol = (int)session.getAttribute("id_rolUsuario");
		
		if(rol == 1){
			
			Usuario u = (Usuario) session.getAttribute("usuario");
			ArrayList<Pedido> pedidosUs = (ArrayList<Pedido>) pds.getListaPedidosUsuario(u.getId());
			
			if(pedidosUs.isEmpty()) {
				return "redirect:/";
			}else {
				mod.addAttribute("listaPedidos",pedidosUs);
			}
			
		}else {
			mod.addAttribute("listaPedidos", pds.getListaPedidos());
		}
	
		
		
		return "pedido/listaPedidos"; 
	}
	
	@GetMapping("/cancelarPedido/{id}")
	public String cancelarPedido(Model mod ,@PathVariable int id ,HttpSession session) {
		
		Pedido p = pds.getPedidoXId(id);
		int rol = (int)session.getAttribute("id_rolUsuario");
		
		
		if(rol == 1){			
			p.setEstado("Pendiente de cancelacion");
		}else {
			p.setEstado("Cancelado");
		}
		
		pds.editPedido(p);
		
		return "redirect:/pedido/historialPedidos"; 
	}
	
	
	@GetMapping("/enviarPedido/{id}")
	public String enviarPedido(Model mod ,@PathVariable int id ,HttpSession session) {
		
		Pedido p = pds.getPedidoXId(id);
			
		p.setEstado("Enviado");
		
		pds.editPedido(p);
		
		return "redirect:/pedido/historialPedidos"; 
	}
	
	
	@GetMapping("/detallePedido/{id}")
	public String detallePedido(Model mod ,@PathVariable int id ,HttpSession session) {
		
		
		Pedido p = pds.getPedidoXId(id);
		
		ArrayList <DetallesPedido> listaD = (ArrayList<DetallesPedido>) ds.getListaDetallesPedidoXIdPedido(p.getId());
		
		mod.addAttribute("listaDetalles",listaD);
		mod.addAttribute("pedido",p);
		
		return "pedido/detallePedido"; 
	}
	
	
	@PostMapping("/finalizarPedido")
	public String finalizarPedido(Model mod ,@ModelAttribute Pedido pedido,HttpSession session) {
		
		System.err.println(pedido.getMetodoPago());
		
		
		ArrayList <DetallesPedido> listaD = (ArrayList <DetallesPedido>) session.getAttribute("carrito");
		Usuario u = (Usuario) session.getAttribute("usuario");
		
		if(u == null || listaD.isEmpty()) {
			
			if(listaD.isEmpty()) {
				return "redirect:/";
			}
			
			return "redirect:/login";
			
		}else {
			
			 java.util.Date utilDate = new java.util.Date();
			 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			 
			//Se forma el pedido nuevo y nuevo se rellenan los campos de los detalles y se suben a la base de datos
			Pedido p = new Pedido(u.getId(),sqlDate, "pendiente", "", pds.calcularTotalPedido(listaD));
			p.setMetodoPago(pedido.getMetodoPago());
			pds.addPedido(p);
			
			//Se guardan detalles 
			for(DetallesPedido d : listaD) {
				d.setId_pedido(p.getId());
				ds.addDetallesPedido(d);
				
				//Actualiza stock
				Producto p2 = ps.getById(d.getId_producto());
				
				if(d.getUnidades()>p2.getStock()) {
					p2.setStock(0);
				}else {
					p2.setStock(p2.getStock() - d.getUnidades());
					
				}
				
				ps.editProducto(p2);
			}
			//Se vacía el carrito
			ArrayList <DetallesPedido> nuevoCarrito = new ArrayList<DetallesPedido>();
			session.setAttribute("carrito", nuevoCarrito);
		}
		return "redirect:/";
	}

	

}
