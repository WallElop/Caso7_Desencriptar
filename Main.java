package Caso7_Desencriptar;


public class Main {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AESDecrypt aes = new AESDecrypt();
		aes.cargarLetrasAndNumeros();
		aes.separarConjuntos();
		aes.probarTodosLosConjuntos();
	
	}

	

}
