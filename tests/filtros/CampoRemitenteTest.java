package filtros;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import filtros.condicionSimple.Remitente;
import directorio.Mail;

public class CampoRemitenteTest {

	// Clase a testear
	private Remitente campo;

	// Mock del mail a testear
	private Mail mail;
	
	@Before
	public void setUp() {

		// Mock del mail a filtrar
		this.mail = mock(Mail.class);
		
		// Clase remitente a testear
		this.campo = new Remitente("valor");
	}

	@Test
	public void testEvaluarContiene() {		

		// Siendo el contacto el remitente del mail
		when(this.mail.getRemitente()).thenReturn("valor");
		assertTrue(this.campo.evaluarContiene(this.mail));

		// Conteniendo el nombre del usuario
		when(this.mail.getRemitente()).thenReturn("lalavalordas");
		assertTrue(this.campo.evaluarContiene(this.mail));
		
		// Conteniendo al usuario entre otros
		when(this.mail.getRemitente()).thenReturn("lala valor das");
		assertTrue(this.campo.evaluarContiene(this.mail));
		
		// Conteniendo las letras del nombre del usuario pero no todas juntas
		when(this.mail.getRemitente()).thenReturn("vadlodr");
		assertFalse(this.campo.evaluarContiene(this.mail));
		
		// Cuando el remitente esta vacio
		when(this.mail.getRemitente()).thenReturn("");
		assertFalse(this.campo.evaluarContiene(this.mail));
		
		// Cuando no contiene el nombre del usuario
		when(this.mail.getRemitente()).thenReturn("bcdefghijkmpqstuwxyz");
		assertFalse(this.campo.evaluarContiene(this.mail));
	}
	
	@Test
	public void testEvaluarEsIgual() {
		// Conteniendo al remitente exactamente
		when(this.mail.getRemitente()).thenReturn("valor");
		assertTrue(this.campo.evaluarEsIgual(this.mail));

		// Conteniendo el nombre del remitente sin espacios
		when(this.mail.getRemitente()).thenReturn("lalavalordas");
		assertFalse(this.campo.evaluarEsIgual(this.mail));
		
		// Conteniendo el nombre del remitente con espacios
		when(this.mail.getRemitente()).thenReturn("lala valor das");
		assertFalse(this.campo.evaluarEsIgual(this.mail));
		
		// Otro nombre de remitente
		when(this.mail.getRemitente()).thenReturn("vadlodr");
		assertFalse(this.campo.evaluarEsIgual(this.mail));
		
		// Sin remitente
		when(this.mail.getRemitente()).thenReturn("");
		assertFalse(this.campo.evaluarEsIgual(this.mail));
		
		// Cuando no contiene ninguna de las letras del remitente
		when(this.mail.getRemitente()).thenReturn("bcdefghijkmpqstuwxyz");
		assertFalse(this.campo.evaluarEsIgual(this.mail));
	}
	
	@Test
	public void testEvaluarEsDistinto() {
		// Conteniendo al remitente exactamente
		when(this.mail.getRemitente()).thenReturn("valor");
		assertFalse(this.campo.evaluarEsDistinto(this.mail));

		// Conteniendo el nombre del remitente sin espacios
		when(this.mail.getRemitente()).thenReturn("lalavalordas");
		assertTrue(this.campo.evaluarEsDistinto(this.mail));
		
		// Conteniendo el nombre del remitente con espacios
		when(this.mail.getRemitente()).thenReturn("lala valor das");
		assertTrue(this.campo.evaluarEsDistinto(this.mail));
		
		// Otro nombre de remitente
		when(this.mail.getRemitente()).thenReturn("vadlodr");
		assertTrue(this.campo.evaluarEsDistinto(this.mail));
		
		// Sin remitente
		when(this.mail.getRemitente()).thenReturn("");
		assertTrue(this.campo.evaluarEsDistinto(this.mail));
		
		// Cuando no contiene ninguna de las letras del remitente
		when(this.mail.getRemitente()).thenReturn("bcdefghijkmpqstuwxyz");
		assertTrue(this.campo.evaluarEsDistinto(this.mail));
	}
}
