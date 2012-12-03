package usuario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import contacto.Contacto;
import contacto.Lista;

import cliente.Cliente;

import directorio.Mail;
import directorio.partesDeMail.Adjunto;
import estrategiaAcceso.EstrategiaAcceso;

public class UsuarioClienteTest {

	UsuarioCliente usuario;
	@Mock Mail mailMock;
	@Mock Adjunto adjuntoMock;
	@Mock Cliente clienteMock;
	@Mock EstrategiaAcceso estrategiaMock;
	@Mock Lista contactoMock;
	String nombreUsuario;
	String passUsuario;
	
	@Before 
	public void setUp() {
		
		mailMock = mock(Mail.class);
		adjuntoMock = mock(Adjunto.class);
		clienteMock = mock(Cliente.class);
		estrategiaMock = mock(EstrategiaAcceso.class);
		contactoMock = mock(Lista.class);
		
		nombreUsuario = "pedro@caskmail.com";
		passUsuario = "iAmGod";
		usuario = new UsuarioCliente(nombreUsuario, passUsuario, clienteMock, estrategiaMock);	
	}
	
	// TestearConstructor????
	
	@Test
	public void adjuntarTest() {
		
		// PRUEBA
		usuario.adjuntar(adjuntoMock, mailMock);
		
		// Verifica que mail reciba el mensaje setAdjunto con el adjunto correcto
		verify(mailMock).setAdjunto(adjuntoMock);
	}
	
	@Test
	public void agregarContactoTest() {
		
		// PRUEBA
		usuario.agregarContacto(contactoMock);
		
		// Verifica que contactos reciba el mensaje addContacto con el contacto correcto
		verify(usuario.getContactos()).addContacto(contactoMock);
		
		// Prueba que contactos ahora contiene a contactoMock
		assertTrue("Se agrego el contacto correctamente", usuario.getContactos().contains(contactoMock));
	}
	
	@Test
	public void agregarEventoTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void agregarFiltroTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void eliminarContactoTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void eliminarEventoTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void eliminarFiltroTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void eliminarMailTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void enviarMailTest() {
		fail("Not yet implemented");
	}
	@Test
	public void enviarMails() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getCuerpoMailTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getAdjuntoMailTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void recibirMailsTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void redactarMailTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void redactarMailConAdjuntoTest() {
		fail("Not yet implemented");
	}
	
	
}
