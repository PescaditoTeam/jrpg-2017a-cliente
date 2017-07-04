package frames;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.EscuchaMensajes;
import dominio.Mercado;
import mensajeriaComandos.Comando;
import mensajeria.PaqueteMercado;
import recursos.Recursos;

import javax.swing.JList;
import javax.swing.JTextArea;

public class MenuMochila extends JFrame{

	private JPanel contentPane;

	public MenuMochila(final Cliente cliente) {

		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		setTitle("Bienvenido a su Mochila");
		setBounds(100, 100, 450, 300);
		// En caso de cerrar
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				synchronized (cliente) {
					cliente.setAccion(Comando.SALIR);
					cliente.notify();
				}
				dispose();
			}
		});

		// Panel
		setTitle("WOME - Mochila");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 444, 271);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 55, 424, 158);
		layeredPane.add(textArea);
		for(int i=0; i < Recursos.getItemsExistentes().length; i++){
			textArea.setText(textArea.getText() + Recursos.getItemsExistentesName(i) + "\t" + cliente.getPaquetePersonaje().getMochila().getInventario()[i] + "\n");
		}
	
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 444, 271);
		layeredPane.add(lblBackground);
		lblBackground.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/menuBackground.jpg")));
	}
}