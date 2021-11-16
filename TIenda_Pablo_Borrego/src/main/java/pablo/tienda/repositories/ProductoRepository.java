package pablo.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pablo.tienda.model.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	Producto findByNombre(String nombre);
	
	@Query(value="select * from Producto where id_categoria=?1 ", nativeQuery=true)
	List<Producto> buscarProductosPorCategoria(String categoria);
}
