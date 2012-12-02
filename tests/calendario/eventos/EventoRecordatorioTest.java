package calendario.eventos;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import calendario.estrategiasRecordatorias.*;
import calendario.exceptions.FechaInicioMayorAFechaFinException;
import calendario.periodicidades.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventoRecordatorioTest {

	EstrategiaRecordatoria estrategia;
	Periodicidad periodicidad;
	Calendar fechaIni;
	Calendar fechaFin;
	TiempoAntelacion tiempoAnt;
	Calendar fechaAct;
	EventoRecordatorio evento;
	
	@Before
	public void setUp(){
	
		periodicidad=mock(Periodicidad.class);
		fechaIni=mock(Calendar.class);
		fechaFin=mock(Calendar.class);
		estrategia=mock(EstrategiaRecordatoria.class);
		fechaAct=mock(Calendar.class);
		tiempoAnt=mock(TiempoAntelacion.class);
		when(fechaIni.after(fechaFin)).thenReturn(false);
		try {
			evento=new EventoRecordatorio("evento",fechaIni,fechaFin,periodicidad,estrategia);
		} catch (FechaInicioMayorAFechaFinException e) {
			
		}
	
		evento.setTiempoDeAntelacion(tiempoAnt);
		
	}
	
	@Test
	public void realizarEventoConPeriodicidadX(){
	
		when(periodicidad.tengoQueEjecutarEvento(fechaAct,fechaIni
				,fechaFin,tiempoAnt)).thenReturn(true);
		
	    evento.realizarEvento(fechaAct);
	    
	    verify(estrategia,times(1)).enviarRecordatorio();
	    verify(periodicidad).tengoQueEjecutarEvento(fechaAct,
	    		fechaIni,fechaFin,tiempoAnt);
	}
	
	@Test
	public void noRealizarEventoConPeriodicidadX(){

		when(periodicidad.tengoQueEjecutarEvento(fechaAct,fechaIni
				,fechaFin,tiempoAnt)).thenReturn(false);
		
	    evento.realizarEvento(fechaAct);
	    
	    verify(estrategia,times(0)).enviarRecordatorio();
	    verify(periodicidad).tengoQueEjecutarEvento(fechaAct,fechaIni
				,fechaFin,tiempoAnt);
	}
	
	@Test
	public void noTermino(){
		when(periodicidad.termino(fechaAct,fechaFin, tiempoAnt))
		         .thenReturn(false);
		assertFalse(evento.termino(fechaAct));
		verify(periodicidad).termino(fechaAct,fechaFin, tiempoAnt);
	}
	
	@Test
	public void siTermino(){
		when(periodicidad.termino(fechaAct,fechaFin, tiempoAnt))
		         .thenReturn(true);
		assertTrue(evento.termino(fechaAct));
		verify(periodicidad).termino(fechaAct,fechaFin, tiempoAnt);
	}

	
	
}
