package calendario.estrategiasRecordatorias;

import org.junit.Test;

import cliente.Servidor;
import directorio.Mail;
import usuario.UsuarioCliente;
import static org.mockito.Mockito.*;

public class PorMailTest {

	@Test
	public void envioRecordatorio(){
		UsuarioCliente usuario=mock(UsuarioCliente.class);
		when(usuario.getUsuario()).thenReturn("ejemplo");
		Servidor servidor=mock(Servidor.class);
		when(servidor.getDominio()).thenReturn("gmail.com");
		when(usuario.getServidor()).thenReturn(servidor);
		
		PorMail porMail=new PorMail(usuario,"mensaje");
		porMail.enviarRecordatorio();
		
		verify(usuario).enviarMail(any(Mail.class));
		verify(usuario).getUsuario();
		verify(usuario).getServidor();
	}
}
