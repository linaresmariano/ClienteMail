package calendario.periodicidades;

import java.util.Calendar;


enum Dia{
	DOMINGO(1) ,LUNES(2) , MARTES(3) ,MIERCOLES(4) , JUEVES(5) ,VIERNES(6) , SABADO(7);
	
    public int dia;
	Dia(int dia){
	    this.dia=dia;	
	}
	public int get(){
	    return this.dia;
	}
};

public class Semanal extends Periodicidad{

	private Dia[] dias;
	
	public Semanal(Dia[] dias){
		this.dias=dias;
	}
	
	/**
	 * chequea si el dia de semana de actual+ta es igual algun dia de Dias,
	 * luego chequea la hora y minutos con la fecha de inicio
	 * 
	 */
	@Override
	public boolean chequearSegunPeriodicidad(Calendar actual, Calendar inicio, TiempoAntelacion ta){
		
		Calendar actualMasAntelacion=sumarAntelacion(actual,ta);	
		boolean res=false;
		for(Dia d:dias){
			res=res || d.get() == actualMasAntelacion.get(Calendar.DAY_OF_WEEK) ;
		}
		return res;
	}
	

	public Dia[] getDias() {
		return dias;
	}

	public void setDias(Dia[] dias) {
		this.dias = dias;
	}



	
}
