package filtros.condicionSimple;

import directorio.Mail;

public class Contiene extends CondicionSimple {

	public Contiene(Campo c) {
		super(c);
	}

	@Override
	public boolean evaluar(Mail m) {
		return (this.getCampo().evaluarContiene(m));
	}
}
