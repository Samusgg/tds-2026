package tds.tpv.adapters.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tds.tpv.adapters.repository.ProductoRepository;
import tds.tpv.negocio.modelo.CarritoCompra;
import tds.tpv.negocio.modelo.Producto;
import tds.tpv.negocio.modelo.impl.ProductoImpl;
import tds.tpv.adapters.repository.exceptions.ElementoExistenteException;
import tds.tpv.adapters.repository.exceptions.ElementoNoEncontradoException;
import tds.tpv.adapters.repository.exceptions.ErrorPersistenciaException;

public class ProductoRepositoryMemoryImpl implements ProductoRepository {
	private static final Logger log = LogManager.getLogger();

	private List<Producto> productos = null;

	private void cargaProductos() throws ErrorPersistenciaException {
		try {
			this.productos = cargarProductos();
		} catch (Exception e) {
			log.error("Error cargando los productos ", e);
			throw new ErrorPersistenciaException(e);
		}
	}

	@Override
	public List<? extends Producto> getProductos() {
		if (productos == null) {
			try {
				cargaProductos();
			} catch (ErrorPersistenciaException e) {
				// Manejo la excepcion pero no la propago porque en este sitio es donde se puede
				// gestionar mejor
				log.error("No se han podido cargar los productos ", e);
			}
		}
		return productos;
	}

	@Override
	public Producto findById(String codigo) throws ElementoNoEncontradoException {
		Optional<Producto> producto = productos.stream()
					.filter(prod -> prod.getCodigo().equals(codigo))
					.findFirst();
		
		if (producto.isEmpty())
				throw new ElementoNoEncontradoException("El producto " + codigo + " no existe");
		return producto.orElse(null);
	}

	@Override
	public void addProducto(Producto producto) throws ElementoExistenteException, ErrorPersistenciaException {
		// Si el producto ya existe no puedo insertarlo
		if (productos.contains(producto)) {
			throw new ElementoExistenteException("El producto " + producto.getCodigo() + " ya existe en el inventario");
		}
		productos.add(producto);
		try {
			guardarProductos(productos);
		} catch (Exception e) {
			log.error("Error persistiendo el producto {}", producto.getCodigo(), e);
			// Capturo las excepciones genericas lanzadas al persistir y lanzo una propia
			// encapsulando la excepcion real
			throw new ErrorPersistenciaException(e);
		}

	}

	@Override
	public void removeProducto(Producto producto) throws ErrorPersistenciaException {

		if (!productos.contains(producto)) {
			return;
		}

		productos.remove(producto);
		try {
			guardarProductos(productos);
		} catch (Exception e) {
			log.error("Error eliminando el producto {}", producto.getCodigo(), e);
			throw new ErrorPersistenciaException(e);
		}
	}

	@Override
	public Producto updateProducto(Producto producto) throws ErrorPersistenciaException {
		// No borramos los productos con stock 0
		try {
			guardarProductos(productos);
			return producto;
		} catch (Exception e) {
			log.error("Error eliminando el producto {}", producto.getCodigo(), e);
			throw new ErrorPersistenciaException(e);
		}
	}

	// Metodo para cargar los productos incialmente : en memoria no tiene efecto
	private List<Producto> cargarProductos() throws Exception {
		if (productos == null) {
		
		productos = new ArrayList<>(List.of(
			    new ProductoImpl("001", "Manzana", 50, 1.20),
			    new ProductoImpl("002", "Plátano", 40, 0.80),
			    new ProductoImpl("003", "Naranja", 60, 1.50),
			    new ProductoImpl("004", "Pera", 35, 1.10),
			    new ProductoImpl("005", "Kiwi", 20, 2.30),
			    new ProductoImpl("006", "Sandía", 15, 4.50),
			    new ProductoImpl("007", "Melón", 18, 3.80),
			    new ProductoImpl("008", "Fresa", 25, 2.00),
			    new ProductoImpl("009", "Cereza", 30, 3.20),
			    new ProductoImpl("010", "Uva", 22, 2.40),
			    new ProductoImpl("011", "Mango", 28, 2.90),
			    new ProductoImpl("012", "Papaya", 17, 3.50),
			    new ProductoImpl("013", "Coco", 10, 4.00),
			    new ProductoImpl("014", "Limón", 45, 0.70),
			    new ProductoImpl("015", "Mandarina", 38, 1.40),
			    new ProductoImpl("016", "Pomelo", 26, 1.80),
			    new ProductoImpl("017", "Aguacate", 12, 3.70),
			    new ProductoImpl("018", "Granada", 14, 2.60),
			    new ProductoImpl("019", "Higo", 16, 2.80),
			    new ProductoImpl("020", "Melocotón", 32, 1.90),
			    new ProductoImpl("021", "Albaricoque", 27, 2.10),
			    new ProductoImpl("022", "Ciruela", 23, 1.60),
			    new ProductoImpl("023", "Arándano", 19, 3.10),
			    new ProductoImpl("024", "Frambuesa", 21, 3.40),
			    new ProductoImpl("025", "Mora", 24, 2.70),
			    new ProductoImpl("026", "Caqui", 11, 2.20),
			    new ProductoImpl("027", "Chirimoya", 13, 3.90),
			    new ProductoImpl("028", "Maracuyá", 9, 4.20),
			    new ProductoImpl("029", "Guayaba", 8, 3.60),
			    new ProductoImpl("030", "Lichi", 7, 4.80)
			));
		}
		return productos;
	}

	// Metodo para guardar los productos : en memoria no tiene efecto
	private void guardarProductos(List<Producto> productos) throws Exception {
		this.productos = productos;  

	}

}
