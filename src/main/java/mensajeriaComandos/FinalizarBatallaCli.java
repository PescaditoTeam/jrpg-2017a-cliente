package mensajeriaComandos;

import java.io.IOException;

import cliente.EscuchaMensajes;
import estados.Estado;
import mensajeria.PaqueteFinalizarBatalla;

public class FinalizarBatallaCli extends ComandoEscuchaMensajes {

	public FinalizarBatallaCli(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida, e);
	}

	@Override
	public void resolver() throws IOException {
		PaqueteFinalizarBatalla paqueteFinalizarBatalla;
		paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(cadenaLeida, PaqueteFinalizarBatalla.class);
		escuchaMensajes.getJuego().getPersonaje().setEstado(Estado.estadoJuego);
		Estado.setEstado(escuchaMensajes.getJuego().getEstadoJuego());
	}

}
