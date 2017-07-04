package mensajeriaComandos;

import java.io.IOException;

import cliente.Cliente;
import mensajeria.Paquete;

public class SalirCli extends ComandoCliente {

	public SalirCli(String cadenaLeida, Cliente cli) {
		super(cadenaLeida);
		this.cliente = cli;
	}

	@Override
	public void resolver() throws IOException {
		cliente.getPaqueteUsuario().setInicioSesion(false);
		cliente.getSalida().writeObject(gson.toJson(new Paquete(Comando.DESCONECTAR), Paquete.class));
		cliente.getSocket().close();
	}

}
