package filtros;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import contacto.*;
import filtros.condicionSimple.Remitente;
import directorio.Mail;

public class CampoRemitenteTest {

	// Clase a testear
	private Remitente campo;
	
	// Mock de contacto a filtrar
	private Contacto contacto;
	// Mock de contacto que no corresponde
	private Contacto contX;
	// Mock de la lista que contiene al contacto
	private Contacto lista;
	// Mock de la lista que no contiene al contacto
	//private Contacto listaX;
	// Mock del mail a testear
	private Mail mail;
	
	@Before
	public void setUp() {
		
		// Mockeando contacto y mail
		this.contacto = mock(ContactoHoja.class);
		
		this.contX = mock(ContactoHoja.class);
		// Mock lista de mail que contiene al contacto
		this.lista = mock(Lista.class);
		// Mock del mail a filtrar
		this.mail = mock(Mail.class);
		
		// Clase remitente a testear
		this.campo = new Remitente(this.contacto);
	}

	@Test
	public void testEvaluarContiene() {
		// Siendo el contacto el remitente del mail
		when(this.mail.getRemitente()).thenReturn(this.contacto);
		when(this.contacto.contains(this.contacto)).thenReturn(true);
		assertTrue(this.campo.evaluarContiene(this.mail));

		// Cuando no es el remitente del mail
		when(this.mail.getRemitente()).thenReturn(this.contX);
		assertFalse(this.campo.evaluarContiene(this.mail));
		
		// Cuando el remitente esta incluido en la lista de mails
		when(this.lista.contains(this.contacto)).thenReturn(true);
		when(this.contacto.equals(this.lista)).thenReturn(true);
		when(this.mail.getRemitente()).thenReturn(this.lista);
		assertTrue(this.campo.evaluarContiene(this.mail));
		
		// Cuando el remitente esta incluido en la lista de mails
		when(this.lista.contains(this.contacto)).thenReturn(false);
		when(this.contacto.contains(this.lista)).thenReturn(false);
		when(this.mail.getRemitente()).thenReturn(this.lista);
		assertFalse(this.campo.evaluarContiene(this.mail));
	}
	
	@Test
	public void testEvaluarEsIgual() {
		// Siendo el contacto el remitente del mail
		when(this.mail.getRemitente()).thenReturn(this.contacto);
		when(this.contacto.equals(this.contacto)).thenReturn(true);
		assertTrue(this.campo.evaluarEsIgual(this.mail));

		// Cuando no es el remitente del mail
		when(this.mail.getRemitente()).thenReturn(this.contX);
		assertFalse(this.campo.evaluarEsIgual(this.mail));
		
		// Cuando el remitente esta incluido en la lista de mails
		when(this.lista.equals(this.contacto)).thenReturn(true);
		when(this.contacto.equals(this.lista)).thenReturn(true);
		when(this.mail.getRemitente()).thenReturn(this.lista);
		assertTrue(this.campo.evaluarEsIgual(this.mail));
		
		// Cuando el remitente esta incluido en la lista de mails
		when(this.lista.equals(this.contacto)).thenReturn(false);
		when(this.contacto.equals(this.lista)).thenReturn(false);
		when(this.mail.getRemitente()).thenReturn(this.lista);
		assertFalse(this.campo.evaluarEsIgual(this.mail));
	}
	
	@Test
	public void testEvaluarEsDistinto() {
		// Siendo el contacto el remitente del mail
		when(this.mail.getRemitente()).thenReturn(this.contacto);
		when(this.contacto.equals(this.contacto)).thenReturn(true);
		assertFalse(this.campo.evaluarEsDistinto(this.mail));

		// Cuando no es el remitente del mail
		when(this.mail.getRemitente()).thenReturn(this.contX);
		assertTrue(this.campo.evaluarEsDistinto(this.mail));
		
		// Cuando el remitente esta incluido en la lista de mails
		when(this.lista.equals(this.contacto)).thenReturn(true);
		when(this.contacto.equals(this.lista)).thenReturn(true);
		when(this.mail.getRemitente()).thenReturn(this.lista);
		assertFalse(this.campo.evaluarEsDistinto(this.mail));
		
		// Cuando el remitente esta incluido en la lista de mails
		when(this.lista.equals(this.contacto)).thenReturn(false);
		when(this.contacto.equals(this.lista)).thenReturn(true);
		when(this.mail.getRemitente()).thenReturn(this.lista);
		assertTrue(this.campo.evaluarEsDistinto(this.mail));
	}
}
