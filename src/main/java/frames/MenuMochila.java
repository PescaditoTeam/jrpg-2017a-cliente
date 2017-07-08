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
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class MenuMochila extends JFrame{

	private JPanel contentPane;
	private JTable table;

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
		
		table = new JTable();
		table.setForeground(Color.GREEN);
		table.setModel(new DefaultTableModel(
		    new Object[][] {
		        {"Item:", "Cantidad:"},
		        {Recursos.getItemsExistentesName(0), cliente.getPaquetePersonaje().getMochila().getInventario()[0]},
		        {Recursos.getItemsExistentesName(1), cliente.getPaquetePersonaje().getMochila().getInventario()[1]},
		        {Recursos.getItemsExistentesName(2), cliente.getPaquetePersonaje().getMochila().getInventario()[2]},
		        {Recursos.getItemsExistentesName(3), cliente.getPaquetePersonaje().getMochila().getInventario()[3]},
		        {Recursos.getItemsExistentesName(4), cliente.getPaquetePersonaje().getMochila().getInventario()[4]},
		        {Recursos.getItemsExistentesName(5), cliente.getPaquetePersonaje().getMochila().getInventario()[5]},
		        {Recursos.getItemsExistentesName(6), cliente.getPaquetePersonaje().getMochila().getInventario()[6]},
		        {Recursos.getItemsExistentesName(7), cliente.getPaquetePersonaje().getMochila().getInventario()[7]},
		        {Recursos.getItemsExistentesName(8), cliente.getPaquetePersonaje().getMochila().getInventario()[8]},
		        {Recursos.getItemsExistentesName(9), cliente.getPaquetePersonaje().getMochila().getInventario()[9]},
		    },
		    new String[] {
		        "Item", "Cantidad"
		    }
		));
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setBorder(new LineBorder(Color.GREEN, 2));
		table.setBackground(Color.BLACK);
		table.setBounds(120, 32, 198, 176);
		layeredPane.add(table);
	
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 444, 271);
		layeredPane.add(lblBackground);
		lblBackground.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/menuBackground.jpg")));
	}
}