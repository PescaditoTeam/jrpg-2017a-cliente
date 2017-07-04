package mensajeria;

import java.io.Serializable;

public class PaqueteIntercambio extends Paquete implements Serializable, Cloneable{
    
    private int id1;
    private int id2;
    private int itemNuevo1;
    private int itemNuevo2;
    public PaqueteIntercambio(int id1, int id2, int itemNuevo1,
            int itemNuevo2) {
        super();
        this.id1 = id1;
        this.id2 = id2;
        this.itemNuevo1 = itemNuevo1;
        this.itemNuevo2 = itemNuevo2;
    }
    
    public PaqueteIntercambio(){
        super();
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    public int getItemNuevo1() {
        return itemNuevo1;
    }

    public int getItemNuevo2() {
        return itemNuevo2;
    }
    
    

}
