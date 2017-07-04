package mensajeriaComandos;

import java.io.IOException;

import cliente.EscuchaMensajes;
import mensajeria.PaqueteMercado;


public class AgregarOfertaCli extends ComandoEscuchaMensajes {


	public AgregarOfertaCli(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida, e);
	}

	@Override
	public void resolver() throws IOException {
		PaqueteMercado paqueteMercado = new PaqueteMercado();
		paqueteMercado = (PaqueteMercado) gson.fromJson(cadenaLeida, PaqueteMercado.class);
		EscuchaMensajes.AgregarOferta(paqueteMercado.getOferta());
	}

}
