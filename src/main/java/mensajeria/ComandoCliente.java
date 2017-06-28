package mensajeria;

import com.google.gson.Gson;

import cliente.EscuchaMensajes;

public abstract class ComandoCliente extends Comando{

	public ComandoCliente(String cadenaLeida, EscuchaMensajes e) {
		super(cadenaLeida);
		escuchaMensajes = e;
	}
	Gson gson = new Gson();
	protected EscuchaMensajes escuchaMensajes;
	public EscuchaMensajes getEscuchaMensajes() {
		return escuchaMensajes;
	}

	public void setEscuchaMensajes(EscuchaMensajes escuchaMensajes) {
		this.escuchaMensajes = escuchaMensajes;
	}
	
	

}
