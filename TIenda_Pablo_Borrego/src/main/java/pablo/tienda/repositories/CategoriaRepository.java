package pablo.tienda.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import pablo.tienda.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	
}
