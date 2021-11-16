package pablo.tienda.controller;



import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pablo.tienda.model.DetallesPedido;
import pablo.tienda.model.Producto;
import pablo.tienda.model.Usuario;
import pablo.tienda.service.CategoriaService;
import pablo.tienda.service.DetallesPedidoService;
import pablo.tienda.service.ProductoService;


@Controller
@RequestMapping("/productos")
public class ProductosController {

	@Autowired
	ProductoService ps;
	@Autowired
	DetallesPedidoService ds;
	@Autowired
	CategoriaService Cs;
	
	
	@GetMapping("")
	public String verProductos(Model mod, HttpSession session) {
		mod.addAttribute("listaCategorias",Cs.getListaCategorias());
		mod.addAttribute("listaProductos",ps.getListaProductos());
		return "productos/listaProductos";
	}
	
	@GetMapping("/verProducto/{id}")
	public String insertarProductos(Model mod, @PathVariable int id, HttpSession session) {
		Producto p = ps.getById(id);
		mod.addAttribute("producto", p);
		return "productos/detalleProducto";
	}
	
	@GetMapping("/crear")
	public String nuevoProducto(Model model) {
		model.addAttribute("producto", new Producto());
		return "productos/altaProductos";
	}
	
	@PostMapping("/crear/submit")
	public String crear(@ModelAttribute Producto producto, @RequestParam("file") MultipartFile file) {
		try {
			if(!file.isEmpty()) {
				producto.setImagen(file.getOriginalFilename());
				
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
			    URL appResourceURL = loader.getResource("static");
			    String dbConfigFileRoute = appResourceURL.getPath();
			    //dbConfigFileRoute = dbConfigFileRoute.substring(1, dbConfigFileRoute.length());
			    int separador = dbConfigFileRoute.lastIndexOf("/");
			    dbConfigFileRoute = dbConfigFileRoute.substring(1, separador);
			    String ruta = dbConfigFileRoute + "/static/img/" + file.getOriginalFilename();
			    
			    //guardar en el fichero
			    Files.copy(file.getInputStream(), Paths.get(ruta));
			    
			    
			}
		} catch (IOException e) {
			System.out.println(e);
			//throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
		
		ps.addProducto(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id) {
		ps.delProducto(id);
		return "redirect:/productos";
	}
	
	@GetMapping("/editar/{id}")
	public String actualizacionForm(@PathVariable int id, Model model) {
		Producto p = ps.getById(id);
		System.out.println(p.getId());
		model.addAttribute("producto",p);
		return "productos/editarProductos";
	}
	
	@GetMapping("/editar/submit")
	public String actualizar(@ModelAttribute Producto producto) {
		
		Producto p = ps.getById(producto.getId());
		
		p.setNombre(producto.getNombre());
		p.setPrecio(producto.getPrecio());
		p.setDescripcion(producto.getDescripcion());
		p.setStock(producto.getStock());
		ps.editProducto(p);
		
		return "redirect:/productos";
	}
	
	@GetMapping("/addProductoCarrito/{id}")
	public String addProductoCarrito(Model mod, HttpSession sesion, @PathVariable int id) {
		
		Producto p = ps.getById(id);
		ArrayList<DetallesPedido> ar = (ArrayList<DetallesPedido>) sesion.getAttribute("carrito");
		int contadorStock = p.getStock();
		
		//Se comprueba si el producto existe dentro del carrito
		boolean existeEnCarrito = false;
		
		for(DetallesPedido d : ar) {
			if(d.getId_producto() == id) {
				existeEnCarrito = true;
			}
		}
		
		//Si existe se suma uno a las unidades del DetallePedido correspondiente
		if(existeEnCarrito == true && contadorStock > 0) {
			for(DetallesPedido d : ar) {
				if(d.getId_producto() == id) {
					contadorStock--;
					d.setUnidades(d.getUnidades()+1);
					d.setTotal(ds.calcularTotal(d, ps.getById(id)));
				}
			}
			 
			
		//Si no existe creamos un DetallePedido nuevo para el producto y iniciamos en 1 
		}else {
			if(contadorStock > 0) {
				DetallesPedido dp = new DetallesPedido(p.getId(),(int)p.getPrecio(),1, p.getImpuesto(), p.getPrecio());
				dp.setUnidades(1);
				dp.setTotal(ds.calcularTotal(dp, ps.getById(id)));
				dp.setNombreprod(ps.getById(p.getId()).getNombre());
				contadorStock--;
				ar.add(dp);
			}
		}
		 
		sesion.setAttribute("carrito", ar);
		
		return "redirect:/";
	}
	
}
