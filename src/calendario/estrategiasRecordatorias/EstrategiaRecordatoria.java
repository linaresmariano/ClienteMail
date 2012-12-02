package calendario.estrategiasRecordatorias;

import usuario.UsuarioCliente;
import calendario.Sms;
import directorio.Mail;
import directorio.partesDeMail.Encabezado;

public abstract class EstrategiaRecordatoria {

	private String mensaje;  
	private Sms sms=new Sms();
	private UsuarioCliente usuario;
	
	public EstrategiaRecordatoria(UsuarioCliente usuario,String mensaje){
		this.usuario=usuario;
		this.mensaje=mensaje;
	}
	
	public void setSms(Sms sms){
		this.sms=sms;
	}
	
    public abstract void enviarRecordatorio();
	
    /**
     *logica de envio recordatorio por email
     */
	public void recordarPorMail(){
		String correo=usuario.getUsuario();
		Encabezado encabezado=new Encabezado();
		encabezado.setRemitente(correo);
		encabezado.setDestinatario(correo);
		encabezado.setAsunto("Mensaje recordatorio");
		Mail mail=new Mail();
		mail.setCuerpo(mensaje);
		mail.setEncabezado(encabezado);
		try { usuario.enviarMail(mail); } 
		catch (Exception e) { System.out.println("No se pudo enviar recordatorio por mail"); }
	}
	
	/**
     *logica de envio recordatorio por email
     */
	public void recordarPorSms(){
		sms.send(usuario.getNroCelular(), mensaje);
	}
}