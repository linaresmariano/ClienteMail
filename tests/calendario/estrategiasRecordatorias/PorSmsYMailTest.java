package calendario.estrategiasRecordatorias;

import org.junit.Test;
import server.Server;
import calendario.Sms;
import directorio.Mail;
import usuario.UsuarioCliente;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class PorSmsYMailTest {

	@Test
	public void envioRecordatorio(){
		//creando el contexto
		UsuarioCliente usuario=mock(UsuarioCliente.class);
		when(usuario.getUsuario()).thenReturn("ejemplo");
		Server servidor=mock(Server.class);
		String mensaje="mensaje";
		Sms sms=mock(Sms.class);
		PorSmsYMail porMailSms=new PorSmsYMail(usuario,mensaje);
		porMailSms.setSms(sms);
		
		//configurando el servidor y el usuario
		when(servidor.getDominio()).thenReturn("gmail.com");
		when(usuario.getServidor()).thenReturn(servidor);
		when(usuario.getNroCelular()).thenReturn(1525354657);
		
	    //realiza la operacion
		porMailSms.enviarRecordatorio();

		//controlando si se envio correctamente por mail
		verify(usuario).enviarMail(any(Mail.class));
		verify(usuario).getUsuario();
		verify(usuario).getServidor();
		
		
		//contrlando si se envio correctamente pos sms
		verify(sms).send(usuario.getNroCelular(), mensaje);
		
		
	}
}