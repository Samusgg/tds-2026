package tds.tpv.adapters.repository.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tds.tpv.adapters.repository.ProductoRepository;
import tds.tpv.adapters.repository.exceptions.ProductoExistenteException;
import tds.tpv.adapters.repository.exceptions.ProductoPersistenciaException;
import tds.tpv.negocio.modelo.Producto;

public class ProductoRepositoryJSONImpl implements ProductoRepository {
	private static final Logger log = LogManager.getLogger();

	private List<Producto> productos = null;

	private void cargaProductos() throws ProductoPersistenciaException {
		try {
			this.productos = cargarProductos();
		} catch (Exception e) {
			log.error("Error cargando los productos ", e);
			throw new ProductoPersistenciaException(e);
		}
	}

	@Override
	public List<? extends Producto> getProductos() {
		if (productos == null) {
			try {
				cargaProductos();
			} catch (ProductoPersistenciaException e) {
				// Manejo la excepcion pero no la propago porque en este sitio es donde se puede
				// gestionar mejor
				log.error("No se han podido cargar los productos ", e);
			}
		}
		return productos;
	}

	@Override
	public Producto findById(String codigo) {
		return productos.stream().filter(producto -> producto.getCodigo().equals(codigo)).findFirst().orElse(null);
	}

	@Override
	public void addProducto(Producto producto) throws ProductoExistenteException, ProductoPersistenciaException {
		// Si el producto ya existe no puedo insertarlo
		if (productos.contains(producto)) {
			throw new ProductoExistenteException("El producto " + producto.getCodigo() + " ya existe en el inventario");
		}
		productos.add(producto);
		try {
			guardarProductos(productos);
		} catch (Exception e) {
			log.error("Error persistiendo el producto {}", producto.getCodigo(), e);
			// Capturo las excepciones genericas lanzadas al persistir y lanzo una propia
			// encapsulando la excepcion real
			throw new ProductoPersistenciaException(e);
		}

	}

	@Override
	public void removeProducto(Producto producto) throws ProductoPersistenciaException {

		if (!productos.contains(producto)) {
			return;
		}

		productos.remove(producto);
		try {
			guardarProductos(productos);
		} catch (Exception e) {
			log.error("Error eliminando el producto {}", producto.getCodigo(), e);
			throw new ProductoPersistenciaException(e);
		}
	}

	@Override
	public Producto updateProducto(Producto producto) throws ProductoPersistenciaException {
		// No borramos los productos con stock 0
		try {
			guardarProductos(productos);
			return producto;
		} catch (Exception e) {
			log.error("Error eliminando el producto {}", producto.getCodigo(), e);
			throw new ProductoPersistenciaException(e);
		}
	}

	// Metodo para cargar los productos incialmente : en memoria no tiene efecto
	private List<Producto> cargarProductos() throws Exception {
		if (productos == null) {
		
		productos = new ArrayList<>(List.of(
			    new Producto("001", "Manzana", 50, 1.20),
			    new Producto("002", "Plátano", 40, 0.80),
			    new Producto("003", "Naranja", 60, 1.50),
			    new Producto("004", "Pera", 35, 1.10),
			    new Producto("005", "Kiwi", 20, 2.30),
			    new Producto("006", "Sandía", 15, 4.50),
			    new Producto("007", "Melón", 18, 3.80),
			    new Producto("008", "Fresa", 25, 2.00),
			    new Producto("009", "Cereza", 30, 3.20),
			    new Producto("010", "Uva", 22, 2.40),
			    new Producto("011", "Mango", 28, 2.90),
			    new Producto("012", "Papaya", 17, 3.50),
			    new Producto("013", "Coco", 10, 4.00),
			    new Producto("014", "Limón", 45, 0.70),
			    new Producto("015", "Mandarina", 38, 1.40),
			    new Producto("016", "Pomelo", 26, 1.80),
			    new Producto("017", "Aguacate", 12, 3.70),
			    new Producto("018", "Granada", 14, 2.60),
			    new Producto("019", "Higo", 16, 2.80),
			    new Producto("020", "Melocotón", 32, 1.90),
			    new Producto("021", "Albaricoque", 27, 2.10),
			    new Producto("022", "Ciruela", 23, 1.60),
			    new Producto("023", "Arándano", 19, 3.10),
			    new Producto("024", "Frambuesa", 21, 3.40),
			    new Producto("025", "Mora", 24, 2.70),
			    new Producto("026", "Caqui", 11, 2.20),
			    new Producto("027", "Chirimoya", 13, 3.90),
			    new Producto("028", "Maracuyá", 9, 4.20),
			    new Producto("029", "Guayaba", 8, 3.60),
			    new Producto("030", "Lichi", 7, 4.80)
			));
		}
		return productos;
	}

	// Metodo para guardar los productos : en memoria no tiene efecto
	private void guardarProductos(List<Producto> productos) throws Exception {
		this.productos = productos;  

	}
}
