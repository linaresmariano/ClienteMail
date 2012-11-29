package filtros.condicionSimple;

import directorio.Mail;

/**
 * Evalua en un mail si el remitente es igual,
 * contine, o si es distinto al Contato especificado
 */
public class Remitente extends Campo {
	
	private String valor;
	
	// Instanciar un FiltroRemitente con el nombre a filtrar
	public Remitente(String c) {
		this.valor = c;
	}
	
	public String getValor() {
		return this.valor;
	}

	@Override
	public boolean evaluarContiene(Mail m) {
		return m.getRemitente().contains(this.getValor());
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
