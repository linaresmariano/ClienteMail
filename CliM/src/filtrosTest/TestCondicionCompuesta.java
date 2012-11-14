package filtrosTest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import filtros.*;

public class TestCondicionCompuesta {

	private CondicionCompuesta condAND;
	private CondicionCompuesta condOR;
	private CondicionSimple cSim1;
	private CondicionSimple cSim2;
	private Mail mail;
	
	@Before
	public void setUp() {
		// Condicion compuesta con AND
		this.condAND = new CondicionCompuesta(new AND());
				
		// Mail a evaluar
		this.mail = mock(Mail.class);
		
		// Condicion simple
		this.cSim1 = mock(Contiene.class);
		this.cSim2 = mock(EsDistinto.class);
		
	}

	@Test
	public void testAddCondicion() {
		assertTrue(this.condAND.getOperandos().isEmpty());
		assertEquals(this.condAND.getOperador().getClass(), AND.class);
		
		this.condAND.addCondicion(cSim1);
		assertEquals(this.condAND.getOperandos().size(), 1);
		assertEquals(this.condAND.getOperador().getClass(), AND.class);
		
		this.condAND.addCondicion(cSim2);
		assertEquals(this.condAND.getOperandos().size(), 2);
		assertEquals(this.condAND.getOperador().getClass(), AND.class);
		
		this.condAND.addCondicion(cSim1);
		assertEquals(this.condAND.getOperandos().size(), 3);
		assertEquals(this.condAND.getOperador().getClass(), AND.class);
	}
	
	@Test
	public void testEvaluarCondicionAND() {
		// Sin condiciones devuelve false
		assertFalse(this.condAND.evaluar(this.mail));
		
		// Con una condicion simple devuelve lo que resulta evaluarla
		this.condAND.addCondicion(cSim1);
		
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condAND.evaluar(this.mail));
		
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condAND.evaluar(this.mail));
		
		// Con dos condiciones evalua las dos y las une por el operador
		this.condAND.addCondicion(cSim2);
		
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condAND.evaluar(this.mail));
		
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condAND.evaluar(this.mail));
		
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		assertFalse(this.condAND.evaluar(this.mail));
		
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condAND.evaluar(this.mail));
	}
	
	@Test
	public void testEvaluarCondicionOR() {
		// Condicion compuesta con OR
		this.condOR = new CondicionCompuesta(new OR());
		
		// Sin condiciones devuelve false
		assertFalse(this.condOR.evaluar(this.mail));
		
		// Con una condicion simple devuelve lo que resulta evaluarla
		this.condOR.addCondicion(cSim1);
		
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condOR.evaluar(this.mail));
		
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condOR.evaluar(this.mail));
		
		// Con dos condiciones evalua las dos y las une por el operador
		this.condOR.addCondicion(cSim2);
		
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condOR.evaluar(this.mail));
		
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		assertTrue(this.condOR.evaluar(this.mail));
		
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		when(cSim1.evaluar(this.mail)).thenReturn(true);
		assertTrue(this.condOR.evaluar(this.mail));
		
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		when(cSim1.evaluar(this.mail)).thenReturn(false);
		assertFalse(this.condOR.evaluar(this.mail));
	}
}
