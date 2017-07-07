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
import java.util.ArrayList;

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
import dominio.Mercado;
import dominio.Ofertas;
import mensajeria.PaqueteIntercambio;
import mensajeriaComandos.Comando;
import recursos.Recursos;

public class OfertasDisponibles extends JFrame {

    private JPanel contentPane;
    boolean yaSeHizo = false;
    ArrayList<Ofertas> ofertasASeleccionar;
    Ofertas oSelect;
    Gson gson = new Gson();

    public OfertasDisponibles(final Cliente cliente, Mercado mercado,
            ArrayList<Ofertas> ofertasUtilizadas) {

        ArrayList<Ofertas> ofertas = new ArrayList<Ofertas>();
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(MenuJugar.class.getResource("/cursor.png"))
                        .getImage(),
                new Point(0, 0), "custom cursor"));

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

        JLabel lblElijeUnaOferta = new JLabel("Elije una oferta de la Lista");
        lblElijeUnaOferta.setBounds(165, 38, 136, 28);
        layeredPane.add(lblElijeUnaOferta);
        lblElijeUnaOferta.setForeground(Color.WHITE);
        lblElijeUnaOferta.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setBounds(15, 77, 425, 38);
        layeredPane.add(comboBox);
        for (int i = 0; i < mercado.getOfertas().size(); i++) {
            for (int j = 0; j < ofertasUtilizadas.size(); j++) {
                if (mercado.getOfertas().get(i)
                        .getDemandado() == ofertasUtilizadas.get(j)
                                .getDemandado()
                        && mercado.getOfertas().get(i)
                                .getOfertado() == ofertasUtilizadas.get(j)
                                        .getOfertado()
                        && mercado.getOfertas().get(i)
                                .getIdUser() == ofertasUtilizadas.get(j)
                                        .getIdUser()) {
                    yaSeHizo = true;
                }
            }
            if (yaSeHizo == false) {
                String s = "El Usuario " + mercado.getOfertas().get(i).getUser()
                        + " ofrece el Item "
                        + Recursos.getItemsExistentesName(
                                mercado.getOfertas().get(i).getOfertado())
                                .toString()
                        + " y pide a cambio el Item "
                        + Recursos.getItemsExistentesName(
                                mercado.getOfertas().get(i).getDemandado())
                                .toString();
                comboBox.addItem(s);
                ofertasASeleccionar.add(mercado.getOfertas().get(i));
            }
            yaSeHizo = false;
        }
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oSelect = ofertasASeleccionar.get(comboBox.getSelectedIndex());
                if (oSelect.getIdUser() == cliente.getPaquetePersonaje()
                        .getId()) {
                    JOptionPane.showMessageDialog(null,
                            "No puede hacerte una oferta a ti mismo");
                } else {
                    if (cliente.getPaquetePersonaje().getMochila()
                            .getInventario()[oSelect.getDemandado()] == 0) {
                        JOptionPane.showMessageDialog(null,
                                "No cuentas con el Item que el Usuario "
                                        + oSelect.getUser() + " pide a cambio");
                    } else {

                        Ofertas nueva = new Ofertas(oSelect.getDemandado(),
                                oSelect.getOfertado(),
                                cliente.getPaqueteUsuario().getUsername(),
                                cliente.getPaquetePersonaje().getId());
                        PaqueteIntercambio paqueteIntercambio = new PaqueteIntercambio(
                                nueva.getIdUser(), oSelect.getIdUser(),
                                nueva.getDemandado(), nueva.getOfertado());
                        paqueteIntercambio.setComando(Comando.INTERCAMBIAR);
                        try {
                            cliente.getSalida()
                                    .writeObject(gson.toJson(paqueteIntercambio));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }
                }

            }
        });
        btnAceptar.setBounds(178, 195, 89, 23);
        layeredPane.add(btnAceptar);

        JLabel lblBackground = new JLabel("");
        lblBackground.setBounds(0, 0, 444, 271);
        layeredPane.add(lblBackground);
        lblBackground.setIcon(new ImageIcon(
                MenuMapas.class.getResource("/frames/menuBackground.jpg")));
    }
}
