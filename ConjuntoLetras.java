package Caso7_Desencriptar;

import java.util.ArrayList;

public class ConjuntoLetras {
	ArrayList<String> letras = new ArrayList<String>();
	double probabilidadConjunto = 0.0;
	
	public void agregarLetra(ArrayList<String> pLetras,int pPosInicio, int posFinal) {
		while(pPosInicio<posFinal) {
			letras.add(pLetras.get(pPosInicio)) ;
			pPosInicio++;
		}
	}
	
	public double getProbabilidadConjunto() {
		return probabilidadConjunto;
	}

	public void setProbabilidadConjunto(double probabilidadConjunto) {
		this.probabilidadConjunto = probabilidadConjunto;
	}

	public ArrayList<String> getLetras() {
		return letras;
	}

	public void setLetras(ArrayList<String> letras) {
		this.letras = letras;
	}
	
	
	
//	public 
	
	
	
}
