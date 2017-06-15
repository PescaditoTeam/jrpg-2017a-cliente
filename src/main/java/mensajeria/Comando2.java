package mensajeria;

import java.io.ObjectOutputStream;

import com.google.gson.Gson;

public abstract class Comando2 {
	public abstract void resolverComando(Paquete paquete, Paquete paqueteSv, PaqueteUsuario paqueteUsuario,
			String cadenaLeida, ObjectOutputStream salida, Gson gson);
}
