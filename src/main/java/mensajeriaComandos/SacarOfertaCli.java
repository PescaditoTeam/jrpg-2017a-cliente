package mensajeriaComandos;

import java.io.IOException;

import cliente.EscuchaMensajes;
import mensajeria.PaqueteMercado;

public class SacarOfertaCli extends ComandoEscuchaMensajes{

    public SacarOfertaCli(String cadenaLeida, EscuchaMensajes e) {
        super(cadenaLeida, e);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void resolver() throws IOException {
        PaqueteMercado paqueteMercado = new PaqueteMercado();
        paqueteMercado = (PaqueteMercado) gson.fromJson(cadenaLeida, PaqueteMercado.class);
        EscuchaMensajes.SacarOferta(paqueteMercado.getOferta(), paqueteMercado.getOferta2());
        
    }
    

}
