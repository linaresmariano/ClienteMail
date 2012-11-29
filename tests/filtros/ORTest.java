package filtros;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import filtros.condicionSimple.CondicionSimple;
import filtros.condicionSimple.Contiene;
import filtros.condicionSimple.EsDistinto;

import directorio.Mail;

public class ORTest {

	private CondicionCompuesta condC;
	private CondicionSimple cond1;
	private CondicionSimple cond2;
	private Mail mail;
	
	@Before
	public void setUp() {
		// Condicion simple
		this.cond1 = mock(Contiene.class);
		this.cond2 = mock(EsDistinto.class);
				
		// Condicion compuesta con AND
		this.condC = new OR();
				
		// Mail a evaluar
		this.mail = mock(Mail.class);
	}
	
	@Test
	public void testEvaluar() {
		// Sin condiciones devuelve false
		assertFalse(this.condC.evaluar(this.mail));
		
		// Con una condicion simple devuelve lo que resulta evaluarla
		this.condC.addCondicion(cond1);
		
		when(cond1.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condC.evaluar(this.mail));
		
		when(cond1.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condC.evaluar(this.mail));
		
		// Con dos condiciones evalua las dos y las une por el operador
		this.condC.addCondicion(cond2);
		
		when(cond1.evaluar(this.mail)).thenReturn(true);
		when(cond2.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condC.evaluar(this.mail));
		
		when(cond1.evaluar(this.mail)).thenReturn(true);
		when(cond2.evaluar(this.mail)).thenReturn(false);
		assertTrue(this.condC.evaluar(this.mail));
		
		when(cond1.evaluar(this.mail)).thenReturn(false);
		when(cond2.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condC.evaluar(this.mail));
		
		when(cond1.evaluar(this.mail)).thenReturn(false);
		when(cond2.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condC.evaluar(this.mail));
	}

}
