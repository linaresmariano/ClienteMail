package filtros.condicionSimple;

import filtros.Mail;

public class EsDistinto extends CondicionSimple {

	public EsDistinto(Campo c) {
		super(c);
	}

	@Override
	public boolean evaluar(Mail m) {
		return (this.getCampo().evaluarContiene(m));
	}

}
