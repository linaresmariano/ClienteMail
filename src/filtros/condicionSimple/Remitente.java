package filtros.condicionSimple;

import directorio.Mail;
import contacto.Contacto;

/**
 * Evalua en un mail si el remitente es igual,
 * contine, o si es distinto al Contato especificado
 */
public class Remitente extends Campo {
	
	private Contacto valor;
	
	public Remitente(Contacto c) {
		this.valor = c;
	}

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
