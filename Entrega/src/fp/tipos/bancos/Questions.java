package fp.tipos.bancos;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;
import us.lsi.tools.Stream2;

public class Questions {

	//	Vencimiento de los prestamos de un cliente
	public static Set<LocalDate> vencimientoDePrestamosDeCliente(Banco banco,String dni) {
		return banco.prestamosDeCliente(dni).stream().map(x->x.fechaVencimiento()).collect(Collectors.toSet());
	}
	//	Persona con más prestamos
	public static Persona clienteConMasPrestamos(Banco banco) {
		Map<String, Integer> prestamosPorDni = Stream2.groupingReduce(banco.prestamos().todos().stream(), x->x.dniCliente(), x->1, (x,y)->x+y);
		Optional<Entry<String, Integer>> entradaConMasPrestamos = prestamosPorDni.entrySet().stream().max(Comparator.comparing((Entry<String,Integer> x) -> x.getValue()));
		String dni = entradaConMasPrestamos.get().getKey();
		return Personas.of().personaDni(dni).get();
	}
	//	Cantidad total de los crétditos gestionados por un empleado
	public static Double cantidadPrestamosPmpledado(Banco banco,String dni) {
		Map<String, Double> cantidadPorDni = Stream2.groupingReduce(banco.prestamos().todos().stream(), Prestamo::dniEmpleado, Prestamo::cantidad,(x,y)->x+y) ;
		return cantidadPorDni.get(dni);
	}
	//	Empleado más longevo
	public static Persona empleadoMasLongevo(Banco banco) {
		return banco.empleados().todos().stream().map(x->x.persona()).max(Comparator.comparing((Persona x)-> x.edad())).get();
	}
	//	Interés mínimo, máximo y medio de los préstamos
	public static record Info(Double min, Double max, Double mean) {
		public String toString() {
			return String.format(Locale.US,"(%.2f,%.2f,%.2f)",this.min(),this.max(),this.mean());
		}
	}
	public static  Info rangoDeIntereseDePrestamos(Banco banco) {
		List<Double> InteresesPrestamos = banco.prestamos().todos().stream().map(Prestamo::interes).collect(Collectors.toList());
		Double min = InteresesPrestamos.stream().collect(Collectors.minBy(Comparator.naturalOrder())).get();
		Double max = InteresesPrestamos.stream().collect(Collectors.maxBy(Comparator.naturalOrder())).get();
		Double media = InteresesPrestamos.stream().collect(Collectors.averagingDouble(x->x));
		return new Info(min, max, media);
	}

	//	Número de préstamos por mes y año
	public static record Info2(Integer mes, Integer año) {
		public String toString() {
			return String.format("(%d,%d)",this.mes(),this.año());
		}
	}
	
	
	public static Map<Info2,Integer> numPrestamosPorMesAño(Banco banco) {
		Function<Prestamo, Integer> mesDePrestamo= x->x.fechaComienzo().getMonthValue();
		Function<Prestamo, Integer> añoDePrestamo= x->x.fechaComienzo().getYear();
		Function<Prestamo, Info2> Info2DePrestamo= x-> new Info2(mesDePrestamo.apply(x), añoDePrestamo.apply(x));
		
		return Stream2.groupingReduce(banco.prestamos().todos().stream(),x-> Info2DePrestamo.apply(x), x->1, (x,y)->x+1);
	}
	

	public static void main(String[] args) {
		Banco b=Banco.of();
		System.out.println(Questions.vencimientoDePrestamosDeCliente(b, "56141207R"));
		System.out.println(Questions.clienteConMasPrestamos(b));
		System.out.println(Questions.cantidadPrestamosPmpledado(b, "39265813Y"));
		System.out.println(Questions.empleadoMasLongevo(b));
		System.out.println(rangoDeIntereseDePrestamos(b));
//		Questions.numPrestamosPorMesAño(b).entrySet().forEach(x->System.out.println(x));
		
	}
}
