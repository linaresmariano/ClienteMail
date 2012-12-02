package red;

import java.util.Calendar;
import java.util.LinkedList;

import directorio.Mail;
import directorio.partesDeMail.Adjunto;
import directorio.partesDeMail.Encabezado;
import server.MailServer;
import server.Server;

public class Red {
	
	public static LinkedList<Mail> getMailsTipoPOP3(Server servidor, String usuario) {
		
		LinkedList<Mail> mailsTipoCliente = new LinkedList<Mail>();
		
		for (MailServer unMailTipoServer : servidor.getMails(usuario)) {
			
			mailsTipoCliente.add(Red.convertirMailServerAClientePOP3(unMailTipoServer));
		}
		
		return mailsTipoCliente;
	}

	public static LinkedList<Mail> getMailsTipoIMAP(Server servidor, String usuario) {
		
		LinkedList<Mail> mailsTipoCliente = new LinkedList<Mail>();
		
		for (MailServer unMailTipoServer : servidor.getMails(usuario)) {
			
			mailsTipoCliente.add(Red.convertirMailServerAClienteIMAP(unMailTipoServer));
		}
		
		return mailsTipoCliente;
	}

	public static void send(Mail mail, Server server) {
	
		
		
	}

	public static String getCuerpoMail(Server servidor, String usuario, int indice) {
		
		return null;
	}

	public static Adjunto getAdjuntoMail(Server servidor, String usuario, int indiceMail) {
		
		return null;
	}

	public static void eliminarMail(Server servidor, String usuario, int indice) {
		
		
	}
	
	private static Mail convertirMailServerAClientePOP3(MailServer unMailTipoServer) {
		
		// Encabezado
		Encabezado encabezado = new Encabezado();
		encabezado.setAsunto(unMailTipoServer.getAsunto());
		encabezado.setDestinatario(unMailTipoServer.getDestinatario());
		encabezado.setFecha(Calendar.getInstance());
		encabezado.setRemitente(unMailTipoServer.getRemitente());
		
		// Mail
		Mail newMail = new Mail();
		newMail.setEncabezado(encabezado);
		newMail.setAdjunto(new Adjunto(unMailTipoServer.getAdjunto().getNombre()));
		newMail.setCuerpo(unMailTipoServer.getCuerpo());
		newMail.setIndice(unMailTipoServer.getIndice());
		newMail.setLeido(unMailTipoServer.isLeido());
		newMail.setEtiqueta(unMailTipoServer.getEtiqueta());
		
		return newMail;
	}
	
	private static Mail convertirMailServerAClienteIMAP(MailServer unMailTipoServer) {
		
		// Encabezado
		Encabezado encabezado = new Encabezado();
		encabezado.setAsunto(unMailTipoServer.getAsunto());
		encabezado.setDestinatario(unMailTipoServer.getDestinatario());
		encabezado.setFecha(Calendar.getInstance());
		encabezado.setRemitente(unMailTipoServer.getRemitente());
				
		// Mail
		Mail newMail = new Mail();
		newMail.setEncabezado(encabezado);
		newMail.setAdjunto(new Adjunto(unMailTipoServer.getAdjunto().getNombre()));
		newMail.setIndice(unMailTipoServer.getIndice());
		newMail.setLeido(unMailTipoServer.isLeido());
		newMail.setEtiqueta(unMailTipoServer.getEtiqueta());
				
		return newMail;
	}
}