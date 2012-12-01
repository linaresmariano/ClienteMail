package estadoUsuario;

import usuario.UsuarioCliente;
import calendario.Sms;
import directorio.Mail;

public class Reunion extends EstadoUsuario {
	
	@Override
	public void teLlegoMail(Mail mail, UsuarioCliente usuario) { this.reenviarPorSMS(mail, usuario.getNroCelular()); }
	
	public void reenviarPorSMS(Mail mail, int nroCelular) {
		
		Sms newSms = new Sms();
		newSms.send(nroCelular, "De: " + mail.getRemitente() + " Asunto: " + mail.getAsunto() + " Mensaje: " + mail.getCuerpo());	
	}
	
}