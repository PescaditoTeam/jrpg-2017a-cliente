package mensajeria;

import java.io.IOException;

import cliente.EscuchaMensajes;

public class SalirCli extends ComandoCliente {

	public SalirCli(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida, e);
	}

	@Override
	public void resolver() throws IOException {

	}

}
