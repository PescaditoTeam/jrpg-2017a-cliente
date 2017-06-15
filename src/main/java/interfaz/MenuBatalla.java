package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import dominio.Personaje;
import juego.Pantalla;
import recursos.Recursos;

public class MenuBatalla {

	private static final int x = 50;
	private static final int y = 380;
	private static final int anchoBoton = 35;
	// private static final int [][] botones = { { x + 48, y + 72 } , { x + 48,
	// y + 146 } , { x + 221 , y + 72 } , { x + 221 , y + 146 } , { x + 394 , y
	// + 72 } , { x + 394 , y + 146 } };
	private static final int[][] botones = { { x + 90, y + 80 }, { x + 90, y + 160 }, { x + 182, y + 80 },
			{ x + 182, y + 160 }, { x + 274, y + 80 }, { x + 274, y + 160 }, { x + 366, y + 80 }, { x + 366, y + 160 },
			{ x + 438, y + 80 }, { x + 438, y + 160 }, { x + 510, y + 80 }, { x + 510, y + 160 }, { x + 582, y + 80 },
			{ x + 582, y + 160 }, { x + 654, y + 80 }, { x + 654, y + 160 } };
	private boolean habilitado;
	private Personaje personaje;
	private int[] itemsDisponibles;

	public MenuBatalla(boolean habilitado, Personaje personaje) {
		this.habilitado = habilitado;
		this.personaje = personaje;
		itemsDisponibles = personaje.getMochila().getInventario();
	}

	public void graficar(Graphics g) {

		if (habilitado)
			// g.drawImage(Recursos.menuBatalla, x, y, null);
			g.drawImage(Recursos.menuBatalla, x, y, 739, 235, null);
		else
			// g.drawImage(Recursos.menuBatallaDeshabilitado, x, y, null);
			g.drawImage(Recursos.menuBatallaDeshabilitado, x, y, 739, 235, null);

		// Dibujo los boones
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesRaza()[0]), botones[0][0], botones[0][1],
				anchoBoton, anchoBoton, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesRaza()[1]), botones[1][0], botones[1][1],
				anchoBoton, anchoBoton, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[0]), botones[2][0], botones[2][1],
				anchoBoton, anchoBoton, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[1]), botones[3][0], botones[3][1],
				anchoBoton, anchoBoton, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[2]), botones[4][0], botones[4][1],
				anchoBoton, anchoBoton, null);
		g.drawImage(Recursos.habilidades.get("Ser Energizado"), botones[5][0], botones[5][1], anchoBoton, anchoBoton,
				null);
		// g.drawImage(Recursos.barraItems, botones[4][0] + 40, botones[4][1],
		// 100, 120, null);
		if (itemsDisponibles[0] > 0)
			g.drawImage(Recursos.items.get("Pocion de Salud"), botones[6][0], botones[6][1], anchoBoton, anchoBoton,
					null);
		else {
			g.drawImage(Recursos.itemsOFF.get("Pocion de Salud OFF"), botones[6][0], botones[6][1], anchoBoton, anchoBoton,
					null);
		}
		if (itemsDisponibles[1] > 0){
		g.drawImage(Recursos.items.get("Pocion de Energia"), botones[7][0], botones[7][1], anchoBoton, anchoBoton,
				null);
		}
		else{
			g.drawImage(Recursos.itemsOFF.get("Pocion de Energia OFF"), botones[7][0], botones[7][1], anchoBoton, anchoBoton,
					null);
		}
		if (itemsDisponibles[2] > 0){
		g.drawImage(Recursos.items.get("Escudo"), botones[8][0], botones[8][1], anchoBoton, anchoBoton, null);
		}
		else{
			g.drawImage(Recursos.itemsOFF.get("Escudo OFF"), botones[8][0], botones[8][1], anchoBoton, anchoBoton, null);

		}
		if (itemsDisponibles[3] > 0){
		g.drawImage(Recursos.items.get("Armamento"), botones[9][0], botones[9][1], anchoBoton, anchoBoton, null);
		}
		else{
			g.drawImage(Recursos.itemsOFF.get("Armamento OFF"), botones[9][0], botones[9][1], anchoBoton, anchoBoton, null);

		}
		if (itemsDisponibles[4] > 0){
		g.drawImage(Recursos.items.get("Agua"), botones[10][0], botones[10][1], anchoBoton, anchoBoton, null);
		}
		else{
			g.drawImage(Recursos.itemsOFF.get("Agua OFF"), botones[10][0], botones[10][1], anchoBoton, anchoBoton, null);

		}
		if (itemsDisponibles[5] > 0){
		g.drawImage(Recursos.items.get("Libros"), botones[11][0], botones[11][1], anchoBoton, anchoBoton, null);
		}
		else{
			g.drawImage(Recursos.itemsOFF.get("Libros OFF"), botones[11][0], botones[11][1], anchoBoton, anchoBoton, null);

		}
		if (itemsDisponibles[6] > 0){
		g.drawImage(Recursos.items.get("Varita Magica"), botones[12][0], botones[12][1], anchoBoton, anchoBoton, null);
		}
		else{
			g.drawImage(Recursos.itemsOFF.get("Varita Magica OFF"), botones[12][0], botones[12][1], anchoBoton, anchoBoton, null);
	
		}
		if (itemsDisponibles[7] > 0){
		g.drawImage(Recursos.items.get("Veneno"), botones[13][0], botones[13][1], anchoBoton, anchoBoton, null);
		}
		else{
			g.drawImage(Recursos.itemsOFF.get("Veneno OFF"), botones[13][0], botones[13][1], anchoBoton, anchoBoton, null);

		}
		if (itemsDisponibles[8] > 0){
		g.drawImage(Recursos.items.get("Pocion de Cansancio"), botones[14][0], botones[14][1], anchoBoton, anchoBoton,
				null);
		}
		else{
			g.drawImage(Recursos.itemsOFF.get("Pocion de Cansancio OFF"), botones[14][0], botones[14][1], anchoBoton, anchoBoton,
					null);
		}
		if (itemsDisponibles[9] > 0){
		g.drawImage(Recursos.items.get("Lentitud"), botones[15][0], botones[15][1], anchoBoton, anchoBoton, null);
		}
		else{
			g.drawImage(Recursos.itemsOFF.get("Lentitud OFF"), botones[15][0], botones[15][1], anchoBoton, anchoBoton, null);

		}
		
		// Dibujo las leyendas
		g.setFont(new Font("Book Antiqua", 1, 12));
		g.drawString(personaje.getHabilidadesRaza()[0], x + 90, y + 65);
		g.drawString(personaje.getHabilidadesRaza()[1], x + 90, y + 145);
		g.drawString(personaje.getHabilidadesCasta()[0], x + 162, y + 65);
		g.drawString(personaje.getHabilidadesCasta()[1], x + 162, y + 145);
		g.drawString(personaje.getHabilidadesCasta()[2], x + 260, y + 65);
		g.drawString("Energizarse", x + 274, y + 145);
		g.drawString("Pocion Salud", x + 362, y + 65);
		g.drawString("Pocion Energia", x + 354, y + 145);
		g.drawString("Escudo", x + 442, y + 65);
		g.drawString("Armamento", x + 438, y + 145);
		g.drawString("Agua", x + 510, y + 65);
		g.drawString("Libros", x + 510, y + 145);
		g.drawString("Varita Magica", x + 575, y + 65);
		g.drawString("Veneno", x + 582, y + 145);
		g.drawString("Cansancio", x + 654, y + 65);
		g.drawString("Lentitud", x + 654, y + 145);

		// Dibujo el turno de quien es
		g.setColor(Color.WHITE);
		if (habilitado)
			Pantalla.centerString(g, new Rectangle(x, y + 5, Recursos.menuBatalla.getWidth(), 20), "Mi Turno");
		else
			Pantalla.centerString(g, new Rectangle(x, y + 5, Recursos.menuBatalla.getWidth(), 20), "Turno Rival");

	}

	public int getBotonClickeado(int mouseX, int mouseY) {
		if (!habilitado)
			return 0;
		for (int i = 0; i < botones.length; i++) {
			if (mouseX >= botones[i][0] && mouseX <= botones[i][0] + anchoBoton && mouseY >= botones[i][1]
					&& mouseY <= botones[i][1] + anchoBoton)
				return i + 1;
		}
		return 0;
	}

	public boolean clickEnMenu(int mouseX, int mouseY) {
		if (mouseX >= x && mouseX <= x + Recursos.menuBatalla.getWidth() + 200 && mouseY >= y
				&& mouseY <= y + Recursos.menuBatalla.getHeight())
			return habilitado;
		return false;
	}
	
	public int[] getItemsDisponibles() {
		return itemsDisponibles;
	}

	public void setItemsDisponibles(int[] itemsDisponibles) {
		this.itemsDisponibles = itemsDisponibles;
	}
	public void restarItem(int index){
		itemsDisponibles[index]--;
	}

	public void setHabilitado(boolean b) {
		habilitado = b;
	}
}
