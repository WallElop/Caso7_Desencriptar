package Caso7_Desencriptar;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.SecretKeySpec;

public class Main {

	private static SecretKeySpec secretKey;// = "29dh120_dk1_3";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setKey("29dh120sdk183");
	}
	
	public static void setKey(String myKey) {
	    MessageDigest sha;
	    try {
	      byte[] key = myKey.getBytes("UTF-8");
	      sha = MessageDigest.getInstance("SHA-1");
	      key = sha.digest(key);
	      //key = Arrays.copyOf(key, 16);
	      secretKey = new SecretKeySpec(key, "AES");
	    } catch (NoSuchAlgorithmException e){
	    	System.out.println("No se pudo completar");
	    } catch (UnsupportedEncodingException e) {
	      System.out.println("No se pudo completar");
	    }
	  }
	
	

}
