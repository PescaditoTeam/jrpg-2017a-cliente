package mensajeria;

import java.io.IOException;

import cliente.EscuchaMensajes;

public class ActualizarPersonajeCli extends ComandoCliente {

	public ActualizarPersonajeCli(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida, e);
	}

	@Override
	public void resolver() throws IOException {
		PaquetePersonaje paquetePersonaje;
		paquetePersonaje = (PaquetePersonaje) gson.fromJson(cadenaLeida, PaquetePersonaje.class);

		escuchaMensajes.getPersonajesConectados().remove(paquetePersonaje.getId());
		escuchaMensajes.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);
		
		if(escuchaMensajes.getJuego().getPersonaje().getId() == paquetePersonaje.getId()) {
			escuchaMensajes.getJuego().actualizarPersonaje();
			escuchaMensajes.getJuego().getEstadoJuego().actualizarPersonaje();
		}
	}

}
