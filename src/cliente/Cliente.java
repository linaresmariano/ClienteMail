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
	
	public void login(String usuario, String password) {
		
		try {
		
			this.server.login(usuario, password);
			for (int i=0; i<this.usuarios.size(); i++) {
				if (this.usuarios.get(i).getUsuario() == usuario) { break; }
				if (i == this.usuarios.size() - 1) { 
					UsuarioCliente newUser = new UsuarioCliente(usuario, password);
					this.usuarios.add(newUser);
				}
			}	
		}
		
		catch (Exception e) { }
	}
	
	public void send(Mail mail) {
	
		this.server.send(mail);
	}
	
	public void logout() {
		
		this.server.logout();
	}
	
}
