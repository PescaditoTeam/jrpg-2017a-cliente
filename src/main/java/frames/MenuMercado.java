package frames;

import mensajeria.Comando;
import recursos.Recursos;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;

import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;

public class MenuMercado extends JFrame {

	private JPanel contentPane;

	public MenuMercado(final Cliente cliente) {
		
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(),
				new Point(0,0),"custom cursor"));
		
		setTitle("Bienvenido al Mercado");
		setBounds(100, 100, 450, 300);
		
		// En caso de cerrar
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				synchronized(cliente){
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
		
		JComboBox comboBoxDemanda = new JComboBox();
		comboBoxDemanda.setBounds(244, 136, 190, 20);
		layeredPane.add(comboBoxDemanda);
		for(int i = 0; i < Recursos.getItemsExistentes().length; i++){
			comboBoxDemanda.addItem(Recursos.getItemsExistentesName(i));
		}
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(171, 210, 102, 27);
		layeredPane.add(botonAceptar);
		
		JComboBox comboBoxOferta = new JComboBox();
		comboBoxOferta.setBounds(10, 136, 190, 20);
		layeredPane.add(comboBoxOferta);
		for(int i = 0; i < Recursos.getItemsExistentes().length; i++){
			comboBoxOferta.addItem(Recursos.getItemsExistentesName(i));
		}
		//comboBoxOferta.addItem("Item 1");
		
		
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
		botonVerOferta.setBounds(298, 28, 117, 23);
		layeredPane.add(botonVerOferta);
		
		JLabel labelMensaje = new JLabel("Hay/No hay ofertas...");
		labelMensaje.setBounds(28, 24, 260, 27);
		layeredPane.add(labelMensaje);
		labelMensaje.setForeground(Color.WHITE);
		labelMensaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 444, 271);
		layeredPane.add(lblBackground);
		lblBackground.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/menuBackground.jpg")));
	}
}
