package cliente;

import java.util.LinkedList;

import directorio.Mail;
import estrategiaAcceso.EstrategiaAcceso;
import usuario.UsuarioCliente;

public class Cliente {

	private Server server;
	private UsuarioCliente usuarioActivo;
	
	public UsuarioCliente login(String usuario, String password, EstrategiaAcceso estrategia) {
		
		try {
			// Se fija si el usuario existe en el server, sino captura excepcion.
			this.server.login(usuario, password);
			
			// Si no hay usuarioActivo, lo crea.
			if (this.usuarioActivo == null) { 
				
				this.usuarioActivo = new UsuarioCliente(usuario, password, this, estrategia);
				// ... y descarga los correos segun la <estrategia de acceso> dada para luego guardarlos en el directorio
				
				LinkedList<Mail> mails = this.usuarioActivo.getEstrategia().bajarYRetornarMails();
				for (Mail unMail : mails) { this.usuarioActivo.getDirectorio().agregarMail(unMail); }
				
				System.out.println("Login exitoso");
			}
			
			// Si el usuarioActivo existe ...
			else {
				// ... y no es el que se intenta loguear
				if (this.usuarioActivo.getUsuario() != usuario) {
				
					System.out.println("Existe otro usuario logueado en el sistema, cierre su cesi—n para loguearse");
				}
				// ... si en cambio es el mismo que se intenta loguear de nuevo
				else {
				
					System.out.println("Ya estas logueado");
				}
				
				return this.usuarioActivo;
					
			}	
		}
		
		catch (InvalidUserOrPass e) { System.out.println("Usuario o Password Inv‡lido");}
	}
	
	// Utiliza la "Red" para enviar el mail al servidor especificado
	public void send(Mail mail) {
	
		Red.send(mail, this.server);
	}
	
	// Desloguearse implica desvincular al usuario del cliente localmente
	public void logout() {
		
		this.usuarioActivo = null;
	}
	
}