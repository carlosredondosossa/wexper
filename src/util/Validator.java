package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Validator {
	
	public static String generarPwd(String pwd){
        String algorithm = "SHA-1";
		byte[] digest = null;
        byte[] buffer = pwd.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);
    }
	
	private static String toHexadecimal(byte[] digest){
        String hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }
	
	public static boolean validarCorreo(String correo) {  
		
		correo = correo.trim().toLowerCase();
		
		String punto;
	    String dominio;
	    String usuario;
	    String Reserv;
		
		try{
			usuario = correo.substring(0, correo.lastIndexOf('@'));                  	// Cadena usuario@
			dominio = correo.substring(correo.lastIndexOf('@') + 1);    				// Aux
			punto = dominio.substring(dominio.indexOf('.') + 1, dominio.length()); 		// Cadena del .com
		    dominio = dominio.substring(0, dominio.indexOf('.'));						// Dominio @empresa
		    Reserv = "@/|¬°º\"\'+*()%&{}=´¨~`\\<>?¿[]áéíóú#$·¡!^;,: ";                  // Letras Reservadas
		    
		} catch (Exception e) {
			return false;
		}
	      
	    // Añadida por el Codigo para poder emitir un alert en funcion de si email valido o no  
	    boolean valido = true;  
	      
	    // Verifica que el correo no tenga un caracter especia
	    correo = correo.replaceFirst("@", "");
	    for (int cont=0; cont<correo.length(); cont++) {  
	        String x = correo.substring(cont,cont+1);  
	        if (Reserv.indexOf(x)!=-1)  
	        	valido = false;  
	    }  
	  
	    // Verifica la sintaxis básica.....  
	    if (punto.length()<2 || dominio.length()<1 || usuario.length()<1) {  
	        valido = false; 
	    } 
	    
	    // Verifica que solo exita un punto valido en el Dominio
	    if (punto.length() != 2 && punto.length() != 3 && punto.length() != 6) {
	    	valido = false;
	    }
	    if (punto.length() == 2 || punto.length() == 3) {
	    	if(punto.indexOf('.')!=-1){
	    		valido = false;
	    	} 
	    }
	    if (punto.length() == 6) {
	    	if(punto.indexOf('.')!=3 || punto.indexOf('.')!=punto.lastIndexOf('.')){
	    		valido = false;
	    	} 
	    }
	    
	    return valido;
	}

}
