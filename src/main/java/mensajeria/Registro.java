package mensajeria;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;


public class Registro extends Comando2{

	@Override
	public void resolverComando(Paquete paquete, Paquete paqueteSv, PaqueteUsuario paqueteUsuario, String cadenaLeida,
			ObjectOutputStream salida, Gson gson) {
		// Paquete que le voy a enviar al usuario
		paqueteSv.setComando(Comando.REGISTRO);
		
		paqueteUsuario = (PaqueteUsuario) (gson.fromJson(cadenaLeida, PaqueteUsuario.class)).clone();

		/*if (Servidor.getConector().registrarUsuario(paqueteUsuario)) {
			paqueteSv.setMensaje(Paquete.msjExito);
			salida.writeObject(gson.toJson(paqueteSv));
		// Si el usuario no se pudo registrar le envio un msj de fracaso
		} else {
			paqueteSv.setMensaje(Paquete.msjFracaso);
			salida.writeObject(gson.toJson(paqueteSv));
		}*/
		
	}

	
}
