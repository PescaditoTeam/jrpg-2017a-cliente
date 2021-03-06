package mensajeria;

import java.io.Serializable;

import dominio.DatosDePersonajeAReplicar;
import dominio.Item;
import dominio.Mochila;
import estados.Estado;

public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idMapa;
	private int estado;
	private String casta;
	private String nombre;
	private String raza;
	private int saludTope;
	private int energiaTope;
	private int fuerza;
	private int destreza;
	private int inteligencia;
	private int nivel;
	private int experiencia;
	private Mochila mochila = new Mochila();
	private boolean gano = false;


    public boolean isGano() {
        return gano;
    }


    public void setGano(boolean gano) {
        this.gano = gano;
    }


    public void setMochila(Mochila mochila) {
        this.mochila = mochila;
    }


    public PaquetePersonaje() {
		estado = Estado.estadoOffline;
	}

	
	public int getIdMapa() {
		return idMapa;
	}


	public void setIdMapa(int idMapa) {
		this.idMapa = idMapa;
	}


	public Mochila getMochila() {
		return mochila;
	}


	public void setMochila(Item item) {
		this.mochila.add(item);
	}


	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public int getMapa(){
		return idMapa;
	}
	
	public void setMapa(int mapa){
		idMapa = mapa;
	}
	
	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCasta() {
		return casta;
	}


	public void setCasta(String casta) {
		this.casta = casta;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRaza() {
		return raza;
	}


	public void setRaza(String raza) {
		this.raza = raza;
	}


	public int getSaludTope() {
		return saludTope;
	}


	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}


	public int getEnergiaTope() {
		return energiaTope;
	}


	public void setEnergiaTope(int energiaTope) {
		this.energiaTope = energiaTope;
	}


	public int getFuerza() {
		return fuerza;
	}


	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}


	public int getDestreza() {
		return destreza;
	}


	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}


	public int getInteligencia() {
		return inteligencia;
	}


	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}


	public void recibirDatosReplicadosDePersonaje(DatosDePersonajeAReplicar p) {
	    this.fuerza = p.getFuerza();
	    this.destreza = p.getDestreza();
	    this.inteligencia = p.getInteligencia();
	    this.experiencia = p.getExperiencia();
	    this.nivel = p.getNivel();
	    this.saludTope = p.getSaludTope();
	    this.energiaTope = p.getEnergiaTope();
		
	}
}
