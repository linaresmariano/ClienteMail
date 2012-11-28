package filtros;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import filtros.accion.*;
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
		
		this.cond = mock(Condicion.class);
		this.act = mock(Accion.class);
		
		this.f = new Filtro(true, this.act, this.cond);
	}

	@Test
	public void testEvaluar() {

		when(this.cond.evaluar(this.mail1)).thenReturn(true).thenReturn(false);
		assertTrue(this.f.evaluar(mail1));
		assertFalse(this.f.evaluar(mail1));
	}
	
	@Test
	public void testEjecutarAccion() {
		
		this.f.ejecutarAccion(this.mail1);
		verify(this.act).ejecutarAccion(this.mail1);
		
	}
	
	@Test
	public void testAplicar() {
		this.f.aplicar(this.mail1);
		verify(this.act).ejecutarAccion(this.mail1);
		verify(this.cond).evaluar(this.mail1);
	}

}

