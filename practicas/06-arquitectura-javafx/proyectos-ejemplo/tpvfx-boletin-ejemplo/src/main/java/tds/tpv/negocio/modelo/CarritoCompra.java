package tds.tpv.negocio.modelo;

import java.util.List;

import tds.tpv.negocio.modelo.impl.ItemImpl;

public interface CarritoCompra {
	
	public String getIdCompra();

	List<? extends Item> getItems();
	
	double getTotal();
}
