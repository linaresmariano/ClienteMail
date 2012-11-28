package calendario.eventos;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import calendario.exceptions.FechaInicioMayorAFechaFinException;
import calendario.periodicidades.Periodicidad;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class EventoTest {

	Calendar fechaIni;
	Calendar fechaFin;
	
	@Before
	public void setUp(){
		fechaIni=mock(Calendar.class);
		fechaFin=mock(Calendar.class);
	}
	
	
	@Test
	public void newEventoConFechaFinMenorAFechaIni(){
		//test del constructor
		when(fechaIni.after(fechaFin)).thenReturn(true);
		try {
			new EventoConcreto("titulo",fechaIni,fechaFin,
					mock(Periodicidad.class));
			fail("la exception FechaInicioMayorAFechaFinException no salto");
		} catch (FechaInicioMayorAFechaFinException e) {
			
		}
	}
	
	@Test
	public void newEventoConFechaFinMayorAFechaIni(){
		//test del constructor
		when(fechaIni.after(fechaFin)).thenReturn(false);
		try {
			new EventoConcreto("titulo",fechaIni,fechaFin,
					mock(Periodicidad.class));
			
		} catch (FechaInicioMayorAFechaFinException e) {
			fail("la exception FechaInicioMayorAFechaFinException no deberia saltar");
		}
		
	}
	
	
	private class EventoConcreto extends Evento{

		
		
		public EventoConcreto(String titulo, Calendar fechaIni,
				Calendar fechaFin, Periodicidad periodicidad)
				throws FechaInicioMayorAFechaFinException {
			super(titulo, fechaIni, fechaFin, periodicidad);
		}

		@Override
		public void realizarEvento(Calendar actual) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean termino(Calendar actual) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
}
