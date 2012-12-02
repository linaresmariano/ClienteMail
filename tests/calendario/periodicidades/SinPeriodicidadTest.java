package calendario.periodicidades;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;


public class SinPeriodicidadTest {

	SinPeriodicidad sinPeriodicidad;
	Calendar fechaIni;
	Calendar fechaFin;
	Calendar fechaAct;
	TiempoAntelacion tiempoAnt;
	
	@Before
	public void setUp(){
		sinPeriodicidad= new SinPeriodicidad();
		fechaIni=Calendar.getInstance();
		fechaFin=Calendar.getInstance();
		fechaAct=Calendar.getInstance();
		tiempoAnt=mock(TiempoAntelacion.class);
		
		//mockeando tiempoAnt
		when(tiempoAnt.getAño()).thenReturn(0);
		when(tiempoAnt.getMes()).thenReturn(0);
		when(tiempoAnt.getDia()).thenReturn(0);
		when(tiempoAnt.getHora()).thenReturn(0);
		when(tiempoAnt.getMinuto()).thenReturn(0);
	}
	
	@Test
	public void siTengoQueEjecutarEvento(){
		
		fechaIni.set(2012, 4, 3, 23, 20);
		fechaFin.set(2012, 7, 8, 12, 20);
		fechaAct.set(2012, 4, 2, 23, 20);
		when(tiempoAnt.getDia()).thenReturn(1);
		//un dia antes
		assertTrue("la fehca actual no coincide con la de inicio- antelacion",
				sinPeriodicidad.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));
		
		//dos dias antes y una hora
		when(tiempoAnt.getDia()).thenReturn(2);
		when(tiempoAnt.getHora()).thenReturn(1);
		fechaAct.set(2012, 4, 1, 22, 20,0);
		assertTrue(sinPeriodicidad.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));
	}

	@Test
	public void noTengoQueEjecutarEventoEnstandoEntreInicioYFin(){
		
		fechaIni.set(2012, 4, 3, 23, 20);
		fechaFin.set(2012, 7, 8, 12, 20);
		fechaAct.set(2012, 4, 5, 23, 20);
		when(tiempoAnt.getDia()).thenReturn(1);
		//un dia antes
		assertFalse(sinPeriodicidad.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));
	
		//mismo dia que inicio
		fechaAct.set(2012, 4, 3, 23, 20);
		assertFalse("no se tiene en cuenta la antelacion",sinPeriodicidad.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));
	}
	
	@Test
	public void chequearSegunPeriodicidadVerdaderoSinAntelacion(){
		fechaAct.set(2012, 4, 5, 23, 20);
		fechaIni.set(2012, 4, 5, 23, 20);
		
		assertTrue(sinPeriodicidad.chequearSegunPeriodicidad(fechaAct,fechaIni,tiempoAnt));
	}
	
	@Test
	public void chequearSegunPeriodicidadVerdaderoConAntelacion(){
		fechaAct.set(2012, 4, 1, 21, 15);
		fechaIni.set(2012, 4, 5, 23, 20);
		when(tiempoAnt.getDia()).thenReturn(4);
		when(tiempoAnt.getHora()).thenReturn(2);
		when(tiempoAnt.getMinuto()).thenReturn(5);
		assertTrue(sinPeriodicidad.chequearSegunPeriodicidad(fechaAct,fechaIni,tiempoAnt));
	}
	
	@Test
	public void chequearSegunPeriodicidadFalsoConAntelacion(){
		fechaAct.set(2012, 4, 5, 21, 15);
		fechaIni.set(2012, 4, 5, 23, 20);
		when(tiempoAnt.getDia()).thenReturn(4);
		when(tiempoAnt.getHora()).thenReturn(2);
		assertFalse(sinPeriodicidad.chequearSegunPeriodicidad(fechaAct,fechaIni,tiempoAnt));
	 
		fechaAct.set(2012, 4, 2, 21, 15);
		assertFalse(sinPeriodicidad.chequearSegunPeriodicidad(fechaAct,fechaIni,tiempoAnt));
	
	}
	
	@Test
	public void chequearSegunPeriodicidadFalsoSinAntelacion(){
		fechaAct.set(2012, 3, 5, 21, 15);
		fechaIni.set(2012, 4, 5, 23, 20);
		
		//solo chequea a�o,mes y dia..
		assertFalse(sinPeriodicidad.chequearSegunPeriodicidad(fechaAct,fechaIni,tiempoAnt));
	 
		//solo chequea a�o,mes y dia..
		fechaAct.set(2012, 4, 4, 21, 15);
		assertFalse(sinPeriodicidad.chequearSegunPeriodicidad(fechaAct,fechaIni,tiempoAnt));
	
	}
}
