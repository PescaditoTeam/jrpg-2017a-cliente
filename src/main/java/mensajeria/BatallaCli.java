package mensajeria;

import java.io.IOException;

import cliente.EscuchaMensajes;
import estados.Estado;
import estados.EstadoBatalla;

public class BatallaCli extends ComandoEscuchaMensajes {

	public BatallaCli(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida, e);
	}

	@Override
	public void resolver() throws IOException {
		PaqueteBatalla paqueteBatalla;
		paqueteBatalla = gson.fromJson(cadenaLeida, PaqueteBatalla.class);
		escuchaMensajes.getJuego().getPersonaje().setEstado(Estado.estadoBatalla);
		Estado.setEstado(null);
		escuchaMensajes.getJuego().setEstadoBatalla(new EstadoBatalla(escuchaMensajes.getJuego(), paqueteBatalla));
		Estado.setEstado(escuchaMensajes.getJuego().getEstadoBatalla());
	}

}
