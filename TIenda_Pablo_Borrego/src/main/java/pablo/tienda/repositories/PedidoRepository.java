package pablo.tienda.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pablo.tienda.model.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	@Query(value="select * from Pedido where id_usuario=?1", nativeQuery=true)
	List<Pedido> buscarPedidosUsuario(int id);
	
}
