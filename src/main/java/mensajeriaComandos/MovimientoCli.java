package mensajeriaComandos;

import java.io.IOException;
import java.util.Map;

import cliente.EscuchaMensajes;
import mensajeria.PaqueteDeMovimientos;
import mensajeria.PaqueteMovimiento;

public class MovimientoCli extends ComandoEscuchaMensajes {

	public MovimientoCli(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida, e);

	}

	@Override
	public void resolver() throws IOException {
		escuchaMensajes.setUbicacionPersonajes((Map<Integer, PaqueteMovimiento>) gson
				.fromJson(cadenaLeida, PaqueteDeMovimientos.class).getPersonajes());

	}

}
