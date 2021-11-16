package pablo.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pablo.tienda.model.Categoria;

import pablo.tienda.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository cr;
	
	@PostConstruct
	public void cargarCategorias() {
		
		Categoria c = new Categoria("Zapatillas Nike","Zapatillas de marca Nike");
		Categoria c1 = new Categoria("Zapatillas Jordan","Zapatillas de marca air jordan");
		Categoria c2 = new Categoria("Zapatillas Adidas","Zapatillas de marca adidas");
		
		cr.save(c);
		cr.save(c1);
		cr.save(c2);
		
	}
	
	public List<Categoria> getListaCategorias() {
		return cr.findAll();
	}
	
}
