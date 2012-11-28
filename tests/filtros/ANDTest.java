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

public class ANDTest {

	private CondicionCompuesta condAND;
	private CondicionSimple cond1;
	private CondicionSimple cond2;
	private Mail mail;
	
	@Before
	public void setUp() {
		// Condicion simple
		this.cond1 = mock(Contiene.class);
		this.cond2 = mock(EsDistinto.class);
				
		// Condicion compuesta con AND
		this.condAND = new AND(this.cond1, this.cond2);
				
		// Mail a evaluar
		this.mail = mock(Mail.class);
	}
	
	@Test
	public void testEvaluar() {
		// Sin condiciones devuelve false
		assertFalse(this.condAND.evaluar(this.mail));
		
		// Con una condicion simple devuelve lo que resulta evaluarla
		this.condAND.addCondicion(cond1);
		
		when(cond1.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condAND.evaluar(this.mail));
		
		when(cond1.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condAND.evaluar(this.mail));
		
		// Con dos condiciones evalua las dos y las une por el operador
		this.condAND.addCondicion(cond2);
		
		when(cond1.evaluar(this.mail)).thenReturn(true);
		when(cond1.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condAND.evaluar(this.mail));
		
		when(cond1.evaluar(this.mail)).thenReturn(true);
		when(cond1.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condAND.evaluar(this.mail));
		
		when(cond1.evaluar(this.mail)).thenReturn(false);
		when(cond1.evaluar(this.mail)).thenReturn(true);
		assertFalse(this.condAND.evaluar(this.mail));
		
		when(cond1.evaluar(this.mail)).thenReturn(false);
		when(cond1.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condAND.evaluar(this.mail));
	}

}
