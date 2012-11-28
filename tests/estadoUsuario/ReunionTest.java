package estadoUsuario;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import usuario.UsuarioCliente;
import directorio.Mail;

public class ReunionTest {

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
