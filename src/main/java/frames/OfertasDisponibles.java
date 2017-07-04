package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
import dominio.Ofertas;
import mensajeriaComandos.Comando;
import recursos.Recursos;

public class OfertasDisponibles extends JFrame {

	private JPanel contentPane;

	public OfertasDisponibles(final Cliente cliente, Mercado mercado) {

		ArrayList<Ofertas> ofertas = new ArrayList<Ofertas>();
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
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAceptar.setBounds(178, 195, 89, 23);
		layeredPane.add(btnAceptar);

		JLabel lblElijeUnaOferta = new JLabel("Elije una oferta de la Lista");
		lblElijeUnaOferta.setBounds(165, 38, 136, 28);
		layeredPane.add(lblElijeUnaOferta);
		lblElijeUnaOferta.setForeground(Color.WHITE);
		lblElijeUnaOferta.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(32, 77, 389, 38);
		layeredPane.add(comboBox);
		for (int i = 0; i < mercado.getOfertas().size(); i++) {
			String s = "El Usuario " + mercado.getOfertas().get(i).getUser() + " ofrece el Item "
					+ Recursos.getItemsExistentesName(mercado.getOfertas().get(i).getOfertado()).toString()
					+ " y pide a cambio el Item "
					+ Recursos.getItemsExistentesName(mercado.getOfertas().get(i).getDemandado()).toString();
			comboBox.addItem(s);
			//ofertas.add(mercado.getOfertas().get(i));
			
		}

		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 444, 271);
		layeredPane.add(lblBackground);
		lblBackground.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/menuBackground.jpg")));
	}
}
