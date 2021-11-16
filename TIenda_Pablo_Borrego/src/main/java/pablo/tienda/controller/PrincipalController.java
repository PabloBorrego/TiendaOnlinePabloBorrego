package pablo.tienda.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pablo.tienda.model.DetallesPedido;
import pablo.tienda.model.Usuario;
import pablo.tienda.service.ProductoService;




@Controller
@RequestMapping("")
public class PrincipalController {

	@Autowired
	ProductoService ps;
	
	@GetMapping("")
	public String entrada(Model mod,HttpSession sesion) {
		
		
		
		ArrayList<DetallesPedido> carrito = (ArrayList<DetallesPedido>) sesion.getAttribute("carrito");
		Usuario u = (Usuario) sesion.getAttribute("usuario");
		Integer id_rol = (Integer) sesion.getAttribute("id_rolUsuario");
		
		System.out.println("ROL INDEX:"+id_rol);
		
		if(carrito == null){
			ArrayList<DetallesPedido> nuevoCarrito = new ArrayList<>();
			sesion.setAttribute("carrito", nuevoCarrito);
		}
		
		if(id_rol == null){
			sesion.setAttribute("id_rolUsuario", null);
		}
		
		if(u == null){
			sesion.setAttribute("usuario", null);
		}
		
		
		mod.addAttribute("listaProductos",ps.getListaProductos());
		return "index";
	}
	
}
