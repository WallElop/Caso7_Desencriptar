package Caso7_Desencriptar;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESDecrypt {
	
	private static SecretKeySpec secretKey;// = "29dh120_dk1_3";
	private static ArrayList<String> letras = new ArrayList<String>();
	private static ArrayList<Integer> numeros = new ArrayList<Integer>();

	public static void setKey(String myKey) {
		MessageDigest sha;
		try {
			byte[] key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No se pudo completar");
		} catch (UnsupportedEncodingException e) {
			System.out.println("No se pudo completar");
		}
	}

	public static String decrypt(String strToDecrypt) {
		try {
			// setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(
					strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error");
		}
		return null;
	}
	
	public void cargarLetrasAndNumeros() {
		String[] pLetras = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		for(int i=0; i<26;i++) {
			letras.add(pLetras[i]);
		}
		for(int i = 0; i<10 ; i++ ){
			numeros.add(i);
		}	
		Collections.shuffle(letras);
		Collections.shuffle(numeros);
	}





}






