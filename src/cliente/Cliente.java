package cliente;

import directorio.Mail;
import estrategiaAcceso.EstrategiaAcceso;
import usuario.UsuarioCliente;

import server.Server;
import exceptions.*;

public class Cliente {

	private Server server;

	private UsuarioCliente usuarioActivo;
	
	public Cliente(Server servidor) {
		this.server = servidor;
	}
	
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
		

		if (this.usuarioActivo == null) {

			try {
				// Se fija si el usuario existe en el server, sino captura excepcion.
				this.server.login(usuario, password);
				
				// Si no hay usuarioActivo, lo crea.				
				this.usuarioActivo = new UsuarioCliente(usuario, password, this, estrategia);
				
				// ... y descarga los correos segun la <estrategia de acceso> dada para luego guardarlos en el directorio
				this.usuarioActivo.recibirMails();

				System.out.println("Login exitoso");
			}

			catch (InvalidUserOrPass e) { System.out.println("Usuario o Password Invalido");}
			catch (AlreadyLogged e) { System.out.println("Ya has iniciado sesion");}
		}

		// Si el usuarioActivo existe ...
		else {
			// ... y no es el que se intenta loguear
			if (this.usuarioActivo.getUsuario() != usuario) { System.out.println("Existe otro usuario logueado en el sistema, cierre su cesion para loguearse"); }

			// ... si en cambio es el mismo que se intenta loguear de nuevo
			else { System.out.println("Ud. ya se encuentra logueado localmente"); }
		}	

		return this.usuarioActivo;
	}
	
	// Deriva el envio del mail a la estrategia del usuario
	public void send(Mail mail)  throws Exception {
	
		try { this.usuarioActivo.getEstrategia().send(mail, this.server); } 
		
		catch (Exception e) { throw new Exception("No se ha podido enviar el mail"); }
	}
	
	// Desloguearse implica desvincular al usuario del cliente local y avisar al servidor que desvincule tambien
	public void logout() {
		
		if (this.usuarioActivo == null) { System.out.println("No hay usuario logueado en el sistema"); }
		else { 
			String nombreUsuario = this.usuarioActivo.getUsuario();
			this.usuarioActivo = null;
			if (this.server.logout(nombreUsuario)) { System.out.println("El usuario: " + nombreUsuario + " ha cerrado cesion correctamente"); }
			else { System.out.println("El usuario: " + nombreUsuario + " no estaba logeuado en el servidor, se ha cerrado cesion localmente"); }
		}
	}
	
	public Server getServer() { return this.server; }
	public UsuarioCliente getUsuario() { return this.usuarioActivo; }
	public void setUsuario(UsuarioCliente usuario) { this.usuarioActivo = usuario; } 
	
}