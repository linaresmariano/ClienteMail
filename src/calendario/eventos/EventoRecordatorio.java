package calendario.eventos;

import java.util.Calendar;
import calendario.estrategiasRecordatorias.EstrategiaRecordatoria;
import calendario.exceptions.FechaInicioMayorAFechaFinException;
import calendario.periodicidades.Periodicidad;
import calendario.periodicidades.TiempoAntelacion;

public  class EventoRecordatorio extends Evento{



	private EstrategiaRecordatoria estrategia;
	private TiempoAntelacion tiempoDeAntelacion;
	

	public EventoRecordatorio(String titulo, Calendar fechaIni,
			Calendar fechaFin, Periodicidad periodicidad,EstrategiaRecordatoria estrategia)
			throws FechaInicioMayorAFechaFinException {
		super(titulo, fechaIni, fechaFin, periodicidad);
	    this.estrategia=estrategia;
	}
	
	/**
	 * controla si la fecha actual esta dentro de la periodicidad asociada,si es 
	 * asi envia el recordatorio
     *@param Calendar
     */
	@Override
    public  void realizarEvento(Calendar actual){
    	if(this.getPeriodicidad().tengoQueEjecutarEvento
    			(actual,this.getFechaInicio(),this.getFechaFin(),tiempoDeAntelacion)){
    		estrategia.enviarRecordatorio();
    	}
    	      
    }
	
	/**
	 * controla si la fecha actual pasa o es igual a la fecha final del evento
	 * teniendo en cuenta el tiempo de antelacion
	 * @param Calendar
	 */
	public boolean termino(Calendar actual){
		return this.getPeriodicidad().termino(actual,getFechaFin(),tiempoDeAntelacion);
	}

	public EstrategiaRecordatoria getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(EstrategiaRecordatoria estrategia) {
		this.estrategia = estrategia;
	}

	public TiempoAntelacion getTiempoDeAntelacion() {
		return tiempoDeAntelacion;
	}

	public void setTiempoDeAntelacion(TiempoAntelacion tiempoDeAntelacion) {
		this.tiempoDeAntelacion = tiempoDeAntelacion;
	}
}