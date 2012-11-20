package filtros;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import filtros.Asunto;

public class CampoAsuntoTest {

	private Asunto asunto;
	private Mail mail;
	
	@Before
	public void setUp() {
		this.asunto = new Asunto("valor");
		this.mail = mock(Mail.class);
	}

	@Test
	public void testEvaluarContiene() {
		// Conteniendo la palabra exactamente
		when(this.mail.getAsunto()).thenReturn("valor");
		assertTrue(this.asunto.evaluarContiene(this.mail));

		// Conteniendo la palabra entre otras sin espacios
		when(this.mail.getAsunto()).thenReturn("lalavalordas");
		assertTrue(this.asunto.evaluarContiene(this.mail));
		
		// Conteniendo la palabra entre otras con espacios
		when(this.mail.getAsunto()).thenReturn("lala valor das");
		assertTrue(this.asunto.evaluarContiene(this.mail));
		
		// Conteniendo las letras de la palabra pero no todas juntas
		when(this.mail.getAsunto()).thenReturn("vadlodr");
		assertFalse(this.asunto.evaluarContiene(this.mail));
		
		when(this.mail.getAsunto()).thenReturn("");
		assertFalse(this.asunto.evaluarContiene(this.mail));
		
		// Cuando no contiene ninguna de las letras
		when(this.mail.getAsunto()).thenReturn("bcdefghijkmpqstuwxyz");
		assertFalse(this.asunto.evaluarContiene(this.mail));
	}
	
	@Test
	public void testEvaluarEsIgual() {
		// Conteniendo la palabra exactamente
		when(this.mail.getAsunto()).thenReturn("valor");
		assertTrue(this.asunto.evaluarEsIgual(this.mail));

		// Conteniendo la palabra entre otras sin espacios
		when(this.mail.getAsunto()).thenReturn("lalavalordas");
		assertFalse(this.asunto.evaluarEsIgual(this.mail));
		
		// Conteniendo la palabra entre otras con espacios
		when(this.mail.getAsunto()).thenReturn("lala valor das");
		assertFalse(this.asunto.evaluarEsIgual(this.mail));
		
		// Conteniendo las letras de la palabra pero no todas juntas
		when(this.mail.getAsunto()).thenReturn("vadlodr");
		assertFalse(this.asunto.evaluarEsIgual(this.mail));
		
		when(this.mail.getAsunto()).thenReturn("");
		assertFalse(this.asunto.evaluarEsIgual(this.mail));
		
		// Cuando no contiene ninguna de las letras
		when(this.mail.getAsunto()).thenReturn("bcdefghijkmpqstuwxyz");
		assertFalse(this.asunto.evaluarEsIgual(this.mail));
	}
	
	@Test
	public void testEvaluarEsDistinto() {
		// Conteniendo la palabra exactamente
		when(this.mail.getAsunto()).thenReturn("valor");
		assertFalse(this.asunto.evaluarEsDistinto(this.mail));

		// Conteniendo la palabra entre otras sin espacios
		when(this.mail.getAsunto()).thenReturn("lalavalordas");
		assertTrue(this.asunto.evaluarEsDistinto(this.mail));
		
		// Conteniendo la palabra entre otras con espacios
		when(this.mail.getAsunto()).thenReturn("lala valor das");
		assertTrue(this.asunto.evaluarEsDistinto(this.mail));
		
		// Conteniendo las letras de la palabra pero no todas juntas
		when(this.mail.getAsunto()).thenReturn("vadlodr");
		assertTrue(this.asunto.evaluarEsDistinto(this.mail));
		
		when(this.mail.getAsunto()).thenReturn("");
		assertTrue(this.asunto.evaluarEsDistinto(this.mail));
		
		// Cuando no contiene ninguna de las letras
		when(this.mail.getAsunto()).thenReturn("bcdefghijkmpqstuwxyz");
		assertTrue(this.asunto.evaluarEsDistinto(this.mail));
	}
}
