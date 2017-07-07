
package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.chat.socket.Mensaje;
import com.chat.socket.SocketCliente;

import cliente.Cliente;

public class MenuChat extends JFrame {

    private JPanel contentPane;
    private JTextField mensajeChat;
    public SocketCliente socketCliente;
    public int puerto = 0;
    public String serverAddr = "";
    public Thread clientThread;
    public DefaultListModel<String> modelo;
    public static String nombreUsuario;
    public JTextArea chatArea;;
    public JButton botonEnviar;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        MenuChat.nombreUsuario = nombreUsuario;
    }

    public MenuChat(final Cliente cliente) {

        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon(MenuJugar.class.getResource("/cursor.png"))
                        .getImage(),
                new Point(0, 0), "custom cursor"));
        nombreUsuario = cliente.getPaqueteUsuario().getUsername();
        // En caso de cerrar
        // addWindowListener(new WindowAdapter() {
        // @Override
        // public void windowClosing(WindowEvent e) {
        // synchronized (cliente) {
        // cliente.setAccion(Comando.SALIR);
        // cliente.notify();
        // }
        // dispose();
        // }
        // });
        // nombreUsuario = cliente.getPaqueteUsuario().getUsername();
        // Panel
        setTitle("WOME - Chat - " + cliente.getPaqueteUsuario().getUsername());
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 444, 271);
        JLabel labelClientes = new JLabel("Clientes");
        labelClientes.setBounds(287, 24, 82, 27);
        labelClientes.setForeground(Color.WHITE);
        labelClientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
        JLabel labelSala = new JLabel("Sala de Chat");
        labelSala.setBounds(28, 24, 82, 27);
        labelSala.setForeground(Color.WHITE);
        labelSala.setFont(new Font("Tahoma", Font.PLAIN, 15));
        JList<String> listaClientes = new JList<String>();
        listaClientes.setBounds(287, 54, 123, 155);
        listaClientes.setFont(new Font("Tahoma", Font.BOLD, 16));
        listaClientes.setModel((modelo = new DefaultListModel<String>()));
        mensajeChat = new JTextField();
        mensajeChat.setBounds(24, 220, 253, 27);
        mensajeChat.setColumns(20);
        mensajeChat.setFont(new Font("Tahoma", Font.PLAIN, 14));
        chatArea = new JTextArea();
        chatArea.setBounds(24, 52, 253, 157);
        chatArea.setEditable(false);
        chatArea.setColumns(20);
        chatArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        chatArea.setRows(5);
        JLabel lblBackground = new JLabel("");
        lblBackground.setBounds(0, 0, 444, 271);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cerrar();
            }
        });
        
        botonEnviar = new JButton("Enviar");
        botonEnviar.setBounds(287, 220, 102, 27);
        botonEnviar.setEnabled(true);
        botonEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String mensaje = mensajeChat.getText();
                String destinatario = listaClientes.getSelectedValue().toString();

                if (!mensaje.isEmpty() && !destinatario.isEmpty()) {
                    mensajeChat.setText("");
                    socketCliente.enviarMensaje(new Mensaje("MENSAJE",nombreUsuario, mensaje, destinatario));
                }
            }
        });
        modelo.addElement("A TODOS");
        listaClientes.setSelectedIndex(0);
        contentPane.add(layeredPane);
        layeredPane.setLayout(null);
        layeredPane.add(labelClientes);
        layeredPane.add(labelSala);
        layeredPane.add(listaClientes);
        layeredPane.add(mensajeChat);
        layeredPane.add(chatArea);
        layeredPane.add(botonEnviar);
        layeredPane.add(lblBackground);
        lblBackground.setIcon(new ImageIcon(
                MenuMapas.class.getResource("/frames/menuBackground.jpg")));

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
//                try {
//                    socketCliente.enviarMensaje(new Mensaje("MENSAJE",
//                            nombreUsuario, ".bye", "SERVER"));
//                    clientThread.stop();
//                } catch (Exception ex) {
//                }
            }

            // esto deberia borrarlo.
            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        correrMetodoRun();

    }

    private void correrMetodoRun() {
        MenuChat MiUi = this;
        serverAddr = "localhost";
        puerto = 1500;
        if (!serverAddr.isEmpty() && puerto > 999) {
            try {
                socketCliente = new SocketCliente(this);
                clientThread = new Thread(socketCliente);
                clientThread.start();
                socketCliente.enviarMensaje(new Mensaje("TEST", "testUser","testContent", "SERVER"));
            } catch (Exception ex) {
                 chatArea.append("Aplicacion->Yo: Servidor No Encontrado\n");
            }
        }
    }
    
    public boolean esWin32(){
        return System.getProperty("os.name").startsWith("Windows");
    }
    
    public void cerrar() {
        this.setVisible(false);
    }

}