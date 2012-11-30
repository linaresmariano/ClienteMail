package estrategiaAcceso;

import java.util.LinkedList;

import directorio.Carpeta;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;

public abstract class EstrategiaAcceso {

	public abstract void login();
	
	public abstract String getCuerpo(Mail mail);
	
	public abstract Adjunto getAdjunto(Mail mail);

	public abstract LinkedList<Mail> bajarYRetornarMails();

	public abstract void eliminarMail(Mail mail, Carpeta directorio, Server servidor);
}