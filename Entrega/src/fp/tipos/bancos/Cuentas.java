package fp.tipos.bancos;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.tools.File2;

public class Cuentas {
	private static Cuentas gestorDeCuentas=null;
	private Set<Cuenta> cuentas;
	private Map<String, Cuenta> cuentasIban;
	
	public Cuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
		this.cuentasIban = cuentas.stream().collect(Collectors.toMap(x->x.iban, x->x));
	}

	public static Cuentas of() {
		if(gestorDeCuentas==null) Cuentas.parse("bancos/cuentas.txt");
		return Cuentas.gestorDeCuentas;
	}
	
	public static Cuentas parse(String txt) {
		Set<Cuenta> cuentas = File2.streamDeFichero(txt).map(x->Cuenta.parse(x)).collect(Collectors.toSet());
		Cuentas.gestorDeCuentas = new Cuentas(cuentas);
		return Cuentas.gestorDeCuentas;
	}
	
	public Set<Cuenta> todas() {
		return this.cuentas;
	}
	
	public Optional<Cuenta> cuentaIban(String iban) {
		return Optional.of(this.cuentasIban.get(iban));
	}
	
	public Integer size() {
		return this.cuentas.size();
	}
	
	public Cuenta cuentaIndex(Integer n) {
		return this.cuentas.stream().collect(Collectors.toList()).get(n);
	}
	
	
	@Override
	public String toString() {
		return this.cuentas.stream().map(x->x.toString()).collect(Collectors.joining("\n", "Cuentas\n", ""));
	}

	

}
