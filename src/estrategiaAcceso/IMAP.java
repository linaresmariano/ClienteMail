package estrategiaAcceso;

import java.util.LinkedList;
import java.util.List;

import directorio.Carpeta;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;

/* 	TODOS LOS METODOS DE LA CLASE DEPENDEN DE SI VA A SER LA RED O EL CLIENTE 
 * EL QUE FUNCIONE COMO INTERMEDIARIO DE LA BAJADA DE MAILS -----ARREGLAR------*/

public class IMAP extends EstrategiaAcceso {
	
	// REVISAR porque getCuerpo supuestamente recibe un parametro mas <indiceMail?>
	@Override
	public String getCuerpo(Mail mail) { return this.getCliente().getServer().getCuerpo(mail); }

	// REVISAR porque getAdjunto supuestamente recibe un parametro mas <indiceMail?>
	@Override
	public Adjunto getAdjunto(Mail mail) { return this.getCliente().getServer().getAdjunto(mail); }

	
	// POR AHORA IMAP baja todo! y borra los archivos asjuntos y el cuerpoo del mail. :)
	// Deberia ser al reves, que pop3 
	@Override
	public LinkedList<Mail> bajarYRetornarMails(String usuario) { return Red.getMails(usuario); }

	@Override
	public void eliminarMail(Mail mail, Carpeta directorio) {
		
		directorio.eliminarMail(mail);
		this.getCliente().getServer().eliminarMail(mail);
		// Red.eliminarMail(mail, this.getCliente().getServer());
	}
}