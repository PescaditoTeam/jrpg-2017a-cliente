package mensajeriaComandos;

import java.io.IOException;

import cliente.EscuchaMensajes;
import mensajeria.PaqueteIntercambio;
import mensajeria.PaquetePersonaje;

public class IntercambiarCli extends ComandoEscuchaMensajes{

    public IntercambiarCli(String cadenaLeida, EscuchaMensajes e) {
        super(cadenaLeida, e);
    }

    @Override
    public void resolver() throws IOException {
        PaqueteIntercambio paqueteIntercambio;
        PaquetePersonaje paquetePersonaje;
        paqueteIntercambio = gson.fromJson(cadenaLeida, PaqueteIntercambio.class);
        paquetePersonaje = escuchaMensajes.getJuego().getCliente().getPaquetePersonaje();
        if(paquetePersonaje.getId() == paqueteIntercambio.getId1()){
            paquetePersonaje.getMochila().add(paqueteIntercambio.getItemNuevo1());
            paquetePersonaje.getMochila().sacar(paqueteIntercambio.getItemNuevo2());
        }else{
            if(paquetePersonaje.getId() == paqueteIntercambio.getId2()){
                paquetePersonaje.getMochila().add(paqueteIntercambio.getItemNuevo2());
                paquetePersonaje.getMochila().sacar(paqueteIntercambio.getItemNuevo1());
            }
        }
        paquetePersonaje.setComando(Comando.ACTUALIZARINTERCAMBIO);
        try {
            escuchaMensajes.getJuego().getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
