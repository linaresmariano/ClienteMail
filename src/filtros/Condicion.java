package filtros;

import directorio.Mail;

public abstract class Condicion {
	public abstract boolean evaluar(Mail m);
}
