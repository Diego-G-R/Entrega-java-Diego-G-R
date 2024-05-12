package fp.tipos.bancos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cuenta {
	public String iban;
	public String dni;
	public LocalDate fechaDeCreacion;
	public Double saldo;
	
	public void ingresar(Double n) {
		this.saldo += n; 
	}

	public void retirar(Double n) {
		this.saldo -= n; 
	}

	private Cuenta(String iban, String dni, LocalDate fechaDeCreacion, Double saldo) {
		this.iban = iban;
		this.dni = dni;
		this.fechaDeCreacion = fechaDeCreacion;
		this.saldo = saldo;
	}

	public static Cuenta of(String iban, String dni, LocalDate fechaDeCreacion, Double saldo) {
		return new Cuenta(iban, dni, fechaDeCreacion, saldo);
	}
	 public static Cuenta parse(String txt) {
		 String[] partes = txt.split(",");
		 String iban= partes[0];
		 String dni = partes[1];
		 String fechaCompletaString = partes[2];
		 LocalDateTime fechaCompleta = LocalDateTime.parse(fechaCompletaString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		 LocalDate fecha = fechaCompleta.toLocalDate();
		 Double saldo = Double.parseDouble(partes[3]);
		 return Cuenta.of(iban, dni, fecha, saldo);
	}
	 
	 
	 
	 @Override
	public String toString() {
		return iban + ", " + saldo;
	}
	 
	@Override
	public int hashCode() {
		return Objects.hash(dni, fechaDeCreacion, iban, saldo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(fechaDeCreacion, other.fechaDeCreacion)
				&& Objects.equals(iban, other.iban) && Objects.equals(saldo, other.saldo);
	}

	public static void main(String[] args) {
		
	}
	
	

}
