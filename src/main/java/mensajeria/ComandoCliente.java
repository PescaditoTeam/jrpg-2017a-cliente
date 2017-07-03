package mensajeria;

import com.google.gson.Gson;

import cliente.Cliente;
import juego.Juego;

public abstract class ComandoCliente extends Comando{

	public ComandoCliente(String cadenaLeida) {
		super(cadenaLeida);
	}
	protected Cliente cliente;
	protected Juego juego;
	protected Gson gson = new Gson();
	
}
