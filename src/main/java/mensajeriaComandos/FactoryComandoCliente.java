package mensajeriaComandos;

import cliente.Cliente;

public class FactoryComandoCliente {

	public Comando elegir(int nro, String cadenaLeida, Cliente cli) {
		Comando comando;
		switch (nro) {
		case Comando.REGISTRO:
			comando = new RegistroCli(cadenaLeida, cli);
			break;
		case Comando.INICIOSESION:
			comando = new InicioSesionCli(cadenaLeida, cli);
			break;
		case Comando.SALIR:
			comando = new SalirCli(cadenaLeida, cli);
			break;
		default:
			return null;
		}
		return comando;
	}

}
