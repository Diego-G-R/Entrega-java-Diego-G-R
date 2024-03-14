package fp.tipos.test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import fp.tipos.Fecha;



public class TestFecha {
	
	public static void testnombreMes() {
		System.out.println("Iniciando el método:testnombreMes");
		
		for (int i = 1; i < 13; i++) {
			System.out.println(Fecha.nombreMes(i));
		}

		System.out.println("-".repeat(100));
	}

	public static void testdiaSemana() {
		System.out.println("Iniciando el método:testdiaSemana");

		System.out.println(Fecha.diaSemana(2024, 3, 5));
		System.out.println(Fecha.diaSemana(2024, 2, 5));
		System.out.println(Fecha.diaSemana(2024, 2, 17));

		System.out.println("-".repeat(100));
	}

	public static void testdíasEnMes() {
		System.out.println("Iniciando el método:testdíasEnMes");

		System.out.println(Fecha.diasEnMes(2023, 2));
		System.out.println(Fecha.diasEnMes(2024, 2));
		System.out.println(Fecha.diasEnMes(2024, 5));
		System.out.println(Fecha.diasEnMes(2024, 11));

		System.out.println("-".repeat(100));
	}
	
	public static void testOf() {
		System.out.println("Iniciando el método:testOf");

		System.out.println(Fecha.of(2024,3,5));
		System.out.println(Fecha.of(2024,2,29));
//		System.out.println(Fecha.of(2023,2,29));	
		
		System.out.println("-".repeat(100));
	}

	public static void testParse() {
		System.out.println("Iniciando el método:testParse");

		System.out.println(Fecha.parse("2023-2-17"));
		System.out.println(Fecha.parse("2023-02-17"));
		System.out.println("-".repeat(100));
	}

	public static void testsumarDias() {
		System.out.println("Iniciando el método:testsumarDias");

		System.out.println(Fecha.of(2023, 2, 17).sumarDias(374));
		System.out.println(LocalDate.of(2023, 2, 17).plusDays(374));

		System.out.println("-".repeat(100));
	}

	public static void testrestarDias() {
		System.out.println("Iniciando el método:testrestarDias");

		System.out.println(Fecha.of(2024, 2, 26).restarDias(374));
		System.out.println(LocalDate.of(2024, 2, 26).minusDays(374));

		System.out.println("-".repeat(100));
	}

	public static void testdiferenciaEnDias() {
		System.out.println("Iniciando el método:testdiferenciaEnDias");

		System.out.println(Fecha.of(2020, 7, 23).diferenciaEnDias(Fecha.of(2003, 3, 17)));
		System.out.println(LocalDate.of(2020, 7, 23).until(LocalDate.of(2003, 3, 17),ChronoUnit.DAYS ));

		System.out.println("-".repeat(100));
	}
	
	public static void main(String[] args) {
		TestFecha.testnombreMes();
		TestFecha.testdiaSemana();
		TestFecha.testdíasEnMes();
		TestFecha.testOf();
		TestFecha.testParse();
		TestFecha.testsumarDias();
		TestFecha.testrestarDias();
		TestFecha.testdiferenciaEnDias();
	}

	
}
