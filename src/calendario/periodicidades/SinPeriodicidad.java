package calendario.periodicidades;

import java.util.Calendar;

public class SinPeriodicidad extends Periodicidad{

	
	public boolean chequearSegunPeriodicidad(Calendar actual,Calendar inicio,TiempoAntelacion ta){
		Calendar actualMasAntelacion=sumarAntelacion(actual,ta);
		return actualMasAntelacion.get(Calendar.YEAR)== inicio.get(Calendar.YEAR) &&
		actualMasAntelacion.get(Calendar.MONTH)== inicio.get(Calendar.MONTH) &&
		actualMasAntelacion.get(Calendar.DATE)== inicio.get(Calendar.DATE);
	}
}
