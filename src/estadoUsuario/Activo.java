package estadoUsuario;

import usuario.UsuarioCliente;
import directorio.Mail;

public class Activo extends EstadoUsuario {
	
	@Override
	public void teLlegoMail(Mail mail, UsuarioCliente usuario) { System.out.println("Usuario activo, no hay acciones especificas de estado"); }
}