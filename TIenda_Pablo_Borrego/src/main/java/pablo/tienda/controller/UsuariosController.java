package pablo.tienda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pablo.tienda.model.Producto;
import pablo.tienda.model.Usuario;
import pablo.tienda.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	UsuarioService us;
		
	@GetMapping("/empleados")
	public String verEmpleados(Model mod, HttpSession session) {
		
			mod.addAttribute("listaEmpleados", us.getListaUsuariosXId_rol(2));
			return "users/listaEmpleados";
		}
	
	@GetMapping("/clientes")
	public String verClientes(Model mod, HttpSession session) {
		
			mod.addAttribute("listaClientes", us.getListaUsuariosXId_rol(1));
			return "users/listaClientes";
		}
	
	
	@GetMapping("/perfilUsuario")
	public String verPerfilUsuario(Model mod, HttpSession session) {
		
		Usuario u = (Usuario) session.getAttribute("usuario");
		mod.addAttribute("usuario", u);
		return "users/perfil";
	}
	
	@GetMapping("/crearCliente")
	public String nuevoForm(Model model) {
		Usuario u = new Usuario();
		u.setId_rol(1);
		model.addAttribute("usuario", u);
		return "users/altaUsuarios";
	}
	
	@GetMapping("/crearEmpleado")
	public String nuevoFormEmpleado(Model model) {
		Usuario u = new Usuario();
		u.setId_rol(2);
		model.addAttribute("usuario", u);
		return "users/altaUsuarios";
	}
	
	
	
	@PostMapping("/crear/submit")
	public String crear(@ModelAttribute Usuario usuario) {
		us.editUsuario(usuario);
		
		if(usuario.getId_rol() == 1) {
			return "redirect:/usuarios/clientes";
		}else {
			return "redirect:/usuarios/empleados";
		}
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id) {
		Usuario u = us.getUsuarioXId(id);
		
		if(u.getId_rol() == 1) {
			us.delUsuario(id);
			return "redirect:/usuarios/clientes";
		}else {
			us.delUsuario(id);
			return "redirect:/usuarios/empleados";
		}
	}
	
	@GetMapping("/editarCliente/{id}")
	public String actualizacionFormCliente(@PathVariable int id, Model model) {
		Usuario u = us.getUsuarioXId(id);
		u.setId_rol(1);
		model.addAttribute("usuario", u);
		return "users/editarUsuarios";
	}
	
	@GetMapping("/editarEmpleado/{id}")
	public String actualizacionFormEmpleado(@PathVariable int id, Model model) {
		Usuario u = us.getUsuarioXId(id);
		u.setId_rol(2);
		model.addAttribute("usuario", u);
		return "users/editarUsuarios";
	}
	
	@PostMapping("/editar/submit")
	public String actualizar(@ModelAttribute Usuario usuario) {
		us.editUsuario(usuario);
		
		if(usuario.getId_rol() == 1) {
			return "redirect:/usuarios/clientes";
		}else {
			return "redirect:/usuarios/empleados";
		}
		
		
	}
	
	
}
