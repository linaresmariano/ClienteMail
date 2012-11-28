package calendario.eventos;

import java.util.Calendar;

import calendario.exceptions.FechaInicioMayorAFechaFinException;
import calendario.periodicidades.Periodicidad;
import usuario.UsuarioCliente;

public abstract class EventoModificador extends Evento{

	UsuarioCliente usuario;
	
	
	public EventoModificador(String titulo, Calendar fechaIni,
			Calendar fechaFin, Periodicidad periodicidad,UsuarioCliente usuario)
			throws FechaInicioMayorAFechaFinException {
		super(titulo, fechaIni, fechaFin, periodicidad);
		this.usuario=usuario;
	}
	
	
	public abstract void realizarEvento(Calendar actual);
	 
	/**
	 * controla si la fecha actual pasa o es igual a la fecha final del evento
	 * @param Calendar
	 */
	public boolean termino(Calendar actual){
		return this.getPeriodicidad().termino(actual,getFechaFin());
	}

	public UsuarioCliente getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioCliente usuario) {
		this.usuario = usuario;
	}
}
