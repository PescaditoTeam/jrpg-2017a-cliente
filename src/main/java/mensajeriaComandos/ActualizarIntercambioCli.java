package mensajeriaComandos;

import java.io.IOException;

import cliente.EscuchaMensajes;
import mensajeria.PaquetePersonaje;

public class ActualizarIntercambioCli extends ComandoEscuchaMensajes{

    public ActualizarIntercambioCli(String cadenaLeida, EscuchaMensajes e) {
        super(cadenaLeida, e);
    }

    @Override
    public void resolver() throws IOException {
        PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson.fromJson(cadenaLeida, PaquetePersonaje.class);
        
        escuchaMensajes.getPersonajesConectados().remove(paquetePersonaje.getId());
        escuchaMensajes.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);
        if(escuchaMensajes.getJuego().getPersonaje().getId() == paquetePersonaje.getId()) {
            escuchaMensajes.getJuego().actualizarPersonaje();
            escuchaMensajes.getJuego().getEstadoJuego().actualizarPersonaje();
        }
        
    }

}
