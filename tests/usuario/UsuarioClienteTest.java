package usuario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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
import directorio.DirectorioUsuario;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;
import directorio.partesDeMail.Encabezado;
import estadoUsuario.EstadoUsuario;
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
	@Mock Encabezado encabezadoMock;
	@Mock EstadoUsuario estadoMock;
	String nombreUsuario;
	String passUsuario;
	String cuerpoMail;
	String destinatario;
	String asunto;
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
		servidorMock = mock(Server.class);
		directorioMock = mock(Carpeta.class);
		encabezadoMock = mock(Encabezado.class);
		estadoMock = mock(EstadoUsuario.class);
		
		nombreUsuario = "pedro@caskmail.com";
		passUsuario = "iAmGod";
		usuario = new UsuarioCliente(nombreUsuario, passUsuario, clienteMock, estrategiaMock);
		cuerpoMail = "Soy el cuerpo de un Mail ;)";
		indiceMail = 2;
		destinatario = "toti@mertmail.com";
		asunto = "UltraImportante";
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
	public void getAdjuntoNullMailTest() {
		
		// El metodo deriva el pedido del adjunto del mail a la estrategia, que pasa si el mail no tiene adjunto!
		when(estrategiaMock.getAdjunto(servidorMock, nombreUsuario, indiceMail)).thenReturn(null);
		when(clienteMock.getServer()).thenReturn(servidorMock);
		when(mailMock.getIndice()).thenReturn(indiceMail);

		// PRUEBA
		// Prueba que el mail retorna el adjunto correcto cuando se lo piden
		assertNull("El adjunto del mail es null", usuario.getAdjuntoMail(mailMock));

		// Verifica que la estrategia recibe el mensaje getAdjunto con los argumentos correctos
		verify(estrategiaMock).getAdjunto(servidorMock, usuario.getUsuario(), mailMock.getIndice());
	}
	
	@Test
	public void eliminarMailTest() {
		
		// La eliminacion del mails depende de la estrategia, por lo que se deriva el pedido
		usuario.setDirectorio(directorioMock);
		when(clienteMock.getServer()).thenReturn(servidorMock);
		
		// PRUEBA
		usuario.eliminarMail(mailMock);
		
		// Verifica que le llega el mensaje eliminarMail a la estrategia con los argumentos correctos
		verify(estrategiaMock).eliminarMail(mailMock, servidorMock, nombreUsuario, directorioMock);
	}
	
	@Test
	public void enviarMailTest() {
		
		List<String> pathEnviados = new LinkedList<String>();
		pathEnviados.add("enviados");
		
		usuario.setDirectorio(directorioMock);
		
		when(mailMock.getEncabezado()).thenReturn(encabezadoMock);
		when(encabezadoMock.getDestinatario()).thenReturn(destinatario);
		
		// PRUEBA
		try { usuario.enviarMail(mailMock); } 
		catch (Exception e) { fail("No se pudo enviar el mensaje enviarMail al usuario"); }
		
		// Verifica que el cliente recibe el mensaje send con el mail adecuado
		try { verify(clienteMock).send(mailMock); } 
		catch (Exception e) { fail("Fallo la verificacion del metodo send enviado a clienteMock"); }
		
		// Verifica que el directorio recibe el mensaje moverA con el mail y el path adecuados
		verify(directorioMock).moverA(mailMock, pathEnviados);
		
	}
	
	@Test
	public void enviarMailsTest() {
		
		usuario.setDirectorio(directorioMock);
		
		Mail mailMock1 = mock(Mail.class);
		Mail mailMock2 = mock(Mail.class);
		Mail mailMock3 = mock(Mail.class);
		
		Encabezado encabezadoMock1 = mock(Encabezado.class);
		Encabezado encabezadoMock2 = mock(Encabezado.class);
		Encabezado encabezadoMock3 = mock(Encabezado.class);
		
		String destinatario1 = "perri@tongomail.com";
		String destinatario2 = "pituco@tongomail.com";
		String destinatario3 = "fermi@tongomail.com";
		
		when(mailMock1.getEncabezado()).thenReturn(encabezadoMock1);
		when(mailMock2.getEncabezado()).thenReturn(encabezadoMock2);
		when(mailMock3.getEncabezado()).thenReturn(encabezadoMock3);
		
		when(encabezadoMock1.getDestinatario()).thenReturn(destinatario1);
		when(encabezadoMock2.getDestinatario()).thenReturn(destinatario2);
		when(encabezadoMock3.getDestinatario()).thenReturn(destinatario3);
		
		Carpeta carpetaMock = mock(Carpeta.class);
		
		LinkedList<DirectorioUsuario> listaHijos = new LinkedList<DirectorioUsuario>();
		
		// Si la lista de mails esta vacia, entonces el metodo no debe hacer nada
		when(directorioMock.retornarCarpetaDeNombre("borradores")).thenReturn(carpetaMock);
		when(carpetaMock.getHijos()).thenReturn(listaHijos);

		// PRUEBA
		usuario.enviarMails();

		// Verifica que no se envia ningun mensaje a ningun objeto
		verifyZeroInteractions(mailMock1, mailMock2, mailMock3, encabezadoMock1, encabezadoMock2, encabezadoMock3);
		
		// Ahora se agregan mails a la lista hijos y se prueba de nuevo
		
		listaHijos.add(mailMock1);
		listaHijos.add(mailMock2);
		listaHijos.add(mailMock3);
		
		when(carpetaMock.getHijos()).thenReturn(listaHijos);
		
		// PRUEBA
		usuario.enviarMails();
		
		// Verifica si se envia el mensaje retornarCarpetaDeNombre al directorio del usuario
		// 2 veces porque ya se llamo en la prueba anterior
		verify(directorioMock, times(2)).retornarCarpetaDeNombre("borradores");
		
		// Verifica si se envia el mensaje getHijos a la carpeta "borradores"
		// 2 veces porque ya se llamo en la prueba anterior
		verify(carpetaMock, times(2)).getHijos();
		
		// Verifica si a todos los mails de la listaHijos les llega el mensaje getEncabezado
		// 2 veces porque se invoca al metodo envialMail(mail) que vuelve a pedir lo mismo
		verify(mailMock1, times(2)).getEncabezado();
		verify(mailMock2, times(2)).getEncabezado();
		verify(mailMock3, times(2)).getEncabezado();
		
		// Verifica si a cada encabezado de cada mail de la lista les llega el mensaje getDestinatario
		// 2 veces porque se invoca al metodo envialMail(mail) que vuelve a pedir lo mismo
		verify(encabezadoMock1, times(2)).getDestinatario();
		verify(encabezadoMock2, times(2)).getDestinatario();
		verify(encabezadoMock3, times(2)).getDestinatario();
	}
	
	@Test
	public void recibirMailsTest() {
		
		usuario.setDirectorio(directorioMock);
		usuario.setEstado(estadoMock);
		
		// Filtros
		LinkedList<Filtro> listaFiltros = new LinkedList<Filtro>();
		Filtro filtroMock1NoExcluyente = mock(Filtro.class);
		Filtro filtroMock2Excluyente = mock(Filtro.class);
		Filtro filtroMock3Ultimo = mock(Filtro.class);
		
		listaFiltros.add(filtroMock1NoExcluyente);
		listaFiltros.add(filtroMock2Excluyente);
		listaFiltros.add(filtroMock3Ultimo);
		
		usuario.setFiltros(listaFiltros);
		
		// Con la lista de mails vacia, el metodo no deberia hacer nada con los mails
		LinkedList<Mail> listaMails = new LinkedList<Mail>();
		
		when(clienteMock.getServer()).thenReturn(servidorMock);
		when(estrategiaMock.bajarYRetornarMails(servidorMock, nombreUsuario)).thenReturn(listaMails);
		
		// PRUEBA - Con la lista de mails vacia, el metodo no deberia hacer nada con los mails
		usuario.recibirMails();
		
		// Verifica que la estrategia recibe el mensaje bajarYRetornarMails con los argumentos correctos
		verify(estrategiaMock).bajarYRetornarMails(servidorMock, nombreUsuario);
		
		// Verifica que el cliente recibe el mensaje getServer
		verify(clienteMock).getServer();
		
		// Ahora agregamos los mails a la listaMails
		Mail mailMockCumpleExcluyente = mock(Mail.class);
		Mail mailMockNoCumpleExcluyente = mock(Mail.class);
		listaMails.add(mailMockCumpleExcluyente);
		listaMails.add(mailMockNoCumpleExcluyente);
		
		// PRUEBA - Ahora la lista de mails tiene mails
		usuario.recibirMails();
		
		// Verifica que le llega el mensaje teLlegoMail al estado, una vez por cada mail en la listaMail.
		verify(estadoMock).teLlegoMail(mailMockNoCumpleExcluyente, usuario);
		verify(estadoMock).teLlegoMail(mailMockCumpleExcluyente, usuario);
		
		// Verifica que le llega el mensaje agregarMail al directorio, una vez por cada mail en la listaMail
		verify(directorioMock).agregarMail(mailMockNoCumpleExcluyente);
		verify(directorioMock).agregarMail(mailMockCumpleExcluyente);
		
		// Verifica que la estrategia recibe el mensaje bajarYRetornarMails con los argumentos correctos
		verify(estrategiaMock, times(2)).bajarYRetornarMails(servidorMock, nombreUsuario);
		
		// Verifica que el cliente recibe el mensaje getServer
		verify(clienteMock, times(2)).getServer();
		
		// Configuracion de filtros mocks //
		
		when(filtroMock1NoExcluyente.esExcluyente()).thenReturn(false);
		when(filtroMock2Excluyente.esExcluyente()).thenReturn(true);
		when(filtroMock3Ultimo.esExcluyente()).thenReturn(false);
		
		when(filtroMock1NoExcluyente.aplicar(mailMockCumpleExcluyente)).thenReturn(true);
		when(filtroMock1NoExcluyente.aplicar(mailMockNoCumpleExcluyente)).thenReturn(false);
		
		when(filtroMock2Excluyente.aplicar(mailMockCumpleExcluyente)).thenReturn(true);
		when(filtroMock2Excluyente.aplicar(mailMockNoCumpleExcluyente)).thenReturn(false);
		
		when(filtroMock3Ultimo.aplicar(mailMockCumpleExcluyente)).thenReturn(false);
		when(filtroMock3Ultimo.aplicar(mailMockNoCumpleExcluyente)).thenReturn(true);
		
		// Verificacion de filtros //
		
		verify(filtroMock1NoExcluyente).aplicar(mailMockCumpleExcluyente);
		verify(filtroMock1NoExcluyente).aplicar(mailMockNoCumpleExcluyente);
		
		verify(filtroMock2Excluyente).aplicar(mailMockCumpleExcluyente);
		verify(filtroMock2Excluyente).aplicar(mailMockNoCumpleExcluyente);
		
		verify(filtroMock3Ultimo).aplicar(mailMockCumpleExcluyente);
		verify(filtroMock3Ultimo).aplicar(mailMockNoCumpleExcluyente);
		
		verifyZeroInteractions(filtroMock1NoExcluyente, filtroMock2Excluyente, filtroMock3Ultimo);	
	}
	
	@Test
	public void redactarMailTest() {
		
		usuario.setDirectorio(directorioMock);
		when(contactoMock.getMail()).thenReturn(destinatario);
		
		// PRUEBA -- Se usa un objeto del dominio, porque sera instanciad de todas formas --
		Mail newMail = usuario.redactarMail(contactoMock, asunto, cuerpoMail);
		
		assertEquals("El nuevo mail tiene el destinatario correcto", contactoMock.getMail(), newMail.getEncabezado().getDestinatario());
		assertEquals("El nuevo mail tiene el asunto correcto", asunto, newMail.getEncabezado().getAsunto());
		assertEquals("El nuevo mail tiene el cuerpo correcto", cuerpoMail, newMail.getCuerpo());
		assertEquals("El nuevo mail no tiene adjunto", null, newMail.getAdjunto());
		assertEquals("El nuevo mail no esta leido", false, newMail.estaLeido());
		assertEquals("El nuevo mail tiene la etiqueta correcta", "borradores", newMail.getEtiqueta().get(0));
		assertEquals("El nuevo mail tiene el remitente correcto", nombreUsuario, newMail.getRemitente());
		Calendar fechaActual = Calendar.getInstance();
		assertTrue("El nuevo mail tiene un fecha correcta", newMail.getFecha().equals(fechaActual));
		
		// Verifica si el directorio recibe el mensaje agregarMail con el mail adecuado
		verify(directorioMock).agregarMail(newMail);
	}
}
