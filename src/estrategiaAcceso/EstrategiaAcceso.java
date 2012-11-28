package estrategiaAcceso;

import directorio.Mail;

public abstract class EstrategiaAcceso {

	public abstract void login();
	
	public abstract String getCuerpo(Mail mail);
	
	public abstract AdjuntoMail getAdjunto(Mail mail);
	
}