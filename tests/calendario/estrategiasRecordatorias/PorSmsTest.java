package calendario.estrategiasRecordatorias;

import org.junit.Test;
import calendario.Sms;
import static org.mockito.Mockito.*;
import usuario.UsuarioCliente;


public class PorSmsTest {

	@Test
	public void enviarRecordatorio(){
		UsuarioCliente usuario=mock(UsuarioCliente.class);
		when(usuario.getNroCelular()).thenReturn(1552344356);
		String mensaje="Este es un mensaje";
		PorSms porSms=new PorSms(usuario,mensaje);
		Sms sms=mock(Sms.class);
		porSms.setSms(sms);
		
		porSms.enviarRecordatorio();
		verify(sms,times(1)).send(usuario.getNroCelular(),mensaje);
	}
}