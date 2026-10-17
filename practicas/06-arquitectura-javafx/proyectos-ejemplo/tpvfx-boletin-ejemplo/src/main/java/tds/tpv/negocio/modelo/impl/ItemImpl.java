package tds.tpv.negocio.modelo.impl;

import tds.tpv.negocio.modelo.Item;
import tds.tpv.negocio.modelo.Producto;

public class ItemImpl implements Item {

	private String idItem;
	private ProductoImpl producto;
	private int cantidad;

	public ItemImpl() {
		this(null, 0);
	}

	public ItemImpl(Producto producto, int cantidad) {
		this.idItem = "";
		this.producto = (ProductoImpl) producto;
		this.cantidad = cantidad;
	}

	public String getIdItem() {
		return idItem;
	}

	public void setIdItem(String id) {
		this.idItem = id;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public ProductoImpl getProducto() {
		return producto;
	}

	@Override
	public int getCantidad() {
		return cantidad;
	}

	@Override
	public String getDescripcion() {
		return producto.getDescripcion();
	}

	@Override
	public double getPrecio() {
		return producto.getPrecio();
	}

	public void incrementarCantidad(int cantidad) {
		this.cantidad += cantidad;
	}

	@Override
	public double getPrecioTotal() {
		return this.producto.getPrecio() * this.cantidad;
	}

}
