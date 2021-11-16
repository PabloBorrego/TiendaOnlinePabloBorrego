package pablo.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pablo.tienda.model.Usuario;
import pablo.tienda.repositories.UsuarioRepository;



@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository ur;
	
	@PostConstruct
	public void cargarUsuarios() {
		Usuario u = new Usuario("Cliente","pw");
		u.setId_rol(1);
		u.setApellido1("Borrego");
		u.setApellido2("Vidal");
		u.setLocalidad("Zamora");
		u.setDireccion("Calle campo de marte ,1 ,3ºC");
		u.setTelefono("904872731");
		ur.save(u);
		
		Usuario u2 = new Usuario("Emp","pw");
		u2.setId_rol(2);
		u2.setApellido1("Gómez");
		u2.setApellido2("Alonso");
		u2.setLocalidad("Castroverde");
		u2.setDireccion("Calle diego de losada ,1 ,3ºC");
		u2.setTelefono("904098451");
		ur.save(u2);
		
		Usuario u3 = new Usuario("Admin","pw");
		u3.setId_rol(3);
		u3.setApellido1("García");
		u3.setApellido2("López");
		u3.setLocalidad("Segovia");
		u3.setDireccion("Plaza maestro haedo ,1 ,3ºC");
		u3.setTelefono("904872895");
		ur.save(u3);
	}
	
	public List<Usuario> getListaUsuarios() {
		return ur.findAll();
	}
	
	public List<Usuario> getListaUsuariosXId_rol(int id_rol) {
		return ur.buscarUsuarioXId_rol(id_rol);
	}
	
	
	public void addUsuario(Usuario usuario) {
		ur.save(usuario);
		
	}
	
	public void delUsuario(int id) {
		Usuario u = ur.getById(id);
		ur.delete(u);
	}
	
	public void editUsuario(Usuario u){
		ur.save(u);
	}
	
	public boolean comprobarLogin(Usuario usuario) {
		boolean result = false;
		
		List<Usuario> lista = ur.buscarUsuarioLogin(usuario.getUser(), usuario.getPassword());
		if(!lista.isEmpty()) {
			result = true;
		}
		return result;
	}
	
	public Usuario getUsuarioXId(int id) {
		Usuario u = ur.getById(id);
		return u;
	}
}

