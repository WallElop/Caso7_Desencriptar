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
//	private static String[] letras = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
//	private static int[] numeros = {0,1,2,3,4,5,6,7,8,9,};

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
	
//	public static void mezclar() {
//		shuffleArray
//		
//	}
	
	public void cargarLetrasAndNumeros() {
		letras.add("a");letras.add("b");letras.add("c");letras.add("d");letras.add("e");letras.add("f");
		letras.add("g");letras.add("h");letras.add("i");letras.add("j");letras.add("k");letras.add("l");
		letras.add("m");letras.add("n");letras.add("o");letras.add("p");letras.add("q");letras.add("r");
		letras.add("s");letras.add("t");letras.add("u");letras.add("v");letras.add("w");letras.add("x");
		letras.add("y");letras.add("z");
		
		numeros.add(0);numeros.add(1);numeros.add(2);numeros.add(3);numeros.add(4);numeros.add(5);
		numeros.add(6);numeros.add(7);numeros.add(8);numeros.add(9);
		
		
		Collections.shuffle(letras);
		Collections.shuffle(numeros);
		
	}
	
}
