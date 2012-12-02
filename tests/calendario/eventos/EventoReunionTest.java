package calendario.eventos;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import usuario.UsuarioCliente;


import calendario.exceptions.FechaInicioMayorAFechaFinException;
import calendario.periodicidades.Periodicidad;
import estadoUsuario.Reunion;
import static org.mockito.Mockito.*;

public class EventoReunionTest {

	Periodicidad periodicidad;
	Calendar fechaIni;
	Calendar fechaFin;
	Calendar fechaAct;
	EventoReunion evento;
	UsuarioCliente usuario;
	
	@Before
	public void setUp(){
	       periodicidad=mock(Periodicidad.class);
	       fechaIni=mock(Calendar.class);
	       fechaFin=mock(Calendar.class);
	       fechaAct=mock(Calendar.class);
	       usuario=mock(UsuarioCliente.class);
	       try {
			evento=new EventoReunion("evento",fechaIni,fechaFin,periodicidad,usuario);
		   }catch (FechaInicioMayorAFechaFinException e) {
			  
		   }
	       
	}
	
	@Test
	public void siRealizarEventoConPeriodicidadX(){
		when(periodicidad.tengoQueEjecutarEvento(fechaAct,
				fechaIni, fechaFin)).thenReturn(true);
		evento.realizarEvento(fechaAct);
		verify(usuario).setEstado(any(Reunion.class));
	}
	
	@Test
	public void noRealizarEventoConPeriodicidadX(){
		when(periodicidad.tengoQueEjecutarEvento(fechaAct,
				fechaIni, fechaFin)).thenReturn(false);
		evento.realizarEvento(fechaAct);
		verify(usuario,times(0)).setEstado(any(Reunion.class));
	}
}
