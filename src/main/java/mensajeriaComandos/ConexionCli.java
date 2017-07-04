package mensajeriaComandos;

import java.io.IOException;
import java.util.Map;

import cliente.EscuchaMensajes;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaquetePersonaje;

public class ConexionCli extends ComandoEscuchaMensajes {

	public ConexionCli(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida, e);
	}

	@Override
	public void resolver() throws IOException {
		escuchaMensajes.setPersonajesConectados(
				(Map<Integer, PaquetePersonaje>) gson.fromJson(cadenaLeida, PaqueteDePersonajes.class).getPersonajes());

	}

}
