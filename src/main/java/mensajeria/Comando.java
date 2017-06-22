package mensajeria;

/**
 * Clase Comando Principal, de ella heredan ComandoCliente y ComandoServidor.
 * 
 */
public class Comando {
	
	public static final int ACTUALIZARPERSONAJE = 11;
	public static final int ATACAR = 9;
	public static final int BATALLA = 8;
	public static final int CONEXION = 0;
	public static final int CREACIONPJ = 1;
	public static final int DESCONECTAR = 2;
	public static final int FINALIZARBATALLA = 10; 
	public static final int INICIOSESION = 3;
	public static final int MOSTRARMAPAS = 4;
	public static final int MOVIMIENTO = 5;
	public static final int REGISTRO = 6;
	public static final int SALIR = 7;
	/**
	 * Cadena Leida.
	 */
	protected String cadenaLeida;
	/**
	 * @return cadenaLeida.
	 */
	public String getCadenaLeida() {
		return cadenaLeida;
	}
	/**
	 * @param cadenaLeida.
	 * Metodo para setear la cadena leida.
	 */
	public void setCadenaLeida(String cadenaLeida) {
		this.cadenaLeida = cadenaLeida;
	}
}
