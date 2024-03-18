package fp.tipos;

import java.util.Arrays;
import java.util.List;

import us.lsi.tools.Preconditions;



public record Fecha(Integer año,Integer mes,Integer dia) {
	
	public static void comprobacionMesEnRango(Integer mes) {
		Preconditions.checkArgument(mes<=12 && mes>=1, String.format("El mes debe estar entre 1 y 12 y es %d", mes));
	}
	
	public static void comprobacionFechaTotal(Integer año,Integer mes,Integer dia) {
		Fecha.comprobacionMesEnRango(mes);
		Boolean comp2=Fecha.diasEnMes(año, mes)>=dia;
		Preconditions.checkArgument(comp2, "El dia de la fecha "+dia+" no está en el rango de su mes "+mes);
	}
	
	public static Boolean esAñoBisiesto(Integer año) {
		if (año%4==0) {
			if (año%100==0) {
				if (año%400==0) {
					return true;
				} else return false;

			}
			else return true;
		} else return false;
	}

	public static String nombreMes(Integer mes) {
		Fecha.comprobacionMesEnRango(mes);
		List<String> AuxMeses= List.of("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Dieciembre");
		return AuxMeses.get(mes-1);
	}
	
    public static Integer congruenciaZeller(Integer año, Integer mes, Integer dia) {
        if (mes < 3) {
            año -= 1;
            mes += 12;
        }
        Integer K = año % 100;
        Integer J = año / 100;
        Integer h = (dia + 13 * (mes + 1) / 5 + K + K / 4 + J / 4 + 5 * J) % 7;
        return h;
    }

    public static String diaSemana(Integer año, Integer mes, Integer dia) {
    	List<String> ListAux=List.of("Sábado","Domingo","Lunes","Martes","Miercoles","Jueves","Viernes");
    	Integer a = Fecha.congruenciaZeller(año, mes, dia);
    	return ListAux.get(a);
    }
    
    public static Integer diasEnMes(Integer año, Integer mes) {
    	Fecha.comprobacionMesEnRango(mes);
    	switch (mes) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			return 31;
		case 2:
			if(Fecha.esAñoBisiesto(año)) return 29;
			else return 28;
		default:
			return 30;
		}
    	
    }
    
    public Fecha sumarDias(Integer dias) {
        Integer newDia = this.dia + dias;
        Integer newMes = this.mes;
        Integer newAño = this.año;

        while (newDia > diasEnMes(newAño, newMes)) {
            newDia -= diasEnMes(newAño, newMes);
            newMes++;
            if (newMes > 12) {
                newMes = 1;
                newAño++;
            }
        }

        return Fecha.of(newAño, newMes, newDia);
    }

    public Fecha restarDias(Integer dias) {
        Integer newDia = this.dia - dias;
        Integer newMes = this.mes;
        Integer newAño = this.año;

        while (newDia <= 0) {
            newMes--;
            if (newMes < 1) {
                newMes = 12;
                newAño--;
            }
            newDia += diasEnMes(newAño, newMes);
        }

        return Fecha.of(newAño, newMes, newDia);
    }
    
    public Integer diferenciaEnDias(Fecha otraFecha) {
        Integer acumDias = 0;
        for (int i = 1; i < this.mes; i++) {
            acumDias += diasEnMes(this.año, i);
        }
        acumDias += this.dia;
        

        Integer totalDiasOtra = 0;
        for (int i = 1; i < otraFecha.mes; i++) {
            totalDiasOtra += diasEnMes(otraFecha.año, i);
        }
        totalDiasOtra += otraFecha.dia;

        Integer diasPorAño = 0;
        for (int i = Math.min(this.año, otraFecha.año); i < Math.max(this.año, otraFecha.año); i++) {
            diasPorAño += esAñoBisiesto(i) ? 366 : 365;
        }
        
        Integer diferenciaDiasSinAño= this.año>otraFecha.año ? acumDias-totalDiasOtra : totalDiasOtra-acumDias;
        

        return Math.abs(diferenciaDiasSinAño + diasPorAño);
    }
    
    
    
    public static Fecha parse(String texto) {
    	List<String> partes= Arrays.asList(texto.split("-"));
    	Integer año= Integer.parseInt(partes.get(0));
    	Integer mes= Integer.parseInt(partes.get(1));
    	Integer dia= Integer.parseInt(partes.get(2));
    	return Fecha.of(año, mes, dia);
    }

	public static Fecha of(Integer año, Integer mes,Integer dia) {
		Fecha.comprobacionFechaTotal(año, mes, dia);
			return new Fecha(año, mes, dia);
		}
	
	public Integer compareTo(Fecha other) {
		Integer r = this.año.compareTo(other.año);
		if (r==0) r = this.mes.compareTo(other.mes);
		if (r==0) r = this.dia.compareTo(other.dia);
		return r;
	}

	@Override
	public String toString() {
		return String.format("%s, %d de %s de %d", Fecha.diaSemana(this.año, this.mes, this.dia),this.dia,Fecha.nombreMes(this.mes),this.año);
	}
}

