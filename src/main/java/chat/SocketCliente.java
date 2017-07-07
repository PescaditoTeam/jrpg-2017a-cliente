package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import frames.MenuChat;

/*
 * Se encarga de recibir los comandos por medio del pipe de entrada..
 */
public class SocketCliente implements Runnable{
    
    public int puerto;
    public String direccionDelServidor;
    public Socket socket;
    public MenuChat ui;
    public ObjectInputStream entrada;
    public ObjectOutputStream salida;

    
    public SocketCliente(MenuChat menuChat) throws IOException{
        ui = menuChat; 
        this.direccionDelServidor = ui.serverAddr; 
        this.puerto = ui.puerto;
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
                
                if(mensaje.tipo.equals("MENSAJE")){
                    if(mensaje.destinatario.equals(ui.getNombreUsuario())){
                        ui.chatArea.setText(ui.chatArea.getText() + mensaje.remitente+" ->Yo: " + mensaje.contenido + "\n");
                    }
                    else{
                        ui.chatArea.setText(ui.chatArea.getText() + mensaje.remitente +"->"+mensaje.destinatario +": " + mensaje.contenido + "\n");
                    }
                                            
                }
                //-------------------------------------------
                else if(mensaje.tipo.equals("TEST")){
                    System.out.println("TEST OK"+mensaje.remitente);
                }
                else if(mensaje.tipo.equals("NUEVO_USUARIO")){
                    if(!mensaje.contenido.equals(ui.getNombreUsuario())){
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
            }
            catch(Exception ex) {
                mantenerCorriendo = false;
                ex.printStackTrace();
                ui.chatArea.setText(ui.chatArea.getText() + "Aplicacion->Yo: Coneccion Fall�\n");
                //ui.jButton4.setEnabled(false); 
                //Quita el usuario del modelo del JList
                for(int i = 1; i < ui.modelo.size(); i++){
                    ui.modelo.removeElementAt(i);
                }
                
                ui.clientThread.stop();
                
                System.out.println("Error en: SocketClient run()");
                
            }
        }
    }
    //Finalmente envio el paquete mensaje. Ya no envio lineas de texto con printLn.
    public void enviarMensaje(Mensaje mensaje){
        try {
            salida.writeObject(mensaje);
            salida.flush();
            System.out.println("Saliente: " + mensaje.toString());
        } 
        catch (IOException ex) {
            System.out.println("Error en SocketClient enviarMensaje()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
