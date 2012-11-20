package filtros;

import filtros.Mail;

public abstract class Campo {
	public abstract boolean evaluarContiene(Mail m);
	public abstract boolean evaluarEsIgual(Mail m);
	public abstract boolean evaluarEsDistinto(Mail m);
}
