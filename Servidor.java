

/**
 *
 * @author yeileen Perez y Anibal Alveo
 * Socimo Roble
   Teoria de Codificacion 
   Date: 15-12-2021
 */


import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.net.*;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class Servidor {
public String mensaje;
     String LLAVE = " ";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws InvalidKeyException {
        // TODO code application logic here

      MarcoServidor mimarco = new MarcoServidor();
     mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     



    }
}




class MarcoServidor extends JFrame implements Runnable{
 
public MarcoServidor(){



setBounds(1200,300,280,350);
JPanel milamina = new JPanel ();
milamina.setLayout(new BorderLayout());
areatexto = new JTextArea();

milamina.add(areatexto,BorderLayout.CENTER);

add(milamina);
setVisible(true);

Thread mihilo = new Thread (this);
mihilo.start();
}




@Override
public void run(){
//System.out.println("estoy a la escucha");
try{
   ServerSocket servidor = new ServerSocket(9999);

String nick,ip,mensaje;

PaqueteEnvio paquete_recibido;


while(true){
    Socket misocket=servidor.accept();

ObjectInputStream paquete_datos= new ObjectInputStream(misocket.getInputStream());

paquete_recibido= (PaqueteEnvio) paquete_datos.readObject();


nick = paquete_recibido.getNick();

ip= paquete_recibido.getIp();

mensaje= paquete_recibido.getMensaje();



/*DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());

String mensaje_texto = flujo_entrada.readUTF();

areatexto.append("\n" +mensaje_texto);*/


String encriptada;
try{
 AES aes = new AES();
 encriptada = aes.EncriptarLLave(mensaje);
System.out.println(aes.EncriptarLLave(encriptada));
System.out.println(aes.DesencriptarLLave(encriptada));
}catch(Exception e ){
}


areatexto.append("\n"+ nick+" : "+mensaje+ " para" + ip);
 
Socket enviaDestinatario = new Socket(ip,9090);
 
ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());

paqueteReenvio.writeObject(paquete_recibido);

paqueteReenvio.close();

enviaDestinatario.close();
misocket.close();
}
}catch(IOException  |ClassNotFoundException e){
  e.printStackTrace();
}

}
public JTextArea areatexto;



}


