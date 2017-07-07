package com.chat.ui;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.chat.socket.Mensaje;
import com.chat.socket.SocketCliente;

import cliente.Cliente;

public class VistaCliente extends javax.swing.JFrame {

    public SocketCliente cliente;
    public int puerto=0;
    public String serverAddr="";
    public static String nombreDeUsuario;
    public Thread clientThread;
    public DefaultListModel<String> modelo;//Aca es donde esta el contenido del JList. Lo manejo con este model
    public File file;
    private Cliente miUsuarioCliente;
    
    public VistaCliente(Cliente clienteYo) {
        miUsuarioCliente = clienteYo;
    	getContentPane().setBackground(new Color(240, 255, 240));
    	nombreDeUsuario = clienteYo.getPaqueteUsuario().getUsername();
        inicializarComponentesDeVentana();
        this.setTitle("Cliente de Chat V3.0");
        agregarClientesALaLista();
        getContentPane().setLayout(null);
        getContentPane().add(jSeparator2);
        getContentPane().add(jSeparator1);
       // getContentPane().add(jButton1);
        getContentPane().add(jTextField4);
        getContentPane().add(jButton4);
        getContentPane().add(jScrollPane1);
        getContentPane().add(jScrollPane2);
        
        JLabel lblClientesConectados = new JLabel("Clientes");
        lblClientesConectados.setBounds(282, 13, 86, 20);
        getContentPane().add(lblClientesConectados);
        
        JLabel lblSalaDeChat = new JLabel("Sala de Chat");
        lblSalaDeChat.setBounds(80, 13, 117, 20);
        getContentPane().add(lblSalaDeChat);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnMenuPrincipal = new JMenu("Menu Principal");
        menuBar.add(mnMenuPrincipal);
        
        /*JMenuItem mntmRegistrarse = new JMenuItem("Configurar IP");
        VistaCliente MiUi=this;
        mntmRegistrarse.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		PuertoIP pip=new PuertoIP(MiUi);
        		pip.setVisible(true);
        	}
        });
        mnMenuPrincipal.add(mntmRegistrarse);*/
        
        mnAyuda = new JMenu("Ayuda");
        menuBar.add(mnAyuda);
        
        mntmAcerca = new JMenuItem("Random");
        mnAyuda.add(mntmAcerca);
        
        this.addWindowListener(new WindowListener() {

            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) { 
            	//try{
            		//cliente.enviarMensaje(new Mensaje("MENSAJE", nombreDeUsuario, ".bye", "SERVER"));
            		//clientThread.stop();
            	//}catch(Exception ex){} 
            	}
            //esto deberia borrarlo.
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
      p();
    }
    

    private void agregarClientesALaLista() {
        modelo.addElement("A TODOS");
//        for(int i=0; i< EscuchaMensajes.getPersonajesConectados.size(); i++){
//            modelo.addElement(EscuchaMensajes.getPersonajesConectados.getPaquetePersonaje.get);
//        }
        
        jList1.setSelectedIndex(0);
        
    }


    @SuppressWarnings("unchecked")
    private void inicializarComponentesDeVentana() {
       /* jButton1 = new JButton();
        jButton1.setHorizontalAlignment(SwingConstants.LEFT);
        jButton1.setBackground(new Color(255, 99, 71));
        jButton1.setForeground(new Color(0, 0, 0));
        jButton1.setFont(new Font("Tahoma", Font.BOLD, 17));
        jButton1.setBounds(142, 11, 111, 29);*/
        jSeparator1 = new JSeparator();
        jSeparator1.setBounds(579, 41, 0, 10);
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setBounds(12, 39, 255, 175);
        jTextArea1 = new JTextArea();
        jScrollPane2 = new JScrollPane();
        jScrollPane2.setBounds(282, 39, 124, 175);
        jList1 = new JList<String>();
        jList1.setFont(new Font("Dialog", Font.BOLD, 16));
        jTextField4 = new JTextField();
        jTextField4.setBounds(14, 230, 392, 26);
        jButton4 = new JButton();
        jButton4.setBounds(117, 269, 176, 96);
        jSeparator2 = new JSeparator();
        jSeparator2.setBounds(579, 392, 0, 10);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cerrar();
            }
        });

       /* jButton1.setText("Conectar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });*/

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new Font("Dialog", Font.PLAIN, 14)); 
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jList1.setModel((modelo = new DefaultListModel<String>()));
        jScrollPane2.setViewportView(jList1);

        jButton4.setText("Enviar");
        jButton4.setEnabled(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        //pack();
    }
    
   /* private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	 VistaCliente MiUi=this;
    	 serverAddr="localhost";
    	 puerto=1500;
    	if(serverAddr.isEmpty()||puerto<999){
    		PuertoIP pip=new PuertoIP(MiUi);
    		pip.setVisible(true);
    	}
    	
        if(!serverAddr.isEmpty() && puerto>999){
            try{
                cliente = new SocketCliente(this);
                clientThread = new Thread(cliente);
                clientThread.start();
                cliente.enviarMensaje(new Mensaje("TEST", "testUser", "testContent", "SERVER"));
            }
            catch(Exception ex){
                jTextArea1.append("Aplicacion->Yo: Servidor No Encontrado\n");
            }
        }
    }*/
    public void p(){
    	 VistaCliente MiUi=this;
    	 serverAddr="localhost";
    	 puerto=1500;
    	/*if(serverAddr.isEmpty()||puerto<999){
    		PuertoIP pip=new PuertoIP(MiUi);
    		pip.setVisible(true);
    	}*/
    	
        if(!serverAddr.isEmpty() && puerto>999){
            try{
                //cliente = new SocketCliente(this);
                clientThread = new Thread(cliente);
                clientThread.start();
                cliente.enviarMensaje(new Mensaje("TEST", "testUser", "testContent", "SERVER"));
            }
            catch(Exception ex){
                jTextArea1.append("Aplicacion->Yo: Servidor No Encontrado\n");
            }
        }
    }


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        String mensaje = jTextField4.getText();
        String target = jList1.getSelectedValue().toString();
        
        if(!mensaje.isEmpty() && !target.isEmpty()){
            jTextField4.setText("");
            cliente.enviarMensaje(new Mensaje("MENSAJE", nombreDeUsuario, mensaje, target));
        }
    }
    
    public boolean esWin32(){
        return System.getProperty("os.name").startsWith("Windows");
    }


//    public static void main(String args[]) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } 
//        catch(Exception ex){
//            System.out.println("Look & Feel exception");
//        }
//        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VistaCliente(clienteYo).setVisible(true);
//            }
//        });
//     
//    }
    public void cerrar() {
        this.setVisible(false);
    }
    public static void setNombreUsuario(String nombre){nombreDeUsuario=nombre;}
   
   // public javax.swing.JButton jButton1;

    public javax.swing.JButton jButton4;
    public javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField4;
    private JMenu mnAyuda;
    private JMenuItem mntmAcerca;
}
