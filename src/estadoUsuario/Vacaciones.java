package estadoUsuario;

import java.util.Calendar;

import usuario.UsuarioCliente;

import directorio.Mail;
import directorio.partesDeMail.Encabezado;

public class Vacaciones extends EstadoUsuario {
	
	private String mensaje = "Me encuentro de vacaciones, en cuanto regrese atenderé su mensaje";
	
	@Override
	public void teLlegoMail(Mail mail, UsuarioCliente usuario) { this.responderARemitente(mail, this.mensaje, usuario); }
	
	public void responderARemitente(Mail mail, String mensaje, UsuarioCliente usuario) {
		
		Mail newMail = new Mail();
		Encabezado newEncabezado = new Encabezado();
		// Encabezado
		newEncabezado.setRemitente(usuario.getUsuario());
		newEncabezado.setDestinatario(mail.getRemitente());
		newEncabezado.setAsunto("Re. Automática: " + mail.getAsunto());
		newEncabezado.setFecha(Calendar.getInstance());
		
		newMail.setEncabezado(newEncabezado);
		newMail.setCuerpo(mensaje + '\n' + mail.getCuerpo());
		try { usuario.enviarMail(newMail); } 
		catch (Exception e) { System.out.println("Registro de mensaje automatico fallido"); }
		
		
	}
	
	
}