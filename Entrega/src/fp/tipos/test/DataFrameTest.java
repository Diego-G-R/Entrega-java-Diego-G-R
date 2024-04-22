package fp.tipos.test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import fp.tipos.DataFrame;

public class DataFrameTest {
	public static DataFrame mascotas = DataFrame.parse("ficheros/mascotas.csv",List.of("Nombre", "Especie", "Sexo","Ubicacion", "IdMascota", "Estado"));
	public static DataFrame personas = DataFrame.parse("ficheros/personas.csv");
	public static DataFrame pp = DataFrame.parse("ficheros/pp.csv");
	public static Stream<DataFrame> ficheros = List.of(mascotas,personas,pp).stream();
	

	
	public static void testDiezPrimerasFilas() {
		System.out.println("Iniciando el método:testDiezPrimerasFilas");

		System.out.println(DataFrameTest.mascotas.head(10));
		
		System.out.println("-".repeat(100));
	}
	
	public static void testDiezUltimasFilas() {
		System.out.println("Iniciando el método:testDiezUltimasFilas");

		System.out.println(DataFrameTest.mascotas.tail(10));
		
		System.out.println("-".repeat(100));
	}
	
	public static void testCincoPrimerasFilas() {
		System.out.println("Iniciando el método:testCincoPrimerasFilas");

		System.out.println(DataFrameTest.mascotas.head(5));
		System.out.println(DataFrameTest.personas.head(5));

		System.out.println("-".repeat(100));
	}
	
	public static void testCincoUltimasFilas() {
		System.out.println("Iniciando el método:testCincoUltimasFilas");

		System.out.println(DataFrameTest.mascotas.tail(5));
		System.out.println(DataFrameTest.personas.tail(5));

		System.out.println("-".repeat(100));
	}
	
	public static void testSlice() {
		System.out.println("Iniciando el método:testSlice");

		DataFrameTest.ficheros.map((DataFrame x)->x.slice(1, 3)).forEach(x->System.out.println(x));;


		System.out.println("-".repeat(100));
	}
	
	public static void testRemoveColum() {
		System.out.println("Iniciando el método:testRemoveColum");

		System.out.println(DataFrameTest.mascotas.removeColum("Nombre"));
		System.out.println(DataFrameTest.personas.removeColum("Nombre"));
		

		System.out.println("-".repeat(100));
	}
	
	public static void testFilter1() {
		System.out.println("Iniciando el método:testFilter1");

		
		System.out.println(DataFrameTest.personas.filter(x->Integer.parseInt(x.get(1))>60));

		System.out.println("-".repeat(100));
	}
	
	
	public static void testOf1() {
		System.out.println("Iniciando el método:testOf1");

		DataFrame A = DataFrame.of(mascotas.columNames(), mascotas.rows());
		System.out.println(A);

		System.out.println("-".repeat(100));
	}
	
	public static void testColum() {
		System.out.println("Iniciando el método:testColum");

		System.out.println(mascotas.colum("Nombre"));
		System.out.println(mascotas.colum(0));

		System.out.println("-".repeat(100));
	}

	public static void testCell() {
		System.out.println("Iniciando el método:testCell");

		System.out.println(mascotas.cell(3, "Nombre"));

		System.out.println("-".repeat(100));
	}
	
	public static void testRowPorString() {
		System.out.println("Iniciando el método:testRowPorString");

		System.out.println(mascotas.row("Titito", "Nombre"));
//		System.out.println(mascotas.row("Pepito", "Nombre"));

		System.out.println("-".repeat(100));
	}
	
	public static void testTail() {
		System.out.println("Iniciando el método:testTail");

		System.out.println(mascotas.tail(3));

		System.out.println("-".repeat(100));
	}
	
	public static void testFilter() {
		System.out.println("Iniciando el método:testFilter");

		Predicate<List<String>> filtro = (List<String> x)->x.get(0).equals("Ras");
		System.out.println(mascotas.filter(filtro));

		System.out.println("-".repeat(100));
	}
	
	public static void testaddCalculatedColum() {
		System.out.println("Iniciando el método:testaddCalculatedColum");

		Function<List<String>, String> funcion = (List<String> x)-> x.get(2).equals("H")? "Hembra":"Macho";
		System.out.println(mascotas.addCalculatedColum("Sexo2", funcion));

		System.out.println("-".repeat(100));
	}
	
	public static void testSortBy() {
		System.out.println("Iniciando el método:testSortBy");

		Function<List<String>, String> orden = x->x.get(0);
		System.out.println(mascotas.sortBy(orden, false));

		System.out.println("-".repeat(100));
	}
	

	public static void main(String[] args) {
		DataFrameTest.testDiezPrimerasFilas();
		DataFrameTest.testDiezUltimasFilas();
		DataFrameTest.testCincoPrimerasFilas();
		DataFrameTest.testCincoUltimasFilas();
		DataFrameTest.testSlice();
		DataFrameTest.testRemoveColum();
		DataFrameTest.testFilter1();
		
		DataFrameTest.testOf1();
		DataFrameTest.testColum();
		DataFrameTest.testCell();
		DataFrameTest.testRowPorString();
		DataFrameTest.testTail();
		DataFrameTest.testFilter();
		DataFrameTest.testaddCalculatedColum();
		DataFrameTest.testSortBy();
		
	} 

}
