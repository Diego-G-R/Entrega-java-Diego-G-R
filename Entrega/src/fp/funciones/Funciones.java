package fp.funciones;

import java.util.ArrayList;
import java.util.List;

import us.lsi.tools.Preconditions;

public class Funciones {
	
	public static Boolean EsPrimo(Integer numero) {
		Double limite = Math.ceil(Math.sqrt(numero.doubleValue()));
		if(numero<1) { 
			System.err.println(String.format("Debe introducir un numero positivo y ha introducido %d", numero));
			return false;
		}
		if(numero==1) return false;
		if(numero==2) return true;
		for (int i = 2; i <= limite; i++) {
			if(numero%i == 0) return false;
		}
		return true;
	}

	public static Integer Factorial(Integer n) {
		Integer factorial=1;
		for (int i = 1; i <= n; i++) {
			factorial=factorial*i;
		}
		return factorial;
	}
	
	public static Integer Combinatorio(Integer n,Integer k) {
		Preconditions.checkArgument(n>=k, "n debe ser mayor que k");
		Integer factorialN = Funciones.Factorial(n);
		Integer factorialK = Funciones.Factorial(k);
		Integer factorialNK = Funciones.Factorial(n-k);
		
		return factorialN/(factorialK*factorialNK);
	}
	
	public static Double NumeroS(Integer n, Integer k) {
		Preconditions.checkArgument(n>=k, "n debe debe ser mayor que k");
		Double sumatorio= 0.;
		for (int i = 0; i <= k; i++) {
			Double x1 = Math.pow((-1), i);
			Integer x2 = Funciones.Combinatorio(k, i);
			Double x3 = Math.pow(k-i, n);
			sumatorio= sumatorio + x1*x2*x3;
		}
		
		return (1/k.doubleValue())*sumatorio;
	}
	
	public static List<Integer> DiferenciasLista(List<Integer> lista) {
		List<Integer> resultado = new ArrayList<Integer>();
		for (int i = 0; i < lista.size()-1; i++) {
			Integer x = lista.get(i);
			Integer y = lista.get(i+1);
			Integer n = x-y;
			resultado.add(n);
		}
		return resultado;
		}
		
	public static String CadenaMasLarga(List<String> lista) {
		String cadena=null;
		for (String i : lista) {
			if(cadena==null) cadena=i;
			if(cadena.length()<i.length()) cadena=i;
		}
		return cadena;
	}
	
}

