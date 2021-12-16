

/**
 *
 * @author yeileen Perez y Anibal Alveo
 * Socimo Roble
   Teoria de Codificacion 
   Date: 15-12-2021
 */

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class AES {
String LLAVE= "";
    //CLAVE DE ENCRIPTACION

public SecretKeySpec CrearClave(String llave){
 
try{

byte[] cadena = llave.getBytes("UTF-8");
MessageDigest md = MessageDigest.getInstance("SHA-256");

cadena = md.digest(cadena);
cadena = Arrays.copyOf(cadena,16);
SecretKeySpec secretKeySpec = new SecretKeySpec ( cadena,"AES");
return secretKeySpec;

}catch(Exception e) {
 return null;

}
}

//ENCRIPTAR CLave
public  String EncriptarLLave (String encriptar) throws InvalidKeyException{

try{
SecretKeySpec secretKeySpec = CrearClave(LLAVE);
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);

byte [] cadena = encriptar.getBytes("UTF-8");
byte [] encriptada = cipher.doFinal(cadena);
String cadena_encriptada= Base64.encode(encriptada);
return cadena_encriptada;
}catch (Exception e){

return "";
}
}


// DES-CRIPTACION

public String DesencriptarLLave (String desencriptar) throws InvalidKeyException{

try{
SecretKeySpec secretKeySpec = CrearClave(LLAVE);
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);

byte [] cadena = Base64.decode(desencriptar);
byte [] desencriptacion = cipher.doFinal(cadena);
String cadena_desencriptada= new String (desencriptacion) ;
return cadena_desencriptada;
}catch (Exception e){

return "";
}
}
}


