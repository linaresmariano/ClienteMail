package estadoUsuario;

import usuario.UsuarioCliente;
import directorio.Mail;

public abstract class EstadoUsuario {

	public abstract void teLlegoMail(Mail mail, UsuarioCliente usuario);
	
}