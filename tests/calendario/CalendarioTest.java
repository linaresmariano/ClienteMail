package calendario;

import java.util.Calendar;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import calendario.eventos.Evento;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CalendarioTest {

	Evento e1;
	Evento e2;
	Evento e3;
	Calendario calendario;
	Calendar fechaAct;
	
	@Before
	public void setUp(){
		e1=mock(Evento.class);
		e2=mock(Evento.class);
		e3=mock(Evento.class);
		fechaAct=mock(Calendar.class);
		calendario=new Calendario();
		calendario.setEventos(new LinkedList<Evento>());
		calendario.setEventosActivos(new LinkedList<Evento>());
		when(e1.termino(fechaAct)).thenReturn(false);
		when(e2.termino(fechaAct)).thenReturn(false);
		when(e3.termino(fechaAct)).thenReturn(false);
		calendario.agregarEvento(e1);
		calendario.agregarEvento(e2);
		calendario.agregarEvento(e3);
	}
	
	@Test
	public void avisarFechaTodosEventosActivos(){
		
		calendario.avisarFecha(fechaAct);
		InOrder orden=inOrder(e1,e2,e3);
		orden.verify(e1).realizarEvento(fechaAct);
		orden.verify(e2).realizarEvento(fechaAct);
		orden.verify(e3).realizarEvento(fechaAct);
	}
	
	@Test
	public void avisarFechaSinTodosEventosActivos(){
		
		when(e2.termino(fechaAct)).thenReturn(true);
		calendario.avisarFecha(fechaAct);
		
	    
	    assertEquals(calendario.getEventosActivos().size(),2);
	    
	    when(e3.termino(fechaAct)).thenReturn(true);
	    when(e1.termino(fechaAct)).thenReturn(true);
	  
	    calendario.avisarFecha(fechaAct);
	 
	    assertEquals(calendario.getEventosActivos().size(),0);
	   
	}
	@Test
	public void eliminarEventos(){
		calendario.eliminarEvento(e1);
		assertEquals(calendario.getEventos().size(),2);
		assertEquals(calendario.getEventosActivos().size(),2);
		
		calendario.eliminarEvento(e2);
		assertEquals(calendario.getEventos().size(),1);
		assertEquals(calendario.getEventosActivos().size(),1);
		
		calendario.eliminarEvento(e3);
		assertEquals(calendario.getEventos().size(),0);
		assertEquals(calendario.getEventosActivos().size(),0);
	}
	
	@Test
	public void eliminarEventosSinEventos(){
		calendario.eliminarEvento(e1);
		calendario.eliminarEvento(e2);
		calendario.eliminarEvento(e3);
		
		//e3 ya fue borrado,por eso no existe
		calendario.eliminarEvento(e3);
		
		//no pasa nada 
		assertEquals(calendario.getEventos().size(),0);
		assertEquals(calendario.getEventosActivos().size(),0);
	}
	
	@Test
	public void agregarEvento(){
		Evento e4=mock(Evento.class);
		Evento e5=mock(Evento.class);
		
		calendario.agregarEvento(e4);
		assertEquals(calendario.getEventos().size(),4);
		assertEquals(calendario.getEventosActivos().size(),4);
		
		calendario.agregarEvento(e5);
		assertEquals(calendario.getEventos().size(),5);
		assertEquals(calendario.getEventosActivos().size(),5);
		
		
	}
	
	@Test
	public void agregarEventoYaExistente(){
		
		//e3 ya existe
		calendario.agregarEvento(e3);

		//si,se agrega no me importa
		assertEquals(calendario.getEventos().size(),4);
		assertEquals(calendario.getEventosActivos().size(),4);
		
		
	}
	
	
}
