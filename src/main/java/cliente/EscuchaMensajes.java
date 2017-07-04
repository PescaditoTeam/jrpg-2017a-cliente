package cliente;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Ofertas;
import juego.Juego;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;
import mensajeriaComandos.Comando;
import mensajeriaComandos.FactoryComandoEscuchaMensaje;

public class EscuchaMensajes extends Thread {

	private Juego juego;
	private Cliente cliente;
	private ObjectInputStream entrada;
	private final Gson gson = new Gson();
	
	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	private Map<Integer, PaquetePersonaje> personajesConectados;
	private static ArrayList<Ofertas> ofertasDisponibles = new ArrayList<Ofertas>();

	public EscuchaMensajes(Juego juego) {
		this.juego = juego;
		cliente = juego.getCliente();
		entrada = cliente.getEntrada();
	}

	public void run() {

		try {

			Paquete paquete;
			PaquetePersonaje paquetePersonaje;
			PaqueteMovimiento personaje;
			PaqueteBatalla paqueteBatalla;
			PaqueteAtacar paqueteAtacar;
			PaqueteFinalizarBatalla paqueteFinalizarBatalla;
			personajesConectados = new HashMap<>();
			ubicacionPersonajes = new HashMap<>();
			ofertasDisponibles = new ArrayList<Ofertas>();

			while (true) {
				
				String objetoLeido = (String)entrada.readObject();

				paquete = gson.fromJson(objetoLeido , Paquete.class);
				
				FactoryComandoEscuchaMensaje factory = new FactoryComandoEscuchaMensaje();
				Comando comando = factory.elegir(paquete.getComando(), objetoLeido, this);
				comando.resolver();
					
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor.");
			e.printStackTrace();
		}
	}

	public Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
		return ubicacionPersonajes;
	}
	
	public Map<Integer, PaquetePersonaje> getPersonajesConectados(){
		return personajesConectados;
	}

	public void setPersonajesConectados(Map<Integer, PaquetePersonaje> personajesConectados) {
		this.personajesConectados = personajesConectados;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public void setUbicacionPersonajes(Map<Integer, PaqueteMovimiento> ubicacionPersonajes) {
		this.ubicacionPersonajes = ubicacionPersonajes;
	}

	public static ArrayList<Ofertas> getOfertasDisponibles() {
		return ofertasDisponibles;
	}

	public static void setOfertasDisponibles(ArrayList<Ofertas> ofertasDisponibles) {
		EscuchaMensajes.ofertasDisponibles = ofertasDisponibles;
	}
	public static void AgregarOferta(Ofertas o){
		ofertasDisponibles.add(o);
	}
	
}