package usuario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import server.Server;

import contacto.ContactoHoja;
import contacto.Lista;

import calendario.Calendario;
import calendario.eventos.Evento;
import cliente.Cliente;

import directorio.Carpeta;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;
import estrategiaAcceso.EstrategiaAcceso;
import filtros.Filtro;

public class UsuarioClienteTest {

	UsuarioCliente usuario;
	@Mock Mail mailMock;
	@Mock Adjunto adjuntoMock;
	@Mock Cliente clienteMock;
	@Mock EstrategiaAcceso estrategiaMock;
	@Mock ContactoHoja contactoMock;
	@Mock Lista listaMock;
	@Mock Evento eventoMock;
	@Mock Calendario calendarioMock;
	@Mock Filtro filtroMock;
	@Mock LinkedList<Filtro> listaFiltrosMock;
	@Mock Server servidorMock;
	@Mock Carpeta directorioMock;
	String nombreUsuario;
	String passUsuario;
	String cuerpoMail;
	int indiceMail;
	
	@Before 
	public void setUp() {
		
		mailMock = mock(Mail.class);
		adjuntoMock = mock(Adjunto.class);
		clienteMock = mock(Cliente.class);
		estrategiaMock = mock(EstrategiaAcceso.class);
		contactoMock = mock(ContactoHoja.class);
		listaMock = mock(Lista.class);
		eventoMock = mock(Evento.class);
		calendarioMock = mock(Calendario.class);
		filtroMock = mock(Filtro.class);
		listaFiltrosMock = mock(LinkedList.class);
		servidorMock = mock(Server.class);
		directorioMock = mock(Carpeta.class);
		
		nombreUsuario = "pedro@caskmail.com";
		passUsuario = "iAmGod";
		usuario = new UsuarioCliente(nombreUsuario, passUsuario, clienteMock, estrategiaMock);
		usuario.setCliente(clienteMock);
		cuerpoMail = "Soy el cuerpo de un Mail ;)";
		indiceMail = 2;
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
	public void agregarYEliminarContactoTest() {
		
		// PRUEBA
		usuario.agregarContacto(contactoMock);

		// Prueba que el contacto se agrego correctamente
		assertTrue("El Contacto se agrego correctamente", usuario.getContactos().contains(contactoMock));

		// PRUEBA
		usuario.eliminarContacto(contactoMock);

		// Prueba que el contacto se elimino correctamente
		assertFalse("El Contacto se elimino correctamente", usuario.getContactos().contains(contactoMock));

		// Ponemos la listaMock como lista de contactos del usuario
		usuario.setContactos(listaMock);

		// PRUEBA
		usuario.agregarContacto(contactoMock);

		// Verifica que la lista de contactos reciba el mensaje addContacto con el contacto correcto
		verify(listaMock).addContacto(contactoMock);

		// PRUEBA
		usuario.eliminarContacto(contactoMock);

		// Verifica que la lista de contactos reciba el mensaje eliminarContacto con el contacto correcto
		verify(listaMock).eliminarContacto(contactoMock);
	}
	
	@Test
	public void agregarYEliminarEventoTest() {

		// PRUEBA
		usuario.agregarEvento(eventoMock);

		// Prueba que el evento se agrego correctamente al calendario
		assertTrue("El evento se agrego correctamente", usuario.getCalendario().getEventos().contains(eventoMock));

		// PRUEBA
		usuario.eliminarEvento(eventoMock);

		// Prueba que el evento se elimino correctamente del calendario
		assertFalse("El evento se elimino correctamente", usuario.getCalendario().getEventos().contains(eventoMock));

		// Ponemos el calendarioMock como calendario del usuario
		usuario.setCalendario(calendarioMock);

		// PRUEBA
		usuario.agregarEvento(eventoMock);

		// Verifica que el calendario reciba el mensaje agregarEvento con el evento correcto
		verify(calendarioMock).agregarEvento(eventoMock);

		// PRUEBA
		usuario.eliminarEvento(eventoMock);

		// Verifica que el calendario reciba el mensaje eliminarEvento con el evento correcto
		verify(calendarioMock).eliminarEvento(eventoMock);
	}
	
	@Test
	public void agregarYEliminarFiltroTest() {
		
		// PRUEBA
		usuario.agregarFiltro(filtroMock);

		// Prueba que el filtro se agrego correctamente a la lista de filtros
		assertTrue("El filtro se agrego correctamente", usuario.getFiltros().contains(filtroMock));

		// PRUEBA
		usuario.eliminarFiltro(filtroMock);

		// Prueba que el filtro se elimino correctamente de la lista de filtros
		assertFalse("El evento se elimino correctamente", usuario.getCalendario().getEventos().contains(eventoMock));

		// Ponemos el la listaMock como lista de filtros filtro del usuario
		usuario.setFiltros(listaFiltrosMock);

		// PRUEBA
		usuario.agregarFiltro(filtroMock);

		// Verifica que la lista de filtros reciba el mensaje agregarFiltro con el filtro correcto
		verify(listaFiltrosMock).add(filtroMock);

		// PRUEBA
		usuario.eliminarFiltro(filtroMock);

		// Verifica que la lista de filtros reciba el mensaje eliminarFiltro con el filtro correcto
		verify(listaFiltrosMock).remove(filtroMock);		
	}
	
	@Test
	public void getCuerpoMailTest() {
		
		// El metodo deriva el pedido del cuerpo del mail a la estrategia
		when(estrategiaMock.getCuerpo(servidorMock, nombreUsuario, mailMock)).thenReturn(cuerpoMail);
		when(clienteMock.getServer()).thenReturn(servidorMock);
		
		
		// Prueba que el mail retorna el cuerpo correcto cuando se lo piden
		assertEquals("El cuerpo del mail es el correcto", cuerpoMail, usuario.getCuerpoMail(mailMock));
		
		// PRUEBA
		usuario.getCuerpoMail(mailMock);
		
		// Verifica que la estrategia recibe el mensaje getCuerpo con los argumentos correctos
		// Se verifican dos llamados, ya que uno se realiza en el assertEquals de este test
		verify(estrategiaMock, times(2)).getCuerpo(servidorMock, usuario.getUsuario(), mailMock);
	}
	
	@Test
	public void getAdjuntoMailTest() {
		
		// El metodo deriva el pedido del adjunto del mail a la estrategia
		when(estrategiaMock.getAdjunto(servidorMock, nombreUsuario, indiceMail)).thenReturn(adjuntoMock);
		when(clienteMock.getServer()).thenReturn(servidorMock);
		when(mailMock.getIndice()).thenReturn(indiceMail);

		// Prueba que el mail retorna el adjunto correcto cuando se lo piden
		assertEquals("El adjunto del mail es el correcto", adjuntoMock, usuario.getAdjuntoMail(mailMock));

		// PRUEBA
		usuario.getAdjuntoMail(mailMock);

		// Verifica que la estrategia recibe el mensaje getAdjunto con los argumentos correctos
		// Se verifican dos llamados, ya que uno se realiza en el assertEquals de este test
		verify(estrategiaMock, times(2)).getAdjunto(servidorMock, usuario.getUsuario(), mailMock.getIndice());
	}
	
	@Test
	public void eliminarMailTest() {
		
		// La liminacion del mails depende de la estrategia, por lo que se deriva el pedido
		when(clienteMock.getServer()).thenReturn(servidorMock);
		when(mailMock.getIndice()).thenReturn(indiceMail);
		
		
		
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
