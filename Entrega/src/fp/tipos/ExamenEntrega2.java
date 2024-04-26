package fp.tipos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.tools.Preconditions;

public class ExamenEntrega2 {
	
	public static DataFrame emptyDataFrame(DataFrame df) {
		return DataFrame.of(df.columNames().stream().collect(Collectors.toMap(x->x, x->new ArrayList<String>())));
		
	}
	
	public static DataFrame addDataFrame(DataFrame df1, DataFrame df2) {
		Preconditions.checkArgument(df1.rowNumber()==df2.rowNumber(),"Los DataFrames deben tener el mismo numero de filas");
		List<String> columNames = new ArrayList<>();
		columNames.addAll(df1.columNames());
		columNames.addAll(df2.columNames());
		List<List<String>> rows = new ArrayList<List<String>>();
		for (int i = 0; i < df1.rowNumber(); i++) {
			List<String> row = new ArrayList<String>();
			row.addAll(df1.row(i));
			row.addAll(df2.row(i));
			rows.add(row);
		}
		return DataFrame.of(columNames, rows);
	}
	
	public static DataFrame removeColumnIndex(DataFrame df, int ci) {
		Preconditions.checkArgument(ci>=0 && ci<df.columNumber(),String.format("El argumento ci (%d) debe estar entre 0 y el numero de columnas(%d)",ci,df.columNumber()));
		String column = df.columNames().get(ci);
		DataFrame df2 = DataFrame.of(df.columNames(), df.rows());
		return df2.removeColum(column);
	}
	
	public static List<DataFrame> divideDataFrame(DataFrame df, int ci) {
		Preconditions.checkArgument(ci>=0 && ci<df.columNumber(),String.format("El argumento ci (%d) debe estar entre 0 y el numero de columnas(%d) -1",ci,df.columNumber()-1));
		List<String> columNames1 = new ArrayList<String>(df.columNames().subList(0, ci+1));
		List<String> columNames2 = new ArrayList<String>(df.columNames().subList(ci+1, df.columNames().size()));
//		System.out.println(columNames1);
//		System.out.println(columNames2);
		Map<String, List<String>> map1 = columNames1.stream().collect(Collectors.toMap(x->x, x->df.colum(x)));
		Map<String, List<String>> map2 = columNames2.stream().collect(Collectors.toMap(x->x, x->df.colum(x)));
		
		DataFrame df1 = DataFrame.of(map1);
		DataFrame df2 = DataFrame.of(map2);
		
		List<DataFrame> lista = new ArrayList<>(List.of(df1,df2));
		return lista;
	}

	public static void main(String[] args) {
		DataFrame mascotas = DataFrame.parse("ficheros/mascotas.csv");
		DataFrame personas = DataFrame.parse("ficheros/personas.csv");

		System.out.println(ExamenEntrega2.emptyDataFrame(mascotas));
		System.out.println(ExamenEntrega2.addDataFrame(mascotas.slice(0, 5), personas.slice(0, 5)));
//		System.out.println(ExamenEntrega2.addDataFrame(mascotas, personas)); //Da error
		System.out.println(ExamenEntrega2.removeColumnIndex(personas, 0));
//		System.out.println(ExamenEntrega2.removeColumnIndex(personas, 7)); //Da error
		System.out.println(ExamenEntrega2.divideDataFrame(personas, 2));
		System.out.println(ExamenEntrega2.divideDataFrame(personas, 0));
//		System.out.println(ExamenEntrega2.divideDataFrame(personas, 5)); //Da error
	}

}
