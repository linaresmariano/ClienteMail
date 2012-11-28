package calendario.estrategiasRecordatorias;

import usuario.UsuarioCliente;

public class PorSms extends EstrategiaRecordatoria{

    public PorSms(UsuarioCliente usuario,String mensaje) {
		super(usuario, mensaje);
	}

    /**
     *envia por sms el mensaje configurado por
     *el ususario
     */
	public void enviarRecordatorio(){
		recordarPorSms();
	}
}
