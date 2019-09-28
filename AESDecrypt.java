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

/**
 * 
 * @author Eduard Jiménez Molinares.
 * @author Walter López Araya.
 *
 */
public class AESDecrypt {
	//Atributos de la clase.
	private SecretKeySpec secretKey;
	private ArrayList<String> letras = new ArrayList<String>();
	private ArrayList<Integer> numeros = new ArrayList<Integer>();
	private ArrayList<ConjuntoLetras> conjuntoLetras = new ArrayList<ConjuntoLetras>();
	private String textoEncriptado ="xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=" ;
	private int intentos = 0;
	
	/**
	 * Algoritmo facilitado por el profesor.
	 * @param myKey Tipo String. Llave para desencriptar.
	 */
	public void setKey(String myKey) {
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

	/**
	 * Algoritmo para desencriptar AES
	 * @param strToDecrypt Tipo String. Texto a desencriptar
	 * @return el string desencriptado o nulo en caso de no poder desencriptarlo.
	 */
	public String decrypt(String strToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Carga el alfabeto y los numeros del 0 al 9 en sus respectivos arrays.
	 */
	public void cargarLetrasAndNumeros() {
		String[] pLetras = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		for(int contadorLetras=0; contadorLetras<26;contadorLetras++) {
			letras.add(pLetras[contadorLetras]);
		}
		for(int contadorNumeros = 0; contadorNumeros<10 ; contadorNumeros++ ){
			numeros.add(contadorNumeros);
		}
		
		//Se hace revuelven las letras de forma random
		Collections.shuffle(letras);
		//Se revuelven los numeros de forma random.
		Collections.shuffle(numeros);
	}
	
	/**
	 * Se prueban los conjuntos hasta obtener la respuesta.
	 */
	public void probarTodosLosConjuntos() {
		String respuesta = "";
		while(respuesta == "") {
			respuesta = encontrarClave();
		}
		//Imprime la respuesta(posible letra, posible numero y texto descifrado) y el numero de intentos
		System.out.println(respuesta);
		System.out.println(intentos);
	}
	
	/**
	 * Prueba las combinaciones hasta encontrar la posible clave
	 * @return un string con posible letra, posible numero y texto descifrado
	 */
	public String encontrarClave() {
		//Se obtiene el conjunto que se va a probar.
		ConjuntoLetras miConjuntoLetras = obtenerConjuntoLetras();
		
		//Se recorren la letras del conjunto seleccionado
		for(int contadorLetras = 0; contadorLetras<miConjuntoLetras.getLetras().size();contadorLetras++) {
			//Se recorre la lista de numeros.
			for(int contadorNumeros = 0; contadorNumeros<numeros.size(); contadorNumeros++) {
				String miClave = "29dh120"+ miConjuntoLetras.getLetras().get(contadorLetras) +"dk1"+numeros.get(contadorNumeros)+"3";
				setKey(miClave);
				String respuesta = decrypt(textoEncriptado);
				//Se suma el intento.
				intentos++;
				if( respuesta != null) {
					String msg = "Posible letra: "+miConjuntoLetras.getLetras().get(contadorLetras)+"\n";
					msg+= "Posible numero: "+numeros.get(contadorNumeros)+"\n";
					msg+="mensaje: "+respuesta;
					return msg;
				}
			}
		}
		return "";
	}
	
	/**
	 * Obtiene el conjunto de letras con el que se trabajará.
	 * @return conjunto de letras con el que se trabajará.
	 */
	public ConjuntoLetras obtenerConjuntoLetras() {
		//Respuesta va a ser el primer elemento del arreglo.
		ConjuntoLetras respuesta = conjuntoLetras.get(0);
		//Se inicia en uno para no compara la posición 0 con la 0
		for(int contadorConjuntoLetras=1; contadorConjuntoLetras<conjuntoLetras.size();contadorConjuntoLetras++) {
			//obtiene el conjunto con la mayor probabilidad de que la letra esté en el.
			if(respuesta.getProbabilidadConjunto()<conjuntoLetras.get(contadorConjuntoLetras).getProbabilidadConjunto()) {
				respuesta = conjuntoLetras.get(contadorConjuntoLetras);
			}
		}
		//Se deja la probabilidad en cero una vez el conjunto ha sido probado.
		respuesta.setProbabilidadConjunto(0);
		return respuesta;
	}

	/**
	 * Se ha la separacion del conjunto de letras en subconjuntos.
	 */
	public void separarConjuntos() {
		//el tamaño de cada subConjunto se hace de manera random.
		int divisionInicioLetras = 0;
		//Se hace una division del conjunto a la mitad para que al menos se creen 2 subconjuntos
		int divisionFinalLetras = (int)(Math.random()*13 +1);
		while(divisionInicioLetras<26) {
			ConjuntoLetras conjunto = new ConjuntoLetras();
			conjunto.agregarLetra(letras, divisionInicioLetras, divisionFinalLetras);
			double cantidadElementos = divisionFinalLetras-divisionInicioLetras;
			//Probabilidad de que la letra esté en el conjunto
			conjunto.setProbabilidadConjunto(cantidadElementos/26d);
			divisionInicioLetras = divisionFinalLetras;
			divisionFinalLetras += (int)(Math.random()*(26-divisionInicioLetras) + 1);
			conjuntoLetras.add(conjunto);
		}
	}



}






