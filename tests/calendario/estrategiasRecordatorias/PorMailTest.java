package calendario.estrategiasRecordatorias;

import org.junit.Test;

import directorio.Mail;
import usuario.UsuarioCliente;
import static org.mockito.Mockito.*;

public class PorMailTest {

	@Test
	public void envioRecordatorio(){
		UsuarioCliente usuario=mock(UsuarioCliente.class);
		when(usuario.getUsuario()).thenReturn("ejemplo");
			
		
		PorMail porMail=new PorMail(usuario,"mensaje");
		porMail.enviarRecordatorio();
		
		try { verify(usuario).enviarMail(any(Mail.class)); } 
		catch (Exception e) { e.printStackTrace(); }
		
		verify(usuario).getUsuario();
		
	}
}
