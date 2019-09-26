package Caso7_Desencriptar;

import java.util.ArrayList;

public class ConjuntoNumeros {
	ArrayList<Integer> numeros = new ArrayList<Integer>();
	double probabilidadConjunto = 0d;

	public void agregarNumeros(ArrayList<Integer> pNumeros, int posicionInicio, int posicionFinal) {
		while (posicionInicio < posicionFinal) {
			numeros.add(pNumeros.get(posicionInicio));
			posicionInicio++;
		}
	}

	public double getProbabilidadConjunto() {
		return probabilidadConjunto;
	}

	public void setProbabilidadConjunto(double probabilidadConjunto) {
		this.probabilidadConjunto = probabilidadConjunto;
	}

	public ArrayList<Integer> getNumeros() {
		return numeros;
	}

	public void setNumeros(ArrayList<Integer> numeros) {
		this.numeros = numeros;
	}
	
	

}
