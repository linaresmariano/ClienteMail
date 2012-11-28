package estadoUsuario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import usuario.UsuarioCliente;

import directorio.Carpeta;
import directorio.Mail;

public class ActivoTest {

	@Mock UsuarioCliente usuarioMock;
	@Mock Mail mailMock;
	
	@Before
	public void setUp(){
		
		mailMock=mock(Mail.class);
		usuarioMock=mock(UsuarioCliente.class);
		
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
