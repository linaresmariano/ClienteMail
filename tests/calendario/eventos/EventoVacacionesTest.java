package calendario.eventos;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import usuario.UsuarioCliente;
import calendario.exceptions.FechaInicioMayorAFechaFinException;
import calendario.periodicidades.Periodicidad;
import estadoUsuario.Vacaciones;


public class EventoVacacionesTest {

	Periodicidad periodicidad;
	Calendar fechaIni;
	Calendar fechaFin;
	Calendar fechaAct;
	EventoVacaciones evento;
	UsuarioCliente usuario;
	
	@Before
	public void setUp(){
	       periodicidad=mock(Periodicidad.class);
	       fechaIni=mock(Calendar.class);
	       fechaFin=mock(Calendar.class);
	       fechaAct=mock(Calendar.class);
	       usuario=mock(UsuarioCliente.class);
	       try {
			evento=new EventoVacaciones("evento",fechaIni,fechaFin,periodicidad,usuario);
		   } catch (FechaInicioMayorAFechaFinException e) {
			  
		   };
	       
	}
	
	@Test
	public void siRealizarEventoConPeriodicidadX(){
		when(periodicidad.tengoQueEjecutarEvento(fechaAct,
				fechaIni, fechaFin)).thenReturn(true);
		evento.realizarEvento(fechaAct);
		verify(usuario).setEstado(any(Vacaciones.class));
	}
	
	@Test
	public void noRealizarEventoConPeriodicidadX(){
		when(periodicidad.tengoQueEjecutarEvento(fechaAct,
				fechaIni, fechaFin)).thenReturn(false);
		evento.realizarEvento(fechaAct);
		verify(usuario,times(0)).setEstado(any(Vacaciones.class));
	}
}

