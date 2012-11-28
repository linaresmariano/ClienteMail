package calendario.periodicidades;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;

public class DiariaTest {

	Diaria diaria;
	Calendar fechaIni;
	Calendar fechaFin;
	Calendar fechaAct;
	TiempoAntelacion tiempoAnt;
	
	@Before
	public void setUp(){
		diaria=new Diaria();
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
	public void siTengoQueTengoQueEjecutarEventoConActualEntreInicioYFin(){
		fechaAct.set(2010,2 , 4, 15, 7);
		fechaIni.set(2010, 2, 3, 15, 7);
		fechaFin.set(2010, 2, 12, 19, 8);
		when(tiempoAnt.getDia()).thenReturn(2);
		
		assertTrue(diaria.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));

		//ultima vez que se ejecuta
		fechaAct.set(2010,2 , 10, 15, 7);
		assertTrue(diaria.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));
	}
	
	@Test
	public void noTengoQueTengoQueEjecutarEventoConActualEntreInicioYFin(){
		fechaAct.set(2010,2 , 4, 16, 7);
		fechaIni.set(2010, 2, 3, 15, 7);
		fechaFin.set(2010, 2, 12, 19, 8);
		when(tiempoAnt.getDia()).thenReturn(2);
		
		//difieren en una hora
		assertFalse(diaria.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));

		//por un mminuto
		fechaAct.set(2010,2 , 4, 15, 8);
		assertFalse(diaria.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));
	}
	
	@Test
	public void noTengoQueTengoQueEjecutarEventoSinActualEntreInicioYFin(){
		fechaAct.set(2010,2 , 2, 13, 7);
		fechaIni.set(2010, 2, 3, 15, 7);
		fechaFin.set(2010, 2, 12, 19, 8);
		when(tiempoAnt.getDia()).thenReturn(2);
		when(tiempoAnt.getHora()).thenReturn(1);
		
		//todavia no empezo,falta una hora
		assertFalse(diaria.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));

		//ya termino,hace 2 dias
		fechaAct.set(2010, 2, 12, 19, 8);
		assertFalse(diaria.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));
	}
	
	@Test
	public void siTengoQueTengoQueEjecutarEventoSinActualEntreInicioYFin(){
		fechaAct.set(2012, 1 , 25, 14, 7);
		fechaIni.set(2012, 2, 3, 15, 7);
		fechaFin.set(2012, 2, 12, 19, 8);
		when(tiempoAnt.getDia()).thenReturn(8);
		when(tiempoAnt.getHora()).thenReturn(1);
		
		
		assertTrue(diaria.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));

		when(tiempoAnt.getMinuto()).thenReturn(5);
		fechaAct.set(2012,2 , 4, 14, 2);
		assertTrue(diaria.tengoQueEjecutarEvento(fechaAct,fechaIni,fechaFin,tiempoAnt));
	}
}
