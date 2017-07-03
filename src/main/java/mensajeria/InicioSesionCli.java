package mensajeria;

import java.io.IOException;

import javax.swing.JOptionPane;

import cliente.Cliente;

public class InicioSesionCli extends ComandoCliente{

	public InicioSesionCli(String cadenaLeida, Cliente cli) {
		super(cadenaLeida);
		this.cliente = cli;
	}

	@Override
	public void resolver() throws IOException {
		Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);
		if (paquete.getMensaje().equals(Paquete.msjExito)) {
			
			// El usuario ya inicio sesi�n
			cliente.getPaqueteUsuario().setInicioSesion(true);
			
			// Recibo el paquete personaje con los datos
			cliente.setPaquetePersonaje((PaquetePersonaje) gson.fromJson(cadenaLeida, PaquetePersonaje.class));

		} else {
			if (paquete.getMensaje().equals(Paquete.msjFracaso))
				JOptionPane.showMessageDialog(null, "Error al iniciar sesi�n. Revise el usuario y la contrase�a");

			// El usuario no pudo iniciar sesi�n
			cliente.getPaqueteUsuario().setInicioSesion(false);
		}
		
	}

}
