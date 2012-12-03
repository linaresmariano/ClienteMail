package cliente;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import directorio.Mail;
import estrategiaAcceso.EstrategiaAcceso;
import exceptions.AlreadyLogged;
import exceptions.InvalidUserOrPass;

import server.MailServer;
import server.Server;
import usuario.UsuarioCliente;

public class ClienteTest {

	Cliente cliente;
	@Mock UsuarioCliente usuarioMock;
	@Mock Server servidorMock;
	@Mock MailServer mailServerMock;
	@Mock Mail mailClienteMock;
	@Mock EstrategiaAcceso estrategiaMock;
	String nombreUsuario;
	String passwordUsuario;
	String nombreUsuarioInvalido;
	String passwordUsuarioInvalido;
	
	@Before
	public void setUp() {
		
		servidorMock = mock(Server.class);
		usuarioMock = mock(UsuarioCliente.class);
		mailServerMock = mock(MailServer.class);
		mailClienteMock = mock(Mail.class);
		estrategiaMock = mock(EstrategiaAcceso.class);
		
		cliente = new Cliente(servidorMock);
		cliente.setUsuario(usuarioMock);
	}

	@Test
	public void logoutServerResponseFalseTest() {

		nombreUsuario = "pedro@caskmail.com";
		when(usuarioMock.getUsuario()).thenReturn(nombreUsuario);
		
		// Si logout en el servidor no funciona (devuelve false) deberia deslogeuar localmente.
		when(servidorMock.logout(nombreUsuario)).thenReturn(false);
		
		// Se intenta un primer logout (hay un usuario logueado)
		cliente.logout();
		
		// Verifica que el usuarioActivo recibe el mensaje getUsuario
		verify(usuarioMock).getUsuario();
		// Verifica que el servidor recibe el mensaje logout con el nombre de usuario correcto
		verify(servidorMock).logout(nombreUsuario);
		// Pruebo que el usuario ahora sea null
		assertNull("Luego del logout el usuarioActivo debe ser null", cliente.getUsuario());
		
		// Si se intenta logout de nuevo (ahora el usuario es null)
		cliente.logout();
		
		// Verifica que no se envian mas mensajes al usuario (null) ni al servidor. 
		verifyNoMoreInteractions(usuarioMock, servidorMock);
	}
	
	@Test
	public void logoutServerResponseTrueTest() {

		nombreUsuario = "pedro@caskmail.com";
		when(usuarioMock.getUsuario()).thenReturn(nombreUsuario);
		
		// Si logout en el servidor funciona (devuelve true) deberia deslogeuar localmente.
		when(servidorMock.logout(nombreUsuario)).thenReturn(true);
		
		// Se intenta un primer logout (hay un usuario logueado)
		cliente.logout();
		
		// Verifica que el usuarioActivo recibe el mensaje getUsuario
		verify(usuarioMock).getUsuario();
		// Verifica que el servidor recibe el mensaje logout con el nombre de usuario correcto
		verify(servidorMock).logout(nombreUsuario);
		// Pruebo que el usuario ahora sea null
		assertNull("Luego del logout el usuarioActivo debe ser null", cliente.getUsuario());
		
		// Si se intenta logout de nuevo (ahora el usuario es null)
		cliente.logout();
		
		// Verifica que no se envian mas mensajes al usuario (null) ni al servidor. 
		verifyNoMoreInteractions(usuarioMock, servidorMock);
	}
	
	@Test
	public void sendTest() {
		
		when(usuarioMock.getEstrategia()).thenReturn(estrategiaMock);
		
		try { cliente.send(mailClienteMock); } catch (Exception e) { e.printStackTrace(); }
		
		// Pruebo que el usuarioActivo recibe el mensaje getEstrategia
		verify(usuarioMock).getEstrategia();
		
		// Pruebo que la estrategia del usuarioActivo recibe el mensaje send con el mail y el servidor correctos
		verify(estrategiaMock).send(mailClienteMock, servidorMock);
	}
	
	@Test
	public void loginTestConUsuarioActivo() {
			
		// PRUEBA
		cliente.login(nombreUsuario, passwordUsuario, estrategiaMock);
		
		// Verifico que el usuario recibe el mensaje getUsuario
		verify(usuarioMock).getUsuario();
		// Como el usuarioActivo existe y esta logueado, aca se imprime un msj y listo.
		// Verifico que el usuario no recibe ningun otro mensaje.
		verifyZeroInteractions(usuarioMock);
	}
		
	@Test
	public void loginTestSinUsuarioActivo() {
		
		// Si no hay usuario activo (null), el metodo login deberia crear uno.
		cliente.setUsuario(null);
		
		// La clase UsuarioCliente devolvera el usuarioMock en respuesta al pedido de instanciacion de un usuario nuevo
		// Como hagooooooo????? AAAAAHHHHHH
		
		nombreUsuario = "pedro@caskmail.com";
		passwordUsuario = "iAmGod";
		
		when(usuarioMock.getUsuario()).thenReturn(nombreUsuario);
		
		// Los argumentos del login pertenecen a un usuario logueado en el servidor
		try { when(servidorMock.login(nombreUsuario, passwordUsuario)).thenReturn(true); }
		catch (InvalidUserOrPass e1) { e1.printStackTrace(); } 
		catch (AlreadyLogged e1) { e1.printStackTrace(); }
		
		// PRUEBA
		cliente.login(nombreUsuario, passwordUsuario, estrategiaMock);
		
		// Verifico que el servidor recibe el mensaje login con el nombre de usuario y el password correctos
		try { verify(servidorMock).login(nombreUsuario, passwordUsuario); } 
		catch (InvalidUserOrPass e) { e.printStackTrace(); } 
		catch (AlreadyLogged e) { e.printStackTrace(); }
		
		// Verifico que se llama al constructor de la clase UsuarioCliente
		// Como :( ???????
		
		// Pruebo que se instancia un nuevo usuario para el usuarioActivo del cliente (que va a ser el usuarioMock)
		// assertEquals("El UsuarioActivo se instancio correctamente", usuarioMock, cliente.getUsuario());
		
		// Verifico que se envia el mensaje recibirMails al usuarioActivo
		// verify(usuarioMock).recibirMails();
	}

	@Test
	public void loginTestServerResponseInvalidUser() {
		
		// Si no hay usuario activo (null), el metodo login deberia crear uno.
		cliente.setUsuario(null);
		
		nombreUsuarioInvalido = "invalid@User.com";
		passwordUsuario = "iAmGod";
		
		when(usuarioMock.getUsuario()).thenReturn(nombreUsuarioInvalido);
		
		// El nombre de usuario no existe en el servidor
		try { when(servidorMock.login(nombreUsuarioInvalido, passwordUsuario)).thenThrow(new InvalidUserOrPass()); }
		catch (InvalidUserOrPass e1) { e1.printStackTrace(); } 
		catch (AlreadyLogged e1) { e1.printStackTrace(); }
		
		// PRUEBA
		cliente.login(nombreUsuarioInvalido, passwordUsuario, estrategiaMock);
		
		// Verifico que el servidor recibe el mensaje login con el nombre de usuario incorrecto y que captura la excepcion correcta.
		try { verify(servidorMock).login(nombreUsuarioInvalido, passwordUsuario); } 
		catch (InvalidUserOrPass e) { assertTrue("Capturo la excepcion de invalidUserName", true); } 
		catch (AlreadyLogged e) { e.printStackTrace(); }
		
		// Prueba que el usuario sigue siendo null
		assertEquals("El usuario sigue siendo null", null, cliente.getUsuario());
	}

	@Test
	public void loginTestServerResponseInvalidPass() {
		
		// Si no hay usuario activo (null), el metodo login deberia crear uno.
		cliente.setUsuario(null);
		
		nombreUsuario = "pedro@caskmail.com";
		passwordUsuarioInvalido = "iAmEvil";
		
		when(usuarioMock.getUsuario()).thenReturn(nombreUsuario);
		
		// El nombre de usuario no existe en el servidor
		try { when(servidorMock.login(nombreUsuario, passwordUsuarioInvalido)).thenThrow(new AlreadyLogged()); }
		catch (InvalidUserOrPass e1) { e1.printStackTrace(); } 
		catch (AlreadyLogged e1) { e1.printStackTrace(); }
		
		// PRUEBA
		cliente.login(nombreUsuario, passwordUsuarioInvalido, estrategiaMock);
		
		// Verifico que el servidor recibe el mensaje login con el nombre de usuario incorrecto y que captura la excepcion correcta.
		try { verify(servidorMock).login(nombreUsuario, passwordUsuarioInvalido); } 
		catch (InvalidUserOrPass e) { e.printStackTrace(); } 
		catch (AlreadyLogged e) { assertTrue("Capturo la excepcion de invalidPass", true);}
		
		// Prueba que el usuario sigue siendo null
		assertEquals("El usuario sigue siendo null", null, cliente.getUsuario());
	}
}
