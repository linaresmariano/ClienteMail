package calendario.estrategiasRecordatorias;

import usuario.UsuarioCliente;

public class PorMail extends EstrategiaRecordatoria{

	public PorMail(UsuarioCliente usuario,String mensaje) {
		super(usuario, mensaje);
	}

	/**
     *envia por mail el mensaje configurado por
     *el ususario
     */
	public void enviarRecordatorio(){
		recordarPorMail();
	}
	
	
}
