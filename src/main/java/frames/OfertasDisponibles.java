package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import dominio.Mercado;
import mensajeria.Comando;
import recursos.Recursos;

public class OfertasDisponibles extends JFrame {

	private JPanel contentPane;

	public OfertasDisponibles(final Cliente cliente, Mercado mercado) {

		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		setTitle("Elije una oferta");
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
		setTitle("WOME - Ofertas Disponibles");
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

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(178, 195, 89, 23);
		layeredPane.add(btnAceptar);

		JLabel lblElijeUnaOferta = new JLabel("Elije una oferta de la Lista");
		lblElijeUnaOferta.setBounds(165, 38, 136, 28);
		layeredPane.add(lblElijeUnaOferta);
		lblElijeUnaOferta.setForeground(Color.WHITE);
		lblElijeUnaOferta.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(32, 77, 389, 38);
		layeredPane.add(comboBox);
		for (int i = 0; i < mercado.getOfertas().size(); i++) {
			comboBox.addItem("El Usuario" + mercado.getOfertas().get(i).getUser() + "ofrece el Item"
					+ Recursos.getItemsExistentesName(mercado.getOfertas().get(i).getOfertado())
					+ "y pide a cambio el Item"
					+ Recursos.getItemsExistentesName(mercado.getOfertas().get(i).getDemandado()));
		}

		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 444, 271);
		layeredPane.add(lblBackground);
		lblBackground.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/menuBackground.jpg")));
	}
}
