package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import cliente.EscuchaMensajes;
import dominio.Mercado;
import dominio.Ofertas;
import mensajeria.PaqueteMercado;
import mensajeriaComandos.Comando;
import recursos.Recursos;

public class MenuMercado extends JFrame {

	private JPanel contentPane;
	private Mercado mercado;
	private PaqueteMercado paqueteMercado;
	private Gson gson = new Gson();

	public MenuMercado(final Cliente cliente) {

		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		setTitle("Bienvenido al Mercado");
		setBounds(100, 100, 450, 300);
		paqueteMercado = new PaqueteMercado();
		mercado = new Mercado(EscuchaMensajes.getOfertasDisponibles());
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
		setTitle("WOME - Mercado");
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

		JComboBox<String> comboBoxDemanda = new JComboBox<String>();
		comboBoxDemanda.setBounds(244, 136, 190, 20);
		layeredPane.add(comboBoxDemanda);
		for (int i = 0; i < Recursos.getItemsExistentes().length; i++) {
			comboBoxDemanda.addItem(Recursos.getItemsExistentesName(i));
		}

		JComboBox<String> comboBoxOferta = new JComboBox<String>();
		comboBoxOferta.setBounds(10, 136, 190, 20);
		layeredPane.add(comboBoxOferta);
		for (int i = 0; i < Recursos.getItemsExistentes().length; i++) {
			comboBoxOferta.addItem(Recursos.getItemsExistentesName(i));
		}

		JLabel labelDemandar = new JLabel("Demandar");
		labelDemandar.setBounds(327, 77, 82, 27);
		layeredPane.add(labelDemandar);
		labelDemandar.setForeground(Color.WHITE);
		labelDemandar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel labelOfertar = new JLabel("Ofertar");
		labelOfertar.setBounds(55, 77, 82, 27);
		layeredPane.add(labelOfertar);
		labelOfertar.setForeground(Color.WHITE);
		labelOfertar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton botonVerOferta = new JButton("Ver Oferta");
		botonVerOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OfertasDisponibles o = new OfertasDisponibles(cliente, mercado);
				o.setVisible(true);
			}
		});
		botonVerOferta.setBounds(298, 28, 117, 23);
		layeredPane.add(botonVerOferta);

		JLabel labelMensaje = new JLabel("");
		labelMensaje.setBounds(28, 24, 260, 27);
		layeredPane.add(labelMensaje);
		labelMensaje.setForeground(Color.WHITE);
		labelMensaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		if (!mercado.getOfertas().isEmpty()) {
			labelMensaje.setText("Hay Ofertas");
			botonVerOferta.setEnabled(true);
		} else {
			labelMensaje.setText("NO Hay Ofertas");
			botonVerOferta.setEnabled(false);
		}
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ofertas o1 = new Ofertas();
				if (cliente.getPaquetePersonaje().getMochila().getInventario()[comboBoxOferta.getSelectedIndex()] > 0) {
					o1 = new Ofertas(comboBoxOferta.getSelectedIndex(), comboBoxDemanda.getSelectedIndex(),
							cliente.getPaqueteUsuario().getUsername());
					mercado.AddOferta(o1);
					paqueteMercado.setOferta(o1);
					paqueteMercado.setComando(Comando.AGREGAROFERTA);

					try {
						cliente.getSalida().writeObject(gson.toJson(paqueteMercado));
					} catch (IOException e) {
						e.printStackTrace();
					}
					/*
					 * Servidor.AgregarOferta(new
					 * Ofertas(comboBoxOferta.getSelectedIndex(),
					 * comboBoxDemanda.getSelectedIndex(),
					 * cliente.getPaqueteUsuario().getUsername()));
					 */
				} else {
					JOptionPane.showMessageDialog(null, "El item a ofertar no esta disponible en tu mochila");
				}
				for (int m = 0; m < mercado.getOfertas().size(); m++) {
					if (mercado.getOfertas().get(m).getDemandado() == o1.getOfertado()
							&& mercado.getOfertas().get(m).getOfertado() == o1.getDemandado()
							&& mercado.getOfertas().get(m).getUser() != o1.getUser()) {
						JOptionPane.showConfirmDialog(null,
								"Hay coincidencia con la oferta de otro Usuario, ¿Queres Intercambiar?");
					}
				}
			}

		});
		botonAceptar.setBounds(171, 210, 102, 27);
		layeredPane.add(botonAceptar);

		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 444, 271);
		layeredPane.add(lblBackground);
		lblBackground.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/menuBackground.jpg")));
	}
}
