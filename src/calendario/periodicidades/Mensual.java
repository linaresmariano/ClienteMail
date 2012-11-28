package calendario.periodicidades;

import java.util.Calendar;

public class Mensual extends Periodicidad{

	private int diaDelMes;
	
	public Mensual(int dia){
		diaDelMes=dia;
	}
	
	/**
	 * chequea si el dia del mes de actual+ta es igual algun dia del mes
	 * que debe ser notificado.
	 * luego chequea la hora y minutos con la fecha de inicio
	 * 
	 */
	public  boolean chequearSegunPeriodicidad(Calendar actual,Calendar inicio,TiempoAntelacion ta){
		Calendar actualMasAntelacion=sumarAntelacion(actual,ta);
		return actualMasAntelacion.get(Calendar.DAY_OF_MONTH)== diaDelMes ;
	}

	public int getDiaDelMes() {
		return diaDelMes;
	}

	public void setDiaDelMes(int diaDelMes) {
		this.diaDelMes = diaDelMes;
	}

}