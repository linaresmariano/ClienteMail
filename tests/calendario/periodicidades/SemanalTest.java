package calendario.periodicidades;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;

//La doble SS es porque eclipse esta loco y con una no me deja testear
public class SemanalTest {

	Semanal semanal;
	Calendar fechaIni;
	Calendar fechaFin;
	Calendar fechaAct;
	TiempoAntelacion tiempoAnt;
	
	@Before
	public void setUp(){
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
		
		Dia[] dias={Dia.LUNES,Dia.VIERNES};
		semanal=new Semanal(dias);
		fechaIni.set(2012, 10, 6, 15, 5);
		fechaFin.set(2012, 11, 19, 17, 0);
		fechaAct.set(2012, 10, 20, 15, 5);
		when(tiempoAnt.getDia()).thenReturn(3);
		
		
		assertTrue("no notifica,error de calculo",semanal.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	    
		fechaAct.set(2012, 10, 23, 15, 5);
		assertTrue(semanal.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	
	}
	
	@Test
	public void siTengoQueTengoQueEjecutarEventoEstandoActualEntreInicioYFin(){
		
		Dia[] dias={Dia.LUNES,Dia.VIERNES,Dia.DOMINGO};
		semanal=new Semanal(dias);
		fechaIni.set(2012, 10, 6, 15, 5);
		fechaFin.set(2012, 11, 19, 17, 0);
		fechaAct.set(2012, 10, 20, 15, 5);
		when(tiempoAnt.getDia()).thenReturn(3);
		
		//tres dias antes del viernes
		assertTrue("tres dias antes del viernes debe ser verdadero",
				semanal.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	    
		//tres dias antes del lunes
		fechaAct.set(2012, 10, 23, 15, 5);
		assertTrue(semanal.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	
	}
	
	@Test
	public void siTengoQueTengoQueEjecutarEventoNoEstandoActualEntreInicioYFin(){
		
		Dia[] dias={Dia.MARTES,Dia.VIERNES};
		semanal=new Semanal(dias);
		fechaIni.set(2012, 10, 6, 19, 34);
		fechaFin.set(2012, 11, 19, 18, 7);
		fechaAct.set(2012, 10, 2, 19, 34);
		when(tiempoAnt.getDia()).thenReturn(4);
		
		//con 4 dias de antelacion
		assertTrue("",semanal.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	    
		//con 26 dias de antelacion
		when(tiempoAnt.getDia()).thenReturn(26);
		fechaAct.set(2012, 9, 11, 19, 34);
		assertTrue("",semanal.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	}
	
	@Test
	public void noTengoQueTengoQueEjecutarEventoEstandoActualEntreInicioYFin(){
		
		Dia[] dias={Dia.MARTES,Dia.VIERNES};
		semanal=new Semanal(dias);
		fechaIni.set(2011, 10, 6, 19, 34);
		fechaFin.set(2012, 11, 19, 18, 7);
		fechaAct.set(2012, 5, 2, 19, 34);
		when(tiempoAnt.getDia()).thenReturn(4);
	
		
		//con 4 dias de antelacion ,no notifica por un dia
		assertFalse("se notifico un dia antes de ser notificado",semanal.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	    
		//con 4 dias de antelacion ,y 4 meses
		when(tiempoAnt.getMes()).thenReturn(4);
		fechaAct.set(2012, 5, 2, 19, 34);
		assertFalse("notifico 4 dias y 4 meses antes,calculo mal hecho",semanal.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
		
		//ya termino el evento
		fechaAct.set(2012, 11, 23, 19, 34);
		assertFalse("el evento no termino",semanal.tengoQueEjecutarEvento(fechaAct, fechaIni,fechaFin,tiempoAnt));
	}

}