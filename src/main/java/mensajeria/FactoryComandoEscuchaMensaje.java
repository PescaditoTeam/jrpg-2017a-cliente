package mensajeria;

import cliente.EscuchaMensajes;

public class FactoryComandoEscuchaMensaje {

	public Comando elegir(int nro, String objetoLeido, EscuchaMensajes em) {
		Comando comando;
		switch (nro) {
		case Comando.CONEXION:
			comando = new ConexionCli(objetoLeido, em);
			break;
		case Comando.MOVIMIENTO:
			comando = new MovimientoCli(objetoLeido, em);
			break;
		case Comando.BATALLA:
			comando = new BatallaCli(objetoLeido, em);
			break;
		case Comando.ATACAR:
			comando = new AtacarCli(objetoLeido, em);
			break;
		case Comando.FINALIZARBATALLA:
			comando = new FinalizarBatallaCli(objetoLeido, em);
			break;
		case Comando.ACTUALIZARPERSONAJE:
			comando = new ActualizarPersonajeCli(objetoLeido, em);
			break;
		default:
			return null;
		}
		return comando;

	}
}
