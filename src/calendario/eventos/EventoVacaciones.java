package calendario.eventos;

import java.util.Calendar;

import calendario.exceptions.FechaInicioMayorAFechaFinException;
import calendario.periodicidades.Periodicidad;
import usuario.UsuarioCliente;
import estadoUsuario.Vacaciones;

public class EventoVacaciones extends EventoModificador{



	public EventoVacaciones(String titulo, Calendar fechaIni,
			Calendar fechaFin, Periodicidad periodicidad, UsuarioCliente usuario)
			throws FechaInicioMayorAFechaFinException {
		super(titulo, fechaIni, fechaFin, periodicidad, usuario);
		
	}

	@Override
	public void realizarEvento(Calendar actual) {
		if(this.getPeriodicidad().tengoQueEjecutarEvento(actual,this.getFechaInicio(), 
                        this.getFechaFin())){
			this.getUsuario().setEstado(new Vacaciones());
		}
		

	}

	

}