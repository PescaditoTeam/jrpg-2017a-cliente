package mensajeria;

import java.io.IOException;
import java.util.Map;

import cliente.EscuchaMensajes;

public class MovimientoCli extends ComandoCliente {

	public MovimientoCli(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida, e);

	}

	@Override
	public void resolver() throws IOException {
		escuchaMensajes.setUbicacionPersonajes((Map<Integer, PaqueteMovimiento>) gson
				.fromJson(cadenaLeida, PaqueteDeMovimientos.class).getPersonajes());

	}

}
