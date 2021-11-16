package pablo.tienda.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pablo.tienda.model.DetallesPedido;
import pablo.tienda.model.Usuario;
import pablo.tienda.service.UsuarioService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UsuarioService us;
	
	@GetMapping("")
	public String entradaLogin(Model mod,HttpSession sesion) {
		
		Usuario us = new Usuario();
		sesion.setAttribute("usuario", us);
		
		mod.addAttribute("usuario",us);
		return "login/indexLogin";
	}
	
	@PostMapping("/evaluarSesion")
	public String salidaLogin(Model mod, @ModelAttribute Usuario user,HttpSession sesion) {
		
		if(us.comprobarLogin(user)) {
			
			//Pasar a la página principal con la sesion abierta
			ArrayList<Usuario> arUs = (ArrayList<Usuario>) us.getListaUsuarios();
			
			for(Usuario u : arUs) {
				if((user.getUser().equals(u.getUser()) && (user.getPassword().equals(u.getPassword())))){
					
					user = u;
					System.err.println(user.getId_rol());
				}
			}
			
			System.out.println(user.getId_rol());
			
			sesion.setAttribute("usuario", user);
			sesion.setAttribute("nombreUsuario", user.getUser());
			sesion.setAttribute("id_rolUsuario", user.getId_rol());
			
			System.out.println("EXISTE");
			
			return "redirect:/";
		
		}else {
			return "redirect:/login";
		}
			
		
	}
	
	
	@GetMapping("/alta")
	public String alta( Model mod, @ModelAttribute Usuario us) {
			return "users/alta";
	}
	

	@GetMapping("/cerrarSesion")
	public String cerrarSesion(HttpSession session) {
		
		session.setAttribute("usuario", null);
		session.setAttribute("carrito", new ArrayList<DetallesPedido>());
		session.setAttribute("nombreUsuario",null);
		session.setAttribute("id_rolUsuario", 0);
	    return "redirect:/";
	}
}
