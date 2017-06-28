package mensajeria;

import java.io.IOException;

import cliente.EscuchaMensajes;

public class AtacarCli extends ComandoCliente {

	public AtacarCli(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida, e);
	}

	@Override
	public void resolver() throws IOException {
		PaqueteAtacar paqueteAtacar;
		paqueteAtacar = (PaqueteAtacar) gson.fromJson(cadenaLeida, PaqueteAtacar.class);
		escuchaMensajes.getJuego().getEstadoBatalla().getEnemigo().recibirDatosReplicadosDePersonajeAtacar(
				paqueteAtacar.getNuevaSaludPersonaje(), paqueteAtacar.getNuevaEnergiaPersonaje());
		escuchaMensajes.getJuego().getEstadoBatalla().getPersonaje().recibirDatosReplicadosDePersonajeAtacar(
				paqueteAtacar.getNuevaSaludEnemigo(), paqueteAtacar.getNuevaEnergiaEnemigo());
		escuchaMensajes.getJuego().getEstadoBatalla().setMiTurno(true);
	}

}
