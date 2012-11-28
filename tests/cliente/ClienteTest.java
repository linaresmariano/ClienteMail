package cliente;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import servidor.Servidor;
import usuario.UsuarioCliente;

import directorio.Carpeta;
import directorio.Mail;

public class ClienteTest {

	LinkedList<UsuarioCliente> usuarios;
	@Mock UsuarioCliente usuarioMock1,usuarioMock2;
	@Mock Servidor servidorMock;
	
	@Before
	public void setUp(){
		servidorMock=mock(Servidor.class);
		usuarioMock1=mock(UsuarioCliente.class);
		usuarioMock2=mock(UsuarioCliente.class);
		usuarios=new LinkedList<UsuarioCliente>();
		usuarios.add(usuarioMock1);
		usuarios.add(usuarioMock2);
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
}