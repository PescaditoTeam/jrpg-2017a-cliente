package cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import frames.MenuCarga;
import frames.MenuJugar;
import frames.MenuMapas;
import juego.Juego;
import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;
import mensajeriaComandos.Comando;
import mensajeriaComandos.FactoryComandoCliente;

public class Cliente extends Thread {
	
	private Socket cliente;
	private String miIp;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	
	// Objeto gson
	private final Gson gson = new Gson();
	
	// Paquete usuario y paquete personaje
	private PaqueteUsuario paqueteUsuario;
	private PaquetePersonaje paquetePersonaje;
	
	// Acciones que realiza el usuario
	private int accion;
	
	// Ip y puerto
	private String ip;
	private int puerto = 9999;

	public int getAccion() {
		return accion;
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}
	
	private Juego wome;
	private MenuCarga menuCarga;

	public Cliente() {
		
	      ip = JOptionPane.showInputDialog("Ingrese IP:");
	        if(ip == null) {
	            ip = "localhost";
	        }
		
		try {
			cliente = new Socket(ip, puerto);
			miIp = cliente.getInetAddress().getHostAddress();
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo al iniciar la aplicaci�n. Revise la conexi�n con el servidor.");
			System.exit(1);
			e.printStackTrace();
		}
	}
	   public Cliente(String ip, int puerto) {
	        try {
	            cliente = new Socket(ip, puerto);
	            miIp = cliente.getInetAddress().getHostAddress();
	            entrada = new ObjectInputStream(cliente.getInputStream());
	            salida = new ObjectOutputStream(cliente.getOutputStream());
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "Fallo al iniciar la aplicación. "
	                    + "Revise la conexión con el servidor.");
	            System.exit(1);
	            e.printStackTrace();
	        }
	    }

	public void run() {
		synchronized(this){
			try {
				
				// Creo el paquete que le voy a enviar al servidor
				paqueteUsuario = new PaqueteUsuario();

				while (!paqueteUsuario.isInicioSesion()) {
					
					// Muestro el men� principal
					new MenuJugar(this).setVisible(true);
					
					// Creo los paquetes que le voy a enviar al servidor
					paqueteUsuario = new PaqueteUsuario();
					paquetePersonaje = new PaquetePersonaje();
					
					// Espero a que el usuario seleccione alguna accion
					wait();
					
	
					switch (getAccion()) {
					
					case Comando.REGISTRO:
						paqueteUsuario.setComando(Comando.REGISTRO);
						break;
					case Comando.INICIOSESION:
						paqueteUsuario.setComando(Comando.INICIOSESION);
						break;
					case Comando.SALIR:
						paqueteUsuario.setIp(getMiIp());
						paqueteUsuario.setComando(Comando.SALIR);
						break;
					}
	
					// Le envio el paquete al servidor
					salida.writeObject(gson.toJson(paqueteUsuario));
	
					// Recibo el paquete desde el servidor
					String cadenaLeida = (String) entrada.readObject();
					Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);
				
					FactoryComandoCliente factory = new FactoryComandoCliente();
					Comando comando = factory.elegir(paquete.getComando(), cadenaLeida, this);
					comando.resolver();
					
				}
				
				// Creo un paquete con el comando mostrar mapas
				paquetePersonaje.setComando(Comando.MOSTRARMAPAS);
				
				// Abro el menu de eleccion del mapa
				MenuMapas menuElegirMapa = new MenuMapas(this);
				menuElegirMapa.setVisible(true);
				
				// Espero a que el usuario elija el mapa
				wait();
				
				// Establezco el mapa en el paquete personaje
				paquetePersonaje.setIp(miIp);
				
				// Le envio el paquete con el mapa seleccionado
				salida.writeObject(gson.toJson(paquetePersonaje));
	
				// Instancio el juego y cargo los recursos
				wome = new Juego("World Of the Middle Earth", 800, 600, this, paquetePersonaje);
				
				// Muestro el menu de carga
				menuCarga = new MenuCarga(this);
				menuCarga.setVisible(true);
				
				// Espero que se carguen todos los recursos
				wait();
				
				// Inicio el juego
				wome.start();
				
				// Finalizo el menu de carga
				menuCarga.dispose();
	
			} catch (IOException | InterruptedException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor durante el inicio de sesi�n.");
				System.exit(1);
				e.printStackTrace();
			}
		}

	}
	
	public Socket getSocket() {
		return cliente;
	}

	public void setSocket(Socket cliente) {
		this.cliente = cliente;
	}

	public String getMiIp() {
		return miIp;
	}

	public void setMiIp(String miIp) {
		this.miIp = miIp;
	}

	public ObjectInputStream getEntrada() {
		return entrada;
	}

	public void setEntrada(ObjectInputStream entrada) {
		this.entrada = entrada;
	}

	public ObjectOutputStream getSalida() {
		return salida;
	}

	public void setSalida(ObjectOutputStream salida) {
		this.salida = salida;
	}
	
	public PaqueteUsuario getPaqueteUsuario(){
		return paqueteUsuario;
	}
	
	public PaquetePersonaje getPaquetePersonaje(){
		return paquetePersonaje;
	}
	
	public void setPaqueteUsuario(PaqueteUsuario paqueteUsuario) {
		this.paqueteUsuario = paqueteUsuario;
	}

	public void setPaquetePersonaje(PaquetePersonaje paquetePersonaje) {
		this.paquetePersonaje = paquetePersonaje;
	}

	public Juego getJuego(){
		return wome;
	}
	
	
	public MenuCarga getMenuCarga() {
		return menuCarga;
	}
}
