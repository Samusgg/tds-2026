package tds.tpv;

import tds.tpv.adapters.repository.impl.CompraRepositoryMemoryImpl;
import tds.tpv.adapters.repository.impl.ProductoRepositoryMemoryImpl;
import tds.tpv.negocio.controladores.ControladorTPV;

public class ConfiguracionImpl extends Configuracion {

	private ControladorTPV controlador;

	public ConfiguracionImpl() {
		this.controlador = new ControladorTPV(new ProductoRepositoryMemoryImpl(), new CompraRepositoryMemoryImpl());
	}
	
	@Override
	public ControladorTPV getControladorTPV() {
		return controlador;
	}


}