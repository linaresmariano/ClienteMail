package estrategiaAcceso;

import java.util.LinkedList;

import directorio.Carpeta;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;

public class IMAP extends EstrategiaAcceso {
	
	@Override
	public void login() {
		
		
	}
	
	@Override
	public String getCuerpo(Mail mail) {
		
		
	}

	@Override
	public Adjunto getAdjunto(Mail mail) {
		
		
	}

	@Override
	public LinkedList<Mail> bajarYRetornarMails() {
		
	}

	@Override
	public void eliminarMail(Mail mail, Carpeta directorio, Server servidor) {
		
		directorio.eliminarMail();
		Red.eliminarMail(mail, servidor);
		
	}
	
}