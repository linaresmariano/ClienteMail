package estrategiaAcceso;

import java.util.LinkedList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Redefinable;

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

	// ARREGALR************************
	@Override
	public LinkedList<Mail> bajarYRetornarMails(String usuario) { 
		
		LinkedList<Mail> listaMails = Red.getMails(usuario);
		for (Mail unMail : listaMails)
		
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