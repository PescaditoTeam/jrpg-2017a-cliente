package mensajeria;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import cliente.Cliente;
import frames.MenuCreacionPj;

public class RegistroCli extends ComandoCliente{

	public RegistroCli(String cadenaLeida, Cliente cli) {
		super(cadenaLeida);
		this.cliente = cli;
	}

	@Override
	public void resolver() throws IOException {
		Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);
		if (paquete.getMensaje().equals(Paquete.msjExito)) {

			// Abro el menu para la creaci�n del personaje
			MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(cliente, cliente.getPaquetePersonaje());
			menuCreacionPJ.setVisible(true);
			
			// Espero a que el usuario cree el personaje
			try {
				wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			// Le envio los datos al servidor
			cliente.getPaquetePersonaje().setComando(Comando.CREACIONPJ);
			cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));									
			JOptionPane.showMessageDialog(null, "Registro exitoso.");
			
			// Recibo el paquete personaje con los datos (la id incluida)
			try {
				cliente.setPaquetePersonaje((PaquetePersonaje) gson.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class));
			} catch (JsonSyntaxException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			// Indico que el usuario ya inicio sesion
			cliente.getPaqueteUsuario().setInicioSesion(true);
			
		} else {
			if (paquete.getMensaje().equals(Paquete.msjFracaso))
				JOptionPane.showMessageDialog(null, "No se pudo registrar.");

			// El usuario no pudo iniciar sesi�n
			cliente.getPaqueteUsuario().setInicioSesion(false);
		}
		
	}

}
