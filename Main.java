package Caso7_Desencriptar;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Arrays;
import javax.crypto.Cipher;

public class Main {
	private static SecretKeySpec secretKey;// = "29dh120_dk1_3";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setKey("29dh120sdk183");
		String hola = decrypt("xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=");
		System.out.println(hola);
	}

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

}
