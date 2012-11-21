package filtros.condicionSimple;

import directorio.Mail;

public class EsDistinto extends CondicionSimple {

	public EsDistinto(Campo c) {
		super(c);
	}

	@Override
	public boolean evaluar(Mail m) {
		return (this.getCampo().evaluarContiene(m));
	}

}
