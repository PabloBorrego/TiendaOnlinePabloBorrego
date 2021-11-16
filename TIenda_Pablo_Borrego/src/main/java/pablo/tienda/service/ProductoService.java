package pablo.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pablo.tienda.model.Producto;
import pablo.tienda.repositories.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository pr;
	
	@PostConstruct
	public void cargarProductos() {
		
		Producto p = new Producto("Jordan 4 retro \"Lightning\"", "Zapatillas Nike");
			p.setPrecio(400);
			p.setImpuesto(21);
			p.setId_categoria(1);
			p.setStock(34);
			p.setImagen("JordanLightning.png");
		Producto p1 = new Producto("Nike Airforce 1  \"Purple Skeleton\" ", "Zapatillas Nike");
			p1.setPrecio(100);
			p1.setImpuesto(21);
			p1.setId_categoria(2);
			p1.setStock(39);
			p1.setImagen("NikePurpleSkeleton.png");
		Producto p2 = new Producto("Bad Bunny Pink Easter Egg", "Zapatillas Adidas");
			p2.setPrecio(200);
			p2.setImpuesto(21);
			p2.setId_categoria(3);
			p2.setStock(27);
			p2.setImagen("BadBunnyEasterEgg.png");
		
		
		pr.save(p);
		pr.save(p1);
		pr.save(p2);
		
	}

	public List<Producto> getListaProductos() {
		return pr.findAll();
	}

	
	public void addProducto(Producto prod) {
		pr.save(prod);
		
	}
	
	public void delProducto(int id) {
		Producto p = pr.getById(id);
		pr.delete(p);
	}
	
	public void editProducto(Producto prod) {
		pr.save(prod);
	}
	
	public Producto getById(int id) {
		return pr.getById(id);
	}
	
	
	
}
