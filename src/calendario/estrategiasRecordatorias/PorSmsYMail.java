package calendario.estrategiasRecordatorias;

import usuario.UsuarioCliente;

public class PorSmsYMail extends EstrategiaRecordatoria{

	 public PorSmsYMail(UsuarioCliente usuario,String mensaje) {
		super(usuario, mensaje);
	}

	 /**
	     *envia por mail y sms el mensaje configurado por
	     *el ususario
	     */ 
	public void enviarRecordatorio(){
			recordarPorSms();
			recordarPorMail();
		}
}
