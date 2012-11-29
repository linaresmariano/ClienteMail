package filtros;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import filtros.accion.*;
import filtros.condicionSimple.*;
import directorio.Mail;

public class FiltroTest {

	private Accion act;
	private Condicion cond;
	private Filtro f;
	private Mail mail1;
	
	@Before
	public void setUp() throws Exception {
		// Preparar el mail a filtrar 
		this.mail1 = mock(Mail.class);
		
		// Condicion y accion del filtro
		this.cond = mock(Contiene.class);
		this.act = mock(MarcarComoLeido.class);
		
		// La clase filtro a testear
		this.f = new Filtro(true, this.act, this.cond);
	}

	@Test
	public void testEvaluar() {
		// La condicion evaluada con un mail primero devuelve true y desp false
		when(this.cond.evaluar(this.mail1)).thenReturn(true).thenReturn(false);
		
		// Verificar que el evaluar devuelve lo que la condicion
		assertTrue(this.f.evaluar(mail1));
		assertFalse(this.f.evaluar(mail1));
	}
	
	@Test
	public void testEjecutarAccion() {
		// Ejecutar la accion sobre el mail
		this.f.ejecutarAccion(this.mail1);
		
		// Verificar que a la accion le llegue el mensaje
		verify(this.act).ejecutarAccion(this.mail1);
		
	}
	
	@Test
	public void testAplicarWHENCondRetornaTrue() {
		
		// Cuando la condicion evalua al mail como verdadero
		when(this.cond.evaluar(this.mail1)).thenReturn(true);
		
		// Al aplicar el filtro se evalua y se ejecuta la accion
		this.f.aplicar(this.mail1);
		verify(this.cond).evaluar(this.mail1);
		verify(this.act).ejecutarAccion(this.mail1);
	}
	
	@Test
	public void testAplicarWHENCondRetornaFalse() {
		
		// Cuando la condicion evalua al mail como false
		when(this.cond.evaluar(this.mail1)).thenReturn(false);

		// Al aplicar el filtro se evalua y no se ejecuta la accion
		this.f.aplicar(this.mail1);
		verify(this.cond).evaluar(this.mail1);
	}

}

