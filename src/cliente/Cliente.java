package cliente;

import java.util.LinkedList;

import directorio.Mail;
import servidor.Servidor;
import usuario.UsuarioCliente;

public class Cliente {

	private Servidor server;
	private LinkedList<UsuarioCliente> usuarios;
	
	public Cliente() {
		usuarios=new LinkedList<UsuarioCliente>();
		
	}
	
	public void login(String usuario, String password, String servidor) {
		
		String username = usuario.concat("@").concat(servidor);
		this.server.login(username, password, serverSMTP);
		
	}
	
	public void send(Mail mail) {
	
		this.server.send(mail);
	}
	
	public void logout() {
		
		this.server.logout();
	}
	
}
