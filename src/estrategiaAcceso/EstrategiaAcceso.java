package estrategiaAcceso;

import java.util.LinkedList;

import red.Red;
import server.Server;
import directorio.Carpeta;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;

public abstract class EstrategiaAcceso {
	
	public abstract String getCuerpo(Server servidor, String usuario, Mail mail);
	
	public Adjunto getAdjunto(Server servidor, String usuario, int indiceMail) { 
		
		return Red.getAdjuntoMail(servidor, usuario, indiceMail); 	
	}

	public abstract LinkedList<Mail> bajarYRetornarMails(Server servidor, String usuario);

	public abstract void eliminarMail(Mail mail, Server servidor, String usuario, Carpeta directorio);

	public abstract void send(Mail mail, Server server);
}