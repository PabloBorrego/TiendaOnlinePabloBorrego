package pablo.tienda.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pablo.tienda.model.DetallesPedido;


public interface DetallesPedidoRepository extends JpaRepository<DetallesPedido, Integer>{

	@Query(value="select * from detalles_pedido where id_pedido =?1", nativeQuery=true)
	List<DetallesPedido> buscarDetallesPedidoxPedido(int id);
	
	
}
