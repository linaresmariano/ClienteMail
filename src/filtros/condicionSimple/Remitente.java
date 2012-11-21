package filtros.condicionSimple;

import directorio.Mail;

public class Remitente extends Campo {
	
	private Contacto valor;

	@Override
	public boolean evaluarContiene(Mail m) {
		return m.getRemitente().contains(this.valor);
	}

	@Override
	public boolean evaluarEsIgual(Mail m) {
		return m.getRemitente().equals(this.valor);
	}

	@Override
	public boolean evaluarEsDistinto(Mail m) {
		return !(m.getRemitente().equals(this.valor));
	}
}
