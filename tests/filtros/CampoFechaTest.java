package filtros;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import directorio.Mail;
import filtros.condicionSimple.Fecha;

public class CampoFechaTest {

	private Fecha asunto;
	private Mail mail;
	private Calendar date = Calendar.getInstance(); 
	
	@Before
	public void setUp() throws Exception {
		Calendar fechaFiltro = Calendar.getInstance();
		fechaFiltro.set(2000, 10, 1, 15, 15);
		this.asunto = new Fecha(fechaFiltro);
		
		date.set(2000, 10, 1, 15, 15);
		this.mail = mock(Mail.class);
		when(this.mail.getFecha()).thenReturn(date);
	}

	@Test
	public void testEvaluarContiene() {
		// Conteniendo la fecha exactamente
		assertTrue(this.asunto.evaluarContiene(this.mail));

		// Cuando no corresponde la fecha
		date.set(2010, 3, 4, 16, 16);
		assertFalse(this.asunto.evaluarContiene(this.mail));
	}
	
	@Test
	public void testEvaluarEsIgual() {
		// Conteniendo la fecha exactamente
		assertTrue(this.asunto.evaluarEsIgual(this.mail));

		// Cuando no corresponde la fecha
		date.set(2010, 3, 4, 16, 16);
		assertFalse(this.asunto.evaluarEsIgual(this.mail));
	}
	
	@Test
	public void testEvaluarEsDistinto() {
		// Conteniendo la fecha exactamente
		assertFalse(this.asunto.evaluarEsDistinto(this.mail));

		// Cuando no corresponde la fecha
		date.set(2010, 3, 4, 16, 16);
		assertTrue(this.asunto.evaluarEsDistinto(this.mail));
	}

}
