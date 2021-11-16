package pablo.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pablo.tienda.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Usuario findByUser(String user);
	
	@Query(value="select * from Usuario where user=?1 and password=?2", nativeQuery=true)
	List<Usuario> buscarUsuarioLogin(String user, String pass);
	
	@Query(value="select * from Usuario where id_rol=?1", nativeQuery=true)
	List<Usuario> buscarUsuarioXId_rol(int id_rol);
	
}
