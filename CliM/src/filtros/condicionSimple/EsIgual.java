package filtros.condicionSimple;

import filtros.Mail;

public class EsIgual extends CondicionSimple {

	public EsIgual(Campo c) {
		super(c);
	}

	@Override
	public boolean evaluar(Mail m) {
		return (this.getCampo().evaluarContiene(m));
	}

}
