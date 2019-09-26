package Caso7_Desencriptar;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESDecrypt {
	
	private static SecretKeySpec secretKey;// = "29dh120_dk1_3";
	private static ArrayList<String> letras = new ArrayList<String>();
	private static ArrayList<Integer> numeros = new ArrayList<Integer>();
	private static ArrayList<ConjuntoLetras> conjuntoLetras = new ArrayList<ConjuntoLetras>();
	private static ArrayList<ConjuntoNumeros> conjuntoNumeros = new ArrayList<ConjuntoNumeros>();
	private static String textoEncriptado ="xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=" ;
	private static int intentos = 0;
	
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void probarTodosLosConjuntos() {
		String respuesta = "";
		while(respuesta == "") {
			respuesta = encontrarClave();
		}
		System.out.println(respuesta);
		System.out.println(intentos);
	}
	
	
	public String encontrarClave() {
		ConjuntoLetras miConjuntoLetras = obtenerConjuntoLetras();
		ConjuntoNumeros miConjuntoNumeros = obtenerConjuntoNumeros();
		
		for(int i = 0; i<miConjuntoLetras.getLetras().size();i++) {
			for(int j = 0; j<miConjuntoNumeros.getNumeros().size(); j++) {
				String miClave = "29dh120"+ miConjuntoLetras.getLetras().get(i) +"dk1"+miConjuntoNumeros.getNumeros().get(j)+"3";
				setKey(miClave);
				String respuesta = decrypt(textoEncriptado);
				if( respuesta != null) {
					String msg = "Posible letra: "+miConjuntoLetras.getLetras().get(i)+"\n";
					msg+= "Posible numero: "+miConjuntoNumeros.getNumeros().get(j)+"\n";
					msg+="mensaje: "+respuesta;
					return msg;
				}
			}
		}
		return "";
	}
	
	public ConjuntoLetras obtenerConjuntoLetras() {
		ConjuntoLetras respuesta = conjuntoLetras.get(0);
		for(int i=1; i<conjuntoLetras.size();i++) {
			if(respuesta.getProbabilidadConjunto()<conjuntoLetras.get(i).getProbabilidadConjunto()) {
				respuesta = conjuntoLetras.get(i);
			}
		}
		return respuesta;
	}
	
	public ConjuntoNumeros obtenerConjuntoNumeros() {
		ConjuntoNumeros respuesta = conjuntoNumeros.get(0);
		for(int i=1; i<conjuntoLetras.size();i++) {
			if(respuesta.getProbabilidadConjunto()<conjuntoNumeros.get(i).getProbabilidadConjunto()) {
				respuesta = conjuntoNumeros.get(i);
			}
		}
		return respuesta;
	}

	public void separarConjuntos() {
		int divisionInicioLetras = 0;
		//Se hace una division del conjunto a la mitad para que al menos se creen 2 subconjuntos
		int divisionFinalLetras = (int)(Math.random()*13);
		int divisionInicioNumeros = 0;
		int divisionFinalNumeros = (int)(Math.random()*5);
		while(divisionInicioLetras<26) {
			ConjuntoLetras conjunto = new ConjuntoLetras();
			conjunto.agregarLetra(letras, divisionInicioLetras, divisionFinalLetras);
			divisionInicioLetras = divisionFinalLetras;
			divisionFinalLetras += (int)(Math.random()*(26-divisionInicioLetras));
		}
		while(divisionInicioNumeros<10) {
			ConjuntoNumeros conjunto = new ConjuntoNumeros();
			conjunto.agregarNumeros(numeros, divisionInicioNumeros, divisionFinalNumeros);
			divisionInicioNumeros = divisionFinalNumeros;
			divisionFinalNumeros += (int)(Math.random()*(10-divisionInicioNumeros));
		}
	}



}






