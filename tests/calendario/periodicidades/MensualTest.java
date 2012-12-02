package calendario.periodicidades;

import static org.junit.Assert.*;

import java.util.Calendar;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;


public class MensualTest {

	Mensual mensual;
	Calendar fechaIni;
	Calendar fechaFin;
	Calendar fechaAct;
	TiempoAntelacion tiempoAnt;
	
	@Before
	public void setUp(){
		mensual=new Mensual(0);
		fechaIni=Calendar.getInstance();
		fechaFin=Calendar.getInstance();
		fechaAct=Calendar.getInstance();
		tiempoAnt=mock(TiempoAntelacion.class);
		when(tiempoAnt.getAnio()).thenReturn(0);
		when(tiempoAnt.getMes()).thenReturn(0);
		when(tiempoAnt.getDia()).thenReturn(0);
		when(tiempoAnt.getHora()).thenReturn(0);
		when(tiempoAnt.getMinuto()).thenReturn(0);
	}
	
	@Test
	public void siTengoQueTengoQueEjecutarEventoTresDiasAntesDeInicio(){
		mensual.setDiaDelMes(7);
		fechaIni.set(2012, 5, 6, 15, 5);
		fechaFin.set(2012, 5, 19, 17, 0);
		fechaAct.set(2012, 5, 4, 15, 5);
		when(tiempoAnt.getDia()).thenReturn(3);
		assertTrue(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	}
	
	@Test
	public void noTengoQueTengoQueEjecutarEstandoEntreInicioYFin(){
		mensual.setDiaDelMes(10);
		fechaIni.set(2012, 5, 6, 15, 5);
		fechaFin.set(2012, 5, 19, 17, 0);
		fechaAct.set(2012, 5, 9, 15, 5);
		when(tiempoAnt.getDia()).thenReturn(3);
		assertFalse(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
		
		//no notifica solo por un minuto
		fechaAct.set(2012, 5, 7, 15, 6);
		assertFalse(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
		
		//no notifica,es el mismo dia que diaDelMes
		fechaAct.set(2012, 5, 10, 15, 5);
		assertFalse(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
		
	}
	
	@Test
	public void noTengoQueTengoQueEjecutarNoEstandoEntreInicioYFin(){
		mensual.setDiaDelMes(7);
		fechaIni.set(2012, 5, 6, 15, 5);
		fechaFin.set(2012, 5, 19, 17, 0);
		fechaAct.set(2012, 5, 3, 15, 5);
		when(tiempoAnt.getDia()).thenReturn(2);
		
		//falta un dia
		assertFalse(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
		
		//ya termino
		fechaAct.set(2012, 5, 19, 15, 8);
		assertFalse(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
		
	}
	
	@Test
	public void siTengoQueTengoQueEjecutarEstandoEntreInicioYFin(){
		mensual.setDiaDelMes(18);
		fechaIni.set(2010, 3, 6, 15, 5);
		fechaFin.set(2012, 5, 19, 17, 0);
		fechaAct.set(2010, 3, 15, 13, 45);
		when(tiempoAnt.getDia()).thenReturn(3);
		when(tiempoAnt.getMinuto()).thenReturn(20);
		when(tiempoAnt.getHora()).thenReturn(1);
		
		assertTrue(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
		
		//avisa dos a�os antes
		when(tiempoAnt.getAnio()).thenReturn(2);
		assertTrue(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
		
		
		fechaAct.set(2010, 4, 15, 13, 45);
		assertTrue(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
		
		fechaAct.set(2010, 5, 15, 13, 45);
		assertTrue(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	}
	
	@Test
	public void tengoQueEjecutarEventoSinAntelacion(){
		mensual.setDiaDelMes(26);
		fechaIni.set(2011, 3, 6, 15, 5);
		fechaFin.set(2012, 7, 19, 17, 25);
		fechaAct.set(2011, 4, 26, 15, 5);
		
		//es 26 asi que es tiene que ejecutarse
		assertTrue(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin));
		
		//es 26 del siguiente mes y esta entre inicio y fin
		fechaAct.set(2011, 5, 26, 15, 5);
		assertTrue(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin));
		
		//es 26 del siguiente mes y esta entre inicio y fin
		fechaAct.set(2011, 6, 26, 15, 5);
		assertTrue(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin));
		//es 26 del siguiente mes y a�o  y esta entre inicio y fin
		fechaAct.set(2012, 6, 26, 15, 5);
		assertTrue(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin));
		
		fechaAct.set(2012, 7, 26, 15, 5);
		assertFalse(mensual.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin));
	}
	

	
	@Test
	public void chequearSegunPeriodicidadVerdadero(){
		mensual.setDiaDelMes(23);
		when(tiempoAnt.getDia()).thenReturn(12);
		when(tiempoAnt.getHora()).thenReturn(2);
		fechaIni.set(2012, 6, 23, 12, 44);
		fechaAct.set(2012, 6, 11, 10, 44);
		//fecha ini -12 dias de antelacion  en 11 ,menos 2 horas.es igual
		//a la fecha actual
		assertTrue(mensual.chequearSegunPeriodicidad(fechaAct, fechaIni, tiempoAnt));
		
	}
	
	@Test
	public void chequearSegunPeriodicidadFalso(){
		mensual.setDiaDelMes(23);
		when(tiempoAnt.getDia()).thenReturn(12);
		when(tiempoAnt.getHora()).thenReturn(2);
		when(tiempoAnt.getMes()).thenReturn(1);
		fechaIni.set(2012, 6, 23, 12, 44);
		fechaAct.set(2012, 6, 10, 10, 44);
		
		//
		assertFalse(mensual.chequearSegunPeriodicidad(fechaAct, fechaIni, tiempoAnt));
		
	}
}