package estrategiaAcceso;

import java.util.LinkedList;
import red.Red;
import server.Server;
import directorio.Carpeta;
import directorio.Mail;

public class IMAP extends EstrategiaAcceso {
	
	@Override
	public String getCuerpo(Server servidor, String usuario, Mail mail) { 
		
		return Red.getCuerpoMail(servidor, usuario, mail.getIndice());
	}
 
	@Override
	public LinkedList<Mail> bajarYRetornarMails(Server servidor, String usuario) { 
		
		return Red.getMailsTipoIMAP(servidor, usuario); 
	}

	@Override
	public void eliminarMail(Mail mail, Server servidor, String usuario, Carpeta directorio) {
		
		LinkedList<String> etiqueta = new LinkedList<String>();
		etiqueta.add("papelera");
		Red.setEtiqueta(servidor, usuario, mail.getIndice(), etiqueta);
		directorio.eliminarMail(mail);
	}

	@Override
	public void send(Mail mail, Server server) { Red.sendPOP3(mail, server); }
}