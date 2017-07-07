package com.chat.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.chat.ui.VistaCliente;

import cliente.Cliente;
import frames.MenuChat;

/*
 * Se encarga de recibir los comandos por medio del pipe de entrada..
 */
public class SocketCliente implements Runnable{
    
    public int puerto;
    public String direccionDelServidor;
    public Socket socket;
    //public VistaCliente ui;
    public MenuChat ui;
    public ObjectInputStream entrada;
    public ObjectOutputStream salida;
    public String miUserName="Not Set yet";

    
    public SocketCliente(/*VistaCliente vistaCliente*/ MenuChat menuChat) throws IOException{
        ui = menuChat; this.direccionDelServidor = ui.serverAddr; this.puerto = ui.puerto;
        socket = new Socket(InetAddress.getByName(direccionDelServidor), puerto);
            
        salida = new ObjectOutputStream(socket.getOutputStream());
        //SI no limpio a veces quedaba con basura en los intentos anteriores. usando PrintLn
        salida.flush();
        entrada = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        boolean mantenerCorriendo = true;
        while(mantenerCorriendo){
            try {
            	//aca se bloquea el hilo hasta que llegue info al pipe de entrada
                Mensaje mensaje = (Mensaje) entrada.readObject();
                System.out.println("Entrada: "+mensaje.toString());
                ui.setMensajePrivado(false);
                if(mensaje.tipo.equals("MENSAJE")){
                    if(mensaje.destinatario.equals(ui.nombreUsuario)){
                        ui.chatArea.append(mensaje.remitente+" ->Yo: " + mensaje.contenido + "\n");
                        ui.setMensajePrivado(true);
                    }
                    else{
                        ui.chatArea.append(mensaje.remitente +"->"+mensaje.destinatario +": " + mensaje.contenido + "\n");
                    }
                                            
                }
                //-------------------------------------------
                // Comando Login
                //-------------------------------------------
                else if(mensaje.tipo.equals("LOGIN")){
                    if(mensaje.contenido.equals("TRUE")){
                     
                        ui.botonEnviar.setEnabled(true);
                        ui.chatArea.append("SERVER->Yo: Login Exitoso\n");
                    }
                    else{
                        ui.chatArea.append("SERVER->Yo: Login Fallido\n");
                    }
                }
              //-------------------------------------------
                // Comando Test, usado para probar conexion la privera vez que se comunica cliente con servidor.
                //-------------------------------------------
                else if(mensaje.tipo.equals("TEST")){
                    System.out.println("TEST OK"+mensaje.remitente);
                }
              //-------------------------------------------
                // Comando nuevo usuario,ya registrado.
                //-------------------------------------------
                else if(mensaje.tipo.equals("NUEVO_USUARIO")){
                    if(!mensaje.contenido.equals(ui.nombreUsuario)){
                        boolean yaEexiste = false;
                        for(int i = 0; i < ui.modelo.getSize(); i++){
                            if(ui.modelo.getElementAt(i).equals(mensaje.contenido)){
                            	yaEexiste = true;
                            	break;
                            }
                        }
                        if(!yaEexiste){ 
                            ui.modelo.addElement(mensaje.contenido); 
                            }
                    }
                }
                //-------------------------------------------
                // Comando salir del sistema
                //-------------------------------------------
                else if(mensaje.tipo.equals("SALIR")){
                    if(mensaje.contenido.equals(ui.nombreUsuario)){
                        ui.chatArea.append(mensaje.remitente +"->Yo: Saliendo..\n");
                       // ui.jButton1.setEnabled(true);
                        ui.botonEnviar.setEnabled(false); 
                        
                        for(int i = 1; i < ui.modelo.size(); i++){
                            ui.modelo.removeElementAt(i);
                        }
                        
                        ui.clientThread.stop();
                    }
                    else{
                        ui.modelo.removeElement(mensaje.contenido);
                        ui.chatArea.append(mensaje.remitente +"->All: "+ mensaje.contenido +" Se Ha desconectado..\n");
                    }
                }

            }
            catch(Exception ex) {
                mantenerCorriendo = false;
                ui.chatArea.append("Aplicacion->Yo: Coneccion Fall�\n");
             //   ui.jButton1.setEnabled(true);
                ui.botonEnviar.setEnabled(false); 
                //Quita el usuario del modelo del JList
                for(int i = 1; i < ui.modelo.size(); i++){
                    ui.modelo.removeElementAt(i);
                }
                
                ui.clientThread.stop();
                
                System.out.println("Error en: SocketClient run()");
                ex.printStackTrace();
            }
        }
    }
    //Finalmente envio el paquete mensaje. Ya no envio lineas de texto con printLn.
    public void enviarMensaje(Mensaje mensaje){
        try {
            salida.writeObject(mensaje);
            salida.flush();
            System.out.println("Saliente: "+mensaje.toString());
        } 
        catch (IOException ex) {
            System.out.println("Error en SocketClient enviarMensaje()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
