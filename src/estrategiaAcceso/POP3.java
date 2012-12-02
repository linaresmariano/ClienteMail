package estrategiaAcceso;

import java.util.LinkedList;

import directorio.Carpeta;
import directorio.DirectorioUsuario;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;

/* 	TODOS LOS METODOS DE LA CLASE DEPENDEN DE SI VA A SER LA RED O EL CLIENTE 
 * EL QUE FUNCIONE COMO INTERMEDIARIO DE LA BAJADA DE MAILS -----ARREGLAR------*/

public class POP3 extends EstrategiaAcceso {

	private boolean dejarCopia;

	@Override
	public String getCuerpo(Mail mail) { return mail.getCuerpo(); }

	@Override
	public Adjunto getAdjunto(Mail mail) { return mail.getAdjunto(); }

	@Override
	public LinkedList<Mail> bajarYRetornarMails() {

		LinkedList<Mail> listaMails = new LinkedList<Mail>();
		
		return listaMails;
		
	}

	@Override
	public void eliminarMail(Mail mail, Carpeta directorio) {
		
		if (this.dejarCopia) { directorio.eliminarMail(mail); }
		else { 
			this.getCliente().getServer().eliminarMail(mail);
			directorio.eliminarMail(mail);
			// Red.eliminarMail(mail, this.getCliente().getServer());
		}
	}
}