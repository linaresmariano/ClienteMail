package estrategiaAcceso;

public abstract class EstrategiaAcceso {

	public abstract void login();
	
	public abstract String getCuerpo(Mail mail);
	
	public AdjuntoMail getAdjunto(Mail mail);
	
}