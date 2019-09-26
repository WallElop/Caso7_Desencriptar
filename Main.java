package Caso7_Desencriptar;


public class Main {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		setKey("29dh120sdk183");
//		String hola = decrypt("xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=");
//		System.out.println(hola);
		
		AESDecrypt aes = new AESDecrypt();
		aes.cargarLetrasAndNumeros();
		aes.separarConjuntos();
		aes.probarTodosLosConjuntos();
	
	}

	

}
