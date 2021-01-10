package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashSenha {

     private static final String hexDigits = "0123456789abcdef";

     private static byte[] digest(byte[] input, String algoritmo)
         throws NoSuchAlgorithmException {
         MessageDigest md = MessageDigest.getInstance(algoritmo);
         md.reset();
         return md.digest(input);
     }
   
     public static String criptografar(String texto,String algoritmo){
         String result = null;
        try {
            result =  byteArrayToHexString(digest(texto.getBytes(), algoritmo));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashSenha.class.getName()).log(Level.SEVERE, null, ex);
        }

         return result;
     }

     private static String byteArrayToHexString(byte[] b) {
         StringBuffer buf = new StringBuffer();
     
         for (int i = 0; i < b.length; i++) {
            int j = ((int) b[i]) & 0xFF; 
             buf.append(hexDigits.charAt(j / 16)); 
             buf.append(hexDigits.charAt(j % 16)); 
         }
         
         return buf.toString();
     }
  
    /**
      * Converte uma String hexa no array de bytes correspondente.
     * @param hexa - A String hexa
      * @return O vetor de bytes
      * @throws IllegalArgumentException - Caso a String não sej auma
      * representação haxadecimal válida
      */
     private static byte[] hexStringToByteArray(String hexa)
         throws IllegalArgumentException {
       
         //verifica se a String possui uma quantidade par de elementos
         if (hexa.length() % 2 != 0) {
             throw new IllegalArgumentException("String hexa inválida");  
         }
       
         byte[] b = new byte[hexa.length() / 2];
       
         for (int i = 0; i < hexa.length(); i+=2) {
             b[i / 2] = (byte) ((hexDigits.indexOf(hexa.charAt(i)) << 4) |
                 (hexDigits.indexOf(hexa.charAt(i + 1))));          
         }
         return b;
     }

}
