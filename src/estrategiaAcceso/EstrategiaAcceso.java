package estrategiaAcceso;

import java.util.LinkedList;

import cliente.Cliente;

import directorio.Carpeta;
import directorio.Mail;
import directorio.partesDeMail.Adjunto;

public abstract class EstrategiaAcceso {
	
	private Cliente cliente;

	public abstract String getCuerpo(Mail mail);
	
	public abstract Adjunto getAdjunto(Mail mail);

	public abstract LinkedList<Mail> bajarYRetornarMails();

	public abstract void eliminarMail(Mail mail, Carpeta directorio);

	public Cliente getCliente() { return cliente; }
	
	public void setCliente(Cliente cliente) { this.cliente = cliente; }
}