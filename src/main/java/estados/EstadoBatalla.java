package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Asesino;
import dominio.Casta;
import dominio.DatosDePersonajeAReplicar;
import dominio.Elfo;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Item;
import dominio.Mochila;
import dominio.MyRandom;
import dominio.Orco;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.MenuBatalla;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaquetePersonaje;
import mensajeriaComandos.Comando;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoBatalla extends Estado {

	private Mundo mundo;
	private Personaje personaje;
	private Personaje enemigo;
	private int[] posMouse;
	private PaquetePersonaje paquetePersonaje;
	private PaquetePersonaje paqueteEnemigo;
	private PaqueteAtacar paqueteAtacar;
	private PaqueteFinalizarBatalla paqueteFinalizarBatalla;
	private boolean miTurno;

	private boolean haySpellSeleccionada;
	private boolean seRealizoAccion;

	private Gson gson = new Gson();

	private BufferedImage miniaturaPersonaje;
	private BufferedImage miniaturaEnemigo;

	private MenuBatalla menuBatalla;

	public EstadoBatalla(Juego juego, PaqueteBatalla paqueteBatalla) {
		super(juego);
		mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
		miTurno = paqueteBatalla.isMiTurno();

		paquetePersonaje = juego.getEscuchaMensajes().getPersonajesConectados().get(paqueteBatalla.getId());
		paqueteEnemigo = juego.getEscuchaMensajes().getPersonajesConectados().get(paqueteBatalla.getIdEnemigo());

		crearPersonajes();

		menuBatalla = new MenuBatalla(miTurno, personaje);

		miniaturaEnemigo = Recursos.personaje.get(enemigo.getNombreRaza()).get(5)[0];
		miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(5)[0];

		paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
		paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
		paqueteFinalizarBatalla.setIdEnemigo(enemigo.getIdPersonaje());

		// por defecto batalla perdida
		juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuPerderBatalla);

		// limpio la accion del mouse
		juego.getHandlerMouse().setNuevoClick(false);

	}

	@Override
	public void actualizar() {

		juego.getCamara().setxOffset(-350);
		juego.getCamara().setyOffset(150);

		seRealizoAccion = false;
		haySpellSeleccionada = false;

		if (miTurno) {

			if (juego.getHandlerMouse().getNuevoClick()) {
				posMouse = juego.getHandlerMouse().getPosMouse();

				if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 1) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadRaza1(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 2) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadRaza2(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 3) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadCasta1(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 4) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadCasta2(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 5) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadCasta3(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 6) {
						seRealizoAccion = true;
						personaje.serEnergizado(5);
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 7) {
						if (personaje.getMochila().getInventario()[0] > 0) {
							personaje.efectuarItem(Recursos.itemsExistentes[0]);
							personaje.getMochila().getInventario()[0]--;
							menuBatalla.restarItem(0);
						}

					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 8) {
						if (personaje.getMochila().getInventario()[1] > 0) {
							personaje.efectuarItem(Recursos.itemsExistentes[1]);
							personaje.getMochila().getInventario()[1]--;
							menuBatalla.restarItem(1);
						}

					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 9) {
						if (personaje.getMochila().getInventario()[2] > 0) {
							personaje.efectuarItem(Recursos.itemsExistentes[2]);
							personaje.getMochila().getInventario()[2]--;
							menuBatalla.restarItem(2);
						}

					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 10) {
						if (personaje.getMochila().getInventario()[3] > 0) {
							personaje.efectuarItem(Recursos.itemsExistentes[3]);
							personaje.getMochila().getInventario()[3]--;
							menuBatalla.restarItem(3);
						}

					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 11) {
						if (personaje.getMochila().getInventario()[4] > 0) {
							personaje.efectuarItem(Recursos.itemsExistentes[4]);
							personaje.getMochila().getInventario()[4]--;
							menuBatalla.restarItem(4);
						}

					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 12) {
						if (personaje.getMochila().getInventario()[5] > 0) {
							personaje.efectuarItem(Recursos.itemsExistentes[5]);
							personaje.getMochila().getInventario()[5]--;
							menuBatalla.restarItem(5);
						}

					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 13) {
						if (personaje.getMochila().getInventario()[6] > 0) {
							personaje.efectuarItem(Recursos.itemsExistentes[6]);
							personaje.getMochila().getInventario()[6]--;
							menuBatalla.restarItem(6);
						}

					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 14) {
						if (personaje.getMochila().getInventario()[7] > 0) {
							enemigo.efectuarItem(Recursos.itemsExistentes[7]);
							personaje.getMochila().getInventario()[7]--;
							menuBatalla.restarItem(7);
							// seRealizoAccion = true;
						}

					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 15) {
						if (personaje.getMochila().getInventario()[8] > 0) {
							enemigo.efectuarItem(Recursos.itemsExistentes[8]);
							personaje.getMochila().getInventario()[8]--;
							menuBatalla.restarItem(8);
							// seRealizoAccion = true;
						}

					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 16) {
						if (personaje.getMochila().getInventario()[9] > 0) {
							enemigo.efectuarItem(Recursos.itemsExistentes[9]);
							personaje.getMochila().getInventario()[9]--;
							menuBatalla.restarItem(9);
							// seRealizoAccion = true;
						}

					}
				}

				if (haySpellSeleccionada && seRealizoAccion) {
					if (!enemigo.estaVivo()) {
						juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),
								MenuInfoPersonaje.menuGanarBatalla);
						if (personaje.ganarExperiencia(enemigo.getNivel() * 40)) {
							juego.getPersonaje().setNivel(personaje.getNivel());
							juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),
									MenuInfoPersonaje.menuSubirNivel);
						}
						finalizarBatalla();
						Estado.setEstado(juego.getEstadoJuego());
					} else {
						paqueteAtacar = new PaqueteAtacar(paquetePersonaje.getId(), paqueteEnemigo.getId(),
								personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(), enemigo.getEnergia());
						enviarAtaque(paqueteAtacar);
						miTurno = false;
						menuBatalla.setHabilitado(false);
					}
				} else if (haySpellSeleccionada && !seRealizoAccion) {
					JOptionPane.showMessageDialog(null,
							"No posees la energ�a suficiente para realizar esta habilidad.");
				}

				juego.getHandlerMouse().setNuevoClick(false);
			}
		}

	}

	@Override
	public void graficar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, juego.getAncho(), juego.getAlto());
		mundo.graficar(g);

		g.drawImage(Recursos.personaje.get(paquetePersonaje.getRaza()).get(3)[0], 0, 175, 256, 256, null);
		g.drawImage(Recursos.personaje.get(paqueteEnemigo.getRaza()).get(7)[0], 550, 75, 256, 256, null);

		mundo.graficarObstaculos(g);
		menuBatalla.graficar(g);

		g.setColor(Color.GREEN);

		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 25, 5, personaje, miniaturaPersonaje);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 550, 5, enemigo, miniaturaEnemigo);

	}

	private void crearPersonajes() {
		String nombre = paquetePersonaje.getNombre();
		int salud = paquetePersonaje.getSaludTope();
		int energia = paquetePersonaje.getEnergiaTope();
		int fuerza = paquetePersonaje.getFuerza();
		int destreza = paquetePersonaje.getDestreza();
		int inteligencia = paquetePersonaje.getInteligencia();
		int experiencia = paquetePersonaje.getExperiencia();
		int nivel = paquetePersonaje.getNivel();
		int id = paquetePersonaje.getId();
		Mochila mochila = paquetePersonaje.getMochila();
		Casta casta = null;
		if (paquetePersonaje.getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else if (paquetePersonaje.getCasta().equals("Hechicero")) {
			casta = new Hechicero();
		} else if (paquetePersonaje.getCasta().equals("Asesino")) {
			casta = new Asesino();
		}

		if (paquetePersonaje.getRaza().equals("Humano")) {
			personaje = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel,
					id, mochila);
		} else if (paquetePersonaje.getRaza().equals("Orco")) {
			personaje = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id,
					mochila);
		} else if (paquetePersonaje.getRaza().equals("Elfo")) {
			personaje = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id,
					mochila);
		}

		nombre = paqueteEnemigo.getNombre();
		salud = paqueteEnemigo.getSaludTope();
		energia = paqueteEnemigo.getEnergiaTope();
		fuerza = paqueteEnemigo.getFuerza();
		destreza = paqueteEnemigo.getDestreza();
		inteligencia = paqueteEnemigo.getInteligencia();
		experiencia = paqueteEnemigo.getExperiencia();
		nivel = paqueteEnemigo.getNivel();
		id = paqueteEnemigo.getId();
		mochila = paqueteEnemigo.getMochila();
		casta = null;
		if (paqueteEnemigo.getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else if (paqueteEnemigo.getCasta().equals("Hechicero")) {
			casta = new Hechicero();
		} else if (paqueteEnemigo.getCasta().equals("Asesino")) {
			casta = new Asesino();
		}

		if (paqueteEnemigo.getRaza().equals("Humano")) {
			enemigo = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id,
					mochila);
		} else if (paqueteEnemigo.getRaza().equals("Orco")) {
			enemigo = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id,
					mochila);
		} else if (paqueteEnemigo.getRaza().equals("Elfo")) {
			enemigo = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id,
					mochila);
		}
	}

	public void enviarAtaque(PaqueteAtacar paqueteAtacar) {
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteAtacar));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
			e.printStackTrace();
		}
	}

	private void finalizarBatalla() {
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));

			DatosDePersonajeAReplicar datosP = new DatosDePersonajeAReplicar(personaje.getSaludTope(),
					personaje.getEnergiaTope(), personaje.getFuerza(), personaje.getDestreza(),
					personaje.getInteligencia(), personaje.getExperiencia(), personaje.getNivel());
			;
			paquetePersonaje.recibirDatosReplicadosDePersonaje(datosP);

			DatosDePersonajeAReplicar datosE = new DatosDePersonajeAReplicar(enemigo.getSaludTope(),
					enemigo.getEnergiaTope(), enemigo.getFuerza(), enemigo.getDestreza(), enemigo.getInteligencia(),
					enemigo.getExperiencia(), enemigo.getNivel());

			paqueteEnemigo.recibirDatosReplicadosDePersonaje(datosE);
			paquetePersonaje.setGano(true);
			paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
			paqueteEnemigo.setComando(Comando.ACTUALIZARPERSONAJE);
			
			juego.getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteEnemigo));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor.");
			e.printStackTrace();
		}
	}

	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	public PaquetePersonaje getPaqueteEnemigo() {
		return paqueteEnemigo;
	}

	public void setMiTurno(boolean b) {
		miTurno = b;
		menuBatalla.setHabilitado(b);
		juego.getHandlerMouse().setNuevoClick(false);
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public Personaje getEnemigo() {
		return enemigo;
	}
}
