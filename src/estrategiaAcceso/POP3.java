package estrategiaAcceso;

import java.util.LinkedList;

import red.Red;
import server.Server;
import directorio.Carpeta;
import directorio.Mail;

public class POP3 extends EstrategiaAcceso {

	private boolean dejarCopia;

	@Override
	public String getCuerpo(Server servidor, String usuario, Mail mail) {
		
		return mail.getCuerpo();
	}

	@Override
	public LinkedList<Mail> bajarYRetornarMails(Server servidor, String usuario) { 
		
		if (this.dejarCopia) { return Red.getMailsTipoPOP3(servidor, usuario); }
		else {
			
			LinkedList<Mail> mails = Red.getMailsTipoPOP3(servidor, usuario);
			
			for (Mail unMail : mails) { Red.eliminarMail(servidor, usuario, unMail.getIndice()); }
			return mails;
		}
	}
	
	@Override
	public void eliminarMail(Mail mail, Server servidor, String usuario, Carpeta directorio) {
		 
		directorio.eliminarMail(mail);
	}

	@Override
	public void send(Mail mail, Server server) { Red.sendPOP3(mail, server); }
}