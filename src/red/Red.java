package red;

import java.util.LinkedList;

import directorio.Mail;
import directorio.partesDeMail.Adjunto;
import directorio.partesDeMail.Encabezado;
import server.AdjuntoServer;
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

	// Los dos metodos a continuacion son iguales en implementacion porque el servidor no soporta carpetas al momento.
	
	public static void sendPOP3(Mail mail, Server server) {	server.send(convertirMailClienteAServer(mail)); }
		
	public static void sendIMAP(Mail mail, Server server) { server.send(convertirMailClienteAServer(mail)); }

	public static String getCuerpoMail(Server servidor, String usuario, int indice) { return servidor.getMail(usuario, indice).getCuerpo(); }

	public static Adjunto getAdjuntoMail(Server servidor, String usuario, int indice) {
		
		Adjunto adjuntoCliente =  new Adjunto(servidor.getMail(usuario, indice).getAdjunto().getNombre());
		adjuntoCliente.setArchivo(servidor.getMail(usuario, indice).getAdjunto().getArchivo());
		
		return adjuntoCliente;
	}

	public static void setEtiqueta(Server servidor, String usuario, int indice, LinkedList<String> etiqueta) { servidor.setEtiqueta(usuario, indice, etiqueta); }
	
	private static Mail convertirMailServerAClientePOP3(MailServer unMailTipoServer) {
		
		// Encabezado
		Encabezado encabezado = new Encabezado();
		encabezado.setAsunto(unMailTipoServer.getAsunto());
		encabezado.setDestinatario(unMailTipoServer.getDestinatario());
		encabezado.setFecha(unMailTipoServer.getFecha());
		encabezado.setRemitente(unMailTipoServer.getRemitente());
		
		// Mail
		Mail newMail = new Mail();
		newMail.setEncabezado(encabezado);
		if (unMailTipoServer.getAdjunto() != null) {
			newMail.setAdjunto(new Adjunto(unMailTipoServer.getAdjunto().getNombre()));
		}
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
		encabezado.setFecha(unMailTipoServer.getFecha());
		encabezado.setRemitente(unMailTipoServer.getRemitente());
				
		// Mail
		Mail newMail = new Mail();
		newMail.setEncabezado(encabezado);
		if (unMailTipoServer.getAdjunto() != null) {
			newMail.setAdjunto(new Adjunto(unMailTipoServer.getAdjunto().getNombre()));
		}
		newMail.setIndice(unMailTipoServer.getIndice());
		newMail.setLeido(unMailTipoServer.isLeido());
		newMail.setEtiqueta(unMailTipoServer.getEtiqueta());
				
		return newMail;
	}
	
	private static MailServer convertirMailClienteAServer(Mail unMailTipoCliente) {
		
		AdjuntoServer adjuntoServer = null;
		
		// Adjunto
		if (unMailTipoCliente.getAdjunto() != null) {
			
			adjuntoServer = new AdjuntoServer(unMailTipoCliente.getAdjunto().getNombre(), unMailTipoCliente.getAdjunto().getArchivo());
		}
		
		// Mail
		MailServer newMail = new MailServer(unMailTipoCliente.getEncabezado().getDestinatario(), unMailTipoCliente.getCuerpo());
		newMail.setAsunto(unMailTipoCliente.getAsunto());
		newMail.setEtiqueta(unMailTipoCliente.getEtiqueta());
		newMail.setFecha(unMailTipoCliente.getFecha());
		newMail.setRemitente(unMailTipoCliente.getRemitente());
		newMail.setAdjunto(adjuntoServer);
		newMail.setLeido(false);
		
		return newMail;
	}
}