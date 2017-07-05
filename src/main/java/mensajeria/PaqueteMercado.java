package mensajeria;

import java.io.Serializable;

import dominio.Ofertas;

public class PaqueteMercado extends Paquete implements Serializable, Cloneable {
	private int id;
	private Ofertas oferta;
	private Ofertas oferta2=null;
	
	public Ofertas getOferta2() {
        return oferta2;
    }
    public void setOferta2(Ofertas oferta2) {
        this.oferta2 = oferta2;
    }
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Ofertas getOferta() {
		return oferta;
	}
	public void setOferta(Ofertas oferta) {
		this.oferta = oferta;
	}
	
	
}
