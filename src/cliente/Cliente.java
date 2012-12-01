package cliente;

import java.util.LinkedList;

import directorio.Mail;
import estrategiaAcceso.EstrategiaAcceso;
import usuario.UsuarioCliente;

public class Cliente {

	private Server server;

	private UsuarioCliente usuarioActivo;
	
	/**
	 * Loguea a un usuario si este existe en el servidor, tambien baja y guarda los mails del servidor segun la estrategia
	 * indicada. Si el usuario no existe localmente lo crea.
	 * 
	 * @param usuario
	 * @param password
	 * @param estrategia
	 * @return UsuarioCliente
	 */
	public UsuarioCliente login(String usuario, String password, EstrategiaAcceso estrategia) {
		
		try {
			// Se fija si el usuario existe en el server, sino captura excepcion.
			this.server.login(usuario, password);	
		}
		
		catch (InvalidUserOrPass e) { System.out.println("Usuario o Password Inv‡lido");}
			
		// Si no hay usuarioActivo, lo crea.
		if (this.usuarioActivo == null) { 
				
			this.usuarioActivo = new UsuarioCliente(usuario, password, this, estrategia);
			
			// ... y descarga los correos segun la <estrategia de acceso> dada para luego guardarlos en el directorio
			this.usuarioActivo.recibirMails();
			
			System.out.println("Login exitoso");
		}
			
		// Si el usuarioActivo existe ...
		else {
			// ... y no es el que se intenta loguear
			if (this.usuarioActivo.getUsuario() != usuario) { System.out.println("Existe otro usuario logueado en el sistema, cierre su cesi—n para loguearse"); }
			
			// ... si en cambio es el mismo que se intenta loguear de nuevo
			else { System.out.println("Ud. ya se encuentra logueado"); }
				
			return this.usuarioActivo;		
		}	
	}
	
	// Utiliza la "Red" para enviar el mail al servidor especificado
	public void send(Mail mail)  throws Exception {
	
		try { Red.send(mail, this.server); } 
		
		catch (Exception e) { throw new Exception("No se ha podido enviar el mail"); }
	}
	
	// Desloguearse implica desvincular al usuario del cliente localmente
	public void logout() {
		
		System.out.println("El usuario: " + this.usuarioActivo.getUsuario() + " ha cerrado cesi—n correctamente");
		this.usuarioActivo = null;
	}
	
	public Server getServer() { return this.server; }
	
}