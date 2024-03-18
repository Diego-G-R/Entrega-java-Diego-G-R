package fp.funciones;

import java.util.List;

public class FuncionesTest {
	
	public static void testEsPrimo() {
		System.out.println("Iniciando el método:testEsPrimo");
		for (int i = 1; i < 13; i++) {
			System.out.println(i+ "," +Funciones.EsPrimo(i));
		}
		System.out.println("-".repeat(100));
	}

	public static void testCombinatorio() {
		System.out.println("Iniciando el método:testCombinatorio");
		for (int n = 0; n < 3; n++) {
			for (int k = 0; k < 3; k++) {
				try {
					System.out.println(n+","+k+":"+ Funciones.Combinatorio(n, k));
				} catch (Exception e) {
					System.err.println("n debe ser mayor que k");
				}				
			}		
		}
		System.out.println("-".repeat(100));
	}
	
	public static void testNumeroS() {
		System.out.println("Iniciando el método:testNumeroS");

		try {
			System.out.println("4,5:"+Funciones.NumeroS(4, 5));
		} catch (Exception e) {
			System.err.println("n debe ser mayor que k");
		}
		System.out.println("6,3:"+Funciones.NumeroS(6, 3));

		System.out.println("-".repeat(100));
	}

	public static void testDiferenciasLista() {
		System.out.println("Iniciando el método:testDiferenciasLista");
		
		List<Integer> lista= List.of(2,3,4,5,6,56,57,7);
		System.out.println(lista);
		System.out.println(Funciones.DiferenciasLista(lista));

		System.out.println("-".repeat(100));
	}

	public static void testCadenaMasLarga() {
		System.out.println("Iniciando el método:testCadenaMasLarga");

		System.out.println(Funciones.CadenaMasLarga(List.of("Manzana", "Banana", "Naranjas", "Pera", "Mango")));
		System.out.println(Funciones.CadenaMasLarga(List.of("Carlos", "María", "Juan", "Ana", "Pedro")));
		System.out.println(Funciones.CadenaMasLarga(List.of("Perro", "Gato", "Elefante", "León", "Tigre")));

		System.out.println("-".repeat(100));
	}
	
	public static void main(String[] args) {
		FuncionesTest.testEsPrimo();
		FuncionesTest.testCombinatorio();
		FuncionesTest.testNumeroS();
		FuncionesTest.testDiferenciasLista();
		FuncionesTest.testCadenaMasLarga();
	}
}
