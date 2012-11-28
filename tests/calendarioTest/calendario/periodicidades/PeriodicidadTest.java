package calendario.periodicidades;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;


public class PeriodicidadTest {

	
	Periodicidad periodicidad;
	Calendar fechaIni;
	Calendar fechaFin;
	Calendar fechaAct;
	TiempoAntelacion tiempoAnt;
	
	@Before
	public void setUp(){
		periodicidad=new PeriodicidadConcreta();
		fechaIni=Calendar.getInstance();
		fechaFin=Calendar.getInstance();
		fechaAct=Calendar.getInstance();
		tiempoAnt=mock(TiempoAntelacion.class);
		when(tiempoAnt.getAño()).thenReturn(0);
		when(tiempoAnt.getMes()).thenReturn(0);
		when(tiempoAnt.getDia()).thenReturn(0);
		when(tiempoAnt.getHora()).thenReturn(0);
		when(tiempoAnt.getMinuto()).thenReturn(0);
	}
	
	@Test
	public void sumaAntelacion(){
		fechaAct.set(2012, 7, 21, 15, 35);
		
		//Suma una hora,12 dias y 5 min
		when(tiempoAnt.getAño()).thenReturn(7);
		when(tiempoAnt.getDia()).thenReturn(12);
		when(tiempoAnt.getMinuto()).thenReturn(5);
		
		Calendar res=periodicidad.sumarAntelacion(fechaAct,tiempoAnt);
	
		assertEquals(res.get(Calendar.DATE),2);
		assertEquals(res.get(Calendar.MONTH),8);
		assertEquals(res.get(Calendar.MINUTE),40);
		assertEquals(res.get(Calendar.YEAR),2019);
	}
	
	@Test
	public void restarAntelacion(){
		fechaAct.set(2011, 4, 21, 17, 45);
		
		
		when(tiempoAnt.getAño()).thenReturn(2);
		when(tiempoAnt.getDia()).thenReturn(36);
		when(tiempoAnt.getMinuto()).thenReturn(5);
		when(tiempoAnt.getHora()).thenReturn(2);
		
		Calendar res=periodicidad.restarAntelacion(fechaAct,tiempoAnt);
		assertEquals(res.get(Calendar.DATE),15);
		assertEquals(res.get(Calendar.MONTH),3);
		assertEquals(res.get(Calendar.MINUTE),40);
		assertEquals(res.get(Calendar.YEAR),2009);
	}
	
	@Test
	public void siEstaEntre(){
		fechaAct.set(2012, 7, 12, 4, 7);
		fechaFin.set(2012, 9, 21, 14, 15);
		fechaIni.set(2012, 7, 12, 4, 7);
		
		//extactamente la misma fecha que inicio
		assertTrue(periodicidad.estaEntre(fechaAct,fechaIni,fechaFin));
		
		//extactamente la misma fecha que fin
		fechaAct.set(2012, 9, 21, 14, 15);
		assertTrue(periodicidad.estaEntre(fechaAct,fechaIni,fechaFin));
		
		//entre inicio y fin
		fechaAct.set(2012, 8, 23, 19, 17);
		assertTrue(periodicidad.estaEntre(fechaAct,fechaIni,fechaFin));
	}
	
	@Test
	public void noEstaEntre(){
		fechaAct.set(2012, 7, 12, 4, 6);
		fechaFin.set(2012, 9, 21, 14, 15);
		fechaIni.set(2012, 7, 12, 4, 7);
		
		//por un minuto
		assertFalse(periodicidad.estaEntre(fechaAct,fechaIni,fechaFin));
		
		//un minuto despues de fecha fin
		fechaAct.set(2012, 9, 21, 14, 16);
		assertFalse(periodicidad.estaEntre(fechaAct,fechaIni,fechaFin));
		
		//termino hace rato
		fechaAct.set(2012, 11, 23, 23,23);
		assertFalse(periodicidad.estaEntre(fechaAct,fechaIni,fechaFin));
	}
	
	@Test
	public void noSonIguales(){
		fechaAct.set(2012, 9, 21, 4, 6);
		fechaIni.set(2012, 9, 21, 14, 15);
		assertFalse(periodicidad.iguales(fechaIni, fechaAct));
		
		//por un minuto
		fechaAct.set(2012, 9, 21, 14, 16);
		assertFalse(periodicidad.iguales(fechaIni, fechaAct));
	}
	
	@Test
	public void siSonIguales(){
		fechaAct.set(2012, 9, 21, 14, 15);
		fechaIni.set(2012, 9, 21, 14, 15);
		assertTrue(periodicidad.iguales(fechaIni, fechaAct));
		
		//le agrego segundo,pero no importa por controla solo hasta minutos
		fechaAct.set(2012, 9, 21, 14, 15,30);
		assertTrue(periodicidad.iguales(fechaIni, fechaAct));
	}
	
	@Test
	public void terminoSinAntelacion(){
		fechaAct.set(2012, 9, 21, 14, 15);
		fechaFin.set(2012, 9, 21, 14, 15);
		
		//termino,por que son iguales
		assertTrue(periodicidad.termino(fechaAct,fechaFin));
		
		//termino hace un dia y 2 minutos
		fechaAct.set(2012, 10, 21, 14, 17);
		assertTrue(periodicidad.termino(fechaAct,fechaFin));
	}
	
	@Test
	public void noTerminoSinAntelacion(){
		fechaAct.set(2012, 9, 21, 14, 14);
		fechaFin.set(2012, 9, 21, 14, 15);
		
		//no termino,por un minuto
		assertFalse(periodicidad.termino(fechaAct,fechaFin));
		
		//no termino falta mucho
		fechaAct.set(2012, 8, 21, 14, 17);
		assertFalse(periodicidad.termino(fechaAct,fechaFin));
	}
	
	@Test
	public void siTerminoConAntelacion(){
		fechaAct.set(2012, 8, 19, 10, 14);
		fechaFin.set(2012, 9, 21, 14, 15);
		when(tiempoAnt.getMes()).thenReturn(1);
		when(tiempoAnt.getDia()).thenReturn(2);
		when(tiempoAnt.getHora()).thenReturn(4);
		when(tiempoAnt.getMinuto()).thenReturn(1);
		
		//si termino,son iguales
		assertTrue(periodicidad.termino(fechaAct,fechaFin,tiempoAnt));
		
	    
	}
	
	@Test
	public void noTerminoConAntelacion(){
		fechaAct.set(2012, 8, 19, 10, 14);
		fechaFin.set(2012, 9, 21, 14, 15);
		when(tiempoAnt.getMes()).thenReturn(1);
		when(tiempoAnt.getDia()).thenReturn(2);
		when(tiempoAnt.getHora()).thenReturn(3);
		when(tiempoAnt.getMinuto()).thenReturn(1);
		
		//no termino,falta una hora
		assertFalse(periodicidad.termino(fechaAct,fechaFin,tiempoAnt));
		
	
	}
	
	
	private class PeriodicidadConcreta extends Periodicidad{

		@Override
		public boolean chequearSegunPeriodicidad(Calendar actual,
				Calendar inicio, TiempoAntelacion ta) {
			return false;
		}
		
	}
}
